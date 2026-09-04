package com.example.filehandling94;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class externalstorage extends AppCompatActivity {
    EditText edtName, edtRollNo;
    Button btnSave, btnRead;
    TextView txtResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_externalstorage);

        edtName = findViewById(R.id.edtName);
        edtRollNo = findViewById(R.id.edtRollNo);
        btnSave = findViewById(R.id.btnSave);
        btnRead = findViewById(R.id.btnRead);
        txtResult = findViewById(R.id.txtResult);
// SAVE DATA
        btnSave.setOnClickListener(v -> {

            String name = edtName.getText().toString();
            String rollNo = edtRollNo.getText().toString();

            String data = "Student Name: " + name
                    + "\nRoll Number: " + rollNo;

            try {

                // Get external storage directory
                File folder = getExternalFilesDir(null);

                // Create student.txt file
                File file = new File(folder, "student.txt");

                // Open file for writing
                FileOutputStream fos =
                        new FileOutputStream(file);

                // Write data into file
                fos.write(data.getBytes());

                // Close file
                fos.close();

                Toast.makeText(
                        this,
                        "Data Saved Successfully",
                        Toast.LENGTH_SHORT
                ).show();

            } catch (Exception e) {

                Toast.makeText(
                        this,
                        "Error while saving",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });


        // READ DATA
        btnRead.setOnClickListener(v -> {

            try {

                // Get external storage directory
                File folder = getExternalFilesDir(null);

                // Open student.txt
                File file = new File(folder, "student.txt");

                FileInputStream fis =
                        new FileInputStream(file);

                StringBuilder data = new StringBuilder();

                int ch;

                // Read data character by character
                while ((ch = fis.read()) != -1) {
                    data.append((char) ch);
                }

                fis.close();

                // Display data
                txtResult.setText(data.toString());

            } catch (Exception e) {

                Toast.makeText(
                        this,
                        "No data found",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}
