package com.rk_softwares.bdlinkhub.Utils;

import android.app.Activity;
import android.graphics.Color;

import com.google.android.material.snackbar.Snackbar;

public class Short_message {

    public static void snack_bar(Activity activity, String info, String bg_color, String text_color){

        Snackbar snackbar = Snackbar.make(activity.findViewById(android.R.id.content), "" + info, Snackbar.LENGTH_SHORT);
        snackbar.setBackgroundTint(Color.parseColor(bg_color));
        snackbar.setTextColor(Color.parseColor(text_color));
        snackbar.show();
    }

}
