package com.lappy.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView register, resetPassword;
    private EditText emailId, password;
    private Button signIn;

    private FirebaseAuth mAuth;
    private FirebaseAuth mUser;
    private ProgressBar progressBar;

    FirebaseDatabase database;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fitness-app-0118-default-rtdb.firebaseio.com/").child("getdata");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (TextView) findViewById(R.id.mainActivityRegisterButton);
        register.setOnClickListener(this);

        signIn = (Button) findViewById(R.id.mainActivitySignInButton);
        signIn.setOnClickListener(this);

        emailId = (EditText) findViewById(R.id.mainActivityEmailId);
        password = (EditText) findViewById(R.id.mainActivityPassword);

        progressBar = (ProgressBar) findViewById(R.id.registrationPageProgressBar);

        mAuth = FirebaseAuth.getInstance();

        resetPassword = (TextView) findViewById(R.id.mainActivityResetPasswordButton);
        resetPassword.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mainActivityRegisterButton:
                startActivity(new Intent(this, RegistrationPage.class));
                break;
            case R.id.mainActivitySignInButton:
                userLogin();
                break;
            case R.id.mainActivityResetPasswordButton:
                startActivity(new Intent(this, ResetPassword.class));
                break;
        }

    }

    private void userLogin() {
        String email = emailId.getText().toString().trim();
        String password = this.password.getText().toString().trim();

        Log.v("MainActivity", "'" + email + "'");
        if(email.isEmpty()){
            emailId.setError("Email is required!");
            emailId.requestFocus();
            return;
        }
//        Pattern pattern = Pattern.compile("\\w\\@\\w\\.\\w");
//        Matcher matcher = pattern.matcher(email);
        Pattern regexPattern = Pattern.compile("^(.+)@(.+)$ ");
        Matcher matcher = regexPattern.matcher(email);
//        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
        if(matcher.find()){
            emailId.setError("Enter a valid email ID!");
            emailId.requestFocus();
        return;
        }
        if (password.isEmpty()){
            this.password.setError("Password is required");
            this.password.requestFocus();
            return;
        }
        if (password.length()<6){
            this.password.setError("Minimum password length is 6 characters!");
            this.password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user.isEmailVerified()) {
                        //redirect to HomepageActivity
                        startActivity(new Intent(MainActivity.this, HomepageActivity.class));
                    }
                    else {
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this, "Check your email to verify your account", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Failed to login! Please check your credentials.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}