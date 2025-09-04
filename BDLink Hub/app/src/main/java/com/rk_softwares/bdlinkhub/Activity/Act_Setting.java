package com.rk_softwares.bdlinkhub.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.rk_softwares.bdlinkhub.Api.DeleteApi;
import com.rk_softwares.bdlinkhub.Api.Request_link;
import com.rk_softwares.bdlinkhub.Database.MyDatabase;
import com.rk_softwares.bdlinkhub.Utils.ApiResponseListener;
import com.rk_softwares.bdlinkhub.Model.c_api_config;
import com.rk_softwares.bdlinkhub.Model.User_info;
import com.rk_softwares.bdlinkhub.R;
import com.rk_softwares.bdlinkhub.Utils.NetworkUtils;
import com.rk_softwares.bdlinkhub.Utils.SecureStorge;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class Act_Setting extends AppCompatActivity {

    //XML id's---------------------------------------------------------

    private Request_link link;
    private AppCompatTextView tv_profile_setting, tv_theme, tv_support, tv_sing_out, tv_clear_history, tv_delete_link;
    private AppCompatTextView tv_password_change, tv_name_change, tv_delete_account;
    private CardView cv_edit_password_and_name_email;
    private MyDatabase db;
    private AppCompatImageView iv_back;


    //XML id's---------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_setting);

        //identity period----------------------------------------------------
        tv_profile_setting = findViewById(R.id.tv_profile_setting);
        tv_theme = findViewById(R.id.tv_theme);
        tv_support = findViewById(R.id.tv_support);
        tv_sing_out = findViewById(R.id.tv_sing_out);
        tv_clear_history = findViewById(R.id.tv_clear_history);
        tv_delete_link = findViewById(R.id.tv_delete_link);

        cv_edit_password_and_name_email = findViewById(R.id.cv_edit_password_and_name_email);

        tv_password_change = findViewById(R.id.tv_password_change);
        tv_name_change = findViewById(R.id.tv_name_change);
        tv_delete_account = findViewById(R.id.tv_delete_account);

        iv_back = findViewById(R.id.iv_back);
        //identity period----------------------------------------------------


        db = MyDatabase.getInstance(Act_Setting.this);

        check_network(this);
        profile_setting();
        edit_password_name_email_delete_account();


        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                startActivity(new Intent(Act_Setting.this, Act_Home.class));
                finishAffinity();
            }
        });

        tv_clear_history.setOnClickListener(view -> {

            delete_search_history_data();

        });

        tv_delete_link.setOnClickListener(view -> {

            delete_all_save_links();

        });

        tv_theme.setOnClickListener(view -> {

            theme();

        });
        
        tv_sing_out.setOnClickListener(view -> {

            SecureStorge secureStorge = new SecureStorge(Act_Setting.this);
            
            secureStorge.remove_key("user_id");
            secureStorge.remove_key("name");

            Toast.makeText(this, "সাইন আউট হয়েছে।", Toast.LENGTH_SHORT).show();
            
        });

        iv_back.setOnClickListener(view -> {

            startActivity(new Intent(Act_Setting.this, Act_Home.class));
            finishAffinity();

        });

    }//on create================================================================================================
    

    //delete user data
    private void delete_user_data(String user_id, String url){

        Gson gson = new Gson();

        User_info userInfo = new User_info();
        userInfo.setUser_id(user_id);
        String delete_user = gson.toJson(userInfo);

        DeleteApi deleteApi = new DeleteApi(url ,delete_user);

        deleteApi.deleteApi(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                new Handler(Looper.getMainLooper()).post(() -> {

                    Toast.makeText(Act_Setting.this, "দয়া করে আবার চেষ্টা করুন।", Toast.LENGTH_SHORT).show();

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

                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "" + userInfo.getMessage(), Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.parseColor("#323232"));
                                snackbar.setTextColor(Color.WHITE);
                                snackbar.show();

                                new Handler().postDelayed(() -> {

                                    startActivity(new Intent(Act_Setting.this, Act_Home.class));
                                    finishAffinity();

                                }, 2000);

                            } else if (userInfo.getStatus().equals("Failed")) {

                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "" + userInfo.getMessage(), Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.RED);
                                snackbar.setTextColor(Color.WHITE);
                                snackbar.show();

                            } else {

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

    //delete user dialog
    private void delete_user_dialog(String userid){

         new AlertDialog.Builder(this)
                 .setTitle("ডিলিট একাউন্ট!")
                 .setMessage("আপনি কি একাউন্ট ডিলিট করতে চান ?")
                 .setPositiveButton("হ্যাঁ", (dialogInterface, i) -> {

                     link = new Request_link(new ApiResponseListener() {
                         @Override
                         public void onApiResponse(c_api_config config) {

                             String d_user = config.getUser_reg_login_delete_account();

                             delete_user_data(userid, d_user);

                         }

                         @Override
                         public void onApiFailed(String error) {

                         }
                     });
                     link.Apis();


                     dialogInterface.dismiss();

                 })
                 .setNegativeButton("না",(dialogInterface, i) -> {

                     dialogInterface.dismiss();
                 })
                 .show();


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

    //delete search data---------------------------------------------------------
    private void delete_search_history_data(){

        Dialog dialog = new Dialog(Act_Setting.this);
        dialog.setContentView(R.layout.lay_delete);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        AppCompatTextView tv_title = dialog.findViewById(R.id.tv_title);
        AppCompatTextView tv_description = dialog.findViewById(R.id.tv_description);
        AppCompatTextView tv_yes_btn = dialog.findViewById(R.id.tv_yes_btn);
        AppCompatTextView tv_no_btn = dialog.findViewById(R.id.tv_no_btn);

        tv_title.setText("ক্লিন সার্চ হিস্ট্রি ?");
        tv_description.setText("এই কাজটি করলে আপনার সব সার্চ হিস্ট্রি স্থায়ীভাবে মুছে যাবে। পরে আর ফেরত আনা যাবে না।");

        tv_yes_btn.setOnClickListener(view -> {

            new Thread(() -> {

                db.historyDao().DeleteAll();

            }).start();

            dialog.dismiss();

        });

        tv_no_btn.setOnClickListener(view -> {

            dialog.dismiss();

        });

        dialog.show();

    }

    //delete all save links-------------------------------------------------------------------------

    private void delete_all_save_links(){

        Dialog dialog = new Dialog(Act_Setting.this);
        dialog.setContentView(R.layout.lay_delete);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        AppCompatTextView tv_title = dialog.findViewById(R.id.tv_title);
        AppCompatTextView tv_description = dialog.findViewById(R.id.tv_description);
        AppCompatTextView tv_yes_btn = dialog.findViewById(R.id.tv_yes_btn);
        AppCompatTextView tv_no_btn = dialog.findViewById(R.id.tv_no_btn);

        tv_title.setText("সব সেভ করা লিংক মুছবেন?");
        tv_description.setText("এই কাজটি করলে আপনার সব সেভ করা লিংক স্থায়ীভাবে মুছে যাবে। পরে ফেরত আনা যাবে না।");

        tv_yes_btn.setOnClickListener(view -> {

            SharedPreferences user_link1 = getSharedPreferences("user_link1", Context.MODE_PRIVATE);
            SharedPreferences user_link2 = getSharedPreferences("user_link2", Context.MODE_PRIVATE);

            user_link1.edit().clear().apply();
            user_link2.edit().clear().apply();

            dialog.dismiss();

        });

        tv_no_btn.setOnClickListener(view -> {

            dialog.dismiss();

        });

        dialog.show();

    }

    //theme dialog------------------------------------------------------------------------------------
    private void theme(){

        Dialog dialog = new Dialog(Act_Setting.this);
        dialog.setContentView(R.layout.lay_nightmode_lightmode);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));


        dialog.show();

    }


    //profile setting----------------------------------------------------------------------------------------------
    private void profile_setting(){

        tv_profile_setting.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_profile, 0, R.drawable.ic_down, 0);

        cv_edit_password_and_name_email.setVisibility(View.GONE);
        tv_profile_setting.setTag("open");

        tv_profile_setting.setOnClickListener(view -> {

            String currentTag = tv_profile_setting.getTag().toString();

            if ("open".equals(currentTag)){

                tv_profile_setting.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_profile, 0, R.drawable.ic_up, 0);
                cv_edit_password_and_name_email.setVisibility(View.VISIBLE);
                tv_profile_setting.setTag("close");

            } else if ("close".equals(currentTag)) {

                tv_profile_setting.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_profile, 0, R.drawable.ic_down, 0);
                cv_edit_password_and_name_email.setVisibility(View.GONE);
                tv_profile_setting.setTag("open");
            }


        });

    }

    //edit password, email, password, delete account-------------------------------------------------------------

    private void edit_password_name_email_delete_account(){

        tv_password_change.setOnClickListener(view -> {

            Intent intent = new Intent(Act_Setting.this, Act_edit_password_name_email_delete_ac.class);
            intent.putExtra("fg_change_password", "change_password");
            startActivity(intent);

        });

        tv_name_change.setOnClickListener(view -> {

            Intent intent = new Intent(Act_Setting.this, Act_edit_password_name_email_delete_ac.class);
            intent.putExtra("fg_change_name", "change_name");
            startActivity(intent);

        });

        tv_delete_account.setOnClickListener(view -> {

            Intent intent = new Intent(Act_Setting.this, Act_edit_password_name_email_delete_ac.class);
            intent.putExtra("fg_delete_account", "delete_account");
            startActivity(intent);

        });





    }





}//public class========================