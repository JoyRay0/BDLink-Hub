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

public class RangpurCityActivity extends AppCompatActivity {


    //XML id's----------------------------------------------

    ImageButton back;

    ListView listView;

    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;

    //XML id's----------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rangpur_city);

        //identity period------------------------------------------
        back = findViewById(R.id.back);
        listView = findViewById(R.id.listview);

        //identity period------------------------------------------

        //Adpter-------------------------------------


        hashmap7();

        Myadpter7 myadpter7 = new Myadpter7();
        listView.setAdapter(myadpter7);

        //Adpter-------------------------------------


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(RangpurCityActivity.this, MainActivity.class));

            }
        });

    }//on create======================
    public class Myadpter7 extends BaseAdapter {


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

            String jela7 = hashMap1.get("jela");

            tvDisplay.setText(jela7);

            item_jela.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    startActivity(new Intent(RangpurCityActivity.this, TvDisplayActivity.class));



                }
            });

            return view1;
        }
    }

    public void hashmap7(){
        hashMap = new HashMap<>();
        hashMap.put("jela","রংপুর");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","নীলফামারী");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","গাইবান্ধা");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","কুড়িগ্রাম");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","দিনাজপুর");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","লালমনিরহাট");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","ঠাকুরগাঁও");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","পঞ্চগড়");
        arrayList.add(hashMap);


    }

}//public class======================