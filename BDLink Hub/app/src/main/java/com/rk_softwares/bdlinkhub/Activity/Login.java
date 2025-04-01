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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;


import com.airbnb.lottie.LottieAnimationView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.rk_softwares.bdlinkhub.R;
import com.rk_softwares.bdlinkhub.Utils.InputValidation;
import com.rk_softwares.bdlinkhub.Utils.Request_limit;

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

public class Login extends AppCompatActivity {

    //XML id's----------------------------------------------

    private FrameLayout fl_backButton;
    private AppCompatTextView tv_new_account,tv_forgetPassword;
    private TextInputEditText login_email,login_password;
    private AppCompatButton btn_login;
    private AppCompatImageView google_login;
    private LottieAnimationView loading_anim;

    private static final String CLIENT_ID = "310756133467-950cq4pki54074keh6vpkafj90g1m69u.apps.googleusercontent.com";
    private static final int RC_SIGN_IN = 100;
    private GoogleSignInClient signInClient;
    private  FirebaseAuth auth;

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
        loading_anim = findViewById(R.id.loading_anim);

        //identity period------------------------------------------


        auth = FirebaseAuth.getInstance();

        Request_limit limit = new Request_limit(this);

        loading_anim.setVisibility(View.GONE);
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

                loading_anim.setVisibility(View.VISIBLE);

                if (limit.canMakeRequest(this)){

                    login(email, password);

                }


            }

        });

        //google sign in option
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();

        signInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        //google loin
        google_login.setOnClickListener(view -> {

            if (limit.canMakeRequest(this)){

                Intent intent = signInClient.getSignInIntent();
                startActivityForResult(intent, RC_SIGN_IN);

            }


        });

    }//on create ===============================

    //google sign in option
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {

                GoogleSignInAccount account = task.getResult(ApiException.class);
                loading_anim.setVisibility(View.VISIBLE);
                firebase_auth_google(account);

            } catch (Exception e) {
                e.printStackTrace();

            }

        }

    }

    private void firebase_auth_google(GoogleSignInAccount account){

        AuthCredential authCredential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(authCredential)
                .addOnCompleteListener(this, task -> {

                    if (task.isSuccessful()){

                        FirebaseUser user = auth.getCurrentUser();

                        if (user != null){

                            String name = user.getDisplayName();
                            String email = user.getEmail();

                            loading_anim.setVisibility(View.VISIBLE);
                            send_data_to_server(name, email);

                        }

                    }else {

                        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();

                    }

                });

    }

    //google sign in option
    @Override
    public void onBackPressed() {

        startActivity(new Intent(this, Home_activity.class));
        finishAffinity();
        super.onBackPressed();
    }

    //Login user
    private void login(String email, String password){

        String device_id = UUID.randomUUID().toString();

        OkHttpClient client = new OkHttpClient();

        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("email", email);
            jsonObject.put("password", password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());

        Request request = new Request.Builder()
                .url("https://rksoftwares.xyz/All_app/BDLink_Hub/Api/user_reg_login?res=post_userLogin")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .header("X-UUID",device_id)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                new Handler(Looper.getMainLooper()).post(() -> {

                    loading_anim.setVisibility(View.GONE);
                    Toast.makeText(Login.this, "Check your connection", Toast.LENGTH_SHORT).show();

                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful() && response.body() != null){

                    String data = response.body().string();

                    try {

                        JSONObject object = new JSONObject(data);
                        String status = object.getString("status");
                        String message = object.getString("message");
                        String name = object.getString("name");
                        String user_id = object.getString("user_id");

                        new Handler(Looper.getMainLooper()).post(() -> {

                            if (status.equals("Successful")){

                                loading_anim.setVisibility(View.GONE);

                                saveUserData(user_id, name);

                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "" + message, Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.parseColor("#323232"));
                                snackbar.setTextColor(Color.WHITE);
                                snackbar.show();

                                new Handler().postDelayed(() -> {

                                    startActivity(new Intent(Login.this, Home_activity.class));
                                    finishAffinity();

                                },2000);

                            }else if (status.equals("Failed")){

                                loading_anim.setVisibility(View.GONE);

                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), ""+message , Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.RED);
                                snackbar.setTextColor(Color.WHITE);
                                snackbar.show();

                            }else {

                                loading_anim.setVisibility(View.GONE);
                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), ""+message , Snackbar.LENGTH_SHORT);
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

    //send data to server
    private void send_data_to_server(String name, String email){

        //random uuid
        String device_id = UUID.randomUUID().toString();

        OkHttpClient httpClient = new OkHttpClient();

        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("name", name);
            jsonObject.put("email", email);

        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());

        Request request = new Request.Builder()
                .url("https://rksoftwares.xyz/All_app/BDLink_Hub/Api/user_reg_login?res=post_google_OAuth")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .header("X-UUID", device_id)
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                new Handler(Looper.getMainLooper()).post(() -> {

                    loading_anim.setVisibility(View.GONE);
                    Toast.makeText(Login.this, "Please check your connection", Toast.LENGTH_SHORT).show();

                });

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful() && response.body() != null){

                    String data = response.body().string();

                    try {
                        JSONObject object = new JSONObject(data);
                        String user_id = object.getString("user_id");
                        String name = object.getString("name");
                        String status = object.getString("status");
                        String message = object.getString("message");

                        new Handler(Looper.getMainLooper()).post(() -> {

                            if (status.equals("Successful")){

                                loading_anim.setVisibility(View.GONE);

                                saveUserData(user_id, name);

                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "" + message, Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.parseColor("#323232"));
                                snackbar.setTextColor(Color.WHITE);
                                snackbar.show();

                                new Handler().postDelayed(() -> {

                                    startActivity(new Intent(Login.this, Home_activity.class));

                                },2000);

                            }else if (status.contains("Failed")) {

                                loading_anim.setVisibility(View.GONE);

                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "" + message, Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.RED);
                                snackbar.setTextColor(Color.WHITE);
                                snackbar.show();

                            }else {

                                loading_anim.setVisibility(View.GONE);

                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), ""+message , Snackbar.LENGTH_SHORT);
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


    //saving user data to share preferences
    private void saveUserData(String user_id, String name) {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_id", user_id);
        editor.putString("name", name);
        editor.apply(); // apply() তাত্ক্ষণিকভাবে সংরক্ষণ করে
    }


}//public class====================================