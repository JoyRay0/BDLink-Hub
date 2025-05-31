package com.rk_softwares.bdlinkhub.Api;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class GetApi {
    private OkHttpClient client;
    private String link;

    public GetApi(String link) {
        this.link = link;
        this.client = new OkHttpClient();
    }

    public void getApi(Callback callback){


        Request request = new Request.Builder().url(link).build();

        client.newCall(request).enqueue(callback);

    }


}
