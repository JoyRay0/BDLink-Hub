package com.rk_softwares.bdlinkhub.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.rk_softwares.bdlinkhub.R;

import java.util.HashMap;
import java.util.List;

public class Item_link extends RecyclerView.Adapter{

    private Context context;
    private List<HashMap<String, String>> list;


    private static final int LINK = 0;
    private static final int AD = 1;

    public Item_link(Context context, List<HashMap<String, String>> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == LINK){

            View link = LayoutInflater.from(context).inflate(R.layout.lay_link_view, parent, false);
            return new LinkHolder(link);

        }else if (viewType == AD){

            View ad  = LayoutInflater.from(context).inflate(R.layout.lay_native_ad, parent, false);
            return new AdHolder(ad);

        }else return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        HashMap<String, String> hashMap = list.get(position);

        if (getItemViewType(position) == LINK){

            LinkHolder viewholder1 = (LinkHolder) holder;

            String cat = hashMap.get("category");
            String title = hashMap.get("title");
            String des = hashMap.get("description");
            String link = hashMap.get("link");

            viewholder1.tv_cat.setText(cat);
            viewholder1.tv_title.setText(title);
            viewholder1.tv_description.setText(des);

        } else if (getItemViewType(position) == AD) {

            AdHolder adHolder = (AdHolder) holder;


        }


    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    @Override
    public int getItemViewType(int position) {

        HashMap<String, String> hashMap = list.get(position);

        String itemType = hashMap.get("item_type");

        if (itemType != null && itemType.contains("link")) {

            return LINK;

        } else {
            return AD;
        }
    }
    public static class LinkHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout cl_view;
        private AppCompatTextView tv_cat, tv_title, tv_description;
        private AppCompatImageButton favorite;

        public LinkHolder(@NonNull View itemView) {
            super(itemView);

            cl_view = itemView.findViewById(R.id.cl_view);
            tv_cat = itemView.findViewById(R.id.tv_cat);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_description = itemView.findViewById(R.id.tv_description);
            favorite = itemView.findViewById(R.id.favorite);


        }
    }


    //creating native ads-------------------------------
    public static class AdHolder extends RecyclerView.ViewHolder{

        private AppCompatTextView tv;

        public AdHolder(@NonNull View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.tv);

        }
    }
}
