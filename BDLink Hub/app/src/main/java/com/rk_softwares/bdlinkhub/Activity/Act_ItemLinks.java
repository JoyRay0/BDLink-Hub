package com.rk_softwares.bdlinkhub.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.rk_softwares.bdlinkhub.Adapter.Item_link;
import com.rk_softwares.bdlinkhub.Api.GetApi;
import com.rk_softwares.bdlinkhub.Api.Request_link;
import com.rk_softwares.bdlinkhub.Utils.ApiResponseListener;
import com.rk_softwares.bdlinkhub.Model.Api_config;
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
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "1":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "2":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "3":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "4":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "5":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "6":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "7":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "8":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "9":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "10":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "11":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "12":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "13":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "14":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "15":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "16":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "17":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "18":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "19":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "20":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "21":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "22":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "23":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "24":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "25":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "26":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "27":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "28":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "29":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "30":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "31":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "32":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "33":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "34":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "35":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "36":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "37":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "38":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "39":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "40":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "41":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "42":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "43":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "44":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "45":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "46":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "47":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "48":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "49":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;

            case "50":
                endpointLink = endlink;
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
                break;




        }




        itemLink = new Item_link(this, mapList);
        rv_item_links.setAdapter(itemLink);

        Request_link link = new Request_link(new ApiResponseListener() {
            @Override
            public void onApiResponse(Api_config config) {

                String link = config.getGet_links_ai();

                item_link(link+endpointLink);

                sl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        item_link(endpointLink);
                    }
                });

            }

            @Override
            public void onApiFailed(String error) {

            }
        });
        link.Apis();







    }//on create=========================

    //item_link-----------------------------------
    private void item_link(String link){

        Gson gson = new Gson();

        GetApi getApi = new GetApi(link);

        getApi.getApi(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

            }
        });

    }

}//public class==================================