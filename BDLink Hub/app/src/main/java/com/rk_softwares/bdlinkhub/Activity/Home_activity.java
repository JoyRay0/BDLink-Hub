package com.rk_softwares.bdlinkhub.Activity;



import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.rk_softwares.bdlinkhub.R;

import java.util.List;


public class Home_activity extends AppCompatActivity {




    //XML id's ------------------------------------------------------------

    private FrameLayout frame_layout;
    CardView dh,ch,raj,kh,sy,bo,ra,my,hotline_cardview,hospital_cardview;

    MaterialToolbar toolbar;
    FloatingActionButton floating_button;


    private final String appPackageName = "com.mala.digital_joper_mala";
    public static boolean POST_NOTIFICATION_PERMISSION = false;
    public static boolean INTERNET = false;
    private boolean isDialog = false;

    private BottomNavigationView bottom_nav;



    //XML id's ------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);





        //identity period-----------------------------------------------------

        frame_layout = findViewById(R.id.frame_layout);
        toolbar = findViewById(R.id.toolbar);
        //floating_button = findViewById(R.id.floating_button);
        bottom_nav = findViewById(R.id.bottom_nav);



        //identity period-----------------------------------------------------

        toolbar();
        //check_permission();


        /*
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.nightmode_lightmode);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.show();

         */

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frame_layout, new Home_fragment()).commit();


        bottom_navigation();

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

                    Manifest.permission.CALL_PHONE)

                    .withListener(new MultiplePermissionsListener(){
                @Override
                public void onPermissionsChecked(MultiplePermissionsReport report) {

                    if (report.areAllPermissionsGranted()){

                       // PERMISSION = true;

                    } else if (report.isAnyPermissionPermanentlyDenied()) {

                        //PERMISSION = false;

                        Toast.makeText(Home_activity.this, "Need permission to use this task", Toast.LENGTH_LONG).show();
                    }
                    else {

                        Toast.makeText(Home_activity.this, "Allow all permission to use features", Toast.LENGTH_SHORT).show();

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

                    Toast.makeText(Home_activity.this, "search", Toast.LENGTH_SHORT).show();

                } else if (itemId == R.id.profile) {

                    startActivity(new Intent(Home_activity.this, Profile.class));

                }


                return true;
            }
        });


    }

    private void toolbar(){     //customize menu toolbar

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

                } else if (item.getItemId() == R.id.info) {

                    Dialog dialog1 = new Dialog(Home_activity.this);
                    dialog1.setContentView(R.layout.info_dialog);
                    dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    dialog1.show();

                }


                return true;
            }
        });

    }

}//public class ==============================