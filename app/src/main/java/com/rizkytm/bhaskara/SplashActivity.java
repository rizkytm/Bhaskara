package com.rizkytm.bhaskara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    private final android.os.Handler waitHandler = new android.os.Handler();
    private final Runnable waitCallback = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SplashActivity.this, Main2Activity.class));
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();

        waitHandler.postDelayed(waitCallback, 3000);
    }

    @Override
    protected void onDestroy() {
        waitHandler.removeCallbacks(waitCallback);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

    }
}
