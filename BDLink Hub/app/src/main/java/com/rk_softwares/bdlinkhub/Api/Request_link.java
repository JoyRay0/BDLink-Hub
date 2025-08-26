package com.rk_softwares.bdlinkhub.Api;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.rk_softwares.bdlinkhub.Model.m_api_config;
import com.rk_softwares.bdlinkhub.Utils.ApiResponseListener;
import com.rk_softwares.bdlinkhub.Model.c_api_config;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Request_link {

    private OkHttpClient client;
    private ApiResponseListener listener;


    public Request_link(ApiResponseListener listener) {
        this.client = new OkHttpClient();
        this.listener = listener;

    }

    public void Apis (){

        Gson gson = new Gson();

        String url = "https://bdlinkhub.rksoftwares.xyz/api/api_config";

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                if (listener != null){
                    listener.onApiFailed(e.getMessage());
                }

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful() && response.body() != null){

                    String link = response.body().string();

                    try {

                        m_api_config config = gson.fromJson(link, m_api_config.class);

                        Log.d("worked", ""+config.getStatus());

                        if (config.getStatus().contains("successful")){

                           c_api_config links = config.getLinks();

                            if (listener != null){
                                listener.onApiResponse((c_api_config) links);
                            }

                        }


                    } catch (Exception e) {
                        if (listener != null){
                            listener.onApiFailed(e.getMessage());
                        }
                    }

                }

            }
        });


    }

}
