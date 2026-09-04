package com.example.firebasecrud;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText edtId, edtName, edtCourse;
    Button btnInsert, btnRead, btnUpdate, btnDelete;
    TextView txtResult;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtCourse = findViewById(R.id.edtCourse);

        btnInsert = findViewById(R.id.btnInsert);
        btnRead = findViewById(R.id.btnRead);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        txtResult = findViewById(R.id.txtResult);

        databaseReference =
                FirebaseDatabase.getInstance()
                        .getReference("Students");

        // CREATE
        btnInsert.setOnClickListener(v -> insertStudent());

        // READ
        btnRead.setOnClickListener(v -> readStudents());

        // UPDATE
        btnUpdate.setOnClickListener(v -> updateStudent());

        // DELETE
        btnDelete.setOnClickListener(v -> deleteStudent());
    }

    private void insertStudent() {

        String id = edtId.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        String course = edtCourse.getText().toString().trim();

        if (id.isEmpty() || name.isEmpty() || course.isEmpty()) {

            Toast.makeText(
                    this,
                    "Please enter all details",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        Student student =
                new Student(id, name, course);

        databaseReference
                .child(id)
                .setValue(student)
                .addOnSuccessListener(unused -> {

                    Toast.makeText(
                            MainActivity.this,
                            "Student inserted successfully",
                            Toast.LENGTH_SHORT
                    ).show();

                    clearFields();
                })
                .addOnFailureListener(e -> {

                    Toast.makeText(
                            MainActivity.this,
                            "Insert failed: " + e.getMessage(),
                            Toast.LENGTH_SHORT
                    ).show();
                });
    }

    private void readStudents() {

        databaseReference.addListenerForSingleValueEvent(
                new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        StringBuilder result =
                                new StringBuilder();

                        for (DataSnapshot studentSnapshot :
                                snapshot.getChildren()) {

                            Student student =
                                    studentSnapshot.getValue(Student.class);

                            if (student != null) {

                                result.append("ID: ")
                                        .append(student.getId())
                                        .append("\n");

                                result.append("Name: ")
                                        .append(student.getName())
                                        .append("\n");

                                result.append("Course: ")
                                        .append(student.getCourse())
                                        .append("\n\n");
                            }
                        }

                        if (result.length() == 0) {

                            txtResult.setText(
                                    "No students found"
                            );

                        } else {

                            txtResult.setText(
                                    result.toString()
                            );
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                        Toast.makeText(
                                MainActivity.this,
                                "Read failed: "
                                        + error.getMessage(),
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });
    }

    private void updateStudent() {

        String id = edtId.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        String course = edtCourse.getText().toString().trim();

        if (id.isEmpty() || name.isEmpty() || course.isEmpty()) {

            Toast.makeText(
                    this,
                    "Enter ID, Name and Course",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        Student student =
                new Student(id, name, course);

        databaseReference
                .child(id)
                .setValue(student)
                .addOnSuccessListener(unused -> {

                    Toast.makeText(
                            MainActivity.this,
                            "Student updated successfully",
                            Toast.LENGTH_SHORT
                    ).show();

                    clearFields();
                })
                .addOnFailureListener(e -> {

                    Toast.makeText(
                            MainActivity.this,
                            "Update failed",
                            Toast.LENGTH_SHORT
                    ).show();
                });
    }

    private void deleteStudent() {

        String id =
                edtId.getText()
                        .toString()
                        .trim();

        if (id.isEmpty()) {

            Toast.makeText(
                    this,
                    "Enter Student ID",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        databaseReference
                .child(id)
                .removeValue()
                .addOnSuccessListener(unused -> {

                    Toast.makeText(
                            MainActivity.this,
                            "Student deleted successfully",
                            Toast.LENGTH_SHORT
                    ).show();

                    clearFields();
                })
                .addOnFailureListener(e -> {

                    Toast.makeText(
                            MainActivity.this,
                            "Delete failed",
                            Toast.LENGTH_SHORT
                    ).show();
                });
    }

    private void clearFields() {

        edtId.setText("");
        edtName.setText("");
        edtCourse.setText("");
    }
}