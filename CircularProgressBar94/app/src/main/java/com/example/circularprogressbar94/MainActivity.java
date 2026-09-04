package com.example.circularprogressbar94;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView txtProgress;
    TextView txtStatus;
    Button btnStart;

    int progress = 0;

    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Connect XML views with Java
        progressBar = findViewById(R.id.progressBar);
        txtProgress = findViewById(R.id.txtProgress);
        txtStatus = findViewById(R.id.txtStatus);
        btnStart = findViewById(R.id.btnStart);

        // Start button
        btnStart.setOnClickListener(v -> {
            startProgress();
        });
    }

    private void startProgress() {

        progress = 0;

        btnStart.setEnabled(false);
        txtStatus.setText("Progress running...");

        Runnable runnable = new Runnable() {

            @Override
            public void run() {

                if (progress <= 100) {

                    // Update circular progress bar
                    progressBar.setProgress(progress);

                    // Update percentage text
                    txtProgress.setText(progress + "%");

                    progress++;

                    // Speed of progress
                    handler.postDelayed(this, 50);

                } else {

                    // Completed
                    txtStatus.setText("Completed!");
                    btnStart.setEnabled(true);
                }
            }
        };

        handler.post(runnable);
    }
}
