package com.appforall.justintimefinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signUp extends AppCompatActivity {

    EditText edtAccountNumber, edtName, edtEmail, edtPassword;
    JITF_DBhelper DB;
    Button loginBtn, signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtAccountNumber=(EditText) findViewById(R.id.inputAccountNumberSignup);
        edtName=(EditText)findViewById(R.id.inputNameSignup);
        edtEmail=(EditText)findViewById(R.id.inputEmailSignup);
        edtPassword=(EditText)findViewById(R.id.inputPasswordSignup);

        signUpBtn=(Button)findViewById(R.id.signUpButtonWorkingSignup);
        loginBtn=(Button)findViewById(R.id.loginButtonTransferSignup);

        DB= new JITF_DBhelper(this);


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String accountNumber = edtAccountNumber.getText().toString();
                String email = edtEmail.getText().toString();
                String name = edtName.getText().toString();
                String password = edtPassword.getText().toString();

                if(accountNumber.equals("") || email.equals("") || name.equals("") || password.equals("")){
                    Toast.makeText(signUp.this, "Please fill all the entries", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkemail = DB.checkingEmail(email); //checking if email already exist
                    if(checkemail==false){
                        Boolean insrt = DB.insertingData(accountNumber,email, name, password); //inserting data into database
                        if(insrt == true) {
                            Toast.makeText(signUp.this, "User Registered!", Toast.LENGTH_SHORT).show();

                            Intent intnt = new Intent(getApplicationContext(), MainActivity.class); //move from this activity to mainactivity
                            startActivity(intnt); //start the new activity
                        }else{
                            Toast.makeText(signUp.this, "User failed to register", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(signUp.this, "Email already exist!. Please Login", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signUp.this,login.class); //move from this activity to mainactivity
                startActivity(intent);//start the new activity
            }
        });
    }
}