package com.bullsheep.bullsheepfood_android.ui.login;

public interface LoginListener {
    void onFacebookLoginSucceed(String token);

    void onLoginSucceed(String email, String password);
}
