package com.rk_softwares.bdlinkhub.Activity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rk_softwares.bdlinkhub.Adapter.Search;
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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Fg_search_view extends Fragment {
    // XML id's---------------------------------------------

    private AppCompatAutoCompleteTextView ATV_search;
    private RecyclerView rv_search_item;

    private List<HashMap<String, String>> hashMapList = new ArrayList<>();
    private HashMap<String, String> hashMap;

    private Search search;
    private ProgressBar pb;

    // XML id's---------------------------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fg_search_view, container, false);

        //identity period----------------------------------------------

        ATV_search = view.findViewById(R.id.ATV_search);
        rv_search_item = view.findViewById(R.id.rv_search_item);
        pb = view.findViewById(R.id.pb);

        //identity period----------------------------------------------

        search = new Search(requireActivity(), hashMapList);
        rv_search_item.setAdapter(search);
        rv_search_item.setLayoutManager(new LinearLayoutManager(requireActivity()));


        send_data();

        return view;
    }//on create=====================

    //send search data------------------------------------------
    private void send_data(){


        ATV_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if (i == EditorInfo.IME_ACTION_SEARCH){

                    InputMethodManager manager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

                    pb.setVisibility(View.VISIBLE);

                    if (manager != null){
                        manager.hideSoftInputFromWindow(textView.getWindowToken(),0 );
                    }

                    String ed_search = ATV_search.getText().toString().trim();

                    if (ed_search == null || ed_search.isEmpty()){

                        Toast.makeText(requireActivity(), "Invalid keyword", Toast.LENGTH_SHORT).show();

                    }else {

                        Request_link link = new Request_link(new ApiResponseListener() {
                            @Override
                            public void onApiResponse(c_api_config config) {

                                String url = config.getSearch();

                                search(url+ed_search);

                                new Handler(Looper.getMainLooper()).post(() -> {

                                    Toast.makeText(requireActivity(), ""+url+ed_search, Toast.LENGTH_SHORT).show();

                                });

                            }

                            @Override
                            public void onApiFailed(String error) {

                                new Handler(Looper.getMainLooper()).post(() -> {

                                    pb.setVisibility(View.GONE);
                                    Toast.makeText(requireActivity(), "অনুগ্রহ করে আপনার ইন্টারনেট কানেকশন চেক করুন", Toast.LENGTH_SHORT).show();

                                });


                            }
                        });
                        link.Apis();

                    }

                    return true;
                }

                return false;
            }
        });




    }

    //search -----------------------------------------
    private void search(String url){

        Gson gson = new Gson();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {



            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful() && response.body() != null){

                    String link = response.body().string();


                    try {

                        m_All_data allData = gson.fromJson(link, m_All_data.class);

                        hashMapList.clear();

                        if (allData.getStatus().contains("successful")){

                            List<c_all_data> data = allData.getData();

                            //mapList.clear();

                            for (int i = 0; i < data.size(); i++){

                                c_all_data data1 = data.get(i);

                                hashMap = new HashMap<>();
                                hashMap.put("item_type","link");
                                hashMap.put("category", data1.getCategory());
                                hashMap.put("title", data1.getTitle());
                                hashMap.put("description", data1.getDescription());
                                hashMap.put("link", data1.getLink());
                                hashMapList.add(hashMap);

                            }

                            new Handler(Looper.getMainLooper()).post(() -> {
                                pb.setVisibility(View.GONE);
                                search.notifyDataSetChanged();

                            });

                        }else {

                            if (allData.getStatus().equals("Failed")){

                                new Handler(Looper.getMainLooper()).post(() -> {

                                    pb.setVisibility(View.GONE);
                                    search.notifyDataSetChanged();
                                    Toast.makeText(requireActivity(), ""+allData.getMessage(), Toast.LENGTH_SHORT).show();

                                });

                            }

                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                }

            }
        });

    }

}//public class =========================