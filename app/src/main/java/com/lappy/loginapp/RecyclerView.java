package com.lappy.loginapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class RecyclerView extends AppCompatActivity {

    private RecyclerAdapter recyclerAdapter;
    private SQLiteDatabase sqLiteDatabase;
    private TextView emptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
    }

    public class Adapter<T> {
    }
}