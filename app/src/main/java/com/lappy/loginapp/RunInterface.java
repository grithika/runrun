package com.lappy.loginapp;

import static com.lappy.loginapp.RunSettings.GetInfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Locale;

public class RunInterface extends AppCompatActivity implements com.google.android.gms.location.LocationListener, android.location.LocationListener {

    RunDatabaseHelper Run_database;

    FusedLocationProviderClient fusionprovider;
    LocationManager locationManager;
    LocationRequest locationRequest;
    Location start_location, end_location, current_location;

    TextView distanceCounter, speedInmph, speedInkmph, countdownTimerView;
    Button resumeButton, pauseButton, stopButton;

    Chronometer timer;
    CountDownTimer countDownTimer;

    static final long StartTime = 11000;
    long TimeLeft = StartTime;

    boolean active;
    long update;
    double distance = 0;
    double current_speed;

    LinearLayout distanceButtonGroup;
//    RadioButton milesButton, kilometerButton;

    private static final int AccessCode = 48;
    String current_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_interface);

        Run_database = new RunDatabaseHelper(this);

        distanceCounter = findViewById(R.id.runInterfaceDistanceCounter);
        resumeButton = findViewById(R.id.runInterfaceResumeButton);
        pauseButton = findViewById(R.id.runInterfacePauseButton);
        stopButton = findViewById(R.id.runInterfaceStopButton);
        speedInmph = findViewById(R.id.runInterfaceSpeedInmph);
        speedInkmph = findViewById(R.id.runInterfaceSpeedInkmph);
        timer = findViewById(R.id.runInterfaceTimer);
        countdownTimerView = findViewById(R.id.runInterfaceCountdownTimerView);
        distanceButtonGroup = findViewById(R.id.runInterfaceDistanceGroup);
