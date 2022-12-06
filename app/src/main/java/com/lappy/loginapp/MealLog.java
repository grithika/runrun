package com.lappy.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MealLog extends AppCompatActivity {
    MealLogDatabaseHelper mealLogDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_log);
        mealLogDB = new MealLogDatabaseHelper(this);
    }
}