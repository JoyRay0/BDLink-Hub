package com.rk_softwares.bdlinkhub.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.rk_softwares.bdlinkhub.Api.DeleteApi;
import com.rk_softwares.bdlinkhub.Model.User_info;
import com.rk_softwares.bdlinkhub.R;
import com.rk_softwares.bdlinkhub.Utils.NetworkUtils;

import java.io.IOException;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Act_Profile extends AppCompatActivity {

    //XML id's---------------------------------------------------------

    //XML id's---------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_profile);

        //identity period----------------------------------------------------

        //identity period----------------------------------------------------

        check_network(this);

    }//on create=========================
    
    //login data saved
    private void logoutUser() {
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear(); // ❌ সব ডাটা মুছে ফেলবে
        editor.apply();

        startActivity( new Intent(Act_Profile.this, Act_Login.class));
        finishAffinity();
    }
    
    //delete user data
    private void delete_user_data(String user_id){

        Gson gson = new Gson();

        User_info userInfo = new User_info();
        userInfo.setUser_id(user_id);
        String delete_user = gson.toJson(userInfo);

        DeleteApi deleteApi = new DeleteApi("delete_user" ,delete_user);

        deleteApi.deleteApi(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                new Handler(Looper.getMainLooper()).post(() -> {

                    Toast.makeText(Act_Profile.this, "দয়া করে আবার চেষ্টা করুন।", Toast.LENGTH_SHORT).show();

                });

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful() && response.body() != null) {

                    String data = response.body().string();

                    try {

                        User_info userInfo = gson.fromJson(data, User_info.class);

                        new Handler(Looper.getMainLooper()).post(() -> {

                            if (userInfo.getStatus().equals("Successful")) {

                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "" + userInfo.getMessage(), Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.parseColor("#323232"));
                                snackbar.setTextColor(Color.WHITE);
                                snackbar.show();

                                new Handler().postDelayed(() -> {

                                    startActivity(new Intent(Act_Profile.this, Act_Home_activity.class));
                                    finishAffinity();

                                }, 2000);

                            } else if (userInfo.getStatus().equals("Failed")) {

                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "" + userInfo.getMessage(), Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.RED);
                                snackbar.setTextColor(Color.WHITE);
                                snackbar.show();

                            } else {

                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "" + userInfo.getMessage(), Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.RED);
                                snackbar.setTextColor(Color.WHITE);
                                snackbar.show();

                            }

                        });

                    } catch (Exception e) {
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

    //checking internet
    private void check_network(Context context){

        NetworkUtils.start_monitoring(context, new NetworkUtils.NetworkChangesListener() {
            @Override
            public void NetworkConnected() {

                runOnUiThread(() -> {

                    Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();

                });

            }

            @Override
            public void NetworkDisconnected() {

                runOnUiThread(() -> {

                    Toast.makeText(context, "Not Connected", Toast.LENGTH_SHORT).show();

                });

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkUtils.stop_monitoring(this);
    }

    //back button

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(Act_Profile.this, Act_Home_activity.class));
    }
}//public class========================