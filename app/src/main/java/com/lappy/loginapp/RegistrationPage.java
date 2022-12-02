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
import com.google.firebase.database.FirebaseDatabase;

//import java.util.regex.Pattern;

public class RegistrationPage extends AppCompatActivity implements View.OnClickListener {


//    DatabaseReference FirebaseDatabase= com.google.firebase.database.FirebaseDatabase.getInstance().getReferenceFromUrl("https://fitness-app-0118-default-rtdb.firebaseio.com/");

    private EditText firstname, lastname, age, emailId, pass;
    private TextView registerbtn, applogo;
    private ProgressBar progressBar;


    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        mAuth = FirebaseAuth.getInstance();

        applogo = (TextView) findViewById(R.id.appLogo);
        applogo.setOnClickListener(this);

        registerbtn = (TextView) findViewById(R.id.registerBtn);
        registerbtn.setOnClickListener(this);

        firstname = (EditText) findViewById(R.id.firstName);
        lastname = (EditText) findViewById(R.id.lastName);
        age = (EditText) findViewById(R.id.age);
        emailId = (EditText) findViewById(R.id.emailIdR);
        pass = (EditText) findViewById(R.id.passwordR);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.appLogo:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.registerBtn:
                registerbtn();
                break;
        }

    }

    private void registerbtn() {
        String firstName = firstname.getText().toString().trim();
        String lastName = lastname.getText().toString().trim();
        String age = this.age.getText().toString().trim();
        String emailId = this.emailId.getText().toString().trim();
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
            this.age.setError("Please enter your age!");
            this.age.requestFocus();
            return;
        }
        if(emailId.isEmpty()) {
            this.emailId.setError("Please enter your email address!");
            this.emailId.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailId).matches()){
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
            pass.setError("Minimum 6 characters should be present");
            pass.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(emailId, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //User user = new User(firstname, lastname, age1, emailid);
                            User user = new User(firstName, lastName, age, emailId);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if(task.isSuccessful()){
                                                Toast.makeText(RegistrationPage.this, "User has been registered succesfully", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);

                                                //redirect to login layout
                                            }else{
                                                Toast.makeText(RegistrationPage.this, "Failed to register the user. Try again!", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        }else{
                            Toast.makeText(RegistrationPage.this, "Failed to register the user!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });
    }
}