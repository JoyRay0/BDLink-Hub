package com.rk_softwares.bdlinkhub.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;


import com.rk_softwares.bdlinkhub.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class HotlineActivity extends AppCompatActivity {

    //XML id's -------------------------------------------------
    GridView hotline_gridview;

    ImageButton back;

    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

    HashMap<String, String> hashMap;



    //XML id's -------------------------------------------------

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotline_activity);

        //identity period ---------------------------------------------
        hotline_gridview = findViewById(R.id.hotline_gridview);

        //identity period ---------------------------------------------

        hashmap();
        hotlineadpter hotlineadpter = new hotlineadpter();
        hotline_gridview.setAdapter(hotlineadpter);









    }//on create ended==================================

    public class hotlineadpter extends BaseAdapter{


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
            View view1 = layoutInflater.inflate(R.layout.hotline_view_degsin, viewGroup, false);

            Animation animation = AnimationUtils.loadAnimation(HotlineActivity.this, R.anim.slide_in);
            view1.startAnimation(animation);

            HashMap<String, String > hashMap1 = arrayList.get(i);

            TextView hotline_text = view1.findViewById(R.id.hotline_text);
            TextView hotline_num_textview = view1.findViewById(R.id.hotline_num_textview);
            AppCompatImageView hotline_img = view1.findViewById(R.id.hotline_img);
            CardView call_cardview = view1.findViewById(R.id.call_cardview);

            String hotline_text1 = hashMap1.get("officer");
            String hotline_num_textview1 = hashMap1.get("number");
            String img1 = hashMap1.get("img");



            hotline_text.setText(hotline_text1);
            hotline_num_textview.setText(hotline_num_textview1);

            Picasso.get().load(img1).placeholder(R.drawable.baseline_call_25).into(hotline_img);




            /*

            if (MainActivity.PERMISSION == true){  //checking permission

                call_cardview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent call_intent = new Intent(Intent.ACTION_DIAL);
                        call_intent.setData(Uri.parse("tel:"+hotline_num_textview1));
                        startActivity(call_intent);

                    }
                });

            }

             */



            return view1;
        }
    }//base adapter========================

    private void hashmap(){

        String hotline_numbers = getString(R.string.hotline_numbers);
        String hotline_officer = getString(R.string.hotline_officer);
        String customer_service_img1 = getString(R.string.customer_service_img);

        String hotline_numbers1 = getString(R.string.hotline_numbers1);
        String hotline_officer1 = getString(R.string.hotline_officer1);
        String baby_img2 = getString(R.string.baby_img);

        String hotline_numbers2 = getString(R.string.hotline_numbers2);
        String hotline_officer2 = getString(R.string.hotline_officer2);
        String find_img3 = getString(R.string.find_img);

        String hotline_numbers3 = getString(R.string.hotline_numbers3);
        String hotline_officer3 = getString(R.string.hotline_officer3);
        String storm_img4 = getString(R.string.storm_img);

        String hotline_numbers4 = getString(R.string.hotline_numbers4);
        String hotline_officer4 = getString(R.string.hotline_officer4);
        String fire_img5 = getString(R.string.fire_img);

        String hotline_numbers5 = getString(R.string.hotline_numbers5);
        String hotline_officer5 = getString(R.string.hotline_officer5);
        String info_img6 = getString(R.string.info_img);

        String hotline_numbers6 = getString(R.string.hotline_numbers6);
        String hotline_officer6 = getString(R.string.hotline_officer6);
        String medical_img7 = getString(R.string.medical_img);

        String hotline_numbers7 = getString(R.string.hotline_numbers7);
        String hotline_officer7 = getString(R.string.hotline_officer7);
        String women_img8 = getString(R.string.women_img);

        String hotline_numbers8 = getString(R.string.hotline_numbers8);
        String hotline_officer8 = getString(R.string.hotline_officer8);
        String agriculture_img9 = getString(R.string.agriculture_img);

        String hotline_numbers9 = getString(R.string.hotline_numbers9);
        String hotline_officer9 = getString(R.string.hotline_officer9);
        String law_img10 = getString(R.string.law_img);

        String hotline_numbers10 = getString(R.string.hotline_numbers10);
        String hotline_officer10 = getString(R.string.hotline_officer10);
        String union_img11 = getString(R.string.union_img);

        String hotline_numbers11 = getString(R.string.hotline_numbers11);
        String hotline_officer11 = getString(R.string.hotline_officer11);
        String identity_img12 = getString(R.string.identity_img);

        String hotline_numbers12 = getString(R.string.hotline_numbers12);
        String hotline_officer12 = getString(R.string.hotline_officer12);
        String human_right_img13 = getString(R.string.human_right_img);

        String hotline_numbers13 = getString(R.string.hotline_numbers13);
        String hotline_officer13 = getString(R.string.hotline_officer13);
        String train_info14 = getString(R.string.train_info);

        String hotline_numbers14 = getString(R.string.hotline_numbers14);
        String hotline_officer14 = getString(R.string.hotline_officer14);
        String telecommunication_img15 = getString(R.string.telecommunication_img);

        String hotline_numbers15 = getString(R.string.hotline_numbers15);
        String hotline_officer15 = getString(R.string.hotline_officer15);
        String telephone_img16 = getString(R.string.telephone_img);





        hashMap = new HashMap<>(); //999
        hashMap.put("number",hotline_numbers);
        hashMap.put("officer",hotline_officer);
        hashMap.put("img", customer_service_img1);
        arrayList.add(hashMap);

        hashMap = new HashMap<>(); //1098
        hashMap.put("number",hotline_numbers1);
        hashMap.put("officer",hotline_officer1);
        hashMap.put("img", baby_img2);
        arrayList.add(hashMap);

        hashMap = new HashMap<>(); //106
        hashMap.put("number",hotline_numbers2);
        hashMap.put("officer",hotline_officer2);
        hashMap.put("img", find_img3);
        arrayList.add(hashMap);

        hashMap = new HashMap<>(); //10941
        hashMap.put("number",hotline_numbers3);
        hashMap.put("officer",hotline_officer3);
        hashMap.put("img", storm_img4);
        arrayList.add(hashMap);

        hashMap = new HashMap<>(); //16163
        hashMap.put("number",hotline_numbers4);
        hashMap.put("officer",hotline_officer4);
        hashMap.put("img", fire_img5);
        arrayList.add(hashMap);

        hashMap = new HashMap<>(); //333
        hashMap.put("number",hotline_numbers5);
        hashMap.put("officer",hotline_officer5);
        hashMap.put("img", info_img6);
        arrayList.add(hashMap);

        hashMap = new HashMap<>(); //16263
        hashMap.put("number",hotline_numbers6);
        hashMap.put("officer",hotline_officer6);
        hashMap.put("img", medical_img7);
        arrayList.add(hashMap);

        hashMap = new HashMap<>(); //109
        hashMap.put("number",hotline_numbers7);
        hashMap.put("officer",hotline_officer7);
        hashMap.put("img", women_img8);
        arrayList.add(hashMap);

        hashMap = new HashMap<>(); //16123
        hashMap.put("number",hotline_numbers8);
        hashMap.put("officer",hotline_officer8);
        hashMap.put("img", agriculture_img9);
        arrayList.add(hashMap);

        hashMap = new HashMap<>(); //16430
        hashMap.put("number",hotline_numbers9);
        hashMap.put("officer",hotline_officer9);
        hashMap.put("img", law_img10);
        arrayList.add(hashMap);

        hashMap = new HashMap<>(); //16256
        hashMap.put("number",hotline_numbers10);
        hashMap.put("officer",hotline_officer10);
        hashMap.put("img", union_img11);
        arrayList.add(hashMap);

        hashMap = new HashMap<>(); //105
        hashMap.put("number",hotline_numbers11);
        hashMap.put("officer",hotline_officer11);
        hashMap.put("img", identity_img12);
        arrayList.add(hashMap);

        hashMap = new HashMap<>(); //16108
        hashMap.put("number",hotline_numbers12);
        hashMap.put("officer",hotline_officer12);
        hashMap.put("img", human_right_img13);
        arrayList.add(hashMap);

        hashMap = new HashMap<>(); //131
        hashMap.put("number",hotline_numbers13);
        hashMap.put("officer",hotline_officer13);
        hashMap.put("img", train_info14);
        arrayList.add(hashMap);

        hashMap = new HashMap<>(); //100
        hashMap.put("number",hotline_numbers14);
        hashMap.put("officer",hotline_officer14);
        hashMap.put("img", telecommunication_img15);
        arrayList.add(hashMap);


        hashMap = new HashMap<>(); //16420
        hashMap.put("number",hotline_numbers15);
        hashMap.put("officer",hotline_officer15);
        hashMap.put("img", telephone_img16);
        arrayList.add(hashMap);


    }//hashmap==============================

    @Override
    protected void onRestart() {
        super.onRestart();

        Intent intent = new Intent(this, Act_Home_activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}//public class ended===================================