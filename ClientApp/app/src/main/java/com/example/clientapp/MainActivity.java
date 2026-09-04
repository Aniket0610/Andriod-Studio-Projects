package com.example.clientapp;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnRetrieve;
    TextView txtStudents;

    // URI of Content Provider from ContentProvider94
    private static final String CONTENT_URI =
            "content://com.example.contentprovider94.provider/students";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnRetrieve = findViewById(R.id.btnRetrieve);
        txtStudents = findViewById(R.id.txtStudents);

        btnRetrieve.setOnClickListener(v -> {

            retrieveStudents();

        });
    }

    private void retrieveStudents() {

        try {

            Uri uri = Uri.parse(CONTENT_URI);

            Cursor cursor = getContentResolver().query(
                    uri,
                    null,
                    null,
                    null,
                    null
            );

            if (cursor == null) {

                txtStudents.setText(
                        "Cursor is NULL\n\n" +
                                "Provider URI:\n" +
                                CONTENT_URI
                );

                return;
            }

            StringBuilder result = new StringBuilder();

            while (cursor.moveToNext()) {

                int id = cursor.getInt(
                        cursor.getColumnIndexOrThrow("_id")
                );

                String name = cursor.getString(
                        cursor.getColumnIndexOrThrow("name")
                );

                result.append(id)
                        .append(". ")
                        .append(name)
                        .append("\n");
            }

            cursor.close();

            if (result.length() == 0) {

                txtStudents.setText("No students found");

            } else {

                txtStudents.setText(result.toString());
            }

        } catch (Exception e) {

            txtStudents.setText(
                    "ERROR:\n" + e.toString()
            );
        }
    }
}