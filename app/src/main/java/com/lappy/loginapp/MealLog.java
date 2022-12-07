package com.lappy.loginapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class MealLog extends AppCompatActivity {
    MealLogDatabaseHelper mealLogDB;
    ImageView logImage;

    private final int IMAGE_REQ_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_log);
        mealLogDB = new MealLogDatabaseHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        LayoutInflater inflater = (LayoutInflater) MealLog.this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.meal_add_layout, null);
        EditText logDate = view.findViewById(R.id.DateEditText);
        EditText logDescription = view.findViewById(R.id.DescptionEditText);
        logImage = view.findViewById(R.id.mealLogImageView);
        logImage.setOnClickListener((new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                boolean pick = true;
                if (pick == true){
                    if (!checkCameraPermission()){
                        requestCameraPermission();
                    }
                    else PickImage();
                }
                else{
                    if (!checkStoragePermission()){
                        requestStoragePermission();
                    }
                    else PickImage();
                }
            }
        }));
        AlertDialog.Builder builder = new AlertDialog.Builder(MealLog.this);
        builder.setView(view)
                .setTitle("Adding new log")
                .setMessage("Enter date and description")
                .setPositiveButton("Add new log", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String date = logDate.getText().toString();
                        String description = logDescription.toString();
                      boolean res =  mealLogDB .insertMealLogData(date, description, ImageToByte(logImage));
                      if (res == true){
                          Toast.makeText(MealLog.this, "New log added!", Toast.LENGTH_SHORT).show();
                      }
                      else{
                          Toast.makeText(MealLog.this, "Couldn't be added please try again!", Toast.LENGTH_SHORT).show();
                      }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.create().show();
        return super.onOptionsItemSelected(item);
    }

    private void PickImage() {
    }

    private Object ImageToByte(ImageView logImage) {
        Bitmap bitmap = ((BitmapDrawable) logImage.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestStoragePermission() {
        requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestCameraPermission() {
        requestPermissions(new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
            try {
                InputStream stream = getContentResolver().openInputStream(resultUri);
                Bitmap bitmap = BitmapFactory.decodeStream(stream);
                logImage.setImageBitmap(bitmap);
            }
            catch (Exception e){
                e.printStackTrace();
            }
//            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                Exception error = result.getError();
//            }
        }
    }

    private boolean checkStoragePermission() {
        boolean res1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        return res1;
    }

    private boolean checkCameraPermission() {
        boolean res1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        boolean res2 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        return res1 && res2;
    }
}