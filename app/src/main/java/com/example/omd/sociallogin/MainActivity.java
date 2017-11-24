package com.example.omd.sociallogin;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LoginButton login_button;
    private CallbackManager callbackManager;
    private TextView details;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        details = (TextView) findViewById(R.id.details);
        login_button = (LoginButton) findViewById(R.id.login_button);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        callbackManager = CallbackManager.Factory.create();
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken  token = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();
                if (profile!=null)
                {
                    details.setText("Welcome "+profile.getName().toString());
                }

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn)
        {
            String lang = "ar";
            Locale locale = new Locale(lang);
            locale.setDefault(locale);
            Configuration confg = new Configuration();
            confg.locale = locale;
            getBaseContext().getResources().updateConfiguration(confg,getBaseContext().getResources().getDisplayMetrics());
            this.setContentView(R.layout.activity_main);


        }

    }
}
