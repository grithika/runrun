package com.lappy.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
//import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

//import java.util.regex.Pattern;

public class RegistrationPage extends AppCompatActivity implements View.OnClickListener {


    DatabaseReference FirebaseDatabase= com.google.firebase.database.FirebaseDatabase.getInstance().getReferenceFromUrl("https://fitness-app-0118-default-rtdb.firebaseio.com/");

    private EditText firstname, lastname, years, emailId, pass;
    private TextView registerbtn, applogo;
    private ProgressBar progressBar;


    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        mAuth = FirebaseAuth.getInstance();

        applogo = findViewById(R.id.appLogo);
        applogo.setOnClickListener(this);

        registerbtn = findViewById(R.id.registrationPageRegisterButton);
        registerbtn.setOnClickListener(this);

        firstname = findViewById(R.id.registrationPageFirstName);
        lastname = findViewById(R.id.registrationPageLastName);
        years = findViewById(R.id.registrationPageAge);
        emailId = findViewById(R.id.registrationPageEmailId);
        pass = findViewById(R.id.registrationPagePassword);

        progressBar = findViewById(R.id.registrationPageProgressBar);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.appLogo:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.registrationPageRegisterButton:
                registerbtn();
                break;
        }

    }

    private void registerbtn() {
        String firstName = firstname.getText().toString().trim();
        String lastName = lastname.getText().toString().trim();
        String age = years.getText().toString().trim();
        String emailIdR = emailId.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if(firstName.isEmpty()){
            firstname.setError("Please enter your First Name!");
            firstname.requestFocus();
            return;
        }
        if(lastName.isEmpty()) {
            lastname.setError("Please enter your Last Name!");
            lastname.requestFocus();
            return;
        }
        if(age.isEmpty()) {
            this.years.setError("Please enter your age!");
            this.years.requestFocus();
            return;
        }
        if(emailIdR.isEmpty()) {
            this.emailId.setError("Please enter your email address!");
            this.emailId.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailIdR).matches()){
            this.emailId.setError("Please type a valid email address!");
            this.emailId.requestFocus();
            return;
        }
        if(password.isEmpty()) {
            pass.setError("Please enter a password!");
            pass.requestFocus();
            return;
        }
        if(password.length()<6){
            pass.setError("Minimum 6 characters are required");
            pass.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(emailIdR, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(firstName, lastName, age, emailIdR);

                            FirebaseDatabase.getDatabase().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if(task.isSuccessful()){
                                                Toast.makeText(RegistrationPage.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                            else{
                                                Toast.makeText(RegistrationPage.this, "Failed to register the user. Try again!", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        }
                        else{
                            Toast.makeText(RegistrationPage.this, "Failed to register the user!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });
    }
}