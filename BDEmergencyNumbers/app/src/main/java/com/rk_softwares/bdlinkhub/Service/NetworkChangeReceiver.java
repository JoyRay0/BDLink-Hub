package com.rk_softwares.bdlinkhub.Service;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.rk_softwares.bdlinkhub.R;

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        if (NetworkUtils.isInternetConnected(context)) {
            Toast.makeText(context, "ইন্টারনেট সংযোগ আছে", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
        }


    }
}