package com.rk_softwares.bdlinkhub.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class NetworkUtils {


    private static ConnectivityManager.NetworkCallback callback;

    public interface NetworkChangesListener{
        void NetworkConnected();
        void NetworkDisconnected();
    }

    public static void start_monitoring(Context context, NetworkChangesListener listener){

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkRequest request = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build();

        callback = new ConnectivityManager.NetworkCallback(){

            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                if (listener != null){

                    listener.NetworkConnected();

                }
            }

            @Override
            public void onLost(@NonNull Network network) {
                super.onLost(network);
                if (listener != null){

                    listener.NetworkDisconnected();

                }
            }
        };
        manager.registerNetworkCallback(request, callback);

    }

    public static void stop_monitoring(Context context){


        if (callback != null){

            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            manager.unregisterNetworkCallback(callback);
            callback = null;

        }

    }

}
