package com.rk_softwares.bdlinkhub.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputEditText;
import com.rk_softwares.bdlinkhub.R;

public class ForgetPasswordActivity extends AppCompatActivity {

    //XML id's-------------------------------------------------------
    private FrameLayout fl_backButton;
    private TextInputEditText ed_userOld_Password, ed_userNew_Password, ed_userEmail;
    private AppCompatButton btn_submit;

    //XML id's-------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        //identity period------------------------------------------------
        fl_backButton = findViewById(R.id.fl_backButton);
        ed_userOld_Password = findViewById(R.id.ed_userOld_Password);
        ed_userNew_Password = findViewById(R.id.ed_userNew_Password);
        btn_submit = findViewById(R.id.btn_submit);
        ed_userEmail = findViewById(R.id.ed_userEmail);

        //identity period------------------------------------------------

        fl_backButton.setOnClickListener(view -> {      //back button

            startActivity(new Intent(this, Login.class));
            finishAffinity();

        });

        btn_submit.setOnClickListener(view -> {     //submit button

            String email = InputValidation.filterInput(ed_userEmail.getText().toString());
            String oldPassword = InputValidation.filterInput(ed_userOld_Password.getText().toString());
            String newPassword = InputValidation.filterInput(ed_userNew_Password.getText().toString());

            if (!InputValidation.isValidEmail(email)){

                Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();

            } else if (!InputValidation.isValidPassword(oldPassword)) {

                Toast.makeText(this, "Old password much contains 12+ character or number", Toast.LENGTH_SHORT).show();

            } else if (!InputValidation.isValidPassword(newPassword)) {

                Toast.makeText(this, "New password much contains 12+ character or number", Toast.LENGTH_SHORT).show();

            }else {

                Toast.makeText(this, "Password update successfully", Toast.LENGTH_SHORT).show();

            }

        });



    }//on create==================================

    @Override
    public void onBackPressed() {

        startActivity(new Intent(this, Login.class));
        finishAffinity();

        super.onBackPressed();
    }
}//public class==================================