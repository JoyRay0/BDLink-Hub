package com.rk_softwares.bdlinkhub.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.rk_softwares.bdlinkhub.R;

public class Act_ItemLinks extends AppCompatActivity {

    //XML id's------------------------------------------------------

    //XML id's------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.act_item_links);

        //identity period---------------------------------------------

        //identity period---------------------------------------------

        Intent intent = getIntent();
        String position = intent.getStringExtra("position");

        if (position.contains("0")){

            Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();

        }else {

            Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
        }

    }//on create=========================

}//public class==================================