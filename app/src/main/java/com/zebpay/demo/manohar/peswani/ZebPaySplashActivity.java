package com.zebpay.demo.manohar.peswani;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class ZebPaySplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(() ->
                startActivity(new Intent(ZebPaySplashActivity.this, MainActivity.class)), 3000);
        if (getActionBar() != null) {
            getActionBar().hide();
        }
    }
}
