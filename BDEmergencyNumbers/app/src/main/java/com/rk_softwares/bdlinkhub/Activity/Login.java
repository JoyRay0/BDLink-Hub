package com.rk_softwares.bdlinkhub.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textfield.TextInputEditText;
import com.rk_softwares.bdlinkhub.R;

public class Login extends AppCompatActivity {

    //XML id's----------------------------------------------

    private FrameLayout fl_backButton;
    private AppCompatTextView tv_new_account,tv_forgetPassword;
    private TextInputEditText login_email,login_password;
    private AppCompatButton btn_login;
    private AppCompatImageView google_login,facebook_login;
    private LottieAnimationView loading_anim;

    //XML id's----------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //identity period------------------------------------------

        fl_backButton = findViewById(R.id.fl_backButton);
        tv_new_account = findViewById(R.id.tv_new_account);
        tv_forgetPassword = findViewById(R.id.tv_forgetPassword);
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        btn_login = findViewById(R.id.btn_login);
        google_login = findViewById(R.id.google_login);
        facebook_login = findViewById(R.id.facebook_login);
        loading_anim = findViewById(R.id.loading_anim);

        //identity period------------------------------------------


        fl_backButton.setOnClickListener(view -> {  //back Button

            startActivity(new Intent(this, MainActivity.class));
            finishAffinity();

        });

        tv_new_account.setOnClickListener(view -> {  //Registration page

            startActivity(new Intent(this, UserRegistrationActivity.class));


        });







    }//on create ===============================

    @Override
    public void onBackPressed() {

        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
        super.onBackPressed();
    }
}//public class====================================