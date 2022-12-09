package com.lappy.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MealLogDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "DailyMealLog";
    public static final int DB_VERSION = 1;
    public static final String TABLES_NAME = "MealsInfo";
    public static final String COL_ID = "ID";
    public static final String COL_DATE = "Date";
    public static final String COL_DES = "Description";
    public static final String COL_IMAGE = "Image";

    public MealLogDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLES_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Date String, Description String, Image BLOG)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLES_NAME);
        onCreate(db);
    }
    public  boolean insertMealLogData(String Date, String Description, byte[] Image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_DATE, Date);
        contentValues.put(COL_DES, Description);
        contentValues.put(COL_IMAGE, Image);
        long res = db.insert(TABLES_NAME, null, contentValues);
        if (res == -1) return false;
        else return true;
    }
    public ArrayList<Meals> getMealData (){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Meals> arrayList = new ArrayList<Meals>();
        Cursor cursor = db.rawQuery("select * from " + TABLES_NAME, null);
        while (cursor.moveToNext()){
            int ID = cursor.getInt(0);
            String Date = cursor.getString(1);
            String Description = cursor.getString(2);
            byte[] Image = cursor.getBlob(3);
            Meals meals = new Meals(ID, Date, Description, Image);
            arrayList.add(meals);
        }
        return arrayList;
    }
}
