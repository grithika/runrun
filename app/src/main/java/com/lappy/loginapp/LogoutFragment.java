package com.lappy.loginapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class LogoutFragment extends Fragment implements View.OnClickListener {
    Button logout_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_logout, container, false);
       // logout_button = (Button) v.findViewById(R.id.logoutButton);
        //logout_button.setOnClickListener(this);
        return v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logoutButton:
                Toast.makeText(getActivity(), "Long pressing", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}