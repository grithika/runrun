package com.lappy.loginapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RunHistoryDetails extends AppCompatActivity {

    RunDatabaseHelper RunDatabase;


    RecyclerView recyclerView;
    TextView EmptyActivity;
    RecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<RunActivityRetriever> arrayList = new ArrayList<>();

    Button returnButton, settings_btn, deleteButton;


    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = recyclerView.findViewById(R.id.RecycleLayout);
        //EmptyActivity = recyclerView.findViewById(R.id.EmptyActivity);
        RunDatabase = new RunDatabaseHelper(this);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        final RunDatabaseHelper RunDatabase = new RunDatabaseHelper(this);
        final SQLiteDatabase sqLiteDatabase = RunDatabase.getWritableDatabase();
        final Cursor cursor = RunDatabase.RetrieveDataFromDatabase(sqLiteDatabase);


        returnButton = recyclerView.findViewById(R.id.backbutton);


        if (cursor.moveToFirst()) {
            do {
                RunActivityRetriever retrieveRunnerActivity = new RunActivityRetriever
                        (cursor.getString(1), cursor.getString(2),
                                (cursor.getString(3)), (cursor.getString(0)));
                arrayList.add(retrieveRunnerActivity);

                EmptyActivity.setVisibility(View.INVISIBLE);
            }
            while
            (cursor.moveToNext());

        } else
            EmptyActivity.setVisibility(View.VISIBLE);

        //RunDatabaseHelper.close();

        //adapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);


        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReturnButtonRunnable returnButtonRunnable = new ReturnButtonRunnable();
                new Thread(returnButtonRunnable).start();
            }

            class ReturnButtonRunnable implements Runnable {
                @Override
                public void run() {
                    returnButton.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(RunHistoryDetails.this, HomepageActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });

        settings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsBtnRunnable settingsBtnRunnable = new SettingsBtnRunnable();
                new Thread(settingsBtnRunnable).start();

            }

            class SettingsBtnRunnable implements Runnable {
                @Override
                public void run() {
                    settings_btn.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(RunHistoryDetails.this, RunSettings.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });

//        adapter.setItemListener(new RecyclerAdapter.OnItemClickListener() {
//            @Override
//            public void onDeleteClick(int position) {
//            }
//
//            @Override
//            public void onDelete(int position) {
//                deleteItems(position);
//            }
//
//            private void deleteItems(int position) {
//                arrayList.remove(position);
//                adapter.notifyItemRemoved(position);
//            }
//        });

    }
}