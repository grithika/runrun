package com.lappy.loginapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    Button deleteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = recyclerView.findViewById(R.id.recyclerViewRecycleLayout);
        EmptyActivity = recyclerView.findViewById(R.id.recyclerViewEmptyActivity);
        deleteButton = recyclerView.findViewById(R.id.runHistoryDeleteButton);
        RunDatabase = new RunDatabaseHelper(this);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        final RunDatabaseHelper RunDatabase = new RunDatabaseHelper(this);
        final SQLiteDatabase sqLiteDatabase = RunDatabase.getWritableDatabase();
        final Cursor cursor = RunDatabase.RetrieveDataFromDatabase(sqLiteDatabase);



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

        RunDatabase.close();

        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);

        adapter.setItemListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                deteleItems(position);
            }

            private void deteleItems(int position) {
                arrayList.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });

    }
}