package com.lappy.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RunSettings extends AppCompatActivity {

    public static String km_Button = "KMButton";
    public static String miles_Button = "MilesButton";
    public static String ON = "on";
    public static String OFF = "off";
    public static String GetInfo = "GetInfo";
    Button backButton;
    RadioGroup distanceGroup, countdownGroup;
    RadioButton onButton,offButton,kmButton, milesButton;
    SharedPreferences sharedPreferences, LastSelectedItem,LastChosen;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_settings);

        sharedPreferences = getApplicationContext().getSharedPreferences(GetInfo, MODE_PRIVATE);
        LastSelectedItem = getSharedPreferences("PriorSelected", Context.MODE_PRIVATE);
        LastChosen = getSharedPreferences("PriorSelected", Context.MODE_PRIVATE);

        backButton = findViewById(R.id.backButton);
        distanceGroup = findViewById(R.id.DistanceGroupSettings);
        milesButton = findViewById(R.id.MilesButton);
        kmButton = findViewById(R.id.KMButton);
        onButton = findViewById(R.id.OnButton);
        offButton = findViewById(R.id.OffButton);
        countdownGroup = findViewById(R.id.CountdownGroup);

        final int LastSelection = LastSelectedItem.getInt("LastSelection", 0);
        editor = LastSelectedItem.edit();

        final int DefaultItem = LastChosen.getInt("LastChosen", 0);
        editor = LastChosen.edit();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoBackBtnRunnable goBackBtnRunnable = new GoBackBtnRunnable();
                new Thread(goBackBtnRunnable).start();
            }

            class GoBackBtnRunnable implements Runnable {
                @Override
                public void run() {
                    backButton.post(new Runnable() {
                        @Override
                        public void run() {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean(miles_Button, milesButton.isChecked());
                            editor.putBoolean(km_Button, kmButton.isChecked());
                            editor.putBoolean(ON, onButton.isChecked());
                            editor.putBoolean(OFF, offButton.isChecked());
//                            editor.putInt("LastSelection", Myposition);
//                            editor.putInt("LastChosen", lingoPosition);
                            editor.apply();
                            Intent intent = new Intent(getApplicationContext(), HomepageActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });

        kmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KilometerBtnRunnable kilometerBtnRunnable = new KilometerBtnRunnable();
                new Thread(kilometerBtnRunnable).start();

            }

            class KilometerBtnRunnable implements Runnable {
                @Override
                public void run() {
                    kmButton.post(() -> {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(km_Button, kmButton.isChecked());
                        editor.apply();
                    });
                }
            }
        });

        milesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MilesBtnRunnable milesBtnRunnable = new MilesBtnRunnable();
                new Thread(milesBtnRunnable).start();
            }

            class MilesBtnRunnable implements Runnable {
                @Override
                public void run() {
                    milesButton.post(() -> {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(miles_Button, milesButton.isChecked());
                        editor.apply();

                    });
                }
            }
        });

        sharedPreferences = getApplicationContext().getSharedPreferences(GetInfo, MODE_PRIVATE);
        kmButton.setChecked(sharedPreferences.getBoolean(km_Button, true));
        milesButton.setChecked(sharedPreferences.getBoolean(miles_Button, false));

        onButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnButtonRunnable onButtonRunnable = new OnButtonRunnable();
                new Thread(onButtonRunnable).start();
            }

            class OnButtonRunnable implements Runnable {
                @Override
                public void run() {
                    onButton.post(() -> {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(ON, onButton.isChecked());
                        editor.apply();
                    });
                }
            }
        });

        offButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OffButtonRunnable offButtonRunnable = new OffButtonRunnable();
                new Thread(offButtonRunnable).start();
            }

            class OffButtonRunnable implements Runnable {
                @Override
                public void run() {
                    offButton.post(() -> {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(OFF, offButton.isChecked());
                        editor.apply();
                    });
                }
            }
        });

        sharedPreferences = getApplicationContext().getSharedPreferences(GetInfo, MODE_PRIVATE);
        onButton.setChecked(sharedPreferences.getBoolean(ON, false));
        offButton.setChecked(sharedPreferences.getBoolean(OFF, true));

        backButton.setOnClickListener(view -> startActivity(new Intent(RunSettings.this,HomepageActivity.class)));

//        @Override
//        public void onBackPressed() {
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putBoolean(miles_Button, milesButton.isChecked());
//            editor.putBoolean(km_Button, kmButton.isChecked());
//            editor.putBoolean(ON,onButton.isChecked());
//            editor.putBoolean(OFF, offButton.isChecked());
//            editor.apply();
//            Intent intent = new Intent(getApplicationContext(), HomepageActivity.class);
//            startActivity(intent);
//            finish();
//        }



        //TODO create onclicklistner for back button and resolve the above warning
        //TODO add BMI calculator

    }
}