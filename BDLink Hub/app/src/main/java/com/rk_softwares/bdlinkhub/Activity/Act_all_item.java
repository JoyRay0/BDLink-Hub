package com.rk_softwares.bdlinkhub.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.rk_softwares.bdlinkhub.Adapter.All_item_Adapter;
import com.rk_softwares.bdlinkhub.Api.Request_link;
import com.rk_softwares.bdlinkhub.Model.m_All_item_link;
import com.rk_softwares.bdlinkhub.Model.c_all_item_link;
import com.rk_softwares.bdlinkhub.Utils.ApiResponseListener;
import com.rk_softwares.bdlinkhub.Model.c_api_config;
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

    private ShimmerFrameLayout sfl_container;

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
        sfl_container = findViewById(R.id.sfl_container);

        //identity period-----------------------------------------------------

        iv_back.setOnClickListener(view -> {

            startActivity(new Intent(Act_all_item.this, Act_Home.class));
            finishAffinity();
        });


        //all item adapter----------------------------------------------


        rv_item.setLayoutManager(new GridLayoutManager(this, 4));
        itemAdapter = new All_item_Adapter(this, mapList);
        rv_item.setAdapter(itemAdapter);

        //all item adapter----------------------------------------------

        //Api_config apiConfig = new Api_config();
        Request_link requestLink = new Request_link(new ApiResponseListener() {
            @Override
            public void onApiResponse(c_api_config config) {

                String link = config.getItem_links();
                new Handler(Looper.getMainLooper()).post(() -> {

                    //Toast.makeText(Act_all_item.this, ""+link, Toast.LENGTH_SHORT).show();

                    item_data(link);

                });



                Sl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        item_data(link);

                    }
                });

            }

            @Override
            public void onApiFailed(String error) {

                new Handler(Looper.getMainLooper()).post(() -> {

                    sfl_container.stopShimmer();
                    sfl_container.setVisibility(View.GONE);

                });

            }
        });
        requestLink.Apis();



        //back --------------------------------------------

        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                startActivity(new Intent(Act_all_item.this, Act_Home.class));
                finishAffinity();

            }
        });

        //back --------------------------------------------


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

                        m_All_item_link allItemLink = gson.fromJson(data, m_All_item_link.class);

                        if (allItemLink.getStatus().contains("successful")) {

                            List<c_all_item_link> cAllItemLinks = allItemLink.getItem();

                            mapList.clear();

                            for (int i = 0; i < cAllItemLinks.size(); i++) {

                                c_all_item_link c_all_item_link = cAllItemLinks.get(i);

                                map = new HashMap<>();
                                map.put("item_name", c_all_item_link.getItem_name());
                                map.put("item_pic", c_all_item_link.getItem_pic());
                                map.put("endLink", c_all_item_link.getEndLink());
                                mapList.add(map);
                            }

                            new Handler(Looper.getMainLooper()).post(() -> {

                                itemAdapter.notifyDataSetChanged();
                                Sl_refresh.setRefreshing(false);

                                sfl_container.stopShimmer();
                                sfl_container.setVisibility(View.GONE);
                                rv_item.setVisibility(View.VISIBLE);

                            });

                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                }

            }
        });

    }

}//public class==================