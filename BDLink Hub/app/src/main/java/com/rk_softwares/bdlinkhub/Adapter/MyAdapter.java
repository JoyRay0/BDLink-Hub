package com.rk_softwares.bdlinkhub.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.rk_softwares.bdlinkhub.R;

import java.util.HashMap;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    int LINK1 = 0;
    int LINK2 = 1;

    private Context context;
    private List<HashMap<String, String>> itemList;

    public MyAdapter(Context context, List<HashMap<String, String>> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        if (viewType == LINK1){

            View view = LayoutInflater.from(context).inflate(R.layout.lay_link, parent, false);
            return new MyviewHolder(view);

        } else{

            View view = LayoutInflater.from(context).inflate(R.layout.lay_search_bar, parent, false);
            return new Mysearch(view);

        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        HashMap<String, String> item = itemList.get(position);

        if (getItemViewType(position) == LINK1){

           MyviewHolder holder1 = (MyviewHolder) holder;

            holder1.tv_cat.setText(item.get("cat"));
            holder1.tv_title.setText(item.get("title"));
            holder1.tv_description.setText(item.get("description"));

        }else if (getItemViewType(position) == LINK2){

            Mysearch mysearch = (Mysearch) holder;

            mysearch.ATV_search.setOnClickListener(view -> {

                Toast.makeText(context, "worked", Toast.LENGTH_SHORT).show();

            });

        }





    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{

        //XML id's------------------------------------------------

        AppCompatTextView tv_cat, tv_title, tv_description;
        //XML id's------------------------------------------------

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            //identity period--------------------------------------------

            tv_cat = itemView.findViewById(R.id.tv_cat);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_description = itemView.findViewById(R.id.tv_description);

            //identity period--------------------------------------------

        }
    }

    public class Mysearch extends RecyclerView.ViewHolder{

        //XML id's------------------------------------------------

        MaterialAutoCompleteTextView ATV_search;
        //XML id's------------------------------------------------

        public Mysearch(@NonNull View itemView) {
            super(itemView);

            //identity period--------------------------------------------
            ATV_search = itemView.findViewById(R.id.ATV_search);



            //identity period--------------------------------------------

        }
    }

    @Override
    public int getItemViewType(int position) {
        HashMap<String, String> map = itemList.get(position);

        String itemType = map.get("itemType");

        if (itemType.equals("1stLink")){

            return LINK1;

        } else if (itemType.equals("2ndLink")){

            return LINK2;

        }else return position;

    }
}


