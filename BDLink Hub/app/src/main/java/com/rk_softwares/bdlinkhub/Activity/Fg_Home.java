 package com.rk_softwares.bdlinkhub.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;


import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.rk_softwares.bdlinkhub.Adapter.MyAdapter;
import com.rk_softwares.bdlinkhub.Adapter.Popular_item_Adapter;
import com.rk_softwares.bdlinkhub.Adapter.ViewPagerAdapter;
import com.rk_softwares.bdlinkhub.Api.Request_link;
import com.rk_softwares.bdlinkhub.Utils.ApiResponseListener;
import com.rk_softwares.bdlinkhub.Model.Api_config;
import com.rk_softwares.bdlinkhub.Model.Item;
import com.rk_softwares.bdlinkhub.Model.Item_data;
import com.rk_softwares.bdlinkhub.R;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


 public class Fg_Home extends Fragment {

    //XML id's-----------------------------------------------

     //10
     private RecyclerView rv_test;
     private GridView gd_item;
     private AppCompatImageView iv_forward;
     private AppCompatTextView tv_no_links,tv_link1,tv_link2, tv_my_links;
     private LinearLayout ll_saved_links;
     private ViewPager2 vp_img;

     private MyAdapter adapter;

     private Popular_item_Adapter plAdapter;

     List<HashMap<String, String>> list = new ArrayList<>();
     HashMap<String, String> hashMap;

     List<HashMap<String, String>> item_list = new ArrayList<>();
     HashMap<String, String> map;

     List<HashMap<String, String>> image_list = new ArrayList<>();
     HashMap<String, String> img_map;


     private ViewPagerAdapter viewPagerAdapter;


     private boolean link1 = false;
     private boolean link2 = false;

     private WormDotsIndicator dotsIndicator;

     int currentItem = 0;

     private SwipeRefreshLayout sl_refresh;

     Handler handler;

     //XML id's-----------------------------------------------

    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fg_home, container, false);

       //identity period------------------------------------------

        //rv_test = view.findViewById(R.id.rv_test);
        gd_item = view.findViewById(R.id.gd_item);
        iv_forward = view.findViewById(R.id.iv_forward);
        tv_no_links = view.findViewById(R.id.tv_no_links);
        tv_link1 = view.findViewById(R.id.tv_link1);
        tv_link2 = view.findViewById(R.id.tv_link2);
        ll_saved_links = view.findViewById(R.id.ll_saved_links);
        tv_my_links = view.findViewById(R.id.tv_my_links);
        vp_img = view.findViewById(R.id.vp_img);
        sl_refresh = view.findViewById(R.id.sl_refresh);
        dotsIndicator = view.findViewById(R.id.dotsIndicator);


        //identity period------------------------------------------


        //adapter = new MyAdapter(requireActivity(), list);
        //rv_test.setAdapter(adapter);
        //rv_test.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));


        //grid view---------------------------------------
        plAdapter = new Popular_item_Adapter(requireActivity(), item_list);
        gd_item.setAdapter(plAdapter);
        //popular_item();

        //grid view---------------------------------------


        //image slider------------------------------------------
        viewPagerAdapter = new ViewPagerAdapter(requireActivity(), image_list);
        vp_img.setAdapter(viewPagerAdapter);
        dotsIndicator.setViewPager2(vp_img);

        Request_link link = new Request_link(new ApiResponseListener() {
            @Override
            public void onApiResponse(Api_config config) {

                String item_link = config.getP_item_links();
                String viewpager = config.getViewpager();

                item_data(item_link);
                viewPager_img(viewpager);

                sl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        item_data(item_link);
                        viewPager_img(viewpager);

                    }
                });
            }

            @Override
            public void onApiFailed(String error) {

            }
        });
        link.Apis();


        handler  = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if (image_list != null && image_list.size() > 0) {
                    currentItem = (currentItem + 1) % image_list.size();
                    vp_img.setCurrentItem(currentItem, true);
                }

                handler.postDelayed(this, 7000);

            }
        };

        handler.postDelayed(runnable, 5000);

        //image slider------------------------------------------

        hashMap = new HashMap<>();
        hashMap.put("itemType","1stLink");
        hashMap.put("cat", "No cat");
        hashMap.put("title", "No title");
        hashMap.put("description", "No description");
        list.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType","2ndLink");
        hashMap.put("cat", "No cat2");
        hashMap.put("title", "No title2");
        hashMap.put("description", "No description2");
        list.add(hashMap);


        iv_forward.setOnClickListener(view1 -> {

            startActivity(new Intent(requireActivity(), Act_all_item.class));

        });

        //save links--------------------------------------------------------
        save_Links();
        SharedPreferences user_link1 = requireActivity().getSharedPreferences("user_link1", Context.MODE_PRIVATE);
        SharedPreferences user_link2 = requireActivity().getSharedPreferences("user_link2", Context.MODE_PRIVATE);

        String title1 = user_link1.getString("title", "No title");
        String Link1 =user_link1.getString("link", "No link");

        String title2 = user_link2.getString("title", "No title");
        String Link2 = user_link1.getString("link","No link");

        tv_no_links.setVisibility(View.VISIBLE);
        ll_saved_links.setVisibility(View.GONE);
        tv_link1.setVisibility(View.GONE);
        tv_link2.setVisibility(View.GONE);
        tv_my_links.setVisibility(View.GONE);

        if (link1){

            tv_link1.setVisibility(View.VISIBLE);
            tv_link1.setText(title1);
            tv_no_links.setVisibility(View.GONE);
            tv_my_links.setVisibility(View.VISIBLE);
            ll_saved_links.setVisibility(View.VISIBLE);
            tv_link1.setOnClickListener(view1 -> {

                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(Link1)));


            });
        }

        if (link2){

            tv_link2.setVisibility(View.VISIBLE);
            tv_link2.setText(title2);
            tv_no_links.setVisibility(View.GONE);
            tv_my_links.setVisibility(View.VISIBLE);
            ll_saved_links.setVisibility(View.VISIBLE);
            tv_link2.setOnClickListener(view1 -> {

                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(Link2)));

            });

        }
        //save links--------------------------------------------------------


        return view;
    }//on create view =========================================

     //save links----------------------------------------------
    public void save_Links(){

        SharedPreferences user_link1 = requireActivity().getSharedPreferences("user_link1", Context.MODE_PRIVATE);
        SharedPreferences user_link2 = requireActivity().getSharedPreferences("user_link2", Context.MODE_PRIVATE);

        String title1 = user_link1.getString("title", "No title");
        String description1 = user_link1.getString("description", "No description");
        String Link1 = user_link1.getString("link","No link");

        String title2 = user_link2.getString("title", "No title");
        String description2 = user_link2.getString("title", "No description");
        String Link2 = user_link1.getString("link","No link");

        if (title1.contains("No title")){

            link1 = false;

        }else{

            link1 = true;

        }

        if (title2.contains("No title")){

            link2 = false;


        }else {
            link2 = true;

        }

    }


    //popular --------------------------------------------------------
    private void item_data(String url){

        Gson gson = new Gson();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                new Handler(Looper.getMainLooper()).post(() -> {

                    sl_refresh.setRefreshing(false);

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

                            item_list.clear();
                            for (int i = 0; i < items.size(); i++) {

                                Item item = items.get(i);

                                map = new HashMap<>();
                                map.put("item_name", item.getItem_name());
                                map.put("item_pic", item.getItem_pic());
                                map.put("endLink", item.getEndLink());
                                item_list.add(map);
                            }

                            new Handler(Looper.getMainLooper()).post(() -> {

                                plAdapter.notifyDataSetChanged();
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

    //viewPager images--------------------------------------------
     private void viewPager_img(String url){

        Gson gson = new Gson();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).build();

         client.newCall(request).enqueue(new Callback() {
             @Override
             public void onFailure(@NonNull Call call, @NonNull IOException e) {

                 new Handler(Looper.getMainLooper()).post(() -> {

                     sl_refresh.setRefreshing(false);

                 });

             }

             @Override
             public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                 if (response.isSuccessful() && response.body() != null){

                     String data = response.body().string();

                     try {

                         Item_data itemData = gson.fromJson(data, Item_data.class);

                         if (itemData.getStatus().contains("Successful")) {

                             List<Item> items = itemData.getItem();

                             image_list.clear();
                             for (int i = 0; i < items.size(); i++) {

                                 Item item = items.get(i);

                                 img_map = new HashMap<>();
                                 img_map.put("vp_image", item.getImage());
                                 image_list.add(img_map);
                             }

                             new Handler(Looper.getMainLooper()).post(() -> {

                                 viewPagerAdapter.notifyDataSetChanged();
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

     @Override
     public void onDestroy() {
         super.onDestroy();
         handler.removeCallbacksAndMessages(null);
     }
 }//public class===========================================