package com.bullsheep.bullsheepfood_android.ui.stats;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bullsheep.bullsheepfood_android.R;
import com.bullsheep.bullsheepfood_android.ui.stats.actions.ActionDialogFragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_stats, container, false);
        initUi(rootView);
        return rootView;
    }

    private void initUi(View rootView) {
        setupRecycler(rootView);

        FloatingActionButton addButton = rootView.findViewById(R.id.add_fab);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View currentView) {
                ActionDialogFragment actionDialogFragment = new ActionDialogFragment();
                actionDialogFragment.show(getChildFragmentManager(), null);
            }
        });
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

    @Override
    public void onViewCreated(@NonNull View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
        AppCompatActivity currentActivity = (AppCompatActivity) getActivity();
        if (currentActivity != null) {
            BottomAppBar bottomAppBar = rootView.findViewById(R.id.bottom_app_bar);
            currentActivity.setSupportActionBar(bottomAppBar);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.nav_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // TODO: 30.04.19 Implement transitions to screens after it will be finished
        return super.onOptionsItemSelected(item);
    }
}
