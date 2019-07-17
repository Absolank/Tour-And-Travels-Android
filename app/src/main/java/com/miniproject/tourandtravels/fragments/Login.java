package com.miniproject.tourandtravels.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.miniproject.tourandtravels.LoginActivity;
import com.miniproject.tourandtravels.NavigationActivity;
import com.miniproject.tourandtravels.R;
import com.miniproject.tourandtravels.api.ResponseCallback;
import com.miniproject.tourandtravels.api.TourAndTravelsRepository;
import com.miniproject.tourandtravels.api.model.TokenResponse;
import com.miniproject.tourandtravels.views.Admin;

import static android.content.Context.MODE_PRIVATE;

public class Login extends Fragment {
    private TextView username, password;
    private Button login;
    private AppCompatActivity activity;
    private SharedPreferences sharedPreferences;

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private TourAndTravelsRepository repository;
    private ResponseCallback responseCallback;
    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void setApplication(AppCompatActivity activity, ResponseCallback responseCallback){
        this.activity = activity;
        this.responseCallback = responseCallback;
    }

    private void showInvalidLoginDetails(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.login, container, false);


        Button button = view.findViewById(R.id.create_new_account);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                responseCallback.callback(null);
            }
        });

        mEmailView = (AutoCompleteTextView) view.findViewById(R.id.email);

        mPasswordView = (EditText) view.findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) view.findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = view.findViewById(R.id.login_form);
        mProgressView = view.findViewById(R.id.login_progress);
        sharedPreferences = activity.getSharedPreferences("login-data", MODE_PRIVATE);
        String token = sharedPreferences.getString("Token", "");
        System.out.println(token);
        repository = new TourAndTravelsRepository();
        showProgress(true);
        repository.verifyToken(token, tokenResponse -> {
            TokenResponse rs = (TokenResponse)tokenResponse;
            if(rs != null)
            if(rs.STATUS == 200) {
                if(rs.TYPE.equals("admin")){
                    startAdminAct();
                }
                else {
                    startMainAct();
                }
            }
            showProgress(false);
        });

        return view;
    }
    private void startMainAct(){
        Intent intent = new Intent(getContext(), NavigationActivity.class);
        startActivity(intent);
        activity.finish();
    }
    private void startAdminAct(){
        Intent intent = new Intent(getContext(), Admin.class);
        startActivity(intent);
        activity.finish();
    }

    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);


            TourAndTravelsRepository repository = new TourAndTravelsRepository();
            repository.fetchAndSaveLoginToken(email, password, sharedPreferences, new ResponseCallback() {
                @Override
                public void callback(Object param) {
                    showProgress(false);
                    boolean status = (boolean)param;
                    if(status){
                        startMainAct();
                    }
                    else{
                        mPasswordView.setError(getString(R.string.error_incorrect_password));
                        mPasswordView.requestFocus();
                    }
                }
            });

        }
    }
    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}
