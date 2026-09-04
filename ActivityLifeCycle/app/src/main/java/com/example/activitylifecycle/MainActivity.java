package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

