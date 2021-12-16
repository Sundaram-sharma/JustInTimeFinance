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

public class Login extends AppCompatActivity {

    EditText username, password;
    DatabaseHandler db;
    Button loginBtn, signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUpBtn = findViewById(R.id.signUpButtonTransferLogin);
        loginBtn = findViewById(R.id.loginButtonWorkingLogin);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        db = new DatabaseHandler(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());

                if(user.getUsername().equals("") || user.getPassword().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkemailpass = db.Authentication(user);
                    if(checkemailpass == true){
                        Toast.makeText(getApplicationContext(), "Login Successful, Welcome", Toast.LENGTH_LONG).show();
                        Intent intnt = new Intent(getApplicationContext(), MainActivity.class); //move from this activity to mainactivity
                        startActivity(intnt); //start the new activity
                    } else{
                        Toast.makeText(getApplicationContext(), "Invalid Email or Password", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() { //redirect the login activity to signup activity
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,SignUp.class); // moving from login class to signUp class
                startActivity(intent); // start the new activity
            }
        });
    }
}