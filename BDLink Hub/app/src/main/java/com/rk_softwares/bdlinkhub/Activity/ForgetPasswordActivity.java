package com.rk_softwares.bdlinkhub.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.rk_softwares.bdlinkhub.R;
import com.rk_softwares.bdlinkhub.Utils.InputValidation;
import com.rk_softwares.bdlinkhub.Utils.Request_limit;

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

public class ForgetPasswordActivity extends AppCompatActivity {

    //XML id's-------------------------------------------------------
    private FrameLayout fl_backButton;
    private TextInputEditText ed_userOld_Password, ed_userNew_Password, ed_userEmail;
    private AppCompatButton btn_submit;
    private LottieAnimationView loading_anim;

    //XML id's-------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        //identity period------------------------------------------------
        fl_backButton = findViewById(R.id.fl_backButton);
        ed_userOld_Password = findViewById(R.id.ed_userOld_Password);
        ed_userNew_Password = findViewById(R.id.ed_userNew_Password);
        btn_submit = findViewById(R.id.btn_submit);
        ed_userEmail = findViewById(R.id.ed_userEmail);
        loading_anim = findViewById(R.id.loading_anim);

        //identity period------------------------------------------------

        Request_limit limit = new Request_limit(this);

        fl_backButton.setOnClickListener(view -> {      //back button

            startActivity(new Intent(this, Login.class));
            finishAffinity();

        });

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
                    changing_Password(email, newPassword);

                }

            }

        });

    }//on create==================================

    @Override
    public void onBackPressed() {

        startActivity(new Intent(this, Login.class));
        finishAffinity();

        super.onBackPressed();
    }

    //changing password

    private void changing_Password(String email, String new_password) {

        String device_id = UUID.randomUUID().toString();

        OkHttpClient client = new OkHttpClient();

        JSONObject object = new JSONObject();

        try {

            object.put("email", email);
            object.put("new_password", new_password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), object.toString());

        Request request = new Request.Builder()
                .url("https://rksoftwares.xyz/All_app/BDLink_Hub/Api/user_reg_login?res=put_user_password")
                .put(body)
                .addHeader("Content-Type", "application/json")
                .header("X-UUID", device_id)
                .build();

        client.newCall(request).enqueue(new Callback() {
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
                        JSONObject jsonObject = new JSONObject(data);
                        String status = jsonObject.getString("status");
                        String message = jsonObject.getString("message");

                        new Handler(Looper.getMainLooper()).post(() -> {

                            if (status.equals("Successful")) {
                                loading_anim.setVisibility(View.GONE);

                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "" + message, Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.parseColor("#323232"));
                                snackbar.setTextColor(Color.WHITE);
                                snackbar.show();

                                new Handler().postDelayed(() -> {

                                    startActivity(new Intent(ForgetPasswordActivity.this, Home_activity.class));
                                    finishAffinity();

                                }, 2000);

                            } else if (status.equals("Failed")) {

                                loading_anim.setVisibility(View.GONE);

                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "" + message, Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.RED);
                                snackbar.setTextColor(Color.WHITE);
                                snackbar.show();

                            } else {

                                loading_anim.setVisibility(View.GONE);
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
    //changing password

}//public class==================================