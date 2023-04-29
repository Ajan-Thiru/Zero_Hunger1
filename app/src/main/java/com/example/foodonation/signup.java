package com.example.foodonation;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signup extends AppCompatActivity {

    EditText name,email, pw, confirmpw;
    Button signup;
    Database DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name=(EditText)findViewById(R.id.signup_name);
        email=(EditText)findViewById(R.id.signup_email);
        pw=(EditText)findViewById(R.id.signup_password);
        confirmpw=(EditText)findViewById(R.id.signup_confirmpw);

//        getSupportActionBar().setTitle("SIGN UP");

        DB=new Database(this);

        signup=(Button)findViewById(R.id.signup);

        pw.setTransformationMethod(new PasswordTransformationMethod());
        confirmpw.setTransformationMethod(new PasswordTransformationMethod());

        signup.setOnClickListener(v -> {
            String username=name.getText().toString();
            String password=pw.getText().toString();
            String confirmpass=confirmpw.getText().toString();

            if(username.equals("") || password.equals("") || confirmpass.equals(""))
                Toast.makeText(signup.this, "Please enter all the fields", Toast.LENGTH_LONG).show();
            else{
                if(password.equals(confirmpass))
                {
                    Boolean checkuser=DB.checkUsername(username);
                    if(!checkuser){
                        Boolean insert=DB.insertData(username,password);
                        if(insert)
                        {
                            Toast.makeText(signup.this, "registered successfully", Toast.LENGTH_LONG).show();
                            Intent i=new Intent(signup.this,login.class);
                        }
                        else
                        {
                            Toast.makeText(signup.this, "registration failed", Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(signup.this, "user already exists please sign in", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(signup.this, "passwords not matching", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}