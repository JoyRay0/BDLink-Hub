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
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;


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
import java.util.UUID;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Act_Login extends AppCompatActivity {

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
    private Request_link link;

    //XML id's----------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);


        //identity period------------------------------------------

        tv_new_account = findViewById(R.id.tv_new_account);
        tv_forgetPassword = findViewById(R.id.tv_forgetPassword);
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        btn_login = findViewById(R.id.btn_login);
        google_login = findViewById(R.id.google_login);
        loading_anim = findViewById(R.id.loading_anim);

        //identity period------------------------------------------

        //verify_otp();

        getOnBackPressedDispatcher().addCallback(this, callback);   // back button
        auth = FirebaseAuth.getInstance();

        Request_limit limit = new Request_limit(this);
        check_network(this);

        loading_anim.setVisibility(View.GONE);


        tv_new_account.setOnClickListener(view -> {  //Registration page

            startActivity(new Intent(this, Act_UserRegistrationActivity.class));
            new Handler().postDelayed(() -> {

                login_email.setText("");
                login_password.setText("");

            },1000);


        });


        tv_forgetPassword.setOnClickListener(view -> {      //forget password page

            startActivity(new Intent(this, Act_ForgetPasswordActivity.class));
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

                if (limit.canMakeRequest(this)){

                    loading_anim.setVisibility(View.VISIBLE);

                    link = new Request_link(new ApiResponseListener() {
                        @Override
                        public void onApiResponse(c_api_config config) {

                            String link = config.getUser_reg_login_login();

                            login(email, password, link);

                        }

                        @Override
                        public void onApiFailed(String error) {

                        }
                    });
                    link.Apis();



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

                            link = new Request_link(new ApiResponseListener() {
                                @Override
                                public void onApiResponse(c_api_config config) {

                                    String g_link = config.getUser_reg_login_gAuth();

                                    send_data_to_server(name, email, g_link);

                                }

                                @Override
                                public void onApiFailed(String error) {

                                }
                            });
                            link.Apis();



                        }

                    }else {

                        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();

                    }

                });

    }

    //google sign in option
    private OnBackPressedCallback callback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            startActivity(new Intent(Act_Login.this, Act_Home_activity.class));
            finishAffinity();
        }
    };

    //Login user
    private void login(String email, String password, String url){

        String device_id = UUID.randomUUID().toString();

        Gson gson = new Gson();

        User_info userInfo = new User_info();
        userInfo.setEmail(email);
        userInfo.setPassword(password);
        String user_login = gson.toJson(userInfo);

        PostApi postApi = new PostApi(url, user_login, device_id);

        postApi.postApi(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                new Handler(Looper.getMainLooper()).post(() -> {

                    loading_anim.setVisibility(View.GONE);
                    Toast.makeText(Act_Login.this, "Check your connection", Toast.LENGTH_SHORT).show();

                });

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful() && response.body() != null){

                    String data = response.body().string();

                    try {

                        User_info userInfo = gson.fromJson(data, User_info.class);

                        new Handler(Looper.getMainLooper()).post(() -> {


                            if (userInfo.getStatus().equals("Successful")){

                                loading_anim.setVisibility(View.GONE);

                                saveUserData(userInfo.getUser_id(), userInfo.getName());

                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "" +userInfo.getMessage(), Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.parseColor("#323232"));
                                snackbar.setTextColor(Color.WHITE);
                                snackbar.show();

                                new Handler().postDelayed(() -> {

                                    startActivity(new Intent(Act_Login.this, Act_Home_activity.class));
                                    finishAffinity();

                                },2000);

                            }else if (userInfo.getStatus().equals("Failed")){

                                loading_anim.setVisibility(View.GONE);

                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), ""+userInfo.getMessage() , Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.RED);
                                snackbar.setTextColor(Color.WHITE);
                                snackbar.show();

                            }else {

                                loading_anim.setVisibility(View.GONE);
                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), ""+userInfo.getMessage() , Snackbar.LENGTH_SHORT);
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
    private void send_data_to_server(String name, String email, String url){

        String device_id = UUID.randomUUID().toString();
        Gson gson = new Gson();

        User_info userInfo = new User_info();
        userInfo.setName(name);
        userInfo.setEmail(email);
        String user_data = gson.toJson(userInfo);

        PostApi postApi = new PostApi(url,user_data, device_id);

           postApi.postApi(new Callback() {
               @Override
               public void onFailure(@NonNull Call call, @NonNull IOException e) {

                   new Handler(Looper.getMainLooper()).post(() -> {

                       loading_anim.setVisibility(View.GONE);
                       Toast.makeText(Act_Login.this, "Please check your connection", Toast.LENGTH_SHORT).show();

                   });

               }

               @Override
               public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                   if (response.isSuccessful() && response.body() != null){

                       String data = response.body().string();

                       try {

                           User_info userInfo = gson.fromJson(data, User_info.class);

                           new Handler(Looper.getMainLooper()).post(() -> {

                               if (userInfo.getStatus().equals("Successful")){

                                   loading_anim.setVisibility(View.GONE);

                                   saveUserData(userInfo.getUser_id(), name);

                                   Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "" + userInfo.getMessage(), Snackbar.LENGTH_SHORT);
                                   snackbar.setBackgroundTint(Color.parseColor("#323232"));
                                   snackbar.setTextColor(Color.WHITE);
                                   snackbar.show();

                                   new Handler().postDelayed(() -> {

                                       startActivity(new Intent(Act_Login.this, Act_Home_activity.class));

                                   },2000);

                               }else if (userInfo.getStatus().equals("Failed")) {

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

    //saving user data to share preferences
    private void saveUserData(String user_id, String name) {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_id", user_id);
        editor.putString("name", name);
        editor.apply(); // apply() তাত্ক্ষণিকভাবে সংরক্ষণ করে
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

    //otp verification-----------------------------------------
    public void verify_otp(){

        ConstraintLayout parent = findViewById(R.id.main);

        View view = LayoutInflater.from(this).inflate(R.layout.lay_otp_verification, null, false);
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

                Toast.makeText(this, "Verification successful"+otp1+otp2+otp3+otp4+otp5+otp6, Toast.LENGTH_SHORT).show();
                //view.setVisibility(View.GONE);
            }else {
                Toast.makeText(this, "Some filed are missing", Toast.LENGTH_SHORT).show();
            }


        });


    }

}//public class====================================