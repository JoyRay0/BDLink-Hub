package com.rk_softwares.bdemergencynumbers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class ChittagongCityActivity extends AppCompatActivity {


    //XML id's----------------------------------------------

    ImageButton back;

    ListView listView;

    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;

    //XML id's----------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chittagong_city);

        //identity period------------------------------------------
        back = findViewById(R.id.back);
        listView = findViewById(R.id.listview);

        //identity period------------------------------------------


        //Adpter-------------------------------------


        hashmap2();

        Myadpter2 myadpter2 = new Myadpter2();
        listView.setAdapter(myadpter2);

        //Adpter-------------------------------------


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ChittagongCityActivity.this, MainActivity.class));

            }
        });

    }//on create==========================

    public class Myadpter2 extends BaseAdapter {


        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view1 = layoutInflater.inflate(R.layout.jela_degsin, viewGroup, false);

            LinearLayout item_jela = view1.findViewById(R.id.item_jela);
            TextView tvDisplay = view1.findViewById(R.id.tvDisplay);

            HashMap<String, String> hashMap1 = arrayList.get(i);

            String jela2 = hashMap1.get("jela");

            tvDisplay.setText(jela2);

            item_jela.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    startActivity(new Intent(ChittagongCityActivity.this, TvDisplayActivity.class));



                }
            });

            return view1;
        }
    }

    public void hashmap2(){
        hashMap = new HashMap<>();
        hashMap.put("jela","চট্টগ্রাম");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","রাগামাটি");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","বান্দরবান");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","খাগড়াছড়ি");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","কক্সবাজার");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","লক্ষ্মীপুর");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","চাদপুর");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","ফেনী");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","নোয়াখালী");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","কুমিল্লা");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","ব্রক্ষ্মনবাড়িয়া");
        arrayList.add(hashMap);


    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}//public class=========================