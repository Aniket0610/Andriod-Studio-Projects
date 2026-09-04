package com.example.activitylifecycle1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLog = findViewById(R.id.txtLog);

        appendLog("onCreate()");
    }

    // Method to append lifecycle logs
    private void appendLog(String message) {
        txtLog.append(message + "\n");
    }

    @Override
    protected void onStart() {
        super.onStart();
        appendLog("onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        appendLog("onResume()");

        // Wait for 20 seconds and then close the activity
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 80000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        appendLog("onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        appendLog("onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        appendLog("onRestart()");
    }

    @Override
    protected void onDestroy() {
        appendLog("onDestroy()");
        super.onDestroy();
    }
}
