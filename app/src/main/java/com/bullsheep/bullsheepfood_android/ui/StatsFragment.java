package com.bullsheep.bullsheepfood_android.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bullsheep.bullsheepfood_android.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

/**
 * Main and first screen. Shows statistics, such as: charts with kcal consumed for every single day,
 * amount of proteins, fats and carbs.
 */
public class StatsFragment extends Fragment {


    public StatsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
        AppCompatActivity currentActivity = (AppCompatActivity) getActivity();
        if (currentActivity != null) {
            currentActivity.setSupportActionBar((Toolbar) rootView.findViewById(R.id.bottom_app_bar));
        }
    }
}
