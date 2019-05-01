package com.bullsheep.bullsheepfood_android.ui.login;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.bullsheep.bullsheepfood_android.R;
import com.bullsheep.bullsheepfood_android.data.ApiFactory;
import com.bullsheep.bullsheepfood_android.data.ProductService;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Login Fragment. Used for login via Facebook or Google Auth
 */
public class LoginFragment extends Fragment {
    private static final String TAG = "LoginFragment";

    private CallbackManager callbackManager;
    private LoginButton facebookLoginButton;

    private TextInputEditText loginEt;
    private TextInputEditText passwordEt;
    private ProgressBar loadingIndicator;

    private LoginListener loginListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof LoginListener) {
            this.loginListener = (LoginListener) context;
        } else {
            throw new IllegalStateException("Activity should implement LoginListener interface");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        callbackManager = CallbackManager.Factory.create();
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        ProductService apiFactory = ApiFactory.getProductService();

        initUi(rootView);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        callbackManager = CallbackManager.Factory.create();

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null && !accessToken.isExpired()) {
            loginListener.onFacebookLoginSucceed(accessToken.getToken());
        }
    }

    private void initUi(View rootView) {
        loginEt = rootView.findViewById(R.id.login_et);
        passwordEt = rootView.findViewById(R.id.password_et);
        loadingIndicator = rootView.findViewById(R.id.loading_indicator);
        MaterialButton submitButton = rootView.findViewById(R.id.login_submit_btn);
        submitButton.setOnClickListener(view -> login());
        facebookLoginButton = rootView.findViewById(R.id.fb_login_button);
        setupFacebookLogin();
    }

    private void login() {
        if (!isEmpty(passwordEt, loginEt)) {
            loadingIndicator.setVisibility(View.VISIBLE);
            loginListener.onLoginSucceed(loginEt.getText().toString(), passwordEt.getText().toString());
        }
    }

    private void setupFacebookLogin() {
        facebookLoginButton.setReadPermissions("email");
        facebookLoginButton.setFragment(this);
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "onSuccess: " + loginResult.getAccessToken().getToken());
                loginListener.onFacebookLoginSucceed(null);
            }

            @Override
            public void onCancel() {
                Log.e(TAG, "onCancel: check permissions");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG, "onError: ", error);
            }
        });
    }

    private boolean isEmpty(@NonNull EditText... editTexts) {
        boolean isEmpty = false;
        for (EditText editText : editTexts) {
            if (editText.getText().toString().trim().length() == 0) {
                editText.setError(getActivity().getString(R.string.error_field_required));
                isEmpty = true;
            }
        }
        return isEmpty;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        loginListener = null;
    }
}
