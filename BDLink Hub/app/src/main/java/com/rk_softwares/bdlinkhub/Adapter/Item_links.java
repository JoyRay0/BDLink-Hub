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

public class Item_links extends RecyclerView.Adapter<Item_links.link> {

    private Context context;
    private List<HashMap<String, String>> list;

    public Item_links(Context context, List<HashMap<String, String>> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public link onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.lay_link_view, parent, false);

        return new link(view);
    }

    @Override
    public void onBindViewHolder(@NonNull link holder, int position) {

        HashMap<String, String> hashMap = list.get(position);

        String cat = hashMap.get("category");
        String title = hashMap.get("title");
        String des = hashMap.get("description");
        String link = hashMap.get("link");

        holder.tv_cat.setText(cat);
        holder.tv_title.setText(title);
        holder.tv_description.setText(des);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class link extends RecyclerView.ViewHolder{

        private ConstraintLayout cl_view;
        private AppCompatTextView tv_cat, tv_title, tv_description;
        private AppCompatImageButton favorite;

        public link(@NonNull View itemView) {
            super(itemView);

            cl_view = itemView.findViewById(R.id.cl_view);
            tv_cat = itemView.findViewById(R.id.tv_cat);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_description = itemView.findViewById(R.id.tv_description);
            favorite = itemView.findViewById(R.id.favorite);

        }
    }

}
