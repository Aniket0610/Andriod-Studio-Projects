package com.example.alertdialog94;

// Import Bundle class for Activity lifecycle
import android.os.Bundle;

// Import View class for handling button click events
import android.view.View;

// Import Button widget
import android.widget.Button;

// Import AlertDialog class to display popup message
import android.app.AlertDialog;

// Import AppCompatActivity
import androidx.appcompat.app.AppCompatActivity;

// MainActivity is the main screen of our application
// AppCompatActivity provides basic Activity functionality
public class MainActivity extends AppCompatActivity {

    // Declare a Button variable
    // This variable will refer to the button in activity_main.xml
    Button btnAlert;

    // onCreate() method is called when the Activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect MainActivity.java with activity_main.xml
        // activity_main.xml contains the user interface
        setContentView(R.layout.activity_main);

        // Find the button from XML using its ID
        // btnAlert is the ID given to the Button in activity_main.xml
        btnAlert = findViewById(R.id.btnAlert);

        // Set a click listener on the button
        // This code will execute when the user clicks the button
        btnAlert.setOnClickListener(new View.OnClickListener() {

            // onClick() method is automatically called
            // when the button is clicked
            @Override
            public void onClick(View v) {

                // Create an AlertDialog Builder object
                // Builder is used to prepare the AlertDialog
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);

                // Set the title of the AlertDialog
                builder.setTitle("Welcome");

                // Set the message displayed inside the AlertDialog
                builder.setMessage(
                        "Welcome to Android Programming ANIKET LAD 94"
                );

                // Add a Positive button named "OK"
                // null means no additional action is required
                // after clicking OK
                builder.setPositiveButton("OK", null);

                // Display the AlertDialog on the screen
                builder.show();
            }
        });
    }
}