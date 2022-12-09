package com.lappy.loginapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MealLogAdapter extends BaseAdapter {
    Context context;
    ArrayList<Meals> arrayList;

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.meal_history_layout, null);
        ImageView MealHistoryImage = convertView.findViewById(R.id.mealHistoryImage);
        TextView MealHistoryDescription = convertView.findViewById(R.id.mealHistoryDescription);
        Meals MealHistoryMeals = arrayList.get(position);
        String description = MealHistoryMeals.getDescription();
        byte[] image = MealHistoryMeals.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        MealHistoryDescription.setText(description);
        MealHistoryImage.setImageBitmap(bitmap);
        return convertView;
    }
}
