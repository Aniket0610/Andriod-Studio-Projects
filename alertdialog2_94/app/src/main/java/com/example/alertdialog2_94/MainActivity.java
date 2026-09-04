package com.example.alertdialog2_94;

// Import AlertDialog to create a popup dialog box
import android.app.AlertDialog;

// Import Bundle for Activity lifecycle
import android.os.Bundle;

// Import View for handling button click events
import android.view.View;

// Import Button widget
import android.widget.Button;

// Import AppCompatActivity to create an Android Activity
import androidx.appcompat.app.AppCompatActivity;


// MainActivity is the main screen of the application
public class MainActivity extends AppCompatActivity {

    // Declare a Button variable
    // This will refer to the button defined in activity_main.xml
    Button btnConfirm;


    // onCreate() method is called when the Activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java file with activity_main.xml
        setContentView(R.layout.activity_main);


        // Find the Button from XML using its ID
        // btnConfirm is the ID of the button in activity_main.xml
        btnConfirm = findViewById(R.id.btnConfirm);


        // Set click event on the button
        // This code will execute when the user clicks the button
        btnConfirm.setOnClickListener(new View.OnClickListener() {

            // onClick() method is executed when the button is clicked
            @Override
            public void onClick(View v) {

                // Create an AlertDialog Builder
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);

                // Set the title
                builder.setTitle("Confirmation");

                // Set the message
                builder.setMessage("Do you want to continue?");

                // Add Yes button
                builder.setPositiveButton("Yes", null);

                // Add No button
                builder.setNegativeButton("No", null);

                // Display the AlertDialog
                builder.show();
            }
        });
    }
}