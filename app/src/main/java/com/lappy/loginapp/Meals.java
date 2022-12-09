package com.lappy.loginapp;

public class Meals {
    int MealID;
    String MealDate, MealDescription;
    byte[] MealImage;

    public Meals(int ID, String date, String description, byte[] image) {
        this.MealID = ID;
        MealDate = date;
        MealDescription = description;
        MealImage = image;
    }

    public int getID() {
        return MealID;
    }

    public void setID(int ID) {
        this.MealID = ID;
    }

    public String getDate() {
        return MealDate;
    }

    public void setDate(String date) {
        MealDate = date;
    }

    public String getDescription() {
        return MealDescription;
    }

    public void setDescription(String description) {
        MealDescription = description;
    }

    public byte[] getImage() {
        return MealImage;
    }

    public void setImage(byte[] image) {
        MealImage = image;
    }
}
