package com.example.okhttpdemo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    Button btnGet;
    Button btnPost;
    Button btnPut;
    Button btnDelete;

    TextView txtResult;

    OkHttpClient client;

    String BASE_URL =
            "https://jsonplaceholder.typicode.com/posts";

    MediaType JSON =
            MediaType.get("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Connect Java with XML
        btnGet = findViewById(R.id.btnGet);
        btnPost = findViewById(R.id.btnPost);
        btnPut = findViewById(R.id.btnPut);
        btnDelete = findViewById(R.id.btnDelete);

        txtResult = findViewById(R.id.txtResult);

        // Create OkHttp client
        client = new OkHttpClient();

        // GET button
        btnGet.setOnClickListener(v -> getData());

        // POST button
        btnPost.setOnClickListener(v -> postData());

        // PUT button
        btnPut.setOnClickListener(v -> putData());

        // DELETE button
        btnDelete.setOnClickListener(v -> deleteData());
    }


    // =========================================================
    // GET
    // =========================================================

    private void getData() {

        new Thread(() -> {

            try {

                Request request = new Request.Builder()
                        .url(BASE_URL + "/1")
                        .get()
                        .build();

                Response response =
                        client.newCall(request).execute();

                int responseCode =
                        response.code();

                String responseData =
                        response.body().string();

                response.close();

                runOnUiThread(() -> {

                    txtResult.setText(
                            "GET REQUEST\n\n"
                                    + "Response Code: "
                                    + responseCode
                                    + "\n\n"
                                    + "Response:\n"
                                    + responseData
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

                // JSON data to send
                String jsonData =
                        "{"
                                + "\"title\":\"My New Post\","
                                + "\"body\":\"This is my new post\","
                                + "\"userId\":1"
                                + "}";

                RequestBody body =
                        RequestBody.create(
                                jsonData,
                                JSON
                        );

                Request request =
                        new Request.Builder()
                                .url(BASE_URL)
                                .post(body)
                                .build();

                Response response =
                        client.newCall(request).execute();

                int responseCode =
                        response.code();

                String responseData =
                        response.body().string();

                response.close();

                runOnUiThread(() -> {

                    txtResult.setText(
                            "POST REQUEST\n\n"
                                    + "Response Code: "
                                    + responseCode
                                    + "\n\n"
                                    + "Response:\n"
                                    + responseData
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

                // Updated JSON data
                String jsonData =
                        "{"
                                + "\"id\":1,"
                                + "\"title\":\"Updated Post\","
                                + "\"body\":\"This post has been updated\","
                                + "\"userId\":1"
                                + "}";

                RequestBody body =
                        RequestBody.create(
                                jsonData,
                                JSON
                        );

                Request request =
                        new Request.Builder()
                                .url(BASE_URL + "/1")
                                .put(body)
                                .build();

                Response response =
                        client.newCall(request).execute();

                int responseCode =
                        response.code();

                String responseData =
                        response.body().string();

                response.close();

                runOnUiThread(() -> {

                    txtResult.setText(
                            "PUT REQUEST\n\n"
                                    + "Response Code: "
                                    + responseCode
                                    + "\n\n"
                                    + "Response:\n"
                                    + responseData
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

                Request request =
                        new Request.Builder()
                                .url(BASE_URL + "/1")
                                .delete()
                                .build();

                Response response =
                        client.newCall(request).execute();

                int responseCode =
                        response.code();

                String responseData =
                        response.body().string();

                response.close();

                runOnUiThread(() -> {

                    txtResult.setText(
                            "DELETE REQUEST\n\n"
                                    + "Response Code: "
                                    + responseCode
                                    + "\n\n"
                                    + "Response:\n"
                                    + responseData
                    );

                });

            } catch (Exception e) {

                showError(e);
            }
        }).start();
    }


    // =========================================================
    // ERROR HANDLING
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
