package com.rk_softwares.bdemergencynumbers;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class HospitalNumberActivity extends AppCompatActivity {

    //XML id's----------------------------------------------

    AppCompatButton button_refresh;

    Toolbar toolbar;

    LinearLayoutCompat hospital_offline_linerlayout, hospital_online_linerlayout;

    ImageButton back;

    ListView hospital_listview;

    //XML id's----------------------------------------------


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_number);

        //identity period----------------------------------------------

        toolbar = findViewById(R.id.toolbar);
        hospital_listview = findViewById(R.id.hospital_listview);
        button_refresh = findViewById(R.id.button_refresh);
        back = findViewById(R.id.back);
        hospital_offline_linerlayout = findViewById(R.id.hospital_offline_linerlayout);
        hospital_online_linerlayout = findViewById(R.id.hospital_online_linerlayout);

        //identity period----------------------------------------------


        hospital_toolbar();

        if (MainActivity.INTERNET == true){


            hospital_offline_linerlayout.setVisibility(View.GONE);
            hospital_online_linerlayout.setVisibility(View.VISIBLE);

        }else {

            hospital_online_linerlayout.setVisibility(View.GONE);
            hospital_offline_linerlayout.setVisibility(View.VISIBLE);

            button_refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    finishAffinity();
                    System.exit(0);

                }
            });

        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HospitalNumberActivity.this, MainActivity.class));

            }
        });







    }//on create============================================


    public final void hospital_toolbar(){

        toolbar.inflateMenu(R.menu.hospital_menu);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.search){



                }

                return false;
            }
        });




    }


}//public class ===============================