package com.example.calculator94;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText e1, e2;
    Button b1,b2,b3,b4;
    TextView t1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.editTextNumber);
        e2 = findViewById(R.id.editTextNumber2);
        b1 = findViewById(R.id.button);
        t1 = findViewById(R.id.textView2);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);


        b1.setOnClickListener(v -> {
            int num1 = Integer.parseInt(e1.getText().toString());
            int num2 = Integer.parseInt(e2.getText().toString());

            int sum = num1 + num2;

            // t1.setText("Result = " + sum);

            t1.setText(getString(R.string.result, sum));
        });
        b2.setOnClickListener(v -> {
            int num1 = Integer.parseInt(e1.getText().toString());
            int num2 = Integer.parseInt(e2.getText().toString());

            int sub = num1 - num2;

            // t1.setText("Result = " + sum);

            t1.setText(getString(R.string.result, sub));
        });
        b3.setOnClickListener(v -> {
            int num1 = Integer.parseInt(e1.getText().toString());
            int num2 = Integer.parseInt(e2.getText().toString());

            int product = num1*num2;

            // t1.setText("Result = " + sum);

            t1.setText(getString(R.string.result, product));
        });
        b4.setOnClickListener(v -> {
            int num1 = Integer.parseInt(e1.getText().toString());
            int num2 = Integer.parseInt(e2.getText().toString());

            int div = num1/num2;

            // t1.setText("Result = " + sum);

            t1.setText(getString(R.string.result, div));
        });

    }
}