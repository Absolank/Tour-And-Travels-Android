package com.miniproject.tourandtravels;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.miniproject.tourandtravels.api.ResponseCallback;
import com.miniproject.tourandtravels.api.TourAndTravelsRepository;
import com.miniproject.tourandtravels.api.model.TokenResponse;
import com.miniproject.tourandtravels.api.model.User;
import com.miniproject.tourandtravels.fragments.CreateAccountFragment;
import com.miniproject.tourandtravels.fragments.Dashboard;
import com.miniproject.tourandtravels.fragments.Login;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{
    private Login login = new Login();
    private CreateAccountFragment accountFragment = new CreateAccountFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");
        login.setApplication(this, new ResponseCallback() {
            @Override
            public void callback(Object param) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, accountFragment).commit();
            }
        });
        accountFragment.setResponseCallback(new ResponseCallback() {
            @Override
            public void callback(Object param) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, login).commit();
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, login).commit();
    }
}

