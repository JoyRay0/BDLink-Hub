package com.rk_softwares.bdemergencynumbers;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;


public class MainActivity extends AppCompatActivity {




    //XML id's ------------------------------------------------------------

    CardView dh,ch,raj,kh,sy,bo,ra,my,hotline_cardview;

    public static boolean PERMISSION = false;


    //XML id's ------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //identity period-----------------------------------------------------

        dh = findViewById(R.id.dh);
        ch = findViewById(R.id.ch);
        raj = findViewById(R.id.raj);
        kh = findViewById(R.id.kh);
        sy = findViewById(R.id.sy);
        bo = findViewById(R.id.bo);
        my = findViewById(R.id.my);
        ra = findViewById(R.id.ra);
        hotline_cardview = findViewById(R.id.hotline_cardview);

        //identity period-----------------------------------------------------


        check_permission();



        dh.setOnClickListener(new View.OnClickListener() {  //dhaka-----
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, DhakaCityActivity.class));

            }
        });

        ch.setOnClickListener(new View.OnClickListener() {  //chittagong-----
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, ChittagongCityActivity.class));

            }
        });

        raj.setOnClickListener(new View.OnClickListener() {  //rajshahi-----
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, RajshahiCityActivity.class));

            }
        });

        kh.setOnClickListener(new View.OnClickListener() {  //khulna-----
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, KhulnaCityActivity.class));

            }
        });

        sy.setOnClickListener(new View.OnClickListener() {  //sylhet-----
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, SylhetCityActivity.class));

            }
        });

        bo.setOnClickListener(new View.OnClickListener() {  //borishal-----
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, BorishalCityActivity.class));

            }
        });

        my.setOnClickListener(new View.OnClickListener() {  //mymensingh-----
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, MymensinghCityActivity.class));

            }
        });

        ra.setOnClickListener(new View.OnClickListener() {  //rangpur-----
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, RangpurCityActivity.class));

            }
        });

        hotline_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this , HotlineActivity.class));

            }
        });









    }//on create ===============================

    private void check_permission(){    //permission check

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

                 */
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                permissionToken.continuePermissionRequest();
            }
        }).check();

    }




}//public class ==============================