package com.example.filehandling94;

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

public class sdcard94 extends AppCompatActivity {

    EditText edtName, edtRollNo;
    Button btnSave, btnCheck;
    TextView txtResult;

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

        edtName = findViewById(R.id.edtName);
        edtRollNo = findViewById(R.id.edtRollNo);
        btnSave = findViewById(R.id.btnSave);
        btnCheck = findViewById(R.id.btnCheck);
        txtResult = findViewById(R.id.txtResult);

        btnCheck.setOnClickListener(v -> {

            String state = Environment.getExternalStorageState();

            if (Environment.MEDIA_MOUNTED.equals(state)) {
                txtResult.setText("External storage is available");
            } else {
                txtResult.setText("External storage is not available");
            }
        });

        btnSave.setOnClickListener(v -> {

            String name = edtName.getText().toString().trim();
            String rollNo = edtRollNo.getText().toString().trim();

            if (name.isEmpty() || rollNo.isEmpty()) {

                Toast.makeText(
                        sdcard94.this,
                        "Please enter name and roll number",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);

            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TITLE, "student.txt");

            createFileLauncher.launch(intent);
        });
    }

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

                outputStream.write(
                        data.getBytes(StandardCharsets.UTF_8)
                );

                outputStream.close();

                txtResult.setText(
                        "Data saved successfully\n\n" + data
                );

                Toast.makeText(
                        sdcard94.this,
                        "Data Saved Successfully",
                        Toast.LENGTH_SHORT
                ).show();
            }

        } catch (Exception e) {

            Toast.makeText(
                    sdcard94.this,
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}
