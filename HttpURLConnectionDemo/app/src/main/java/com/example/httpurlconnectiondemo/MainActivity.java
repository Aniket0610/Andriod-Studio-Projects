package com.example.httpurlconnectiondemo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    Button btnGet, btnPost, btnPut, btnDelete;
    TextView txtResult;

    String BASE_URL =
            "https://jsonplaceholder.typicode.com/posts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Connect Java variables with XML
        btnGet = findViewById(R.id.btnGet);
        btnPost = findViewById(R.id.btnPost);
        btnPut = findViewById(R.id.btnPut);
        btnDelete = findViewById(R.id.btnDelete);

        txtResult = findViewById(R.id.txtResult);

        // GET
        btnGet.setOnClickListener(v -> {
            getData();
        });

        // POST
        btnPost.setOnClickListener(v -> {
            postData();
        });

        // PUT
        btnPut.setOnClickListener(v -> {
            putData();
        });

        // DELETE
        btnDelete.setOnClickListener(v -> {
            deleteData();
        });
    }

    // =========================================================
    // GET
    // =========================================================

    private void getData() {

        new Thread(() -> {

            try {

                URL url = new URL(BASE_URL + "/1");

                HttpURLConnection connection =
                        (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");

                int responseCode =
                        connection.getResponseCode();

                String response =
                        readResponse(connection);

                connection.disconnect();

                runOnUiThread(() -> {

                    txtResult.setText(
                            "GET REQUEST\n\n"
                                    + "Response Code: "
                                    + responseCode
                                    + "\n\n"
                                    + "Response:\n"
                                    + response
                    );
                });

            } catch (Exception e) {

                showError(e);
            }

        }).start();
    }

    // =========================================================
    // POST
    // =========================================================

    private void postData() {

        new Thread(() -> {

            try {

                URL url = new URL(BASE_URL);

                HttpURLConnection connection =
                        (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("POST");

                connection.setRequestProperty(
                        "Content-Type",
                        "application/json"
                );

                connection.setDoOutput(true);

                // JSON data to send
                String jsonData =
                        "{"
                                + "\"title\":\"My New Post\","
                                + "\"body\":\"This is my post\","
                                + "\"userId\":1"
                                + "}";

                // Send JSON data
                OutputStream outputStream =
                        connection.getOutputStream();

                outputStream.write(
                        jsonData.getBytes(
                                StandardCharsets.UTF_8
                        )
                );

                outputStream.flush();
                outputStream.close();

                // Get response code
                int responseCode =
                        connection.getResponseCode();

                // Read response
                String response =
                        readResponse(connection);

                connection.disconnect();

                runOnUiThread(() -> {

                    txtResult.setText(
                            "POST REQUEST\n\n"
                                    + "Response Code: "
                                    + responseCode
                                    + "\n\n"
                                    + "Response:\n"
                                    + response
                    );
                });

            } catch (Exception e) {

                showError(e);
            }

        }).start();
    }

    // =========================================================
    // PUT
    // =========================================================

    private void putData() {

        new Thread(() -> {

            try {

                // Update post number 1
                URL url =
                        new URL(BASE_URL + "/1");

                HttpURLConnection connection =
                        (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("PUT");

                connection.setRequestProperty(
                        "Content-Type",
                        "application/json"
                );

                connection.setDoOutput(true);

                // Updated JSON data
                String jsonData =
                        "{"
                                + "\"id\":1,"
                                + "\"title\":\"Updated Post\","
                                + "\"body\":\"This post has been updated\","
                                + "\"userId\":1"
                                + "}";

                // Send JSON
                OutputStream outputStream =
                        connection.getOutputStream();

                outputStream.write(
                        jsonData.getBytes(
                                StandardCharsets.UTF_8
                        )
                );

                outputStream.flush();
                outputStream.close();

                // Response code
                int responseCode =
                        connection.getResponseCode();

                // Read response
                String response =
                        readResponse(connection);

                connection.disconnect();

                runOnUiThread(() -> {

                    txtResult.setText(
                            "PUT REQUEST\n\n"
                                    + "Response Code: "
                                    + responseCode
                                    + "\n\n"
                                    + "Response:\n"
                                    + response
                    );
                });

            } catch (Exception e) {

                showError(e);
            }

        }).start();
    }

    // =========================================================
    // DELETE
    // =========================================================

    private void deleteData() {

        new Thread(() -> {

            try {

                // Delete post number 1
                URL url =
                        new URL(BASE_URL + "/1");

                HttpURLConnection connection =
                        (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("DELETE");

                // Get response code
                int responseCode =
                        connection.getResponseCode();

                // Read response
                String response =
                        readResponse(connection);

                connection.disconnect();

                runOnUiThread(() -> {

                    txtResult.setText(
                            "DELETE REQUEST\n\n"
                                    + "Response Code: "
                                    + responseCode
                                    + "\n\n"
                                    + "Response:\n"
                                    + response
                    );
                });

            } catch (Exception e) {

                showError(e);
            }

        }).start();
    }

    // =========================================================
    // READ SERVER RESPONSE
    // =========================================================

    private String readResponse(
            HttpURLConnection connection)
            throws Exception {

        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                connection.getInputStream()
                        )
                );

        StringBuilder response =
                new StringBuilder();

        String line;

        while ((line = reader.readLine()) != null) {

            response.append(line);
        }

        reader.close();

        return response.toString();
    }

    // =========================================================
    // ERROR
    // =========================================================

    private void showError(Exception e) {

        runOnUiThread(() -> {

            txtResult.setText(
                    "Error:\n"
                            + e.getMessage()
            );
        });
    }
}
