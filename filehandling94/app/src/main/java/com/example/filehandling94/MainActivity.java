package com.example.filehandling94;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtRollNo;
    Button btnSave, btnRead;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java file with activity_main.xml
        setContentView(R.layout.activity_main);

        // Connect XML components with Java
        edtName = findViewById(R.id.edtName);
        edtRollNo = findViewById(R.id.edtRollNo);
        btnSave = findViewById(R.id.btnSave);
        btnRead = findViewById(R.id.btnRead);
        txtResult = findViewById(R.id.txtResult);

        // =========================================
        // SAVE DATA INTO INTERNAL STORAGE
        // =========================================

        btnSave.setOnClickListener(v -> {

            String name = edtName.getText().toString();
            String rollNo = edtRollNo.getText().toString();

            String data = "Student Name: " + name
                    + "\nRoll Number: " + rollNo;

            try {

                // Create/Open student.txt
                FileOutputStream fos =
                        openFileOutput("student.txt", MODE_PRIVATE);

                // Write data into file
                fos.write(data.getBytes());

                // Close file
                fos.close();

                Toast.makeText(
                        MainActivity.this,
                        "Data Saved Successfully",
                        Toast.LENGTH_SHORT
                ).show();

            } catch (Exception e) {

                Toast.makeText(
                        MainActivity.this,
                        "Error while saving data",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        // =========================================
        // READ DATA FROM INTERNAL STORAGE
        // =========================================

        btnRead.setOnClickListener(v -> {

            try {

                // Open student.txt
                FileInputStream fis =
                        openFileInput("student.txt");

                StringBuilder data = new StringBuilder();

                int ch;

                // Read file character by character
                while ((ch = fis.read()) != -1) {
                    data.append((char) ch);
                }

                // Close file
                fis.close();

                // Display data
                txtResult.setText(data.toString());

            } catch (Exception e) {

                Toast.makeText(
                        MainActivity.this,
                        "No data found",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}
