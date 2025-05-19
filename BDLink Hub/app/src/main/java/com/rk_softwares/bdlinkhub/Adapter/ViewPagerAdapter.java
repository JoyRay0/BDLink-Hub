package com.rk_softwares.bdlinkhub.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.rk_softwares.bdlinkhub.R;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.Vholder> {


    private Context context;
    private int[] images;

    public ViewPagerAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public Vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.lay_view_pager_images, parent, false);

        return new Vholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vholder holder, int position) {


        holder.iv_viewpager.setImageResource(images[position]);

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class Vholder extends RecyclerView.ViewHolder{

        AppCompatImageView iv_viewpager;

        public Vholder(@NonNull View itemView) {
            super(itemView);

            iv_viewpager = itemView.findViewById(R.id.iv_viewpager);

        }
    }

}
