package com.rk_softwares.bdlinkhub.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.rk_softwares.bdlinkhub.Api.PostApi;
import com.rk_softwares.bdlinkhub.Api.PutApi;
import com.rk_softwares.bdlinkhub.Api.Request_link;
import com.rk_softwares.bdlinkhub.Utils.ApiResponseListener;
import com.rk_softwares.bdlinkhub.Model.c_api_config;
import com.rk_softwares.bdlinkhub.Model.User_info;
import com.rk_softwares.bdlinkhub.R;
import com.rk_softwares.bdlinkhub.Utils.InputValidation;
import com.rk_softwares.bdlinkhub.Utils.NetworkUtils;
import com.rk_softwares.bdlinkhub.Utils.Request_limit;
import com.rk_softwares.bdlinkhub.Utils.Short_message;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Act_ForgetPasswordActivity extends AppCompatActivity {

    //XML id's-------------------------------------------------------
    private TextInputEditText ed_userOld_Password, ed_userNew_Password, ed_userEmail;
    private AppCompatButton btn_submit;
    private LottieAnimationView loading_anim;
    private View view;
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

                            String update_password = config.getUser_update_password();
                            String update_password_code = config.getUser_update_password_code();

                            changing_Password(email, newPassword, update_password, update_password_code);
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

    private void changing_Password(String email, String new_password, String url, String url_code) {

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

                                verify_otp(url_code);

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

    //otp verification----------------------------------------------------------------------
    private void verify_otp(String url){

        ConstraintLayout parent = findViewById(R.id.main);

        view = LayoutInflater.from(this).inflate(R.layout.lay_otp_verification, null, false);
        parent.removeAllViews();
        parent.addView(view);

        AppCompatEditText ed_otp1 = view.findViewById(R.id.ed_otp1);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        AppCompatEditText ed_otp2 = view.findViewById(R.id.ed_otp2);
        AppCompatEditText ed_otp3 = view.findViewById(R.id.ed_otp3);
        AppCompatEditText ed_otp4 = view.findViewById(R.id.ed_otp4);
        AppCompatEditText ed_otp5 = view.findViewById(R.id.ed_otp5);
        AppCompatEditText ed_otp6 = view.findViewById(R.id.ed_otp6);
        AppCompatTextView tv_resend_code = view.findViewById(R.id.tv_resend_code);
        AppCompatButton btn_verify = view.findViewById(R.id.btn_verify);

        btn_verify.setOnClickListener(view1 -> {

            String otp1 = ed_otp1.getText().toString();
            String otp2 = ed_otp2.getText().toString();
            String otp3 = ed_otp3.getText().toString();
            String otp4 = ed_otp4.getText().toString();
            String otp5 = ed_otp5.getText().toString();
            String otp6 = ed_otp6.getText().toString();


            if (!otp1.isEmpty() && !otp2.isEmpty() && !otp3.isEmpty() && !otp4.isEmpty() && !otp5.isEmpty() && !otp6.isEmpty()){

                String otp = otp1+otp2+otp3+otp4+otp5+otp6;

                code(otp, url);


            }else {
                Toast.makeText(this, "Some filed are missing", Toast.LENGTH_SHORT).show();
            }


        });


    }

    private void code(String otp, String url){

        Gson gson = new Gson();
        User_info userInfo = new User_info();
        userInfo.setUser_update_password_code(otp);
        String change_password_code = gson.toJson(userInfo);

        PostApi postApi = new PostApi(url, change_password_code);

        postApi.postApi(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {


            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful() && response.body() != null){

                    String data = response.body().string();

                    try {

                        User_info code_info = gson.fromJson(data, User_info.class);

                        new Handler(Looper.getMainLooper()).post(() -> {

                            if (code_info.getStatus().equals("successful")){

                                Short_message.snack_bar(Act_ForgetPasswordActivity.this, code_info.getMessage(), "#323232", String.valueOf(Color.WHITE));

                                new Handler().postDelayed(() -> {

                                    startActivity(new Intent(Act_ForgetPasswordActivity.this, Act_Login.class));
                                    finishAffinity();

                                }, 2000);


                            } else if (code_info.getStatus().equals("failed")) {

                                Short_message.snack_bar(Act_ForgetPasswordActivity.this, code_info.getMessage(), String.valueOf(Color.RED), String.valueOf(Color.WHITE));


                                new Handler().postDelayed(() -> {

                                    view.setVisibility(View.GONE);

                                }, 2000);



                            }

                        });


                    } catch (Exception e) {
                       e.printStackTrace();
                    }

                }

            }
        });

    }
    //otp verification----------------------------------------------------------------------


    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkUtils.stop_monitoring(this);
    }

}//public class==================================