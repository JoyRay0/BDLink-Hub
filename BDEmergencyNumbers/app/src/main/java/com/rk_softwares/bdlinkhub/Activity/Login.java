package com.rk_softwares.bdlinkhub.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.Toast;

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

            startActivity(new Intent(this, Home_activity.class));
            finishAffinity();

        });

        tv_new_account.setOnClickListener(view -> {  //Registration page

            startActivity(new Intent(this, UserRegistrationActivity.class));
            new Handler().postDelayed(() -> {

                login_email.setText("");
                login_password.setText("");

            },1000);


        });

        tv_forgetPassword.setOnClickListener(view -> {      //forget password page

            startActivity(new Intent(this, ForgetPasswordActivity.class));
            new Handler().postDelayed(() -> {

                login_email.setText("");
                login_password.setText("");

            },1000);

        });

        btn_login.setOnClickListener(view -> {      //login button

            String email = InputValidation.filterInput(login_email.getText().toString());
            String password = InputValidation.filterInput(login_password.getText().toString());

            if (!InputValidation.isValidEmail(email)){

                Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
            } else if (!InputValidation.isValidPassword(password)) {

                Toast.makeText(this, "Weak Password! Use 12+ and chars, A-Z, a-z, 0-9, @#$%", Toast.LENGTH_SHORT).show();
                
            }else {

                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

            }

        });



    }//on create ===============================

    @Override
    public void onBackPressed() {

        startActivity(new Intent(this, Home_activity.class));
        finishAffinity();
        super.onBackPressed();
    }

    private void saveUserLogin(String token) {
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("auth_token", token);
        editor.putBoolean("isLoggedIn", true);
        editor.apply(); // ✅ Data সেভ হবে
    }

}//public class====================================