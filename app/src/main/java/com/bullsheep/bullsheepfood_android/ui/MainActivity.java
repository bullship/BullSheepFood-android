package com.bullsheep.bullsheepfood_android.ui;

import android.os.Bundle;

import com.bullsheep.bullsheepfood_android.R;
import com.bullsheep.bullsheepfood_android.ui.login.LoginFragment;
import com.bullsheep.bullsheepfood_android.ui.login.LoginListener;
import com.bullsheep.bullsheepfood_android.ui.stats.StatsFragment;
import com.bullsheep.bullsheepfood_android.ui.utils.ActivityUtils;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements LoginListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // last visible fragment is always retained. if there are no such - show main fragment
        if (savedInstanceState == null) {
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
    }
}
