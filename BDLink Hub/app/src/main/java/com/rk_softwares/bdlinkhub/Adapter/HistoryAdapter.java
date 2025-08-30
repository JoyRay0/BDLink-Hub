package com.rk_softwares.bdlinkhub.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.rk_softwares.bdlinkhub.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.historyViewHolder> {

    private List<HashMap<String, String>> list;

    private Context context;

    private OnItemClickListener listener;

    public HistoryAdapter(Context context, List<HashMap<String, String>> list) {
        this.list =list;
        this.context = context;
    }

    public interface OnItemClickListener{

        void onItemClick(String history_text);

    }

    public void setOnItemClickListener(OnItemClickListener listener){

        this.listener = listener;

    }


    @NonNull
    @Override
    public historyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.lay_search_history, parent, false);

        return new historyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull historyViewHolder holder, int position) {

        HashMap<String, String> hashMap = list.get(position);
        holder.tv_history.setText(hashMap.get("history"));

        holder.itemView.setOnClickListener(view -> {

            if (listener != null){

                listener.onItemClick(hashMap.get("history"));
            }

        });

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public static class historyViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView tv_history;


        public historyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_history = itemView.findViewById(R.id.tv_history);
        }
    }

}
