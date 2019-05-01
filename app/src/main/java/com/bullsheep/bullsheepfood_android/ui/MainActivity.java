package com.bullsheep.bullsheepfood_android.ui;

import android.os.Bundle;

import com.bullsheep.bullsheepfood_android.R;
import com.bullsheep.bullsheepfood_android.ui.stats.StatsFragment;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // last visible fragment is always retained. if there are no such - show main fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.fragment_container, new StatsFragment())
                    .commit();
        }
    }
}
