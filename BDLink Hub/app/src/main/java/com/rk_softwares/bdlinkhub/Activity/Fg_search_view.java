package com.rk_softwares.bdlinkhub.Activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rk_softwares.bdlinkhub.Api.Request_link;
import com.rk_softwares.bdlinkhub.Utils.ApiResponseListener;
import com.rk_softwares.bdlinkhub.Model.Api_config;
import com.rk_softwares.bdlinkhub.R;


public class Fg_search_view extends Fragment {
    // XML id's---------------------------------------------

    // XML id's---------------------------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fg_search_view, container, false);

        //identity period----------------------------------------------

        //identity period----------------------------------------------

        Request_link requestLink = new Request_link(new ApiResponseListener() {
            @Override
            public void onApiResponse(Api_config config) {




            }

            @Override
            public void onApiFailed(String error) {


            }
        });
        requestLink.Apis();



        return view;
    }//on create=====================

}//public class =========================