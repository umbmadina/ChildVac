package com.example.madina.childvac;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent authIntent = new Intent(IntroActivity.this, AuthActivity.class);
                startActivity(authIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
