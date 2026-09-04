package com.example.progressbar94;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Variable for ProgressBar
    ProgressBar progressBar;


    // Variable for Start Button
    Button btnStart;


    // Variable for TextView
    TextView txtProgress;


    // This variable stores the current progress value
    int progress = 0;


    // Handler is used to execute code after a time delay
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connects the Java file with activity_main.xml
        setContentView(R.layout.activity_main);

// Connect XML ProgressBar with Java variable
        progressBar = findViewById(R.id.progressBar);
        // Connect XML Button with Java variable
        btnStart = findViewById(R.id.btnStart);

        // Connect XML TextView with Java variable
        txtProgress = findViewById(R.id.txtProgress);

        // This code executes when the Start button is clicked
        btnStart.setOnClickListener(v -> {

            // Start the progress from 0
            progress = 0;

            // Set the ProgressBar to 0%
            progressBar.setProgress(0);

            // Display 0% in TextView
            txtProgress.setText("0%");

            // Handler is used to execute the code after 500 milliseconds
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {


                    // Check whether progress is less than or equal to 100
                    if (progress <= 100) {

                        // Display current progress in ProgressBar
                        progressBar.setProgress(progress);

                        // Display current percentage in TextView
                        txtProgress.setText(progress + "%");

                        // Check whether progress has reached 100%
                        if (progress == 100) {

                            // Display completion message
                            txtProgress.setText("Process Completed");

                            // Stop the progress
                            return;
                        }

                        // Increase progress by 10
                        progress = progress + 10;

                        // Run the same code again after 500 milliseconds
                        handler.postDelayed(this, 500);
                    }
                }


            }, 500);


        });


    }
}
