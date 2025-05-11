package com.rk_softwares.bdlinkhub.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.rk_softwares.bdlinkhub.R;

public class Act_all_item extends AppCompatActivity {

    //XML id's--------------------------------------------------------------

    private RecyclerView rv_item;

    //XML id's--------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_all_item);
        //identity period-----------------------------------------------------

        rv_item = findViewById(R.id.rv_item);

        //identity period-----------------------------------------------------

    }//on create================

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Act_all_item.this, Act_Home_activity.class));
        finishAffinity();
    }
}//public class==================