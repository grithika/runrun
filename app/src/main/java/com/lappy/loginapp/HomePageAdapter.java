package com.lappy.loginapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.lappy.loginapp.fragments.MealLogFragment;
import com.lappy.loginapp.fragments.RunFragment;

public class HomePageAdapter extends FragmentStateAdapter {
    public HomePageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return new RunFragment();
            case 1:
                return new MealLogFragment();
            default:
                return new RunFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
