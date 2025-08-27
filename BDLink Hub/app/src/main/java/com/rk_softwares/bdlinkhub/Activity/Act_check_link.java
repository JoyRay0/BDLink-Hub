package com.rk_softwares.bdlinkhub.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.rk_softwares.bdlinkhub.R;
import com.rk_softwares.bdlinkhub.Utils.SecureStorge;

public class Act_check_link extends AppCompatActivity {

    //XML id's----------------------------------------------

    private AppCompatEditText ed_check_link;
    private AppCompatTextView tv_reset;
    private AppCompatButton btn_click;

    public static final String API_KEY = "AIzaSyDBmKOnysqbJl5I0Ml1CZR87pOkbIQ3_tE";
    public static final String API_URL = "https://safebrowsing.googleapis.com/v4/threatMatches:find?key="+API_KEY;

    //XML id's----------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_check_link);

        //identity period---------------------------------

        ed_check_link = findViewById(R.id.ed_check_link);
        tv_reset = findViewById(R.id.tv_reset);
        btn_click = findViewById(R.id.btn_click);

        //identity period---------------------------------


        SecureStorge secureStorge = new SecureStorge(this);

        btn_click.setOnClickListener(view -> {

            String user_link = ed_check_link.getText().toString();

            secureStorge.putString("simple", user_link);

            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();


        });

        tv_reset.setOnClickListener(view -> {

            String ss =  secureStorge.getString("simple");

            Toast.makeText(this, ""+ss, Toast.LENGTH_SHORT).show();

        });


        //10
       getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
           @Override
           public void handleOnBackPressed() {

               startActivity(new Intent(Act_check_link.this, Act_Home_activity.class));
               finishAffinity();

           }
       });

    }//on create==============================
}//public class================================