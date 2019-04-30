package com.bullsheep.bullsheepfood_android.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.bullsheep.bullsheepfood_android.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new StatsFragment())
                    .commit();
        }
    }
}
