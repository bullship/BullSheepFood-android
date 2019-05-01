package com.bullsheep.bullsheepfood_android.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.bullsheep.bullsheepfood_android.R;
import com.bullsheep.bullsheepfood_android.ui.login.LoginFragment;
import com.bullsheep.bullsheepfood_android.ui.login.LoginListener;
import com.bullsheep.bullsheepfood_android.ui.ration.RationFragment;
import com.bullsheep.bullsheepfood_android.ui.stats.StatsFragment;
import com.bullsheep.bullsheepfood_android.ui.utils.ActivityUtils;
import com.bullsheep.bullsheepfood_android.ui.stats.actions.ActionDialogFragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements LoginListener {
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // last visible fragment is always retained. if there are no such - show main fragment
        if (savedInstanceState == null) {
            navigateToFragment(new StatsFragment());
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
                navigateToFragment(new RationFragment());
                break;
            case R.id.nav_stats:
                navigateToFragment(new StatsFragment());
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void navigateToFragment(Fragment fragment) {
        if (currentFragment == null || !fragment.getClass().equals(currentFragment.getClass())) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.fragment_container, new LoginFragment())
                    .commit();
        }
    }

    @Override
    public void onFacebookLoginSucceed(String token) {
        // TODO: 01.05.19 Send to backend via ViewModel
        setMainFragment();
    }

    @Override
    public void onLoginSucceed(String email, String password) {
        // TODO: 01.05.19 Send to backend via ViewModel
        ActivityUtils.hideKeyboard(this);
        setMainFragment();
    }

    private void setMainFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                .replace(R.id.fragment_container, new StatsFragment())
                .commit();
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
            currentFragment = fragment;
        }
    }
}
