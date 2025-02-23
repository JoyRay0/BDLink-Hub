package com.rk_softwares.bdlinkhub.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.rk_softwares.bdlinkhub.R;


public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){

            navigate_main();


        }else {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    navigate_main();
                }
            },1000);

        }



    }

    private void navigate_main(){

        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finishAffinity();

    }
}