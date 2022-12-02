package com.lappy.loginapp;

import android.widget.EditText;

public class User {

    public String firstName, lastName, age, email;

    //public User(EditText firstname, EditText lastname, EditText age1, EditText emailid){}
    public User(){

    }
    public User(String firstName, String lastName, String age, String email){
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
        this.email=email;

    }

}
