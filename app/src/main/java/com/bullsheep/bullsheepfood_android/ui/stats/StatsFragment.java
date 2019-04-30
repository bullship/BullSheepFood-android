package com.bullsheep.bullsheepfood_android.ui.stats;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bullsheep.bullsheepfood_android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        View rootView = inflater.inflate(R.layout.fragment_stats, container, false);
        initUi(rootView);
        return rootView;
    }

    private void initUi(View rootView) {
        RecyclerView statsList = rootView.findViewById(R.id.stats__recycler);
        statsList.setLayoutManager(new LinearLayoutManager(rootView.getContext(), RecyclerView.VERTICAL, false));
        ArrayList<String> statsData = new ArrayList<>();
        // TODO: 30.04.19 Replace with real data
        statsData.add("Monday");
        statsData.add("Tuesday");
        statsList.setAdapter(new StatsAdapter(statsData));
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
