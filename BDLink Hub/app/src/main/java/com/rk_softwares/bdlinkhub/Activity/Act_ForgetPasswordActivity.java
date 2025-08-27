package com.rk_softwares.bdlinkhub.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.rk_softwares.bdlinkhub.Api.PutApi;
import com.rk_softwares.bdlinkhub.Api.Request_link;
import com.rk_softwares.bdlinkhub.Utils.ApiResponseListener;
import com.rk_softwares.bdlinkhub.Model.c_api_config;
import com.rk_softwares.bdlinkhub.Model.User_info;
import com.rk_softwares.bdlinkhub.R;
import com.rk_softwares.bdlinkhub.Utils.InputValidation;
import com.rk_softwares.bdlinkhub.Utils.NetworkUtils;
import com.rk_softwares.bdlinkhub.Utils.Request_limit;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Act_ForgetPasswordActivity extends AppCompatActivity {

    //XML id's-------------------------------------------------------
    private TextInputEditText ed_userOld_Password, ed_userNew_Password, ed_userEmail;
    private AppCompatButton btn_submit;
    private LottieAnimationView loading_anim;

    //XML id's-------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_forget_password);

        //identity period------------------------------------------------
        ed_userOld_Password = findViewById(R.id.ed_userOld_Password);
        ed_userNew_Password = findViewById(R.id.ed_userNew_Password);
        btn_submit = findViewById(R.id.btn_submit);
        ed_userEmail = findViewById(R.id.ed_userEmail);
        loading_anim = findViewById(R.id.loading_anim);

        //identity period------------------------------------------------

        Request_limit limit = new Request_limit(this);

        check_network(this);
        loading_anim.setVisibility(View.GONE);


        btn_submit.setOnClickListener(view -> {     //submit button

            String email = InputValidation.filterInput(ed_userEmail.getText().toString());
            String oldPassword = InputValidation.filterInput(ed_userOld_Password.getText().toString());
            String newPassword = InputValidation.filterInput(ed_userNew_Password.getText().toString());

            if (!InputValidation.isValidEmail(email)){

                Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();

            } else if (!InputValidation.isValidPassword(oldPassword)) {

                Toast.makeText(this, "Old password much contains 12+ character or number", Toast.LENGTH_SHORT).show();

            } else if (!InputValidation.isValidPassword(newPassword)) {

                Toast.makeText(this, "New password much contains 12+ character or number", Toast.LENGTH_SHORT).show();

            }else {

                if (limit.canMakeRequest(this)){

                    loading_anim.setVisibility(View.VISIBLE);

                    Request_link link = new Request_link(new ApiResponseListener() {
                        @Override
                        public void onApiResponse(c_api_config config) {

                            String link = config.getUser_reg_login_reset_password();

                            changing_Password(email, newPassword, link);
                        }

                        @Override
                        public void onApiFailed(String error) {

                        }
                    });
                    link.Apis();


                }

            }

        });

        //back-------------------------------------------------------
        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                startActivity(new Intent(Act_ForgetPasswordActivity.this, Act_Login.class));
            }
        });
        //back-------------------------------------------------------

    }//on create==================================


    //changing password

    private void changing_Password(String email, String new_password, String url) {

        Gson gson = new Gson();
        User_info userInfo = new User_info();
        userInfo.setEmail(email);
        userInfo.setNew_password(new_password);
        String change_password = gson.toJson(userInfo);


        PutApi putApi = new PutApi(url, change_password);

        putApi.putApi(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                new Handler(Looper.getMainLooper()).post(() -> {

                    loading_anim.setVisibility(View.GONE);

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
                                loading_anim.setVisibility(View.GONE);

                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "" + userInfo.getMessage(), Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.parseColor("#323232"));
                                snackbar.setTextColor(Color.WHITE);
                                snackbar.show();

                                new Handler().postDelayed(() -> {

                                    startActivity(new Intent(Act_ForgetPasswordActivity.this, Act_Home_activity.class));
                                    finishAffinity();

                                }, 2000);

                            } else if (userInfo.getStatus().equals("Failed")) {

                                loading_anim.setVisibility(View.GONE);

                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "" + userInfo.getMessage(), Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.RED);
                                snackbar.setTextColor(Color.WHITE);
                                snackbar.show();

                            } else {

                                loading_anim.setVisibility(View.GONE);
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
    //changing password

    //checking internet

    private void check_network(Context context){

        NetworkUtils.start_monitoring(context, new NetworkUtils.NetworkChangesListener() {
            @Override
            public void NetworkConnected() {

                runOnUiThread(() -> {

                    Toast.makeText(Act_ForgetPasswordActivity.this, "Connected", Toast.LENGTH_SHORT).show();

                });

            }

            @Override
            public void NetworkDisconnected() {

                runOnUiThread(() -> {

                    Toast.makeText(Act_ForgetPasswordActivity.this, "Not Connected", Toast.LENGTH_SHORT).show();

                });

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkUtils.stop_monitoring(this);
    }

}//public class==================================