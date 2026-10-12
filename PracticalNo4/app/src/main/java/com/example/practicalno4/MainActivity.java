package com.example.practical No.4;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void Toaster(View view)
    {
        Toast.makeText(this, "Suyog_50", Toast.LENGTH_LONG).show();
    }
}
