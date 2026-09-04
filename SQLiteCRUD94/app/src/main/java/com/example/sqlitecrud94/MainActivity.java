package com.example.sqlitecrud94;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtId, edtName, edtCourse;

    Button btnInsert, btnView, btnUpdate, btnDelete;

    TextView txtResult;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java file with XML layout
        setContentView(R.layout.activity_main);

        // Find views
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtCourse = findViewById(R.id.edtCourse);

        btnInsert = findViewById(R.id.btnInsert);
        btnView = findViewById(R.id.btnView);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        txtResult = findViewById(R.id.txtResult);

        // Create database object
        databaseHelper = new DatabaseHelper(this);


        // ===================================
        // INSERT BUTTON
        // ===================================

        btnInsert.setOnClickListener(v -> {

            int id = Integer.parseInt(edtId.getText().toString());

            String name = edtName.getText().toString();

            String course = edtCourse.getText().toString();

            boolean result = databaseHelper.insertStudent(
                    id,
                    name,
                    course
            );

            if (result) {

                Toast.makeText(
                        MainActivity.this,
                        "Student Inserted Successfully",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                Toast.makeText(
                        MainActivity.this,
                        "Insertion Failed",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });


        // ===================================
        // VIEW BUTTON
        // ===================================

        btnView.setOnClickListener(v -> {

            Cursor cursor = databaseHelper.getAllStudents();

            StringBuilder data = new StringBuilder();

            if (cursor.getCount() == 0) {

                txtResult.setText("No Records Found");

                return;
            }

            while (cursor.moveToNext()) {

                int id = cursor.getInt(0);

                String name = cursor.getString(1);

                String course = cursor.getString(2);

                data.append("ID: ")
                        .append(id)
                        .append("\n");

                data.append("Name: ")
                        .append(name)
                        .append("\n");

                data.append("Course: ")
                        .append(course)
                        .append("\n\n");
            }

            cursor.close();

            txtResult.setText(data.toString());
        });


        // ===================================
        // UPDATE BUTTON
        // ===================================

        btnUpdate.setOnClickListener(v -> {

            int id = Integer.parseInt(
                    edtId.getText().toString()
            );

            String name = edtName.getText().toString();

            String course = edtCourse.getText().toString();

            boolean result = databaseHelper.updateStudent(
                    id,
                    name,
                    course
            );

            if (result) {

                Toast.makeText(
                        MainActivity.this,
                        "Student Updated Successfully",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                Toast.makeText(
                        MainActivity.this,
                        "Student Not Found",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });


        // ===================================
        // DELETE BUTTON
        // ===================================

        btnDelete.setOnClickListener(v -> {

            int id = Integer.parseInt(
                    edtId.getText().toString()
            );

            boolean result = databaseHelper.deleteStudent(id);

            if (result) {

                Toast.makeText(
                        MainActivity.this,
                        "Student Deleted Successfully",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                Toast.makeText(
                        MainActivity.this,
                        "Student Not Found",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}
