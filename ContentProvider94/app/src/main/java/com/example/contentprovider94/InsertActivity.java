package com.example.contentprovider94;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {

    EditText edtName;
    Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_insert);

        edtName = findViewById(R.id.edtName);
        btnInsert = findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(v -> {

            String name =
                    edtName.getText()
                            .toString()
                            .trim();

            if (name.isEmpty()) {

                Toast.makeText(
                        InsertActivity.this,
                        "Please enter student name",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            ContentValues values =
                    new ContentValues();

            values.put("name", name);

            Uri resultUri =
                    getContentResolver().insert(
                            StudentProvider.CONTENT_URI,
                            values
                    );

            if (resultUri != null) {

                Toast.makeText(
                        InsertActivity.this,
                        "Student inserted successfully",
                        Toast.LENGTH_SHORT
                ).show();

                edtName.setText("");

            } else {

                Toast.makeText(
                        InsertActivity.this,
                        "Insert failed",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}