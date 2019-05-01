package com.bullsheep.bullsheepfood_android.ui.stats;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bullsheep.bullsheepfood_android.R;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
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
        View rootView = inflater.inflate(R.layout.fragment_stats, container, false);
        setupRecycler(rootView);
        return rootView;
    }

    private void setupRecycler(View rootView) {
        RecyclerView statsList = rootView.findViewById(R.id.stats__recycler);
        statsList.setLayoutManager(new LinearLayoutManager(rootView.getContext(), RecyclerView.VERTICAL, false));

        // TODO: 30.04.19 Replace with real data(object with day & statistics)
        String[] daysOfWeek = rootView.getResources().getStringArray(R.array.days_of_week);
        StatsAdapter adapter = new StatsAdapter(Arrays.asList(daysOfWeek));
        adapter.setHasStableIds(true);
        statsList.setAdapter(adapter);

        statsList.addItemDecoration(new DividerItemDecoration(rootView.getContext(), RecyclerView.VERTICAL));
    }

}
