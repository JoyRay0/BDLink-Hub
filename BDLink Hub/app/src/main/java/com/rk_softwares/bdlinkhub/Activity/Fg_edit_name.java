package com.rk_softwares.bdlinkhub.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rk_softwares.bdlinkhub.Api.PostApi;
import com.rk_softwares.bdlinkhub.Api.Request_link;
import com.rk_softwares.bdlinkhub.Model.User_info;
import com.rk_softwares.bdlinkhub.Model.c_api_config;
import com.rk_softwares.bdlinkhub.R;
import com.rk_softwares.bdlinkhub.Utils.ApiResponseListener;
import com.rk_softwares.bdlinkhub.Utils.InputValidation;
import com.rk_softwares.bdlinkhub.Utils.Request_limit;
import com.rk_softwares.bdlinkhub.Utils.SecureStorge;
import com.rk_softwares.bdlinkhub.Utils.Short_message;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class Fg_edit_name extends Fragment {

    //XML id's--------------------------------------------------------------

    private AppCompatEditText ed_name, ed_email;
    private AppCompatTextView tv_submit_btn;
    private Request_limit limit;
    private RelativeLayout parent;
    private View view;
    private ProgressBar pb_loading;
    private SecureStorge secureStorge;

    //XML id's--------------------------------------------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fg_edit_name, container, false);

        //identity period----------------------------------------------------------

        ed_name = view.findViewById(R.id.ed_name);
        ed_email = view.findViewById(R.id.ed_email);
        tv_submit_btn = view.findViewById(R.id.tv_submit_btn);
        parent = view.findViewById(R.id.main);
        pb_loading = view.findViewById(R.id.pb_loading);

        //identity period----------------------------------------------------------

        name_email();

        limit = new Request_limit(requireActivity());



        return view;
    }//on create=======================================================================

    private void name_email(){

        tv_submit_btn.setOnClickListener(view -> {
            
            String name = InputValidation.filterInput(ed_name.getText().toString());
            String email = InputValidation.filterInput(ed_email.getText().toString());
            
            if (!InputValidation.isValidUsername(name) && name.length() > 30){

                Toast.makeText(requireActivity(), " সঠিক নাম দিন", Toast.LENGTH_SHORT).show();
                
            } else if (!InputValidation.isValidEmail(email)) {

                Toast.makeText(requireActivity(), "সঠিক ইমেইল দিন", Toast.LENGTH_SHORT).show();
                
            }else {

                if (limit.canMakeRequest(requireActivity())){

                    pb_loading.setVisibility(View.VISIBLE);
                    
                    Request_link link = new Request_link(new ApiResponseListener() {
                        @Override
                        public void onApiResponse(c_api_config config) {

                            String update_name = config.getUpdate_name();
                            String otp_url = config.getUpdate_name_code();

                            send_data(update_name, name, email, otp_url);

                        }

                        @Override
                        public void onApiFailed(String error) {

                            new Handler(Looper.getMainLooper()).post(() -> {

                                pb_loading.setVisibility(View.GONE);

                                Toast.makeText(requireActivity(), "ইন্টারনেট কানেকশন চেক করুন", Toast.LENGTH_SHORT).show();
                                
                            });
                            
                        }
                    });
                    link.Apis();

                }

            }


        });
        
    }

    private void send_data(String url, String name, String email, String otp_url){

        Gson gson = new Gson();

        User_info userInfo = new User_info();
        userInfo.setName(name);
        userInfo.setEmail(email);
        String info = gson.toJson(userInfo);

        PostApi postApi = new PostApi(url, info);

        postApi.postApi(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                new Handler(Looper.getMainLooper()).post(() -> {
                    
                    pb_loading.setVisibility(View.GONE);
                    
                });
                
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful() && response.body() != null){

                    String data =  response.body().string();

                    try {

                        User_info userInfo1 = gson.fromJson(data, User_info.class);

                        new Handler(Looper.getMainLooper()).post(() -> {

                            if (userInfo1.getStatus().contains("successful")){

                                pb_loading.setVisibility(View.GONE);
                                
                                verify_otp(otp_url);

                                Short_message.snack_bar(requireActivity(), userInfo1.getMessage(), "#323232", "#FFFFFF");


                            } else{

                                pb_loading.setVisibility(View.GONE);
                                Short_message.snack_bar(requireActivity(), userInfo1.getMessage(), String.valueOf(Color.RED), "#FFFFFF");

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
    private void verify_otp(String url){


        view = LayoutInflater.from(requireActivity()).inflate(R.layout.lay_otp_verification, null, false);
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
                Toast.makeText(requireActivity(), "Some filed are missing", Toast.LENGTH_SHORT).show();
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

                    Toast.makeText(requireActivity(), "ওটিপি পাঠানো যায়নি", Toast.LENGTH_SHORT).show();

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

                                Short_message.snack_bar(requireActivity(), code_info.getMessage(), "#323232", String.valueOf(Color.WHITE));

                                //removing old user name and saving new name------------------
                                secureStorge = new SecureStorge(requireActivity());
                                secureStorge.remove_key("name");
                                secureStorge.putString("name", code_info.getName());
                                //removing old user name and saving new name------------------

                                new Handler().postDelayed(() -> {

                                    view.setVisibility(View.GONE);

                                }, 2000);
                                
                            } else{

                                Short_message.snack_bar(requireActivity(), code_info.getMessage(), String.valueOf(Color.RED), String.valueOf(Color.WHITE));
                                
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

}//public class========================================================================