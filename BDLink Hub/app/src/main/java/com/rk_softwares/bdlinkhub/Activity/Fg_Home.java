 package com.rk_softwares.bdlinkhub.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;


import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.rk_softwares.bdlinkhub.Adapter.MyAdapter;
import com.rk_softwares.bdlinkhub.Adapter.Popular_item_Adapter;
import com.rk_softwares.bdlinkhub.Adapter.ViewPagerAdapter;
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

     private ViewPagerAdapter viewPagerAdapter;

     int[] images = {R.drawable.img_test2, R.drawable.img_test, R.drawable.img_google_login};

     private boolean link1 = false;
     private boolean link2 = false;

     int currentItem = 0;

     private TabLayout tl_dot;

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


        //identity period------------------------------------------


        //adapter = new MyAdapter(requireActivity(), list);
        //rv_test.setAdapter(adapter);
        //rv_test.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));

        //grid view---------------------------------------
        plAdapter = new Popular_item_Adapter(requireActivity(), item_list);
        gd_item.setAdapter(plAdapter);
        //popular_item();
        item_data();
        //grid view---------------------------------------

        //image slider------------------------------------------
        viewPagerAdapter = new ViewPagerAdapter(requireActivity(), images);
        vp_img.setAdapter(viewPagerAdapter);



        handler  = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                currentItem = (currentItem + 1) % images.length;
                vp_img.setCurrentItem(currentItem, true);
                handler.postDelayed(this, 5000);



            }
        };

        handler.postDelayed(runnable, 2000);

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




        //chcek();

        return view;
    }//on create view =========================================


    //checking network--------------------------------------------------------

    private void chcek(){

       new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
           @Override
           public void run() {

               Dialog dialog = new Dialog(getContext());
               dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
               dialog.setContentView(R.layout.lay_bottom_sheet_dialog);
               dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
               //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
               dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
               dialog.getWindow().setGravity(Gravity.BOTTOM);
               dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
               dialog.show();

               //bottom dialog interface--------------------------------------------------------

               AppCompatButton no_button = dialog.findViewById(R.id.no_button);
               AppCompatButton yes_button = dialog.findViewById(R.id.yes_button);

               no_button.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {

                       dialog.dismiss();

                   }
               });

               yes_button.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {


                       dialog.dismiss();

                   }
               });
           }

       },2000);

    }



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
    private void item_data(){

        Gson gson = new Gson();

        OkHttpClient client = new OkHttpClient();

        String url = "https://rksoftwares.xyz/All_app/BDLink_Hub/Api/item_links?res=popular_item";

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful() && response.body() != null){

                    String data = response.body().string();

                    try {

                        Item_data itemData = gson.fromJson(data, Item_data.class);

                        if (itemData.getStatus().contains("Successful")) {

                            List<Item> items = itemData.getItem();

                            for (int i = 0; i < items.size(); i++) {

                                Item item = items.get(i);

                                map = new HashMap<>();
                                map.put("item_name", item.getItem_name());
                                map.put("item_pic", item.getItem_pic());
                                item_list.add(map);
                            }

                            new Handler(Looper.getMainLooper()).post(() -> {

                                plAdapter.notifyDataSetChanged();

                            });

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        /*
                        new Handler(Looper.getMainLooper()).post(() -> {

                            Toast.makeText(requireActivity(), ""+e, Toast.LENGTH_SHORT).show();

                        });
                         */
                    }

                }

            }
        });
        
    }



     //popular item------------------------------------------------
     private void popular_item(){

         map = new HashMap<>();
         map.put("item_name", "AI");
         map.put("item_pic", getString(R.string.ai_images));
         item_list.add(map);

         map = new HashMap<>();
         map.put("item_name", "পএিকা");
         map.put("item_pic", getString(R.string.news));
         item_list.add(map);

         map = new HashMap<>();
         map.put("item_name", "চাকরি");
         map.put("item_pic", getString(R.string.job));
         item_list.add(map);

         map = new HashMap<>();
         map.put("item_name", "বিনোদন ও টিভি");
         map.put("item_pic", getString(R.string.tv));
         item_list.add(map);

         map = new HashMap<>();
         map.put("item_name", "ইসলামিক সেবা");
         map.put("item_pic", getString(R.string.muslim));
         item_list.add(map);

         map = new HashMap<>();
         map.put("item_name", "শপিং");
         map.put("item_pic", getString(R.string.e_commerce));
         item_list.add(map);

         map = new HashMap<>();
         map.put("item_name", "টিকিট বুকিং");
         map.put("item_pic", getString(R.string.bus));
         item_list.add(map);

         map = new HashMap<>();
         map.put("item_name", "রাইড শেয়ারিং");
         map.put("item_pic", getString(R.string.ride));
         item_list.add(map);

     }


     @Override
     public void onDestroy() {
         super.onDestroy();
         handler.removeCallbacksAndMessages(null);
     }
 }//public class===========================================