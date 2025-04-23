 package com.rk_softwares.bdlinkhub.Activity;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;


import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.rk_softwares.bdlinkhub.Adapter.MyAdapter;
import com.rk_softwares.bdlinkhub.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


 public class Fg_Home extends Fragment {

    //XML id's-----------------------------------------------

     //10

     RecyclerView rv_test;

     MyAdapter adapter;

     List<HashMap<String, String>> list = new ArrayList<>();
     HashMap<String, String> hashMap;

    //XML id's-----------------------------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fg_home, container, false);

        //identity period---------------------------------------------

        rv_test = view.findViewById(R.id.rv_test);

        //identity period---------------------------------------------

        //checkNetwork();

        adapter = new MyAdapter(requireActivity(), list);
        rv_test.setAdapter(adapter);
        rv_test.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));


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


 }//public class===========================================