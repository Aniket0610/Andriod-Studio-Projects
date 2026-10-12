package com.example.retrofitapidemo;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "API";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiService api = RetrofitClient.getApiService();

        // GET API: Retrieve users
        api.getUsers().enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(
                    Call<List<User>> call,
                    Response<List<User>> response) {

                if (response.isSuccessful()
                        && response.body() != null) {

                    List<User> users = response.body();

                    for (User user : users) {
                        Log.d(TAG, user.getName()
                                + " - " + user.getEmail());
                    }

                } else {
                    Log.e(TAG, "HTTP error: "
                            + response.code());
                }
            }

            @Override
            public void onFailure(
                    Call<List<User>> call,
                    Throwable t) {

                Log.e(TAG, "Network error", t);
            }
        });

        // POST API: Submit a test user
        User newUser = new User(
                "Rahul",
                "rahul@example.com"
        );

        api.createUser(newUser).enqueue(new Callback<User>() {

            @Override
            public void onResponse(
                    Call<User> call,
                    Response<User> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, "User created successfully");
                    Log.d(TAG, "HTTP code: "
                            + response.code());
                } else {
                    Log.e(TAG, "POST error: "
                            + response.code());
                }
            }

            @Override
            public void onFailure(
                    Call<User> call,
                    Throwable t) {

                Log.e(TAG, "POST request failed", t);
            }
        });
    }
}
