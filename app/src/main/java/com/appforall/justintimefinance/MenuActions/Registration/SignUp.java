package com.appforall.justintimefinance.MenuActions.Registration;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appforall.justintimefinance.MainActivity;
import com.appforall.justintimefinance.RecyclerAdaptor.Model.User;
import com.appforall.justintimefinance.db.DatabaseHandler;
import com.appforall.justintimefinance.R;

public class SignUp extends AppCompatActivity {

    EditText firstname, lastname, username, password, confirmpassword, accountnumber, email, phonenumber;
    DatabaseHandler db;
    Button loginBtn, signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //removing actionbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();

        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
//        confirmpassword = (EditText)findViewById(R.id.confirmpassword);
        email = (EditText)findViewById(R.id.email);
        phonenumber = (EditText)findViewById(R.id.phonenumber);

        signUpBtn=(Button)findViewById(R.id.signUpButtonWorkingSignup);
        loginBtn=(Button)findViewById(R.id.loginButtonTransferSignup);

        db = new DatabaseHandler(this);


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sfirstname = firstname.getText().toString();
                String susername = username.getText().toString();
                String spassword = password.getText().toString();
//                String sconfirmpassword = confirmpassword.getText().toString();
                String semail = email.getText().toString();
                String sphonenumber = phonenumber.getText().toString();
                User user = new User(sfirstname ,lastname.getText().toString(),
                        susername, spassword,
                         semail,
                        sphonenumber);

                if(sfirstname.equals("") || susername.equals("") || spassword.equals("")  || semail.equals("") || sphonenumber.equals("")){
                    Toast.makeText(SignUp.this, "Please fill all the details", Toast.LENGTH_LONG).show();
                }
                else {
                    Boolean checkemail = db.Authentication(user); //checking if email already exist
                    if(checkemail==false) {
                        long insrt = db.Registration(user); //inserting data into database
                        if(insrt != 0) {
                            Toast.makeText(SignUp.this, "User Registered Successfully!", Toast.LENGTH_LONG).show();
                            Intent intnt = new Intent(getApplicationContext(), MainActivity.class); //move from this activity to mainactivity
                            startActivity(intnt); //start the new activity
                        } else {
                            Toast.makeText(SignUp.this, "User failed to register", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(SignUp.this, "Email already exist!. Please Login", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,Login.class); //move from this activity to mainactivity
                startActivity(intent);//start the new activity
            }
        });
    }
}}

//else if(!spassword.equals(sconfirmpassword)) {
//        Toast.makeText(SignUp.this, "New Password must be equal to Confirm Password", Toast.LENGTH_LONG).show();
//        }