package com.example.miniekz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUp extends AppCompatActivity {
    private Retrofit retrofit;
    private EditText email,password,lastName,firstName;
    private API api;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        button = findViewById(R.id.button);
        email = findViewById(R.id.signUpEmail);
        password = findViewById(R.id.signUpPassword);
        lastName = findViewById(R.id.lastName);
        firstName = findViewById(R.id.firstName);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://cinema.areas.su/").build();
        api = retrofit.create(API.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reg();
            }
        });
    }
    private void Reg(){
        RegParam regParam = new RegParam();
        regParam.setFirsName(firstName.getText().toString());
        regParam.setLastName(lastName.getText().toString());
        regParam.setEmail(email.getText().toString());
        regParam.setPassword(password.getText().toString());
        final String e = email.getText().toString();
        if(!(email.getText().toString().equals("") && firstName.getText().toString().equals("") && lastName.getText().toString().equals("") && password.getText().toString().equals("")) && Patterns.EMAIL_ADDRESS.matcher(e).matches()){
            registerUser();

        }else {
            ShowDialog("Error");
        }
        Call<RegParam> call = api.doReg(regParam);
        call.enqueue(new Callback<RegParam>() {
            @Override
            public void onResponse(Call<RegParam> call, Response<RegParam> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignUp.this, "OK", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SignUp.this, "No", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegParam> call, Throwable t) {

            }
        });
    }

    private void ShowDialog(String error) {
  final AlertDialog alertDialog = new AlertDialog.Builder(SignUp.this).setMessage(error).setPositiveButton("OK", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
          dialog.cancel();
      }
  }).create();
  alertDialog.show();
    }

    private void registerUser() {
    }
}