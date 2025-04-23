package com.rk_softwares.bdlinkhub.Api;

import java.util.UUID;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class DeleteApi {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private String endPointLink;
    private OkHttpClient client;
    private String url;
    private String strValue;
    private String device_id;

    public DeleteApi(String endPointLink, String strValue) {
        this.client = new OkHttpClient();
        this.endPointLink = endPointLink;
        this.strValue = strValue;
    }

    public void deleteApi(Callback callback){

        url = "https://rksoftwares.xyz/All_app/BDLink_Hub/Api/user_reg_login?res="+endPointLink;

        device_id = UUID.randomUUID().toString();

        RequestBody body = RequestBody.create(JSON, strValue);

        Request request = new Request.Builder()
                .url(url)
                .delete(body)
                .addHeader("Content-Type", "application/json")
                .header("X-UUID", device_id)
                .build();

        client.newCall(request).enqueue(callback);

    }

}
