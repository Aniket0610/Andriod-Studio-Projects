package com.example.helloworld94;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends
        AppCompatActivity {

        Button btnShow;
        TextView txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShow = findViewById(R.id.btnShow);
        txtMessage = findViewById(R.id.txtMessage);

        btnShow.setOnClickListener(view -> {
           //  txtMessage.setText("Welcome to Mobile Computing Lab");
            txtMessage.setText(R.string.welcome_message);
            //txtMessage.setTextColor(Color.BLUE);
            txtMessage.setTextColor(ContextCompat.getColor(this, R.color.red));
        });

    }
}