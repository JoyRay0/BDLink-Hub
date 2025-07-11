package com.rk_softwares.bdlinkhub.Activity;



import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.rk_softwares.bdlinkhub.R;
import com.rk_softwares.bdlinkhub.Utils.NetworkUtils;

import java.util.List;


public class Act_Home_activity extends AppCompatActivity {


    //XML id's ------------------------------------------------------------

    private FrameLayout frame_layout;

    MaterialToolbar toolbar;


    private final String appPackageName = "com.mala.digital_joper_mala";
    public static boolean POST_NOTIFICATION_PERMISSION = false;
    public static boolean INTERNET = false;
    private boolean isDialog = false;
    private static final long TIME = 3 * 24 * 60 * 60 * 1000L;



    private BottomNavigationView bottom_nav;

    private DrawerLayout drawer_layout;
    private NavigationView nv_drawer;



    //XML id's ------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home_activity);


        //identity period-----------------------------------------------------

        frame_layout = findViewById(R.id.frame_layout);
        toolbar = findViewById(R.id.toolbar);
        bottom_nav = findViewById(R.id.bottom_nav);
        drawer_layout = findViewById(R.id.drawer_layout);
        nv_drawer = findViewById(R.id.nv_drawer);



        //identity period-----------------------------------------------------

        //toolbar-------------------------------------
        toolbar();
        //toolbar-------------------------------------
        //check_permission();

        //drawer navigation----------------------------------------------
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(Act_Home_activity.this, drawer_layout, toolbar, R.string.open, R.string.close);
        drawer_layout.addDrawerListener(toggle);
        drawer_navigation();
        //drawer navigation----------------------------------------------

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frame_layout, new Fg_Home()).commit();


        bottom_navigation();

        check_network(this);

        user_id();


        //firebase------------------------------------------------------
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {

                    if (!task.isSuccessful()){

                        return;

                    }

                    String token = task.getResult();

                    Log.d("token", "key="+token);

                });

        //firebase------------------------------------------------------
        
    }//on create ===============================

    private void check_permission(){    //permission check


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){


           Dexter.withContext(this).withPermission(Manifest.permission.POST_NOTIFICATIONS)
                   .withListener(new PermissionListener() {
                       @Override
                       public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                           POST_NOTIFICATION_PERMISSION = true;

                       }

                       @Override
                       public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                           POST_NOTIFICATION_PERMISSION = false;

                       }

                       @Override
                       public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                           permissionToken.continuePermissionRequest();

                       }
                   }).check();

                        //    Toast.makeText(MainActivity.this, "Allow all permission to use features", Toast.LENGTH_SHORT).show();

            //       Toast.makeText(MainActivity.this, "Need permission to use this task", Toast.LENGTH_LONG).show();
        }else {

            Dexter.withContext(this).withPermissions(

                    Manifest.permission.CALL_PHONE,
                            Manifest.permission.POST_NOTIFICATIONS)

                    .withListener(new MultiplePermissionsListener(){
                @Override
                public void onPermissionsChecked(MultiplePermissionsReport report) {

                    if (report.areAllPermissionsGranted()){

                       // PERMISSION = true;

                    } else if (report.isAnyPermissionPermanentlyDenied()) {

                        //PERMISSION = false;

                        Toast.makeText(Act_Home_activity.this, "Need permission to use this task", Toast.LENGTH_LONG).show();
                    }
                    else {

                        Toast.makeText(Act_Home_activity.this, "Allow all permission to use features", Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    token.continuePermissionRequest();

                }
            }).check();

        }

        /*
        //single permission


        Dexter.withContext(MainActivity.this).withPermission(Manifest.permission.CALL_PHONE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                PERMISSION = true;
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                PERMISSION = false;
                Toast.makeText(MainActivity.this, "Need call permission to use this task", Toast.LENGTH_LONG).show();

                /*
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                permissionToken.continuePermissionRequest();
            }
        }).check();

         */

    }

    private void bottom_navigation(){

        bottom_nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                if (itemId == R.id.search){

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_layout, new Fg_search_view()).commit();

                } else if (itemId == R.id.profile) {

                    startActivity(new Intent(Act_Home_activity.this, Act_Login.class));

                } else if (itemId ==R.id.home) {

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_layout, new Fg_Home()).commit();

                } else if (itemId == R.id.favorite) {

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_layout, new Fg_favorite()).commit();

                }

                return true;
            }
        });


    }

    //customize menu toolbar------------------------------
    private void toolbar(){

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.share){

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    String Body = "Download this App";
                    String sub = "https://play.google.com/store/apps/details?id="+appPackageName;
                    intent.putExtra(Intent.EXTRA_TEXT,Body);
                    intent.putExtra(Intent.EXTRA_TEXT,sub);
                    startActivity(Intent.createChooser(intent,null));

                }


                return true;
            }
        });

    }

    //checking network---------------------------------------
    private void check_network(Context context){

        NetworkUtils.start_monitoring(context, new NetworkUtils.NetworkChangesListener() {
            @Override
            public void NetworkConnected() {

                runOnUiThread(() -> {

                    Toast.makeText(Act_Home_activity.this, "Connected", Toast.LENGTH_SHORT).show();

                });

            }

            @Override
            public void NetworkDisconnected() {

                runOnUiThread(() -> {

                    Toast.makeText(Act_Home_activity.this, "Not Connected", Toast.LENGTH_SHORT).show();

                });

            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkUtils.stop_monitoring(this);
    }

    //login dialog---------------------------
    private void login_dialog(){

        new Handler().postDelayed(() -> {

            Dialog dialog1 = new Dialog(Act_Home_activity.this);
            dialog1.setContentView(R.layout.lay_login_dialog);
            dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            dialog1.show();
            AppCompatTextView login = dialog1.findViewById(R.id.login);

            login.setOnClickListener(view -> {

                startActivity(new Intent(Act_Home_activity.this, Act_Login.class));

            });

        }, TIME);

    }


    //checking id------------------------------
    private void user_id(){

        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        String user_id = sharedPreferences.getString("user_id", "No id");

        if (user_id.equals("No id")){

            login_dialog();

        }

    }

    //drawer navigation-------------------------------------
    private void drawer_navigation(){

        nv_drawer.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.review){

            } else if (id == R.id.feedback) {

            } else if (id == R.id.login) {

                startActivity(new Intent(this, Act_Login.class));

            } else if (id == R.id.logout) {

                SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
                sharedPreferences.edit().remove("userInfo").apply();

            } else if (id == R.id.info) {

                Dialog dialog1 = new Dialog(Act_Home_activity.this);
                dialog1.setContentView(R.layout.lay_info_dialog);
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                dialog1.show();

            }

            return true;

        });

    }

}//public class ==============================