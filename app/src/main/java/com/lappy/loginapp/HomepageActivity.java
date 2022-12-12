package com.lappy.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;

public class HomepageActivity extends AppCompatActivity implements LocationListener, View.OnClickListener {

    private static final int PermissionCode = 58;

    CardView startrun, runsettings,runhistory, logmeal, logoutButton, mealhistory;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage2);

        startrun = findViewById(R.id.homepageStartRun);
        startrun.setOnClickListener(this);

        runsettings = findViewById(R.id.homepageRunSettings);
        runsettings.setOnClickListener(this);

        runhistory = findViewById(R.id.homepageRunHistory);
        runhistory.setOnClickListener(this);

        logmeal = findViewById(R.id.homepageLogMeal);
        logmeal.setOnClickListener(this);

        logoutButton = findViewById(R.id.homepageAboutLogoutButton);
        logoutButton.setOnClickListener(this);

//        mealhistory = (ImageView) findViewById(R.id.homepageLogMealHistory);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


//          Thread((Runnable)) cannot be applied to cardViewHolder
//        startrun.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                StartRunnable startRunnable = new StartRunnable();
//                new Thread((Runnable) startrun).start();
//            }
//
//
//        });

//        runhistory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                runHistory runHistory = new runHistory();
//                new Thread((Runnable) runhistory).start();
//            }
//
//
//        });
//
//        runsettings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                runSettings runSettings = new runSettings();
//                new Thread((Runnable) runsettings).start();
//            }
//
//            class runSettings implements Runnable{
//                @Override
//                public void run(){
//                    runsettings.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            Intent intent = new Intent(HomepageActivity.this, RunSettings.class);
//                        }
//                    });
//                }
//            }
//        });


//        logmeal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MealLog MealLog = new MealLog();
//                new Thread((Runnable) logmeal).start();
//            }
//
//            class MealLog implements Runnable{
//                @Override
//                public void run(){
//                    logmeal.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            Intent intent = new Intent(HomepageActivity.this, MealLog.class);
//                        }
//                    });
//                }
//            }
//        });
//          no more using this variable
//        mealhistory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MealHistory mealHistory = new MealHistory();
//                new Thread((Runnable) mealhistory).start();
//            }
//
//            class MealHistory implements Runnable{
//                @Override
//                public void run(){
//                    mealhistory.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            Intent intent = new Intent(HomepageActivity.this, MealHistory.class);
//                        }
//                    });
//                }
//            }
//        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homepageStartRun:
                //StartRunnable startRunnable = new StartRunnable();
                startActivity(new Intent(this, RunInterface.class));
                running();
                break;
            case R.id.homepageRunSettings:
                startActivity(new Intent(this, RunSettings.class));
                break;
            case R.id.homepageRunHistory:
                //runHistory runHistory = new runHistory();
                startActivity(new Intent(this, RunHistoryDetails.class));
                history();
                break;
            case R.id.homepageLogMeal:
                startActivity(new Intent(this, MealLog.class));
                break;
            case R.id.homepageAboutLogoutButton:
                startActivity(new Intent(this, LogoutFragment.class));
                break;
        }
    }


//    class StartRunnable implements Runnable{
//        @Override
//
//
//    }
    public void running(){
        startrun.post(new Runnable() {
            @Override
            public void run() {
                if (ContextCompat.checkSelfPermission(HomepageActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(HomepageActivity.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(HomepageActivity.this,
                        Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    startRunInterface();
                } else {
                    LocationPermissionRequest();
                }
            }
        });
    }
//    class runHistory implements Runnable{
//        @Override
//
//    }
    public void history(){
        runhistory.post(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(HomepageActivity.this, RunHistoryDetails.class);
            }
        });
    }




    private void LocationPermissionRequest() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(HomepageActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {

            new AlertDialog.Builder(this)
                    .setTitle("Location Permission Required")
                    .setMessage("Please allow permission access to proceed.")
                    .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                ActivityCompat.requestPermissions(HomepageActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                                Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                                                Manifest.permission.READ_PHONE_STATE}, PermissionCode);
                            }
                            ActivityCompat.requestPermissions(HomepageActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                            Manifest.permission.ACCESS_COARSE_LOCATION,
                                            Manifest.permission.READ_PHONE_STATE}, PermissionCode);
                        }

                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                                Manifest.permission.READ_PHONE_STATE}, PermissionCode);
            }
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.READ_PHONE_STATE}, PermissionCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LocationAlert();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();

            }
        }

    }

    public void LocationAlert() {
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
            dialogbuilder.setMessage(" Enable GPS To Continue")
                    .setPositiveButton("Turn location on", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent call_gps_settings = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(call_gps_settings);


                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            AlertDialog alertDialog = dialogbuilder.create();
            alertDialog.show();

        }
    }

    private void startRunInterface(){
        Intent intent = new Intent(HomepageActivity.this, RunInterface.class);
        startActivity(intent);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}