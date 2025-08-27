package com.rk_softwares.bdlinkhub.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;


import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.rk_softwares.bdlinkhub.Api.PostApi;
import com.rk_softwares.bdlinkhub.Api.Request_link;
import com.rk_softwares.bdlinkhub.Utils.ApiResponseListener;
import com.rk_softwares.bdlinkhub.Model.c_api_config;
import com.rk_softwares.bdlinkhub.Model.User_info;
import com.rk_softwares.bdlinkhub.R;
import com.rk_softwares.bdlinkhub.Utils.InputValidation;
import com.rk_softwares.bdlinkhub.Utils.NetworkUtils;
import com.rk_softwares.bdlinkhub.Utils.Request_limit;

import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class Act_UserRegistrationActivity extends AppCompatActivity {

    //XML id's----------------------------------------------------

    private TextInputEditText ed_name, ed_email, ed_password;
    private AppCompatTextView tv_date;
    private AppCompatButton btn_submit;
    private AppCompatImageView iv_date_btn;
    private DatePicker date_picker;
    private LottieAnimationView loading_anim;

    //XML id's----------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_user_registration);

        //identity period ---------------------------------------------


        ed_name = findViewById(R.id.ed_name);
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        tv_date = findViewById(R.id.tv_date);
        btn_submit = findViewById(R.id.btn_submit);
        iv_date_btn = findViewById(R.id.iv_date_btn);
        date_picker = findViewById(R.id.date_picker);
        loading_anim = findViewById(R.id.loading_anim);

        //identity period ---------------------------------------------

        check_network(this);
        Request_limit limit = new Request_limit(this);



        date_picker.setVisibility(View.GONE);

        //calender---------------------------------------
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        date_picker.setMaxDate(System.currentTimeMillis());
        //calender---------------------------------------

        iv_date_btn.setOnClickListener(view1 -> {
            if (date_picker.getVisibility() == View.GONE) {

                date_picker.setVisibility(View.VISIBLE);

               date_picker.init(year, month, day, new DatePicker.OnDateChangedListener() {
                   @Override
                   public void onDateChanged(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {

                       tv_date.setText(selectedYear+ "/"+(selectedMonth + 1)+"/"+selectedDay);

                   }
               });


            } else {

                date_picker.setVisibility(View.GONE);

            }

        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = InputValidation.filterInput(ed_name.getText().toString());
                String email = InputValidation.filterInput(ed_email.getText().toString());
                String password = InputValidation.filterInput(ed_password.getText().toString());
                String DateofBirth = tv_date.getText().toString();

                if (!InputValidation.isValidUsername(name)){

                    Toast.makeText(Act_UserRegistrationActivity.this, "Invalid username", Toast.LENGTH_SHORT).show();

                } else if (!InputValidation.isValidEmail(email)) {

                    Toast.makeText(Act_UserRegistrationActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();

                } else if (DateofBirth == null || DateofBirth.isEmpty()) {

                    Toast.makeText(Act_UserRegistrationActivity.this, "Invalid Date of Birth", Toast.LENGTH_SHORT).show();

                }else if (!InputValidation.isValidPassword(password)) {

                    Toast.makeText(Act_UserRegistrationActivity.this, "Password much contains 12+ character or number", Toast.LENGTH_SHORT).show();

                }else {


                    if (limit.canMakeRequest(Act_UserRegistrationActivity.this)){

                        loading_anim.setVisibility(View.VISIBLE);

                        Request_link link = new Request_link(new ApiResponseListener() {
                            @Override
                            public void onApiResponse(c_api_config config) {

                                String reg_link = config.getUser_reg_login_reg();

                                send_data_to_server(name, email, password, DateofBirth,reg_link);
                            }

                            @Override
                            public void onApiFailed(String error) {

                            }
                        });
                        link.Apis();



                    }


                }




            }
        });

        //back----------------------------------------------------

        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                startActivity(new Intent(Act_UserRegistrationActivity.this, Act_Home_activity.class));
                finishAffinity();

            }
        });

        //back----------------------------------------------------

    }//on create ================================

    //sending user data to server
    private void send_data_to_server(String name, String email, String password, String DateofBirth, String url){

        String device_id = UUID.randomUUID().toString();

        Gson gson = new Gson();

        User_info userInfo = new User_info();
        userInfo.setName(name);
        userInfo.setEmail(email);
        userInfo.setPassword(password);
        userInfo.setDate_of_birth(DateofBirth);
        String user_reg = gson.toJson(userInfo);


        PostApi postApi = new PostApi(url, user_reg);

        postApi.postApi(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                new Handler(Looper.getMainLooper()).post(() -> {

                    loading_anim.setVisibility(View.VISIBLE);

                    Toast.makeText(Act_UserRegistrationActivity.this, "Please check your connection", Toast.LENGTH_SHORT).show();

                });

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful() && response.body() != null) {

                    String data = response.body().string();

                    try {

                        User_info userInfo = gson.fromJson(data, User_info.class);

                        new Handler(Looper.getMainLooper()).post(() -> {

                            Toast.makeText(Act_UserRegistrationActivity.this, ""+userInfo, Toast.LENGTH_SHORT).show();

                            if (userInfo.getStatus().equals("Successful")) {

                                loading_anim.setVisibility(View.GONE);

                                saveUserData(userInfo.getUser_id(), name);

                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "" + userInfo.getMessage(), Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.parseColor("#323232"));
                                snackbar.setTextColor(Color.WHITE);
                                snackbar.show();

                                new Handler().postDelayed(() -> {

                                    startActivity(new Intent(Act_UserRegistrationActivity.this, Act_Home_activity.class));
                                    finishAffinity();

                                }, 2000);

                            } else if (userInfo.getStatus().equals("Failed")) {

                                loading_anim.setVisibility(View.GONE);

                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "" + userInfo.getMessage(), Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.RED);
                                snackbar.setTextColor(Color.WHITE);
                                snackbar.show();

                            }else {

                                loading_anim.setVisibility(View.GONE);

                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), ""+ userInfo.getMessage() , Snackbar.LENGTH_SHORT);
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

    private void saveUserData(String user_id, String name) {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_id", user_id);
        editor.putString("name", name);
        editor.apply(); // apply() তাত্ক্ষণিকভাবে সংরক্ষণ করে
    }

    private void getUserData() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        String user_id = sharedPreferences.getString("user_id","No id");
        String userName = sharedPreferences.getString("name", "No Name");
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



}//public class=============================