package com.rk_softwares.bdlinkhub.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rk_softwares.bdlinkhub.Database.Favorite;
import com.rk_softwares.bdlinkhub.R;
import com.rk_softwares.bdlinkhub.Utils.OnItemDeletedListener;

import java.util.HashMap;
import java.util.List;

public class Favorite_item extends RecyclerView.Adapter<Favorite_item.favorite>{

    private Context context;
    private List<HashMap<String, String>> hashMapList;
    private Favorite favorite;
    private OnItemDeletedListener itemDelete;

    public Favorite_item(Context context, List<HashMap<String, String>> hashMapList) {
        this.context = context;
        this.hashMapList = hashMapList;
        this.favorite = new Favorite(context);

    }

    @NonNull
    @Override
    public favorite onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.lay_link, parent, false);

        return new favorite(view);
    }

    @Override
    public void onBindViewHolder(@NonNull favorite holder, int position) {

        HashMap<String, String> hashMap = hashMapList.get(position);

        String cat = hashMap.get("category");
        String title = hashMap.get("title");
        String des = hashMap.get("description");
        String link = hashMap.get("link");

        holder.tv_cat.setText(cat);
        holder.tv_title.setText(title);
        holder.tv_description.setText(des);

        holder.cv_view.setOnClickListener(view -> {

            context.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(link)));

        });

        holder.iv_favorite.setImageResource(R.drawable.ic_delete2);

        holder.iv_favorite.setOnClickListener(view -> {

            if (title != null && !title.isEmpty() && link != null && !link.isEmpty()){

                boolean deleted = favorite.delete(title, link);

                Toast.makeText(context, "ডিলিট হয়েছে", Toast.LENGTH_SHORT).show();

                if (deleted){

                  hashMapList.remove(position);
                  notifyItemRemoved(position);
                  notifyItemRangeChanged(position, hashMapList.size());

                }else {

                    Toast.makeText(context, "ডিলিট হয়নি", Toast.LENGTH_SHORT).show();

                }

            }else {

                Toast.makeText(context, "শূন্য তথ্য!", Toast.LENGTH_SHORT).show();

            }

        });

    }

    @Override
    public int getItemCount() {
        return hashMapList.size();
    }

    public class favorite extends RecyclerView.ViewHolder{

        private CardView cv_view;
        private AppCompatTextView tv_cat,tv_title, tv_description;
        private AppCompatImageView iv_favorite;

        public favorite(@NonNull View itemView) {
            super(itemView);

            cv_view = itemView.findViewById(R.id.cv_view);
            tv_cat = itemView.findViewById(R.id.tv_cat);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_description = itemView.findViewById(R.id.tv_description);
            iv_favorite = itemView.findViewById(R.id.iv_favorite);

        }
    }
}
