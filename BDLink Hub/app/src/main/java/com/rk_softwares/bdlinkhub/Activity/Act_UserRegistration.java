package com.rk_softwares.bdlinkhub.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;


import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

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
import com.rk_softwares.bdlinkhub.Utils.SecureStorge;
import com.rk_softwares.bdlinkhub.Utils.Short_message;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class Act_UserRegistration extends AppCompatActivity {

    //XML id's----------------------------------------------------

    private TextInputEditText ed_name, ed_email, ed_password;
    private AppCompatTextView tv_date;
    private AppCompatButton btn_submit;
    private AppCompatImageView iv_date_btn;
    private DatePicker date_picker;
    private LottieAnimationView loading_anim;
    private View view;
    private SecureStorge secureStorge;

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

                       tv_date.setText(selectedDay+ "/"+(selectedMonth + 1)+"/"+selectedYear);

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

                    Toast.makeText(Act_UserRegistration.this, "Invalid username", Toast.LENGTH_SHORT).show();

                } else if (!InputValidation.isValidEmail(email)) {

                    Toast.makeText(Act_UserRegistration.this, "Invalid Email", Toast.LENGTH_SHORT).show();

                } else if (DateofBirth == null || DateofBirth.isEmpty()) {

                    Toast.makeText(Act_UserRegistration.this, "Invalid Date of Birth", Toast.LENGTH_SHORT).show();

                }else if (!InputValidation.isValidPassword(password)) {

                    Toast.makeText(Act_UserRegistration.this, "Password much contains 12+ character or number", Toast.LENGTH_SHORT).show();

                }else {


                    if (limit.canMakeRequest(Act_UserRegistration.this)){

                        loading_anim.setVisibility(View.VISIBLE);

                        Request_link link = new Request_link(new ApiResponseListener() {
                            @Override
                            public void onApiResponse(c_api_config config) {

                                String user_reg = config.getUser_reg();
                                String user_reg_code = config.getUser_reg_code();

                                send_data_to_server(name, email, password, DateofBirth, user_reg, user_reg_code);
                            }

                            @Override
                            public void onApiFailed(String error) {

                                new Handler(Looper.getMainLooper()).post(() -> {

                                    loading_anim.setVisibility(View.GONE);
                                    Toast.makeText(Act_UserRegistration.this, "ইন্টারনেট কানেকশন চেক করুন", Toast.LENGTH_SHORT).show();

                                });

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

                startActivity(new Intent(Act_UserRegistration.this, Act_Home.class));
                finishAffinity();

            }
        });

        //back----------------------------------------------------

    }//on create ================================

    //sending user data to server
    private void send_data_to_server(String name, String email, String password, String DateofBirth, String url, String otp_url){

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


            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful() && response.body() != null) {

                    String data = response.body().string();

                    try {

                        User_info userInfo = gson.fromJson(data, User_info.class);

                        new Handler(Looper.getMainLooper()).post(() -> {

                            if (userInfo.getStatus().equals("successful")) {

                                loading_anim.setVisibility(View.GONE);

                                Short_message.snack_bar(Act_UserRegistration.this, userInfo.getMessage(), "#323232", String.valueOf(Color.WHITE));

                                verify_otp(otp_url);

                            } else {

                                loading_anim.setVisibility(View.GONE);

                                Short_message.snack_bar(Act_UserRegistration.this, userInfo.getMessage(), String.valueOf(Color.RED), String.valueOf(Color.WHITE));

                            }

                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        });

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

                new Handler(Looper.getMainLooper()).post(() -> {

                    Toast.makeText(Act_UserRegistration.this, "ওটিপি পাঠানো যায়নি", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(() -> {

                        view.setVisibility(View.GONE);

                    }, 2000);

                });

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful() && response.body() != null){

                    String data = response.body().string();

                    try {

                        User_info code_info = gson.fromJson(data, User_info.class);

                        new Handler(Looper.getMainLooper()).post(() -> {

                            if (code_info.getStatus().equals("successful")){

                                Short_message.snack_bar(Act_UserRegistration.this, code_info.getMessage(), "#323232", String.valueOf(Color.WHITE));

                                //saving user name-----------------------------------------
                                secureStorge = new SecureStorge(Act_UserRegistration.this);
                                secureStorge.putString("name", code_info.getName());
                                //saving user name-----------------------------------------

                                new Handler().postDelayed(() -> {

                                    startActivity(new Intent(Act_UserRegistration.this, Act_Home.class));
                                    finishAffinity();

                                }, 2000);


                            } else {

                                Short_message.snack_bar(Act_UserRegistration.this, code_info.getMessage(), String.valueOf(Color.RED), String.valueOf(Color.WHITE));

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


}//public class=============================