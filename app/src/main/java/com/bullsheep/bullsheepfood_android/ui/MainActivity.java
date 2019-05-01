package com.bullsheep.bullsheepfood_android.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.bullsheep.bullsheepfood_android.R;
import com.bullsheep.bullsheepfood_android.ui.login.LoginListener;
import com.bullsheep.bullsheepfood_android.ui.ration.RationFragment;
import com.bullsheep.bullsheepfood_android.ui.stats.StatsFragment;
import com.bullsheep.bullsheepfood_android.ui.stats.actions.ActionDialogFragment;
import com.bullsheep.bullsheepfood_android.ui.utils.ActivityUtils;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements LoginListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // last visible fragment is always retained. if there are no such - show main fragment
        if (savedInstanceState == null) {
            navigateToFragment(new StatsFragment(), false);
        }
        initUi();
    }

    private void initUi() {
        FloatingActionButton addButton = findViewById(R.id.add_fab);
        addButton.setOnClickListener(currentView -> {
            ActionDialogFragment actionDialogFragment = new ActionDialogFragment();
            actionDialogFragment.show(getSupportFragmentManager(), null);
        });

        BottomAppBar bottomAppBar = findViewById(R.id.bottom_app_bar);
        setSupportActionBar(bottomAppBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_ration:
                navigateToFragment(new RationFragment(), true);
                break;
            case R.id.nav_stats:
                navigateToFragment(new StatsFragment(), true);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void navigateToFragment(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                .replace(R.id.fragment_container, fragment);

        if (addToBackstack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();

    }

    @Override
    public void onFacebookLoginSucceed(String token) {
        // TODO: 01.05.19 Send to backend via ViewModel
        navigateToFragment(new StatsFragment(), false);
    }

    @Override
    public void onLoginSucceed(String email, String password) {
        // TODO: 01.05.19 Send to backend via ViewModel
        ActivityUtils.hideKeyboard(this);
        navigateToFragment(new StatsFragment(), false);
    }
}
