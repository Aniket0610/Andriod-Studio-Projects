
        package com.example.registrationform94;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtEmail, edtMobile;
    RadioGroup radioGender;
    CheckBox checkAndroid, checkJava;
    Spinner spinnerCourse;
    Button btnRegister;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Connect XML components with Java
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtMobile = findViewById(R.id.edtMobile);

        radioGender = findViewById(R.id.radioGender);

        checkAndroid = findViewById(R.id.checkAndroid);
        checkJava = findViewById(R.id.checkJava);

        spinnerCourse = findViewById(R.id.spinnerCourse);

        btnRegister = findViewById(R.id.btnRegister);
        txtResult = findViewById(R.id.txtResult);

        // Spinner data
        String[] courses = {
                "Select Course",
                "MCA",
                "MBA",
                "BCA",
                "BBA"
        };

        // Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                courses
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        // Attach adapter to Spinner
        spinnerCourse.setAdapter(adapter);

        // Register button click
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get name, email and mobile
                String name = edtName.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String mobile = edtMobile.getText().toString().trim();

                // Basic validation
                if (name.isEmpty()) {
                    edtName.setError("Enter Student Name");
                    edtName.requestFocus();
                    return;
                }

                if (email.isEmpty()) {
                    edtEmail.setError("Enter Email");
                    edtEmail.requestFocus();
                    return;
                }

                if (mobile.isEmpty()) {
                    edtMobile.setError("Enter Mobile Number");
                    edtMobile.requestFocus();
                    return;
                }

                // Get selected gender
                int selectedId = radioGender.getCheckedRadioButtonId();

                String gender = "Not Selected";

                if (selectedId != -1) {
                    RadioButton selectedRadio =
                            findViewById(selectedId);

                    if (selectedRadio != null) {
                        gender = selectedRadio.getText().toString();
                    }
                }

                // Get selected skills
                StringBuilder skills = new StringBuilder();

                if (checkAndroid.isChecked()) {
                    skills.append("Android ");
                }

                if (checkJava.isChecked()) {
                    skills.append("Java ");
                }

                if (skills.length() == 0) {
                    skills.append("Not Selected");
                }

                // Get selected course
                String course = "Not Selected";

                if (spinnerCourse.getSelectedItem() != null) {
                    course = spinnerCourse.getSelectedItem().toString();
                }

                // Create result
                String result =
                        "Registration Successful\n\n" +
                                "Name: " + name + "\n" +
                                "Email: " + email + "\n" +
                                "Mobile: " + mobile + "\n" +
                                "Gender: " + gender + "\n" +
                                "Skills: " + skills.toString().trim() + "\n" +
                                "Course: " + course;

                // Display result
                txtResult.setText(result);
            }
        });
    }
}

