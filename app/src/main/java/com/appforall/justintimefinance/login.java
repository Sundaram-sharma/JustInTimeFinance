package com.appforall.justintimefinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    JITF_DBhelper DB;
    Button loginBtn, signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signUpBtn = findViewById(R.id.signUpButtonTransferLogin);
        loginBtn = findViewById(R.id.loginButtonWorkingLogin);

        edtEmail = findViewById(R.id.inputEmailLogin);
        edtPassword = findViewById(R.id.inputPasswordLogin);

        DB = new JITF_DBhelper(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                if(email.equals("") || password.equals("")){
                    Toast.makeText(login.this, "Please fill all the entries", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkemailpass = DB.checkingEmailPassword(email, password);
                    if(checkemailpass == true){
                        Toast.makeText(login.this, "Login Successful, welcome", Toast.LENGTH_SHORT).show();

                        Intent intnt = new Intent(getApplicationContext(), MainActivity.class); //move from this activity to mainactivity
                        startActivity(intnt); //start the new activity
                    }else{
                        Toast.makeText(login.this, "Invalid Email and password", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() { //redirect the login activity to signup activity
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,signUp.class); // moving from login class to signUp class
                startActivity(intent); // start the new activity
            }
        });
    }
}