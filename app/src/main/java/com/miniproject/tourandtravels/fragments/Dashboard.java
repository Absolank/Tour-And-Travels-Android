package com.miniproject.tourandtravels.fragments;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

public class Dashboard extends Fragment {
    private SharedPreferences sharedPreferences;

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }
}
