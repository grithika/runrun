package com.lappy.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.LocationListener;
import com.google.android.material.tabs.TabLayout;

public class Homepage extends AppCompatActivity implements LocationListener {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    HomePageAdapter homePageAdapter;

    private static final int PermissionCode = 58;

    LocationManager locationManager;
    Button StartRunButton, RunHistoryButton, SettingsButton;
    TextView HomepageDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        tabLayout = findViewById(R.id.tablayout);
        viewPager2 = findViewById(R.id.viewpager);
        homePageAdapter = new HomePageAdapter(this);
        viewPager2.setAdapter(homePageAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

        StartRunButton = findViewById(R.id.runbtn);
        RunHistoryButton = findViewById(R.id.runHistory);
        SettingsButton = findViewById(R.id.settings);

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}