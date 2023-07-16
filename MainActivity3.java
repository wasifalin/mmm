package com.example.signupandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText emailText, passwordtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailText = findViewById(R.id.username);
        passwordtext = findViewById(R.id.password);
    }

    public void signup(View view) {
        String email = emailText.getText().toString();
        String password = passwordtext.getText().toString();

        if (!isvalidpassword(password)) {
            Toast.makeText(this, "Password Does Not meet Requirement ", Toast.LENGTH_LONG).show();
        }else {
            Intent intent = new Intent(this, loginactivity.class);
            intent.putExtra("Email", email);
            intent.putExtra("Password", password);
            startActivity(intent);
        }
    }
    Pattern lowercase = Pattern.compile("^.*[a-z].*$");
    Pattern uppercase = Pattern.compile("^.*[A-Z].*$");
    Pattern digit = Pattern.compile("^.*[0-9].*$");
    Pattern specialchar = Pattern.compile("^.*[^a-zA-Z0-9].*$");
    private Boolean isvalidpassword(String pass){
        if(pass.length()<8)
            return false;
        if(!lowercase.matcher(pass).matches())
            return false;
        if(!uppercase.matcher(pass).matches())
            return false;
        if(!digit.matcher(pass).matches())
            return false;
        if(!specialchar.matcher(pass).matches())
            return false;
        return true;
    }
}