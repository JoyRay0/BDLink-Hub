package com.rk_softwares.bdlinkhub.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.rk_softwares.bdlinkhub.R;

import org.json.JSONObject;

import okhttp3.OkHttpClient;


public class Profile extends AppCompatActivity {

    //XML id's---------------------------------------------------------

    //XML id's---------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        //identity period----------------------------------------------------

        //identity period----------------------------------------------------

    }//on create=========================




    //login data saved
    private void logoutUser() {
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear(); // ❌ সব ডাটা মুছে ফেলবে
        editor.apply();

        startActivity( new Intent(Profile.this, Login.class));
        finishAffinity();
    }

}//public class========================