//        milesButton = findViewById(R.id.MilesButton);
//        kilometerButton = findViewById(R.id.KMButton);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        fusionprovider = LocationServices.getFusedLocationProviderClient(RunInterface.this);

        CountDownSwitch();


        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if ((SystemClock.elapsedRealtime() - timer.getBase()) >= 86400000) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                }
            }
        });

        resumeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ResumeRunnable resumeRunnable = new ResumeRunnable();
                new Thread(resumeRunnable).start();
            }


            class ResumeRunnable implements Runnable {
                @Override
                public void run() {
                    resumeButton.post(() -> {
                        if (!active) {
                            if (ContextCompat.checkSelfPermission(RunInterface.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                                    && ContextCompat.checkSelfPermission(RunInterface.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                                    && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                                    && ContextCompat.checkSelfPermission(RunInterface.this,
                                    Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, (float) 0, (LocationListener) RunInterface.this);
                                createLocationRequest();
                                timer.setBase(SystemClock.elapsedRealtime() - update);
                                timer.start();
                                resumeButton.setVisibility(View.GONE);
                                pauseButton.setVisibility(View.VISIBLE);
                                active = true;
                            } else {
                                RequestPermissions();
                                {
                                }
                                resumeButton.setVisibility(View.VISIBLE);
                                active = false;
                            }
                        }
                    });
                    pauseButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PauseRunnable pauseRunnable = new PauseRunnable();
                            new Thread(pauseRunnable).start();
                        }

                        class PauseRunnable implements Runnable {
                            @Override
                            public void run() {
                                pauseButton.post(() -> {
                                    if (active) {
                                        timer.stop();
                                        active = false;
                                        resumeButton.setVisibility(View.VISIBLE);
                                        update = SystemClock.elapsedRealtime() - timer.getBase();
                                        locationManager.removeUpdates((LocationListener) RunInterface.this);

                                    }
                                });
                            }
                        }
                    });


                    stopButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            StopRunnable stopRunnable = new StopRunnable();
                            new Thread(stopRunnable).start();
                        }

                        class StopRunnable implements Runnable {
                            @Override
                            public void run() {
                                stopButton.post(RunInterface.this::SaveData);
                            }
                        }
                    });
                }
            }
        });
    }

    private void RequestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(RunInterface.this, Manifest.permission.ACCESS_FINE_LOCATION)) {

            new AlertDialog.Builder(this)
                    .setTitle("Location Permission Required")
                    .setMessage("Please allow permission access to proceed.")
                    .setPositiveButton("Accept", (dialog, id) -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            ActivityCompat.requestPermissions(RunInterface.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                            Manifest.permission.ACCESS_COARSE_LOCATION,
                                            Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                                            Manifest.permission.READ_PHONE_STATE}, AccessCode);
                        }
                        ActivityCompat.requestPermissions(RunInterface.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.ACCESS_COARSE_LOCATION,
                                        Manifest.permission.READ_PHONE_STATE}, AccessCode);
                    })
                    .setNegativeButton("cancel", (dialog, which) -> dialog.dismiss())
                    .create().show();

        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                                Manifest.permission.READ_PHONE_STATE}, AccessCode);
            }
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.READ_PHONE_STATE}, AccessCode);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == AccessCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LocationCall();
//                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }

        }

    }

    private void createLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    public void LocationCall() {
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
            dialogbuilder.setMessage(" Enable GPS To Continue")
                    .setPositiveButton("Turn location on", (dialog, which) -> {
                        Intent call_gps_settings = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(call_gps_settings);
                    })
                    .setNegativeButton("Cancel", (dialog, which) -> {

                    });
            AlertDialog alertDialog = dialogbuilder.create();
            alertDialog.show();
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        current_location = location;
        if (start_location == null) {
            start_location = current_location;
            end_location = current_location;
        }
        else
            end_location = current_location;

        ChooseMetricUnits();

        current_speed = location.getSpeed() * 2.236936;
        speedInmph.setText(new DecimalFormat("0.00").format(current_speed));

        current_speed = location.getSpeed() * 3.6;
        speedInkmph.setText(new DecimalFormat("0.00").format(current_speed));

    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }


    @Override
    public void onProviderDisabled(String provider) {

    }


    private void distanceInMiles() {
        String miles = "mi";
        SpannableStringBuilder builder = new SpannableStringBuilder();
        SpannableString spannableString = new SpannableString(miles);
        spannableString.setSpan(new RelativeSizeSpan(0.50f), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        distance = distance + (start_location.distanceTo(end_location) * 0.00062137);
        start_location = end_location;
        builder.append(new DecimalFormat("0.00 ").format(distance));
        builder.append(spannableString);
        distanceCounter.setText(builder);


    }

    private void distanceInkilometers() {
        String kilometers = "km";
        SpannableStringBuilder builder = new SpannableStringBuilder();
        SpannableString spannableString = new SpannableString(kilometers);
        spannableString.setSpan(new RelativeSizeSpan(0.50f), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        distance = distance + (start_location.distanceTo(end_location) / 1000);
        start_location = end_location;
        builder.append(new DecimalFormat("0.00 ").format(distance));
        builder.append(spannableString);
        distanceCounter.setText(builder);

    }

    private void ChooseMetricUnits() {
        final SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(GetInfo, MODE_PRIVATE);
        final boolean MetricUnit = sharedPreferences.getBoolean(RunSettings.km_Button, true);
        if (MetricUnit) {
            distanceInkilometers();
        }
        else
            distanceInMiles();
    }
    private void StartCountDown() {
        countDownTimer = new CountDownTimer(TimeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                resumeButton.setEnabled(false);
                //resumeButton.setBackgroundResource(R.drawable.disabled_button_resource);

                pauseButton.setEnabled(false);
                //pauseButton.setBackgroundResource(R.drawable.disabled_button_resource);

                stopButton.setEnabled(false);
                //stopButton.setBackgroundResource(R.drawable.disabled_button_resource);

                TimeLeft = millisUntilFinished;
                CountDownViewUpdate();

            }


            @Override
            public void onFinish() {
                active = false;
                AutoStartActivity();
                countdownTimerView.setVisibility(View.GONE);

                resumeButton.setEnabled(true);
                //resumeButton.setBackgroundResource(R.drawable.resume_button);

                pauseButton.setEnabled(true);
                //pauseButton.setBackgroundResource(R.drawable.resume_button);

                stopButton.setEnabled(true);
                //stopButton.setBackgroundResource(R.drawable.resume_button);

            }
        }.start();
    }


    private void CountDownViewUpdate() {
        int min = (int) (TimeLeft / 1000) / 60;
        int sec = (int) (TimeLeft / 1000) % 60;

        String TimeLeftFormat = String.format(Locale.getDefault(), "%02d:%02d", min, sec);

        countdownTimerView.setText(TimeLeftFormat);

    }

    private void CountDownSwitch() {
        final SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(GetInfo, MODE_PRIVATE);
        final boolean TimerSwitch = sharedPreferences.getBoolean(String.valueOf(RunSettings.class), false);
        if (TimerSwitch) {
            StartCountDown();
        } else
            countdownTimerView.setVisibility(View.GONE);

    }


    private void AutoStartActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            resumeButton.performContextClick();
        }
        resumeButton = findViewById(R.id.runInterfaceResumeButton);
        resumeButton.performClick();

    }


    private void SaveData() {
        new AlertDialog.Builder(RunInterface.this)
                .setTitle("Save Activity To History")
                .setMessage("Would you like to save your activity?.")
                .setPositiveButton("Yes", (dialog, id) -> {
                    CalenderDate();
                    boolean dataSaved = Run_database.insertData(timer.getText().toString(), distanceCounter.getText().toString(), current_date);
                    boolean dataUpdate = Run_database.updateData(timer.getText().toString(), distanceCounter.getText().toString(), current_date);
                    if (dataSaved && dataUpdate)
                        Toast.makeText(RunInterface.this, "Activity Saved To History", Toast.LENGTH_LONG).show();
                    StopActivity();


                })
                .setNegativeButton("No", (dialog, which) -> {
                    dialog.dismiss();
                    StopActivity();

                })
                .create().show();

    }

    public void CalenderDate() {
        Calendar calendar = Calendar.getInstance();
        current_date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

    }

    public void StopActivity() {
        start_location = null;
        end_location = null;
        distance = 0;
        current_speed = 0;
        distanceCounter.setText(new DecimalFormat("0.00").format(distance));
        timer.setBase(SystemClock.elapsedRealtime());
        locationManager.removeUpdates((LocationListener) RunInterface.this);
        timer.stop();
        active = false;
        update = 0;
        resumeButton.setVisibility(View.VISIBLE);
        speedInmph.setText(new DecimalFormat("0.00").format(current_speed));
        speedInkmph.setText(new DecimalFormat("0.00").format(current_speed));


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), HomepageActivity.class);
        startActivity(intent);
        finish();
    }
}