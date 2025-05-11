package com.rk_softwares.bdlinkhub.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.appcompat.widget.AppCompatImageView;

import com.rk_softwares.bdlinkhub.R;

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
        return 6;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class Viewholder{

        AppCompatImageView iv_pl_icon;

    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        Viewholder viewholder;

        if (view == null){

           view =  LayoutInflater.from(context).inflate(R.layout.lay_pouplar_links, viewGroup, false);

           viewholder = new Viewholder();
           viewholder.iv_pl_icon = view.findViewById(R.id.iv_pl_icon);

           view.setTag(viewholder);

        }else {

            viewholder = (Viewholder) view.getTag();

        }

        return view;
    }
}
