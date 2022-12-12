package com.lappy.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutLogout extends AppCompatActivity implements View.OnClickListener{
    Button about_button, logout_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_logout);

        about_button = findViewById(R.id.aboutButton);
        about_button.setOnClickListener(this);

        logout_button = findViewById(R.id.logoutButton);
        logout_button.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aboutButton:
                startActivity(new Intent(this, AboutFragment.class));
                break;
            case R.id.logoutButton:
                startActivity(new Intent(this, LogoutFragment.class));
                break;
            default:startActivity(new Intent(this, AboutFragment.class));
        }
    }
}