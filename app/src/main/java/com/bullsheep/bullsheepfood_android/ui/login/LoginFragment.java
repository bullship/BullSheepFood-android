package com.bullsheep.bullsheepfood_android.ui.login;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bullsheep.bullsheepfood_android.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * Login Fragment. Used for login via Facebook or Google Auth
 */
public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

}
