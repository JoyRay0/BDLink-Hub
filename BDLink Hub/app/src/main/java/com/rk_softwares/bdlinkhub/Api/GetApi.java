package com.rk_softwares.bdlinkhub.Api;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class GetApi {
    private OkHttpClient client;
    private String endPointLink;

    public GetApi(String endPointLink) {
        this.endPointLink = endPointLink;
        this.client = new OkHttpClient();
    }

    public void getApi(Callback callback){

        String url = "https://rksoftwares.xyz/All_app/BDLink_Hub/Api/get_links?res="+endPointLink;

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(callback);

    }


}
