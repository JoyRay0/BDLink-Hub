 package com.rk_softwares.bdlinkhub.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.rk_softwares.bdlinkhub.R;
import com.rk_softwares.bdlinkhub.Service.NetworkChangeReceiver;
import com.rk_softwares.bdlinkhub.Service.NetworkUtils;


 public class Home_fragment extends Fragment {

    //XML id's-----------------------------------------------

     private NetworkChangeReceiver receiver;

    //XML id's-----------------------------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        //identity period---------------------------------------------

        //identity period---------------------------------------------

        //checkNetwork();

        receiver = new NetworkChangeReceiver();

        //chcek();

        return view;
    }//on create view =========================================


    //checking network--------------------------------------------------------
    private void checkNetwork(){                //checking the internet connection


        ConnectivityManager connectivityManager = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || networkInfo.isFailover()){



        }

    }
    private void chcek(){

       new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
           @Override
           public void run() {
               if (NetworkUtils.isInternetConnected(getActivity())){

                   Toast.makeText(getActivity(), "Connected", Toast.LENGTH_SHORT).show();

               }else {
                   Dialog dialog = new Dialog(getContext());
                   dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                   dialog.setContentView(R.layout.bottom_sheet_dialog);
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
           }
       },2000);

    }

     @Override
     public void onResume() {
         super.onResume();

         receiver = new NetworkChangeReceiver();
         IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
         requireActivity().registerReceiver(receiver, filter);

     }

     @Override
     public void onPause() {
         super.onPause();
         requireActivity().unregisterReceiver(receiver);

     }
 }//public class===========================================