package com.lappy.loginapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lappy.loginapp.R;
import com.lappy.loginapp.RegistrationPage;
import com.lappy.loginapp.ResetPassword;
import com.lappy.loginapp.RunHistory;
import com.lappy.loginapp.RunInterface;
import com.lappy.loginapp.RunSettings;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link RunFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class RunFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public RunFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment RunFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static RunFragment newInstance(String param1, String param2) {
//        RunFragment fragment = new RunFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_run, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.runbtn:
                startActivity(new Intent(this, RunInterface.class));
                break;
            case R.id.runHistory:
                startActivity(new Intent(this, RunHistory.class));
                break;
            case R.id.settings:
                startActivity(new Intent(this, RunSettings.class));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }

    }
}