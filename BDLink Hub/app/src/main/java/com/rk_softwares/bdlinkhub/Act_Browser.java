package com.rk_softwares.bdlinkhub;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;
import androidx.core.content.ContextCompat;

public class Act_Browser extends AppCompatActivity {

    //XML id's--------------------------------------------------

    private static final String CHROME_PACKAGE = "com.android.chrome";
    private CustomTabsClient mClient;
    private CustomTabsSession mSession;
    private CustomTabsServiceConnection mConnection;

    //XML id's--------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_browser);

        //identity period--------------------------------------------

        //identity period--------------------------------------------

        String url = getIntent().getStringExtra("url");


        mConnection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(@NonNull ComponentName name, @NonNull CustomTabsClient client) {
                mClient = client;
                mClient.warmup(0);
                mSession = mClient.newSession(null);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mClient = null;
            }
        };
        CustomTabsClient.bindCustomTabsService(this, CHROME_PACKAGE, mConnection);

        customTab(url);


    }//on create======================

    private void customTab(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder(mSession);
        builder.setToolbarColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark));
        builder.setShowTitle(true);

        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.intent.setPackage(CHROME_PACKAGE);
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mConnection != null) {
            unbindService(mConnection);
        }
    }

}//public class=========================