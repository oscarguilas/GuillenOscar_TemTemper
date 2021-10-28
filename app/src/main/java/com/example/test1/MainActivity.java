package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnSignIn;
    EditText txtUsername;
    EditText txtPassword;
    TextView lblLoginResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignIn = findViewById(R.id.btnSignIn);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        lblLoginResult = findViewById(R.id.lblLoginResult);
    }

    public void loginButtonClicked(View v){
        if(String.valueOf(txtUsername.getText()).equals("admin") && String.valueOf(txtPassword.getText()).equals("admin")) {
            Log.i("login", "login successful");
            goToLanding();
        }
        else Log.i("login", "incorrect login info");
    }

    public void goToLanding(){
        Intent intent = new Intent(this, LandingActivity.class);
        startActivity(intent);
    }
}