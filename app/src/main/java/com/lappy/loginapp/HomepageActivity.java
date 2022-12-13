package com.lappy.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;

import java.text.DateFormat;
import java.util.Calendar;

public class HomepageActivity extends AppCompatActivity implements LocationListener, android.location.LocationListener {

    private static final int PermissionCode = 58;

    CardView startrun, runsettings,runhistory, logmeal, logoutButton, mealhistory;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage2);

        startrun = findViewById(R.id.homepageStartRun);
       // startrun.setOnClickListener(this);

        runsettings = findViewById(R.id.homepageRunSettings);
        //runsettings.setOnClickListener(this);

        runhistory = findViewById(R.id.homepageRunHistory);
        //runhistory.setOnClickListener(this);

        logmeal = findViewById(R.id.homepageLogMeal);
        //logmeal.setOnClickListener(this);

        logoutButton = findViewById(R.id.homepageAboutLogoutButton);
        //logoutButton.setOnClickListener(this);

//        mealhistory = (ImageView) findViewById(R.id.homepageLogMealHistory);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

//        HomePageDate = findViewById(R.id.HomePageDate);
//
//        CalenderDate();


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

//        history_IV_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                HistoryIVRunnable historyIVRunnable = new HistoryIVRunnable();
//                new Thread(historyIVRunnable).start();
//            }
//
//
//            class HistoryIVRunnable implements Runnable {
//                @Override
//                public void run() {
//                    history_IV_btn.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            Intent intent = new Intent(home.this, DisplayHistory.class);
//                            startActivity(intent);
//                        }
//                    });
//                }
//            }
//        });

        logmeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogmealRunnable logmealRunnable = new LogmealRunnable();
                new Thread(logmealRunnable).start();

            }

            class LogmealRunnable implements Runnable {
                @Override
                public void run() {
                    logmeal.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(HomepageActivity.this, MealLog.class);
                            startActivity(intent);

                        }
                    });
                }
            }
        });

        runhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryRunnable historyRunnable = new HistoryRunnable();
                new Thread(historyRunnable).start();

            }

            class HistoryRunnable implements Runnable {
                @Override
                public void run() {
                    runhistory.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(HomepageActivity.this, RunHistoryDetails.class);
                            startActivity(intent);

                        }
                    });
                }
            }
        });


//        settings_IV_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SettingsIVRunnable historyRunnable = new SettingsIVRunnable();
//                new Thread(historyRunnable).start();
//            }
//
//            class SettingsIVRunnable implements Runnable {
//                @Override
//                public void run() {
//                    settings_IV_btn.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            Intent intent = new Intent(home.this, settings.class);
//                            startActivity(intent);
//                        }
//                    });
//                }
//            }
//        });

        runsettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsRunnable settingsRunnable = new SettingsRunnable();
                new Thread(settingsRunnable).start();

            }

            class SettingsRunnable implements Runnable {
                @Override
                public void run() {
                    runsettings.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(HomepageActivity.this, RunSettings.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });

//        music_IV_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MusicIVRunnable musicIVRunnable = new MusicIVRunnable();
//                new Thread(musicIVRunnable).start();
//
//            }
//
//            class MusicIVRunnable implements Runnable {
//                @Override
//                public void run() {
//                    music_IV_btn.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            Intent intent = new Intent(CATEGORY_APP_MUSIC);
//                            startActivity(intent);
//                        }
//                    });
//                }
//            }
//        });
//        music_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MusicRunnable musicRunnable = new MusicRunnable();
//                new Thread(musicRunnable).start();
//
//            }
//
//            class MusicRunnable implements Runnable {
//                @Override
//                public void run() {
//                    music_btn.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            Intent intent = new Intent(CATEGORY_APP_MUSIC);
//                            startActivity(intent);
//                        }
//                    });
//                }
//            }
//        });

//        about_image_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AboutIVRunnable aboutIVRunnable = new AboutIVRunnable();
//                new Thread(aboutIVRunnable).start();
//
//            }
//
//            class AboutIVRunnable implements Runnable {
//                @Override
//                public void run() {
//                    about_image_view.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            Intent intent = new Intent(home.this, About.class);
//                            startActivity(intent);
//                        }
//                    });
//                }
//            }
//        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogoutRunnable logoutRunnable = new LogoutRunnable();
                new Thread(logoutRunnable).start();

            }

            class LogoutRunnable implements Runnable {
                @Override
                public void run() {
                    logoutButton.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(HomepageActivity.this, AboutLogout.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });

        startrun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartRunnable startRunnable = new StartRunnable();
                new Thread(startRunnable).start();
            }


            class StartRunnable implements Runnable {
                @Override
                public void run() {
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
            }
        });
//
    }
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.homepageStartRun:
//                //StartRunnable startRunnable = new StartRunnable();
//                //LocationPermissionRequest();
//                //LocationAlert();
//                //startRunInterface();
////                startActivity(new Intent(this, RunInterface.class));
////                running();
//                break;
//            case R.id.homepageRunSettings:
//                startActivity(new Intent(this, RunSettings.class));
//                break;
//            case R.id.homepageRunHistory:
//               // runHistory runHistory = new runHistory();
////                startActivity(new Intent(this, RunHistoryDetails.class));
////                history();
//                break;
//            case R.id.homepageLogMeal:
//                startActivity(new Intent(this, MealLog.class));
//                break;
//            case R.id.homepageAboutLogoutButton:
//                startActivity(new Intent(this, LogoutFragment.class));
//                break;
//        }
//    }


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
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {

    }
    @Override
    public void onProviderDisabled(final String provider) {

    }

//    public void CalenderDate() {
//        Calendar calendar = Calendar.getInstance();
//        HomePageDate.setText(DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime()));
//    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}