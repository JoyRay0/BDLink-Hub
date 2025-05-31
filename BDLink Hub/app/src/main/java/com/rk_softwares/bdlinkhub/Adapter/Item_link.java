package com.rk_softwares.bdlinkhub.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.rk_softwares.bdlinkhub.R;

import java.util.HashMap;
import java.util.List;

public class Item_link extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<HashMap<String, String>> list;
    private int AD_NUMBER = 3;

    public Item_link(Context context, List<HashMap<String, String>> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == AD_NUMBER){

            return null;

        } else {

            View view = LayoutInflater.from(context).inflate(R.layout.lay_link_view, parent, false);
            return new viewholder(view);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class viewholder extends RecyclerView.ViewHolder{

        private ConstraintLayout cl_view;
        private AppCompatTextView tv_cat, tv_title, tv_description;
        private AppCompatImageView favorite;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            cl_view = itemView.findViewById(R.id.cl_view);
            tv_cat = itemView.findViewById(R.id.tv_cat);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_description = itemView.findViewById(R.id.tv_description);
            favorite = itemView.findViewById(R.id.favorite);


        }
    }


    //creating native ads-------------------------------
    public class adHolder extends RecyclerView.ViewHolder{

        public adHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
