package com.rk_softwares.bdlinkhub.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.rk_softwares.bdlinkhub.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.Vholder> {


    private Context context;
    private List<HashMap<String, String>> img_list;

    public ViewPagerAdapter(Context context, List<HashMap<String, String>> img_list) {
        this.context = context;
        this.img_list = img_list;
    }

    @NonNull
    @Override
    public Vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.lay_view_pager_images, parent, false);

        return new Vholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vholder holder, int position) {

        HashMap<String, String> map = img_list.get(position);

        String img = map.get("vp_image");

        Picasso.get().load(img).into(holder.iv_viewpager);

    }

    @Override
    public int getItemCount() {
        return img_list.size();
    }

    public class Vholder extends RecyclerView.ViewHolder{

        AppCompatImageView iv_viewpager;

        public Vholder(@NonNull View itemView) {
            super(itemView);

            iv_viewpager = itemView.findViewById(R.id.iv_viewpager);

        }
    }

}
