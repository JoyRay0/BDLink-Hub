package com.rk_softwares.bdlinkhub.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.rk_softwares.bdlinkhub.R;


public class Act_SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash_screen);

        //getWindow().setStatusBarColor(Color.TRANSPARENT);
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){

            //navigate_main();
            nav();

        }else {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //navigate_main();
                    nav();
                }
            },2000);

        }



    }

    private void navigate_main(){

        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);

        Intent intent;
        if (isLoggedIn) {
            intent = new Intent(Act_SplashScreenActivity.this, Act_Home_activity.class); // ✅ লগইন থাকলে Home Activity
        } else {
            intent = new Intent(Act_SplashScreenActivity.this, Act_Login.class); // ❌ না থাকলে Login Page
        }
        startActivity(intent);
        finishAffinity(); // সব আগের Activity ক্লোজ করবে
    }

    private void nav(){

        startActivity(new Intent(this, Act_Home_activity.class));
        finishAffinity();


    }



}
