package com.example.sdcard94;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtRollNo;
    Button btnSave, btnCheck;
    TextView txtResult;

    // Android file picker
    private final ActivityResultLauncher<Intent> createFileLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {

                        if (result.getResultCode() == RESULT_OK
                                && result.getData() != null) {

                            Uri uri = result.getData().getData();

                            if (uri != null) {
                                saveDataToUri(uri);
                            }
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Connect XML components
        edtName = findViewById(R.id.edtName);
        edtRollNo = findViewById(R.id.edtRollNo);
        btnSave = findViewById(R.id.btnSave);
        btnCheck = findViewById(R.id.btnCheck);
        txtResult = findViewById(R.id.txtResult);

        // =========================================
        // CHECK SD CARD / EXTERNAL STORAGE
        // =========================================

        btnCheck.setOnClickListener(v -> {

            String state = Environment.getExternalStorageState();

            if (Environment.MEDIA_MOUNTED.equals(state)) {

                txtResult.setText(
                        "External storage is available"
                );

                Toast.makeText(
                        MainActivity.this,
                        "SD Card / External Storage Available",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                txtResult.setText(
                        "External storage is not available"
                );

                Toast.makeText(
                        MainActivity.this,
                        "SD Card / External Storage Not Available",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        // =========================================
        // SAVE DATA
        // =========================================

        btnSave.setOnClickListener(v -> {

            String name = edtName.getText().toString().trim();
            String rollNo = edtRollNo.getText().toString().trim();

            // Check empty fields
            if (name.isEmpty() || rollNo.isEmpty()) {

                Toast.makeText(
                        MainActivity.this,
                        "Please enter student name and roll number",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            // Open Android Storage Access Framework
            Intent intent = new Intent(
                    Intent.ACTION_CREATE_DOCUMENT
            );

            intent.addCategory(
                    Intent.CATEGORY_OPENABLE
            );

            intent.setType("text/plain");

            intent.putExtra(
                    Intent.EXTRA_TITLE,
                    "student.txt"
            );

            createFileLauncher.launch(intent);
        });
    }

    // =========================================
    // SAVE DATA TO SELECTED FILE
    // =========================================

    private void saveDataToUri(Uri uri) {

        String name = edtName.getText().toString().trim();
        String rollNo = edtRollNo.getText().toString().trim();

        String data =
                "Student Name: " + name
                        + "\nRoll Number: " + rollNo;

        try {

            OutputStream outputStream =
                    getContentResolver().openOutputStream(uri);

            if (outputStream != null) {

                // Write data
                outputStream.write(
                        data.getBytes(StandardCharsets.UTF_8)
                );

                // Close file
                outputStream.close();

                // Display result
                txtResult.setText(
                        "Data saved successfully\n\n" + data
                );

                Toast.makeText(
                        MainActivity.this,
                        "Data Saved Successfully",
                        Toast.LENGTH_SHORT
                ).show();
            }

        } catch (Exception e) {

            Toast.makeText(
                    MainActivity.this,
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}