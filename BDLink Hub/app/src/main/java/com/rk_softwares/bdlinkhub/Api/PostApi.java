package com.rk_softwares.bdlinkhub.Api;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class PostApi {

    private String link;
    private OkHttpClient client;
    private String url;
    private String strValue;
    private String device_id;

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    public PostApi(String link, String strValue, String device_id) {
        this.client = new OkHttpClient();
        this.link = link;
        this.strValue = strValue;
        this.device_id = device_id;
    }


    public void postApi(Callback callback){


        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),strValue );


        Request request = new Request.Builder()
                .url(link)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .header("X-UUID", device_id)
                .build();


        client.newCall(request).enqueue(callback);

    }

}
