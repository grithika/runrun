package com.lappy.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class RunHistory extends AppCompatActivity {

    TextView durationColumn, distanceColumn, dateHeading;
    RunDatabaseHelper RunDatabase;
    Button DeleteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_history);

        durationColumn = findViewById(R.id.runHistoryTimeDisplay);
        distanceColumn = findViewById(R.id.runHistoryDistanceDisplay);
        dateHeading = findViewById(R.id.dateDetails);
        DeleteButton = findViewById(R.id.deleteButton);

        DeleteButton.setOnClickListener(view -> {
            RunDatabase.DeleteData(durationColumn.getText().toString());
            RunDatabase.DeleteData(RunDatabaseHelper.TABLE_NAME);
        });


    }
}