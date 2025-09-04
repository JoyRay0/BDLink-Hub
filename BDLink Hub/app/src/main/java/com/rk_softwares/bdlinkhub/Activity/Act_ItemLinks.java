package com.rk_softwares.bdlinkhub.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.rk_softwares.bdlinkhub.Adapter.Item_link;
import com.rk_softwares.bdlinkhub.Api.GetApi;
import com.rk_softwares.bdlinkhub.Api.Request_link;
import com.rk_softwares.bdlinkhub.Model.m_All_data;
import com.rk_softwares.bdlinkhub.Model.c_all_data;
import com.rk_softwares.bdlinkhub.Utils.ApiResponseListener;
import com.rk_softwares.bdlinkhub.Model.c_api_config;
import com.rk_softwares.bdlinkhub.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Act_ItemLinks extends AppCompatActivity {

    //XML id's------------------------------------------------------

    private RecyclerView rv_item_links;
    private String endpointLink;
    private String endlink;
    private Item_link itemLink;
    private SwipeRefreshLayout sl_refresh;
    public static String ENDLINK = "";

    List<HashMap<String, String>> mapList = new ArrayList<>();
    HashMap<String, String> hashMap;

    //XML id's------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_item_links);

        //identity period---------------------------------------------

        rv_item_links = findViewById(R.id.rv_item_links);
        sl_refresh = findViewById(R.id.sl_refresh);

        //identity period---------------------------------------------

        Intent intent = getIntent();
        String position = intent.getStringExtra("position");
        endlink = intent.getStringExtra("end_Link");

        switch (Objects.requireNonNull(position)){

            case "0":
                endpointLink = endlink;
                break;

            case "1":
                endpointLink = endlink;
                break;

            case "2":
                endpointLink = endlink;
                break;

            case "3":
                endpointLink = endlink;
                break;

            case "4":
                endpointLink = endlink;

                break;

            case "5":
                endpointLink = endlink;
                break;

            case "6":
                endpointLink = endlink;
                break;

            case "7":
                endpointLink = endlink;
                break;

            case "8":
                endpointLink = endlink;
                break;

            case "9":
                endpointLink = endlink;
                break;

            case "10":
                endpointLink = endlink;
                break;

            case "11":
                endpointLink = endlink;
                break;

            case "12":
                endpointLink = endlink;
                break;

            case "13":
                endpointLink = endlink;
                break;

            case "14":
                endpointLink = endlink;
                break;

            case "15":
                endpointLink = endlink;
                break;

            case "16":
                endpointLink = endlink;
                break;

            case "17":
                endpointLink = endlink;
                break;

            case "18":
                endpointLink = endlink;
                break;

            case "19":
                endpointLink = endlink;
                break;

            case "20":
                endpointLink = endlink;
                break;

            case "21":
                endpointLink = endlink;
                break;

            case "22":
                endpointLink = endlink;
                break;

            case "23":
                endpointLink = endlink;
                break;

            case "24":
                endpointLink = endlink;
                break;

            case "25":
                endpointLink = endlink;
                break;

            case "26":
                endpointLink = endlink;
                break;

            case "27":
                endpointLink = endlink;
                break;

            case "28":
                endpointLink = endlink;
                break;

            case "29":
                endpointLink = endlink;
                break;

            case "30":
                endpointLink = endlink;
                break;

            case "31":
                endpointLink = endlink;
                break;

            case "32":
                endpointLink = endlink;
                break;

            case "33":
                endpointLink = endlink;
                break;

            case "34":
                endpointLink = endlink;
                break;

            case "35":
                endpointLink = endlink;
                break;

            case "36":
                endpointLink = endlink;
                break;

            case "37":
                endpointLink = endlink;
                break;

            case "38":
                endpointLink = endlink;
                break;

            case "39":
                endpointLink = endlink;
                break;

            case "40":
                endpointLink = endlink;
                break;

            case "41":
                endpointLink = endlink;
                break;

            case "42":
                endpointLink = endlink;
                break;

            case "43":
                endpointLink = endlink;
                break;

            case "44":
                endpointLink = endlink;
                break;

            case "45":
                endpointLink = endlink;
                break;

            case "46":
                endpointLink = endlink;
                break;

            case "47":
                endpointLink = endlink;
                break;

            case "48":
                endpointLink = endlink;
                break;

            case "49":
                endpointLink = endlink;
                break;

            case "50":
                endpointLink = endlink;
                break;



        }




        Ad();

        Request_link link = new Request_link(new ApiResponseListener() {
            @Override
            public void onApiResponse(c_api_config config) {

                String link = config.getGet_links();

                String url = link+endpointLink;

                runOnUiThread(() -> {

                    Toast.makeText(Act_ItemLinks.this, ""+url, Toast.LENGTH_SHORT).show();
                });


                item_link(url);

                sl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        item_link(url);
                    }
                });

            }

            @Override
            public void onApiFailed(String error) {



            }
        });
        link.Apis();


        itemLink = new Item_link(this, mapList);
        rv_item_links.setAdapter(itemLink);
        rv_item_links.setLayoutManager(new LinearLayoutManager(this));

        //back---------------------------------------------

        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                startActivity(new Intent(Act_ItemLinks.this, Act_Home.class));
                finishAffinity();
            }
        });

        //back---------------------------------------------

    }//on create=========================

    //item_link-----------------------------------
    private void item_link(String link){

        Gson gson = new Gson();

        GetApi getApi = new GetApi(link);

        getApi.getApi(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                new Handler(Looper.getMainLooper()).post(() -> {

                    Toast.makeText(Act_ItemLinks.this, "Check your connection", Toast.LENGTH_SHORT).show();
                    sl_refresh.setRefreshing(false);
                });

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {


                if (response.isSuccessful() && response.body() != null){

                    String link = response.body().string();

                    
                    try {

                        m_All_data allData = gson.fromJson(link, m_All_data.class);

                        mapList.clear();

                        if (allData.getStatus().contains("successful")){

                            List<c_all_data> cData = allData.getData();

                            //mapList.clear();

                            for (int i = 0; i < cData.size(); i++){

                                c_all_data cData1 = cData.get(i);

                                hashMap = new HashMap<>();
                                hashMap.put("item_type","link");
                                hashMap.put("category", cData1.getCategory());
                                hashMap.put("title", cData1.getTitle());
                                hashMap.put("description", cData1.getDescription());
                                hashMap.put("link", cData1.getLink());
                                mapList.add(hashMap);

                            }

                            new Handler(Looper.getMainLooper()).post(() -> {

                                itemLink.notifyDataSetChanged();
                                sl_refresh.setRefreshing(false);

                            });

                        }


                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                }


            }
        });

    }

    private void Ad(){

        hashMap = new HashMap<>();
        hashMap.put("item_type", "ad");
        mapList.add(hashMap);

    }

}//public class==================================