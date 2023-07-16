package com.example.signupandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginactivity extends AppCompatActivity {
    EditText emailText, passwordtext;
    String rEmail,rPassword;
    Button loginbtn;
    int count=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        emailText = findViewById(R.id.loginuser);
        passwordtext = findViewById(R.id.loginpassword);
        rEmail = getIntent().getStringExtra("Email");
        rPassword = getIntent().getStringExtra("Password");
        loginbtn = findViewById(R.id.loginbtn);
    }
    public void login(View view){
        String email,password;
        email = emailText.getText().toString();
        password = passwordtext.getText().toString();
        if(email.equals(rEmail)&&password.equals(rPassword)){
//            Toast.makeText(this,"Login Successfull!!",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, success.class);
            startActivity(intent);
        }
        else{
            count--;
            if(count!=0){
                Toast.makeText(this,"Login Failed!",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this,"Too Many Unsuccessful Attempts",Toast.LENGTH_LONG).show();
                loginbtn.setEnabled(false);
            }
        }
    }
}