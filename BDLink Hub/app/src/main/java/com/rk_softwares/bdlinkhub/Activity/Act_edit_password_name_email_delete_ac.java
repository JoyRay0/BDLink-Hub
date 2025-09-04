package com.rk_softwares.bdlinkhub.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.rk_softwares.bdlinkhub.R;

public class Act_edit_password_name_email_delete_ac extends AppCompatActivity {

    //XML id's-----------------------------------------------------------------------

    private AppCompatImageView iv_back;
    private FrameLayout fl_container;

    //XML id's-----------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_edit_password_name_email_delete);

        //identity period------------------------------------------------------------

        iv_back = findViewById(R.id.iv_back);
        fl_container = findViewById(R.id.fl_container);

        //identity period------------------------------------------------------------

        Intent intent = getIntent();

        String change_password = intent.getStringExtra("fg_change_password");
        String change_name = intent.getStringExtra("fg_change_name");

        if (change_password != null && change_password.contains("change_password")){

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fl_container, new Fg_edit_password()).commit();

        }  else if (change_name != null && change_name.contains("change_name")) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fl_container, new Fg_edit_name()).commit();

        }

        iv_back.setOnClickListener(view -> {

            startActivity(new Intent(Act_edit_password_name_email_delete_ac.this, Act_Setting.class));
            finishAffinity();

        });

        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                startActivity(new Intent(Act_edit_password_name_email_delete_ac.this, Act_Setting.class));
                finishAffinity();

            }
        });


    }//on create============================================================================
}//public class=============================================================================