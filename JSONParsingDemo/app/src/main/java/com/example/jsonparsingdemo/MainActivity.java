package com.example.jsonparsingdemo;


import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btnFetch;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFetch = findViewById(R.id.btnFetch);
        txtResult = findViewById(R.id.txtResult);

        btnFetch.setOnClickListener(v -> fetchData());
    }

    private void fetchData() {

        new Thread(() -> {

            HttpURLConnection connection = null;

            try {
                URL url = new URL(
                        "https://jsonplaceholder.typicode.com/users"
                );

                connection = (HttpURLConnection)
                        url.openConnection();

                connection.setRequestMethod("GET");
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);

                int statusCode = connection.getResponseCode();

                if (statusCode != HttpURLConnection.HTTP_OK) {
                    throw new Exception(
                            "HTTP Error: " + statusCode
                    );
                }

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                connection.getInputStream()
                        )
                );

                StringBuilder jsonData = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    jsonData.append(line);
                }

                reader.close();

                JSONArray jsonArray =
                        new JSONArray(jsonData.toString());

                StringBuilder result = new StringBuilder();

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject user =
                            jsonArray.getJSONObject(i);

                    int id = user.getInt("id");
                    String name = user.getString("name");
                    String email = user.getString("email");

                    result.append("ID: ").append(id)
                            .append("\nName: ").append(name)
                            .append("\nEmail: ").append(email)
                            .append("\n\n");
                }

                runOnUiThread(() ->
                        txtResult.setText(result.toString())
                );

            } catch (Exception e) {

                runOnUiThread(() ->
                        txtResult.setText(
                                "Error: " + e.getMessage()
                        )
                );

            } finally {

                if (connection != null) {
                    connection.disconnect();
                }
            }

        }).start();
    }
}
