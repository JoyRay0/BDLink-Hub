package com.rk_softwares.bdlinkhub.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.rk_softwares.bdlinkhub.Adapter.All_item_Adapter;
import com.rk_softwares.bdlinkhub.Api.Request_link;
import com.rk_softwares.bdlinkhub.Utils.ApiResponseListener;
import com.rk_softwares.bdlinkhub.Model.Api_config;
import com.rk_softwares.bdlinkhub.Model.Item;
import com.rk_softwares.bdlinkhub.Model.Item_data;
import com.rk_softwares.bdlinkhub.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Act_all_item extends AppCompatActivity {

    //XML id's--------------------------------------------------------------

    private RecyclerView rv_item;
    private SwipeRefreshLayout Sl_refresh;
    private AppCompatImageView iv_back;

    List<HashMap<String, String>> mapList = new ArrayList<>();
    HashMap<String, String> map;

    private String link;

    All_item_Adapter itemAdapter;

    private boolean isDataloaded = false;

    //XML id's--------------------------------------------------------------

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_all_item);
        //identity period-----------------------------------------------------

        rv_item = findViewById(R.id.rv_item);
        Sl_refresh = findViewById(R.id.Sl_refresh);
        iv_back = findViewById(R.id.iv_back);

        //identity period-----------------------------------------------------

        iv_back.setOnClickListener(view -> {

            startActivity(new Intent(Act_all_item.this, Act_Home_activity.class));
            finishAffinity();
        });


        //all item adapter----------------------------------------------

        rv_item.setLayoutManager(new GridLayoutManager(this, 4));
        itemAdapter = new All_item_Adapter(this, mapList);
        rv_item.setAdapter(itemAdapter);

        //all item adapter----------------------------------------------

        Api_config apiConfig = new Api_config();
        Request_link requestLink = new Request_link(new ApiResponseListener() {
            @Override
            public void onApiResponse(Api_config config) {

                String link = config.getItem_links();

                item_data(link);

                Sl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        item_data(link);


                    }
                });

            }

            @Override
            public void onApiFailed(String error) {


            }
        });
        requestLink.Apis();





    }//on create================

    //all item--------------------------------------------------------------
    private void item_data(String url){

        Gson gson = new Gson();

        OkHttpClient client = new OkHttpClient();


        //String url = "https://rksoftwares.xyz/All_app/BDLink_Hub/Api/item_links?res=all_item";

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                new Handler(Looper.getMainLooper()).post(() -> {

                    Sl_refresh.setRefreshing(false);

                });

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful() && response.body() != null){

                    String data = response.body().string();

                    try {

                        Item_data itemData = gson.fromJson(data, Item_data.class);

                        if (itemData.getStatus().contains("successful")) {

                            List<Item> items = itemData.getItem();

                            mapList.clear();

                            for (int i = 0; i < items.size(); i++) {

                                Item item = items.get(i);

                                map = new HashMap<>();
                                map.put("item_name", item.getItem_name());
                                map.put("item_pic", item.getItem_pic());
                                map.put("endLink", item.getEndLink());
                                mapList.add(map);
                            }

                            new Handler(Looper.getMainLooper()).post(() -> {

                                itemAdapter.notifyDataSetChanged();
                                Sl_refresh.setRefreshing(false);

                            });

                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Act_all_item.this, Act_Home_activity.class));
        finishAffinity();
    }


}//public class==================