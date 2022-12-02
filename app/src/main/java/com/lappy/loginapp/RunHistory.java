package com.lappy.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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

        durationColumn = (TextView) findViewById(R.id.timeDisplay);
        distanceColumn = (TextView) findViewById(R.id.distanceDisplay);
        dateHeading = (TextView) findViewById(R.id.dateDetails);
        DeleteButton = (Button) findViewById(R.id.deleteButton);

        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RunDatabase.DeleteData(durationColumn.getText().toString());
                RunDatabase.DeleteData(RunDatabaseHelper.TABLE_NAME);
            }
        });


    }
}