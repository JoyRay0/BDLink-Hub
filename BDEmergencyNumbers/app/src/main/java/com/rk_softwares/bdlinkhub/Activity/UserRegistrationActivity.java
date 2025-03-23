package com.rk_softwares.bdlinkhub.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textfield.TextInputEditText;
import com.rk_softwares.bdlinkhub.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class UserRegistrationActivity extends AppCompatActivity {

    //XML id's----------------------------------------------------

    private FrameLayout fl_backButton;
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
        setContentView(R.layout.user_registration);

        //identity period ---------------------------------------------

        fl_backButton = findViewById(R.id.fl_backButton);
        ed_name = findViewById(R.id.ed_name);
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        tv_date = findViewById(R.id.tv_date);
        btn_submit = findViewById(R.id.btn_submit);
        iv_date_btn = findViewById(R.id.iv_date_btn);
        date_picker = findViewById(R.id.date_picker);
        loading_anim = findViewById(R.id.loading_anim);

        //identity period ---------------------------------------------

        fl_backButton.setOnClickListener(view -> {      //back button

            startActivity(new Intent(this, Login.class));
            finishAffinity();

        });

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

                    Toast.makeText(UserRegistrationActivity.this, "Invalid username", Toast.LENGTH_SHORT).show();

                } else if (!InputValidation.isValidEmail(email)) {

                    Toast.makeText(UserRegistrationActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();

                } else if (DateofBirth == null || DateofBirth.isEmpty()) {

                    Toast.makeText(UserRegistrationActivity.this, "Invalid Date of Birth", Toast.LENGTH_SHORT).show();

                }else if (!InputValidation.isValidPassword(password)) {

                    Toast.makeText(UserRegistrationActivity.this, "Password much contains 12+ character or number", Toast.LENGTH_SHORT).show();

                }else {


                    sendData(name, email, password, DateofBirth);
                }




            }
        });





    }//on create ================================

    //sending user data to server
    private void sendData(String name, String email, String password, String DateofBirth){
        //1

        OkHttpClient client = new OkHttpClient();

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("name",name);
            jsonObject.put("email", email);
            jsonObject.put("password",password);
            jsonObject.put("date_of_birth", DateofBirth);

        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());


        Request request = new Request.Builder()
                .url("https://rksoftwares.xyz/All_app/BDLink_Hub/Api/user_reg_login?res=post_reg")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                new Handler(Looper.getMainLooper()).post(() -> {

                    loading_anim.setVisibility(View.VISIBLE);

                    Toast.makeText(UserRegistrationActivity.this, "Account not created", Toast.LENGTH_SHORT).show();

                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful() && response.body() != null){

                    String data = response.body().string();

                    Log.e("server","not working"+data);
                    try {
                        JSONObject jsonObject1 = new JSONObject(data);
                        String status = jsonObject1.getString("status");

                        new Handler(Looper.getMainLooper()).post(() -> {

                            loading_anim.setVisibility(View.GONE);
                            Toast.makeText(UserRegistrationActivity.this, ""+status, Toast.LENGTH_SHORT).show();


                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                }else {
                    new Handler(Looper.getMainLooper()).post(() -> {

                        Toast.makeText(UserRegistrationActivity.this, "Error", Toast.LENGTH_SHORT).show();


                    });


                }

            }
        });

    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(this, Login.class));
        finishAffinity();

        super.onBackPressed();
    }
}//public class=============================