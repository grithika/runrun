package com.lappy.loginapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MealLogDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "DailyMealLog";
    public static final int DB_VERSION = 1;
    public static final String TABLES_NAME = "MealsInfo";
    public static final String COL_ID = "ID";
    public static final String COL_DATE = "Date";
    public static final String COL_DES = "Description";
    public static final String COL_IMG = "Photo";

    public MealLogDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLES_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, date String, Description String, Photo BLOG)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLES_NAME);
        onCreate(db);
    }
}
