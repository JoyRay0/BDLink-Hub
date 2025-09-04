package com.rk_softwares.bdlinkhub.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.rk_softwares.bdlinkhub.R;
import com.rk_softwares.bdlinkhub.Utils.SecureStorge;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Act_check_link extends AppCompatActivity {

    //XML id's----------------------------------------------

    private AppCompatEditText ed_check_link;
    private AppCompatButton btn_click;
    private AppCompatImageView iv_back;
    private AppCompatTextView tv_checked_result, tv_reset;
    private ProgressBar pb_loading;

    public static final String API_KEY = "AIzaSyDBmKOnysqbJl5I0Ml1CZR87pOkbIQ3_tE";
    public static final String API_URL = "https://safebrowsing.googleapis.com/v4/threatMatches:find?key="+API_KEY;

    private String full_link;

    //XML id's----------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_check_link);

        //identity period---------------------------------

        ed_check_link = findViewById(R.id.ed_check_link);
        btn_click = findViewById(R.id.btn_click);
        iv_back = findViewById(R.id.iv_back);
        tv_checked_result = findViewById(R.id.tv_checked_result);
        tv_reset = findViewById(R.id.tv_reset);
        pb_loading = findViewById(R.id.pb_loading);

        //identity period---------------------------------

        new AlertDialog.Builder(this)
                .setTitle("নিরাপত্তা তথ্য")
                .setMessage("\uD83D\uDD12 আমরা সবসময় আপনার নিরাপত্তা রক্ষা করার চেষ্টা করি। তবে কিছু সাইট সবসময় ধরা নাও পড়তে পারে।")
                .setPositiveButton("বুঝেছি", (dialogInterface, i) -> {
                    dialogInterface.dismiss();

                })
                .show();


        SecureStorge secureStorge = new SecureStorge(this);

        ed_check_link.setOnClickListener(view -> {

            tv_checked_result.setText("");
            tv_checked_result.setVisibility(View.GONE);
            pb_loading.setVisibility(View.GONE);

        });

        btn_click.setOnClickListener(view -> {

            String user_link = ed_check_link.getText().toString().trim();

            if (!user_link.isEmpty()){

                pb_loading.setVisibility(View.VISIBLE);

                if (!user_link.startsWith("http://") && !user_link.startsWith("https://")){

                    full_link = "https://"+user_link;

                    first_check(full_link);
                }else {

                    first_check(user_link);
                }



            }else {

                Toast.makeText(Act_check_link.this, "অনুগ্রহ করে লিংক দিন।", Toast.LENGTH_SHORT).show();

            }

        });

        tv_reset.setOnClickListener(view -> {

            ed_check_link.setText("");
            tv_checked_result.setText("");
            tv_checked_result.setVisibility(View.GONE);
            pb_loading.setVisibility(View.GONE);


        });


        //back--------------------------------------------------------
        iv_back.setOnClickListener(view -> {

            startActivity(new Intent(Act_check_link.this, Act_Home.class));
            finishAffinity();

        });


       getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
           @Override
           public void handleOnBackPressed() {

               startActivity(new Intent(Act_check_link.this, Act_Home.class));
               finishAffinity();

           }
       });

    }//on create==============================

    //get link check----------------------------------------------------

    private void first_check(String link){

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(link)
                .get()
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                new Handler(Looper.getMainLooper()).post(() -> {

                    pb_loading.setVisibility(View.GONE);

                    tv_checked_result.setVisibility(View.VISIBLE);
                    tv_checked_result.setTextColor(Color.RED);
                    tv_checked_result.setText("⚠\uFE0F লিংক কাজ করছে না বা ভুল");

                });

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful()){

                    new Handler(Looper.getMainLooper()).post(() -> {

                        tv_checked_result.setVisibility(View.GONE);

                    });

                    second_check(link);

                }

            }
        });


    }

    //post link check-------------------------------------------------------
    private void second_check(String link){

        OkHttpClient client = new OkHttpClient();


        String json = "{ \"threatInfo\": { \"threatTypes\": [\"MALWARE\", \"SOCIAL_ENGINEERING\"], \"platformTypes\": [\"ANY_PLATFORM\"], \"threatEntryTypes\": [\"URL\"], \"threatEntries\": [{\"url\": \""+link+"\"}] } }";

        RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                new Handler(Looper.getMainLooper()).post(() -> {
                    pb_loading.setVisibility(View.GONE);
                    tv_checked_result.setVisibility(View.VISIBLE);
                    tv_checked_result.setTextColor(Color.RED);
                    tv_checked_result.setText("⚠️ Safe Browsing check failed। আবার চেষ্টা করুন।");
                });

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful() && response.body() != null){

                    String data = response.body().string();

                    new Handler(Looper.getMainLooper()).post(() -> {

                        tv_checked_result.setVisibility(View.VISIBLE);
                        pb_loading.setVisibility(View.GONE);

                        if (data.contains("matches")){

                            tv_checked_result.setText("⚠\uFE0F এই লিংকটি নিরাপদ নয় বা কাজ করছে না। অনুগ্রহ করে সঠিক লিংক ব্যবহার করুন।");
                            tv_checked_result.setTextColor(Color.RED);

                        }else {

                            tv_checked_result.setText("✅ লিংকটি সঠিক এবং নিরাপদ। আপনি এটা ব্যবহার করতে পারেন।");
                            tv_checked_result.setTextColor(Color.GRAY);

                        }


                    });

                }else {

                    new Handler(Looper.getMainLooper()).post(() -> {

                        tv_checked_result.setVisibility(View.GONE);
                        pb_loading.setVisibility(View.GONE);

                    });
                }

            }
        });

    }


}//public class================================