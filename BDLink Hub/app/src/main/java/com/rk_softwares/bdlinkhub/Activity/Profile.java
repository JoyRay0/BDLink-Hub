package com.rk_softwares.bdlinkhub.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.rk_softwares.bdlinkhub.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


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
    
    //delete user data
    private void delete_user_data(String user_id){

        String device_id = UUID.randomUUID().toString();

        OkHttpClient client = new OkHttpClient();

        JSONObject object = new JSONObject();

        try {

            object.put("user_id", user_id);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), object.toString());

        Request request = new Request.Builder()
                .url("https://rksoftwares.xyz/All_app/BDLink_Hub/Api/user_reg_login?res=put_user_password")
                .delete(body)
                .addHeader("Content-Type", "application/json")
                .header("X-UUID", device_id)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                new Handler(Looper.getMainLooper()).post(() -> {

                    Toast.makeText(Profile.this, "দয়া করে আবার চেষ্টা করুন।", Toast.LENGTH_SHORT).show();

                });

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful() && response.body() != null) {

                    String data = response.body().string();

                    try {
                        JSONObject jsonObject = new JSONObject(data);
                        String status = jsonObject.getString("status");
                        String message = jsonObject.getString("message");

                        new Handler(Looper.getMainLooper()).post(() -> {

                            if (status.equals("Successful")) {

                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "" + message, Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.parseColor("#323232"));
                                snackbar.setTextColor(Color.WHITE);
                                snackbar.show();

                                new Handler().postDelayed(() -> {

                                    startActivity(new Intent(Profile.this, Home_activity.class));
                                    finishAffinity();

                                }, 2000);

                            } else if (status.equals("Failed")) {

                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "" + message, Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.RED);
                                snackbar.setTextColor(Color.WHITE);
                                snackbar.show();

                            } else {

                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "" + message, Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.RED);
                                snackbar.setTextColor(Color.WHITE);
                                snackbar.show();

                            }

                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        });
        
    }

    //delete user dialog
    private void delete_user_dialog(String userid){

         new AlertDialog.Builder(this)
                 .setTitle("ডিলিট একাউন্ট!")
                 .setMessage("আপনি কি একাউন্ট ডিলিট করতে চান ?")
                 .setPositiveButton("হ্যাঁ", (dialogInterface, i) -> {

                     delete_user_data(userid);
                     dialogInterface.dismiss();

                 })
                 .setNegativeButton("না",(dialogInterface, i) -> {

                     dialogInterface.dismiss();
                 })
                 .show();


    }

}//public class========================