package com.rk_softwares.bdlinkhub.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

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