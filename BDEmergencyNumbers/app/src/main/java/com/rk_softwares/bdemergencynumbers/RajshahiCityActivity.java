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

public class RajshahiCityActivity extends AppCompatActivity {


    //XML id's----------------------------------------------

    ImageButton back;
    ListView listView;

    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;

    //XML id's----------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rajshahi_city);

        //identity period------------------------------------------

        back = findViewById(R.id.back);
        listView = findViewById(R.id.listview);

        //identity period------------------------------------------

        //Adpter-------------------------------------


        hashmap6();

        Myadpter6 myadpter6 = new Myadpter6();
        listView.setAdapter(myadpter6);

        //Adpter-------------------------------------


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(RajshahiCityActivity.this, MainActivity.class));

            }
        });

    }//on create ==============================

    public class Myadpter6 extends BaseAdapter {


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

            String jela6 = hashMap1.get("jela");

            tvDisplay.setText(jela6);

            item_jela.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    startActivity(new Intent(RajshahiCityActivity.this, TvDisplayActivity.class));



                }
            });

            return view1;
        }
    }

    public void hashmap6(){
        hashMap = new HashMap<>();
        hashMap.put("jela","রাজশাহী");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","চাপাইনবাবগঞ্জ");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","নওগা");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","নাটোর");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","পাবনা");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","বগুড়া");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","সিরাজগঞ্জ");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","জয়পুরহাট");
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
}//public class ============================