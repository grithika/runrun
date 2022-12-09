package com.lappy.loginapp;

public class Meals {
    int ID;
    String Date, Description;
    byte[] Image;

    public Meals(int ID, String date, String description, byte[] image) {
        this.ID = ID;
        Date = date;
        Description = description;
        Image = image;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }
}
