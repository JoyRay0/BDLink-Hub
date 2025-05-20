package com.rk_softwares.bdlinkhub.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.rk_softwares.bdlinkhub.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class Popular_item_Adapter extends BaseAdapter {

    private List<HashMap<String, String>> list;
    private Context context;

    public Popular_item_Adapter(Context context, List<HashMap<String, String>> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

       @SuppressLint("ViewHolder")
       View view1 =  LayoutInflater.from(context).inflate(R.layout.lay_links_icon, viewGroup, false);

         AppCompatImageView iv_pl_icon = view1.findViewById(R.id.iv_pl_icon);
         AppCompatTextView tv_icon_text = view1.findViewById(R.id.tv_icon_text);

         HashMap<String, String> hashMap = list.get(position);

         String item_name = hashMap.get("item_name");
         String item_pic = hashMap.get("item_pic");

         tv_icon_text.setText(item_name);
         Picasso.get().load(item_pic).into(iv_pl_icon);


        return view1;
    }
}
