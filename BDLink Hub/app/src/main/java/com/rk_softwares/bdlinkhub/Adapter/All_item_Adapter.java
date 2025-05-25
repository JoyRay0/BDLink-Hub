package com.rk_softwares.bdlinkhub.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.rk_softwares.bdlinkhub.Activity.Act_ItemLinks;
import com.rk_softwares.bdlinkhub.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class All_item_Adapter extends RecyclerView.Adapter<All_item_Adapter.myViewholder> {      //RecycleView.ViewHolder

    private Context context;
    List<HashMap<String, String>> list;

    public All_item_Adapter(Context context, List<HashMap<String, String>> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view1 =  LayoutInflater.from(context).inflate(R.layout.lay_links_icon, parent, false);

        return new myViewholder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewholder holder, int position) {

        HashMap<String,String> hashMap = list.get(position);

        holder.tv_icon_text.setText(hashMap.get("item_name"));
        Picasso.get().load(hashMap.get("item_pic")).into(holder.iv_pl_icon);

        holder.iv_pl_icon.setOnClickListener(view -> {

            Intent intent = new Intent(context, Act_ItemLinks.class);
            String po = String.valueOf(position);
            intent.putExtra("position", po);
            context.startActivity(intent);

        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class myViewholder extends RecyclerView.ViewHolder{

        private AppCompatImageView iv_pl_icon;
        private AppCompatTextView tv_icon_text;

        public myViewholder(@NonNull View itemView) {
            super(itemView);

            iv_pl_icon = itemView.findViewById(R.id.iv_pl_icon);
            tv_icon_text = itemView.findViewById(R.id.tv_icon_text);

        }
    }
}
