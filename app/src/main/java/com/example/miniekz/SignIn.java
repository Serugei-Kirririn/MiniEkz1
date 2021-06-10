package com.example.miniekz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignIn extends AppCompatActivity {
    private API api;
    private Retrofit retrofit;
    private Button button;
    private EditText email,password;
    private TextView regText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        button = findViewById(R.id.button);
        email = findViewById(R.id.signInPassword);
        password = findViewById(R.id.signInPassword);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://cinema.areas.su/").build();
        api = retrofit.create(API.class);
        regText = findViewById(R.id.button2);
        regText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this,SignUp.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth();
            }
        });
    }

    private void Auth() {
        AuthParam authParam = new AuthParam();
        authParam.setEmail(email.getText().toString());
        authParam.setPassword(password.getText().toString());
        Call<AuthParam> call = api.doAuth(authParam);
        call.enqueue(new Callback<AuthParam>() {
            @Override
            public void onResponse(Call<AuthParam> call, Response<AuthParam> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignIn.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "NO", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<AuthParam> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}