package com.example.intentdemoimplicit94;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java file with activity_main.xml
        setContentView(R.layout.activity_main);

        // Connect Button with XML
        btnWebsite = findViewById(R.id.btnWebsite);

        // Perform action when button is clicked
        btnWebsite.setOnClickListener(v -> {

            // Create an Implicit Intent
            // ACTION_VIEW tells Android that we want
            // to view some content.
            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com")
            );

            // Start the Intent
            // Android finds a suitable browser application.
            startActivity(intent);
        });
    }
}
