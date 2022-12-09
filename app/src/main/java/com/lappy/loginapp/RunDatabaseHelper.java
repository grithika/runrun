package com.lappy.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RunDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Run_Values.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "userRunValues";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TimeElapsed";
    public static final String COL_3 = "RunDistance";
    public static final String ROW_1 = "Date";
    public RunDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (Id INTEGER PRIMARY KEY AUTOINCREMENT, Elapsed_Time INTEGER,  Total_Distance INTEGER, Date String)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String elapsed_time, String total_distance, String current_date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ROW_1, current_date);
        contentValues.put(COL_2, elapsed_time);
        contentValues.put(COL_3, total_distance);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Cursor RetrieveDataFromDatabase(SQLiteDatabase sqLiteDatabase) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        return cursor;
    }

    public boolean updateData(String elapsed_time, String total_distance, String current_date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ROW_1, current_date);
        contentValues.put(COL_2, elapsed_time);
        contentValues.put(COL_3, total_distance);
        db.update(TABLE_NAME, contentValues, "elapsed_time = ?", new String[]{elapsed_time});
        return true;
    }

    public void DeleteData(String Id) {
        this.getWritableDatabase().delete(TABLE_NAME, "Id ='" + Id + "'", null);
    }
}
