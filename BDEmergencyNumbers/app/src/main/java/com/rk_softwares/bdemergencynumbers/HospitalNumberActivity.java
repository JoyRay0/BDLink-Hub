package com.rk_softwares.bdemergencynumbers;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HospitalNumberActivity extends AppCompatActivity {

    //XML id's----------------------------------------------

    Toolbar toolbar;

    //XML id's----------------------------------------------


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_number);

        //identity period----------------------------------------------

        toolbar = findViewById(R.id.toolbar);

        //identity period----------------------------------------------


        hospital_toolbar();


    }//on create==============================

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