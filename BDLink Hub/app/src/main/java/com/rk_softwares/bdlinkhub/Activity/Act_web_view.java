package com.rk_softwares.bdlinkhub.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rk_softwares.bdlinkhub.R;

public class Act_web_view extends AppCompatActivity {

    //XML id's---------------------------------------------------

    WebView wv_website;

    //XML id's---------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_web_view);

        //identity period----------------------------------------

        //wv_website = findViewById(R.id.wv_website);

        //identity period----------------------------------------

        Intent intent = getIntent();

        String other_app = intent.getStringExtra("other_app");
        String privacy = intent.getStringExtra("privacy");

        if (other_app != null && !other_app.isEmpty()){

            //webview(other_app);

        }else {

            //webview(privacy);

        }


    }//on create=========================

    //webview------------------------------------------------
    private void webview(String url){

        WebSettings settings = wv_website.getSettings();
        settings.setJavaScriptEnabled(true);
        wv_website.loadUrl(url);


    }

    //back--------------------------------------------------
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(this, Act_Home_activity.class));
        finishAffinity();

    }
}//public class==========================