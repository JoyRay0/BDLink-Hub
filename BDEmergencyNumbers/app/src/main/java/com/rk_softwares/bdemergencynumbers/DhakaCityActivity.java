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

public class DhakaCityActivity extends AppCompatActivity {

    //XML id's----------------------------------------------

    ImageButton back;

    ListView listView;

    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;



    //XML id's----------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dhaka_city);

        //identity period------------------------------------------

        back = findViewById(R.id.back);
        listView = findViewById(R.id.listview);


        //identity period------------------------------------------




        //Adpter-------------------------------------

         hashmap3();

         Myadpter3 myadpter3 = new Myadpter3();
         listView.setAdapter(myadpter3);

        //Adpter-------------------------------------


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DhakaCityActivity.this, MainActivity.class));

            }
        });





    }//on create ================================

    public class Myadpter3 extends BaseAdapter{


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




            String jela3 = hashMap1.get("jela");

            //police -----------------------------------------------




            String sub_text1 = hashMap1.get("sub_text1");
            String item_police_num1 = hashMap1.get("item_police_num1");

            String sub_text2 = hashMap1.get("sub_text2");
            String item_police_num2 = hashMap1.get("item_police_num2");

            String sub_text3 = hashMap1.get("sub_text3");
            String item_police_num3 = hashMap1.get("item_police_num3");

            String sub_text4 = hashMap1.get("sub_text4");
            String item_police_num4 = hashMap1.get("item_police_num4");

            String sub_text5 = hashMap1.get("sub_text5");
            String item_police_num5 = hashMap1.get("item_police_num5");

            String sub_text6 = hashMap1.get("sub_text6");
            String item_police_num6 = hashMap1.get("item_police_num6");

            String sub_text7 = hashMap1.get("sub_text7");
            String item_police_num7 = hashMap1.get("item_police_num7");

            String sub_text8 = hashMap1.get("sub_text8");
            String item_police_num8 = hashMap1.get("item_police_num8");

            String sub_text9 = hashMap1.get("sub_text9");
            String item_police_num9 = hashMap1.get("item_police_num9");

            String sub_text10 = hashMap1.get("sub_text10");
            String item_police_num10 = hashMap1.get("item_police_num10");

            String sub_text11 = hashMap1.get("sub_text11");
            String item_police_num11 = hashMap1.get("item_police_num11");

            String sub_text12 = hashMap1.get("sub_text12");
            String item_police_num12 = hashMap1.get("item_police_num12");

            String sub_text13 = hashMap1.get("sub_text13");
            String item_police_num13 = hashMap1.get("item_police_num13");

            String sub_text14 = hashMap1.get("sub_text14");
            String item_police_num14 = hashMap1.get("item_police_num14");

            String sub_text15 = hashMap1.get("sub_text15");
            String item_police_num15 = hashMap1.get("item_police_num15");

            String sub_text16 = hashMap1.get("sub_text16");
            String item_police_num16 = hashMap1.get("item_police_num16");

            String sub_text17 = hashMap1.get("sub_text17");
            String item_police_num17 = hashMap1.get("item_police_num17");

            String sub_text18 = hashMap1.get("sub_text18");
            String item_police_num18 = hashMap1.get("item_police_num18");

            String sub_text19 = hashMap1.get("sub_text19");
            String item_police_num19 = hashMap1.get("item_police_num19");

            String sub_text20 = hashMap1.get("sub_text20");
            String item_police_num20 = hashMap1.get("item_police_num20");

            String sub_text21 = hashMap1.get("sub_text21");
            String item_police_num21 = hashMap1.get("item_police_num21");

            String sub_text22 = hashMap1.get("sub_text22");
            String item_police_num22 = hashMap1.get("item_police_num22");

            String sub_text23 = hashMap1.get("sub_text23");
            String item_police_num23 = hashMap1.get("item_police_num23");

            String sub_text24 = hashMap1.get("sub_text24");
            String item_police_num24 = hashMap1.get("item_police_num24");

            String sub_text25 = hashMap1.get("sub_text25");
            String item_police_num25 = hashMap1.get("item_police_num25");

            String sub_text26 = hashMap1.get("sub_text26");
            String item_police_num26 = hashMap1.get("item_police_num26");

            String sub_text27 = hashMap1.get("sub_text27");
            String item_police_num27 = hashMap1.get("item_police_num27");

            String sub_text28 = hashMap1.get("sub_text28");
            String item_police_num28 = hashMap1.get("item_police_num28");

            String sub_text29 = hashMap1.get("sub_text29");
            String item_police_num29 = hashMap1.get("item_police_num29");

            String sub_text30 = hashMap1.get("sub_text30");
            String item_police_num30 = hashMap1.get("item_police_num30");

            String sub_text31 = hashMap1.get("sub_text31");
            String item_police_num31 = hashMap1.get("item_police_num31");

            String sub_text32 = hashMap1.get("sub_text32");
            String item_police_num32 = hashMap1.get("item_police_num32");

            String sub_text33 = hashMap1.get("sub_text33");
            String item_police_num33 = hashMap1.get("item_police_num33");

            String sub_text34 = hashMap1.get("sub_text34");
            String item_police_num34 = hashMap1.get("item_police_num34");

            String sub_text35 = hashMap1.get("sub_text35");
            String item_police_num35 = hashMap1.get("item_police_num35");

            String sub_text36 = hashMap1.get("sub_text36");
            String item_police_num36 = hashMap1.get("item_police_num36");

            String sub_text37 = hashMap1.get("sub_text37");
            String item_police_num37 = hashMap1.get("item_police_num37");

            String sub_text38 = hashMap1.get("sub_text38");
            String item_police_num38 = hashMap1.get("item_police_num38");

            String sub_text39 = hashMap1.get("sub_text39");
            String item_police_num39 = hashMap1.get("item_police_num39");

            String sub_text40 = hashMap1.get("sub_text40");
            String item_police_num40 = hashMap1.get("item_police_num40");

            String sub_text41 = hashMap1.get("sub_text41");
            String item_police_num41 = hashMap1.get("item_police_num41");

            String sub_text42 = hashMap1.get("sub_text42");
            String item_police_num42 = hashMap1.get("item_police_num42");

            String sub_text43 = hashMap1.get("sub_text43");
            String item_police_num43 = hashMap1.get("item_police_num43");

            String sub_text44 = hashMap1.get("sub_text44");
            String item_police_num44 = hashMap1.get("item_police_num44");

            String sub_text45 = hashMap1.get("sub_text45");
            String item_police_num45 = hashMap1.get("item_police_num45");

            String sub_text46 = hashMap1.get("sub_text46");
            String item_police_num46 = hashMap1.get("item_police_num46");

            String sub_text47 = hashMap1.get("sub_text47");
            String item_police_num47 = hashMap1.get("item_police_num47");

            String sub_text48 = hashMap1.get("sub_text48");
            String item_police_num48 = hashMap1.get("item_police_num48");

            String sub_text49 = hashMap1.get("sub_text49");
            String item_police_num49 = hashMap1.get("item_police_num49");

            String sub_text50 = hashMap1.get("sub_text50");
            String item_police_num50 = hashMap1.get("item_police_num50");




            //police -----------------------------------------------




            tvDisplay.setText(jela3);

            item_jela.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(DhakaCityActivity.this,TvDisplayActivity.class);
                    startActivity(intent);


                    //police-------------------------------------------


                    TvDisplayActivity.sub_text1 = sub_text1;
                    TvDisplayActivity.item_police_num1 = item_police_num1;

                    TvDisplayActivity.sub_text2 = sub_text2;
                    TvDisplayActivity.item_police_num2 = item_police_num2;

                    TvDisplayActivity.sub_text3 = sub_text3;
                    TvDisplayActivity.item_police_num3 = item_police_num3;

                    TvDisplayActivity.sub_text4 = sub_text4;
                    TvDisplayActivity.item_police_num4 = item_police_num4;

                    TvDisplayActivity.sub_text5 = sub_text5;
                    TvDisplayActivity.item_police_num5 = item_police_num5;

                    TvDisplayActivity.sub_text6 = sub_text6;
                    TvDisplayActivity.item_police_num6 = item_police_num6;

                    TvDisplayActivity.sub_text7 = sub_text7;
                    TvDisplayActivity.item_police_num7 = item_police_num7;

                    TvDisplayActivity.sub_text8 = sub_text8;
                    TvDisplayActivity.item_police_num8 = item_police_num8;

                    TvDisplayActivity.sub_text9 = sub_text9;
                    TvDisplayActivity.item_police_num9 = item_police_num9;

                    TvDisplayActivity.sub_text10 = sub_text10;
                    TvDisplayActivity.item_police_num10 = item_police_num10;

                    TvDisplayActivity.sub_text11 = sub_text11;
                    TvDisplayActivity.item_police_num11 = item_police_num11;

                    TvDisplayActivity.sub_text12 = sub_text12;
                    TvDisplayActivity.item_police_num12 = item_police_num12;

                    TvDisplayActivity.sub_text13 = sub_text13;
                    TvDisplayActivity.item_police_num13 = item_police_num13;

                    TvDisplayActivity.sub_text14 = sub_text14;
                    TvDisplayActivity.item_police_num14 = item_police_num14;

                    TvDisplayActivity.sub_text15 = sub_text15;
                    TvDisplayActivity.item_police_num15 = item_police_num15;

                    TvDisplayActivity.sub_text16 = sub_text16;
                    TvDisplayActivity.item_police_num16 = item_police_num16;

                    TvDisplayActivity.sub_text17 = sub_text17;
                    TvDisplayActivity.item_police_num17 = item_police_num17;

                    TvDisplayActivity.sub_text18 = sub_text18;
                    TvDisplayActivity.item_police_num18 = item_police_num18;

                    TvDisplayActivity.sub_text19 = sub_text19;
                    TvDisplayActivity.item_police_num19 = item_police_num19;

                    TvDisplayActivity.sub_text20 = sub_text20;
                    TvDisplayActivity.item_police_num20 = item_police_num20;

                    TvDisplayActivity.sub_text21 = sub_text21;
                    TvDisplayActivity.item_police_num21 = item_police_num21;

                    TvDisplayActivity.sub_text22 = sub_text22;
                    TvDisplayActivity.item_police_num22 = item_police_num22;

                    TvDisplayActivity.sub_text23 = sub_text23;
                    TvDisplayActivity.item_police_num23 = item_police_num23;

                    TvDisplayActivity.sub_text24 = sub_text24;
                    TvDisplayActivity.item_police_num24 = item_police_num24;

                    TvDisplayActivity.sub_text25 = sub_text25;
                    TvDisplayActivity.item_police_num25 = item_police_num25;

                    TvDisplayActivity.sub_text26 = sub_text26;
                    TvDisplayActivity.item_police_num26 = item_police_num26;

                    TvDisplayActivity.sub_text27 = sub_text27;
                    TvDisplayActivity.item_police_num27 = item_police_num27;

                    TvDisplayActivity.sub_text28 = sub_text28;
                    TvDisplayActivity.item_police_num28 = item_police_num28;

                    TvDisplayActivity.sub_text29 = sub_text29;
                    TvDisplayActivity.item_police_num29 = item_police_num29;

                    TvDisplayActivity.sub_text30 = sub_text30;
                    TvDisplayActivity.item_police_num30 = item_police_num30;

                    TvDisplayActivity.sub_text31 = sub_text31;
                    TvDisplayActivity.item_police_num31 = item_police_num31;

                    TvDisplayActivity.sub_text32 = sub_text32;
                    TvDisplayActivity.item_police_num32 = item_police_num32;

                    TvDisplayActivity.sub_text33 = sub_text33;
                    TvDisplayActivity.item_police_num33 = item_police_num33;

                    TvDisplayActivity.sub_text34 = sub_text34;
                    TvDisplayActivity.item_police_num34 = item_police_num34;

                    TvDisplayActivity.sub_text35 = sub_text35;
                    TvDisplayActivity.item_police_num35 = item_police_num35;

                    TvDisplayActivity.sub_text36 = sub_text36;
                    TvDisplayActivity.item_police_num36 = item_police_num36;

                    TvDisplayActivity.sub_text37 = sub_text37;
                    TvDisplayActivity.item_police_num37 = item_police_num37;

                    TvDisplayActivity.sub_text38 = sub_text38;
                    TvDisplayActivity.item_police_num38 = item_police_num38;

                    TvDisplayActivity.sub_text39 = sub_text39;
                    TvDisplayActivity.item_police_num39 = item_police_num39;

                    TvDisplayActivity.sub_text40 = sub_text40;
                    TvDisplayActivity.item_police_num40 = item_police_num40;

                    TvDisplayActivity.sub_text41 = sub_text41;
                    TvDisplayActivity.item_police_num41 = item_police_num41;

                    TvDisplayActivity.sub_text42 = sub_text42;
                    TvDisplayActivity.item_police_num42 = item_police_num42;

                    TvDisplayActivity.sub_text43 = sub_text43;
                    TvDisplayActivity.item_police_num43 = item_police_num43;

                    TvDisplayActivity.sub_text44 = sub_text44;
                    TvDisplayActivity.item_police_num44 = item_police_num44;

                    TvDisplayActivity.sub_text45 = sub_text45;
                    TvDisplayActivity.item_police_num45 = item_police_num45;

                    TvDisplayActivity.sub_text46 = sub_text46;
                    TvDisplayActivity.item_police_num46 = item_police_num46;

                    TvDisplayActivity.sub_text47 = sub_text47;
                    TvDisplayActivity.item_police_num47 = item_police_num47;

                    TvDisplayActivity.sub_text48 = sub_text48;
                    TvDisplayActivity.item_police_num48 = item_police_num48;

                    TvDisplayActivity.sub_text49 = sub_text49;
                    TvDisplayActivity.item_police_num49 = item_police_num49;

                    TvDisplayActivity.sub_text50 = sub_text50;
                    TvDisplayActivity.item_police_num50 = item_police_num50;


                    //police-------------------------------------------




                }
            });

            return view1;
        }
    }

    public void hashmap3(){


        hashMap = new HashMap<>();
        hashMap.put("jela","ঢাকা");

        //police station---------------------------------------

        hashMap.put("sub_text1","আদাবর থানা:");//
        hashMap.put("item_police_num1", "01713373183");

        hashMap.put("sub_text2","বাড্ডা থানা:");//
        hashMap.put("item_police_num2", "01713373173");

        hashMap.put("sub_text3","বনানী থানা:");//
        hashMap.put("item_police_num3", "01769058053");

        hashMap.put("sub_text4","বংশাল থানা:");//
        hashMap.put("item_police_num4", "01713398336");

        hashMap.put("sub_text5", "বিমানবন্দর থানা:");//
        hashMap.put("item_police_num5", "01713373162");

        hashMap.put("sub_text6","ক্যানন্টনমেন্ট থানা:");//
        hashMap.put("item_police_num6", "01713373172");

        hashMap.put("sub_text7","চকবাজার থানা:");//
        hashMap.put("item_police_num7", "01713398337");

        hashMap.put("sub_text8","দক্ষিণখান থানা:");//
        hashMap.put("item_police_num8", "01713373165");

        hashMap.put("sub_text9","দারুস সালাম থানা:");//
        hashMap.put("item_police_num9", "01713398334");

        hashMap.put("sub_text10","ডেমরা থানা:");//
        hashMap.put("item_police_num10", "01713373144");

        hashMap.put("sub_text11","ধানমন্ডি থানা:");//
        hashMap.put("item_police_num11", "01713373126");

        hashMap.put("sub_text12","গেন্ডারিয়া থানা:");//
        hashMap.put("item_police_num12", "01713398331");

        hashMap.put("sub_text13","গুলশান থানা:");//
        hashMap.put("item_police_num13", "01713373171");

        hashMap.put("sub_text14","হাজারীবাগ থানা:");//
        hashMap.put("item_police_num14", "01713373136");

        hashMap.put("sub_text15","যাএাবাড়ি থানা:");//
        hashMap.put("item_police_num15", "01713373146");

        hashMap.put("sub_text16","কাফরুল থানা:");//
        hashMap.put("item_police_num16", "01713373191");

        hashMap.put("sub_text17","কলাবাগান থানা:");//
        hashMap.put("item_police_num17", "01713398339");

        hashMap.put("sub_text18","কামরাঙ্গীরচর থানা:");//
        hashMap.put("item_police_num18", "01713373137");

        hashMap.put("sub_text19","হাতিরঝিল থানা:");//
        hashMap.put("item_police_num19", "01769695100");

        hashMap.put("sub_text20","খিলগাঁও থানা:");//
        hashMap.put("item_police_num20", "01713373154");

        hashMap.put("sub_text21","খিলক্ষেত থানা:"); //
        hashMap.put("item_police_num21", "01713373174");

        hashMap.put("sub_text22", "কদমতলী থানা:");//
        hashMap.put("item_police_num22", "01713398333");

        hashMap.put("sub_text23","কোতোয়ালি থানা:");//
        hashMap.put("item_police_num23", "01713373135");

        hashMap.put("sub_text24","লালবাগ থানা:");//
        hashMap.put("item_police_num24", "01713373134");

        hashMap.put("sub_text25","মিরপুর থানা:");//
        hashMap.put("item_police_num25", "01713373189");

        hashMap.put("sub_text26","মোহাম্মদপুর থানা:");//
        hashMap.put("item_police_num26", "01713373182");

        hashMap.put("sub_text27","মতিঝিল থানা:");//
        hashMap.put("item_police_num27", "01713373152");

        hashMap.put("sub_text28","মুগদা থানা:");//
        hashMap.put("item_police_num28", "01769058061");

        hashMap.put("sub_text29","নিউমার্কেট থানা:");//
        hashMap.put("item_police_num29", "01713373128");

        hashMap.put("sub_text30","পল্লবী থানা:");//
        hashMap.put("item_police_num30", "01713373190");

        hashMap.put("sub_text31","পল্টন থানা:");//
        hashMap.put("item_police_num31", "01713373155");

        hashMap.put("sub_text32","রমনা থানা:");//
        hashMap.put("item_police_num32", "01713373125");

        hashMap.put("sub_text33","রামপুরা থানা:");//
        hashMap.put("item_police_num33", "01713398526");

        hashMap.put("sub_text34","রুপনগর থানা:");//
        hashMap.put("item_police_num34", "01769058059");

        hashMap.put("sub_text35","সবুজবাগ থানা:");//
        hashMap.put("item_police_num35", "01713373153");

        hashMap.put("sub_text36","শাহ আলী থানা:");//
        hashMap.put("item_police_num36", "01713373192");

        hashMap.put("sub_text37","শাহবাগ থানা:");//
        hashMap.put("item_police_num37", "01713373127");

        hashMap.put("sub_text38", "শাহজানপুর থানা:");//
        hashMap.put("item_police_num38", "01769058063");

        hashMap.put("sub_text39","সূএাপুর থানা:");//
        hashMap.put("item_police_num39", "01713373143");

        hashMap.put("sub_text40","শ্যামপুর থানা:");//
        hashMap.put("item_police_num40", "01713373145");

        hashMap.put("sub_text41","শেরেবাংলা নগর থানা:");//
        hashMap.put("item_police_num41", "01713398335");

        hashMap.put("sub_text42","তেজগাঁও থানা:");//
        hashMap.put("item_police_num42", "01713373180");

        hashMap.put("sub_text43","তুরাগ থানা :");//
        hashMap.put("item_police_num43", "01713373163");

        hashMap.put("sub_text44","উওরা পূর্ব থানা:");//
        hashMap.put("item_police_num44", "01713373161");

        hashMap.put("sub_text45","উওরা পশ্চিম থানা:");//
        hashMap.put("item_police_num45", "01769058065");

        hashMap.put("sub_text46","উওরাখান থানা:");//
        hashMap.put("item_police_num46", "01713373164");

        hashMap.put("sub_text47","ভাসানটেক থানা:");//
        hashMap.put("item_police_num47", "01769058057");

        hashMap.put("sub_text48","ভাটারা থানা:");//
        hashMap.put("item_police_num48", "01769050855");

        hashMap.put("sub_text49","ওয়ারী থানা:");//
        hashMap.put("item_police_num49", "01769058051");

        hashMap.put("sub_text50","তেজগাঁও শিল্প এলাকা থানা:");//
        hashMap.put("item_police_num50", "01713373181");

        //police station---------------------------------------

        arrayList.add(hashMap);




        hashMap = new HashMap<>();
        hashMap.put("jela","গাজীপুর");

        //police station---------------------------------------
        hashMap.put("sub_text1","জয়দেবপুর থানা:");//
        hashMap.put("item_police_num1", "01713373363");

        hashMap.put("sub_text2","টঙ্গী থানা:");//
        hashMap.put("item_police_num2", "01713-373364");

        hashMap.put("sub_text3","কাপাসিয়া থানা:");//
        hashMap.put("item_police_num3", "01713-373367");

        hashMap.put("sub_text4","কালিয়াকৈর থানা:");//
        hashMap.put("item_police_num4", "01713-373365");

        hashMap.put("sub_text5", "শ্রীপুর থানা:");//
        hashMap.put("item_police_num5", "01713-373366");

        hashMap.put("sub_text6","কালীগঞ্জ থানা:");//
        hashMap.put("item_police_num6", "01713-373368");

        //police station---------------------------------------

        arrayList.add(hashMap);



        hashMap = new HashMap<>();
        hashMap.put("jela","নারায়ণগঞ্জ");

        //police station---------------------------------------
        hashMap.put("sub_text1","নারায়ণগঞ্জ সদর থানা:");//
        hashMap.put("item_police_num1", "01713373345");

        hashMap.put("sub_text2","আড়াইহাজার থানা:");//
        hashMap.put("item_police_num2", "01713373349");

        hashMap.put("sub_text3","বন্দর থানা:");//
        hashMap.put("item_police_num3", "01713373347");

        hashMap.put("sub_text4","রুপগঞ্জ থানা:");//
        hashMap.put("item_police_num4", "01713373351");

        hashMap.put("sub_text5", "সোনারগাঁও থানা:");//
        hashMap.put("item_police_num5", "01713373350");

        hashMap.put("sub_text6","ফতুল্লা থানা:");//
        hashMap.put("item_police_num6", "01713373346");

        hashMap.put("sub_text7","সিদ্দিরগঞ্জ থানা:");//
        hashMap.put("item_police_num7", "01713373348");
        //police station---------------------------------------

        arrayList.add(hashMap);

        

        hashMap = new HashMap<>();
        hashMap.put("jela","নরসিংদী");

        //police station---------------------------------------
        hashMap.put("sub_text1","নরসিংদী সদর থানা:");//
        hashMap.put("item_police_num1", "01713373412");

        hashMap.put("sub_text2","পলাশ থানা:");//
        hashMap.put("item_police_num2", "01713373417");

        hashMap.put("sub_text3","রায়পুরা থানা:");//
        hashMap.put("item_police_num3", "01713373413");

        hashMap.put("sub_text4","বেলাবো থানা:");//
        hashMap.put("item_police_num4", "01713373415");

        hashMap.put("sub_text5", "মনোহরদী থানা:");//
        hashMap.put("item_police_num5", "01713373416");

        hashMap.put("sub_text6","শিবপুর থানা:");//
        hashMap.put("item_police_num6", "01713373414");

        hashMap.put("sub_text7","মাধবদী থানা:");//
        hashMap.put("item_police_num7", "01320091541");
        //police station---------------------------------------
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","মুন্সিগঞ্জ");

        //police station---------------------------------------

        hashMap.put("sub_text1","মুন্সিগঞ্জ সদর থানা:");//
        hashMap.put("item_police_num1", "01713373396");

        hashMap.put("sub_text2","লৌহজং থানা:");//
        hashMap.put("item_police_num2", "01713373398");

        hashMap.put("sub_text3","গজারিয়া থানা:");//
        hashMap.put("item_police_num3", "01713373401");

        hashMap.put("sub_text4","শ্রীনগর থানা:");//
        hashMap.put("item_police_num4", "01713373399");

        hashMap.put("sub_text5", "সিরাজদিখান থানা:");//
        hashMap.put("item_police_num5", "01713373400");

        hashMap.put("sub_text6","টংগীবাড়ি থানা:");//
        hashMap.put("item_police_num6", "01713373397");

        //police station---------------------------------------
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","মানিকগঞ্জ");

        //police station---------------------------------------
        hashMap.put("sub_text1","মানিকগঞ্জ সদর থানা:");//
        hashMap.put("item_police_num1", "01713373379");

        hashMap.put("sub_text2","সিংগাইর থানা:");//
        hashMap.put("item_police_num2", "01713373385");

        hashMap.put("sub_text3","শিবালয় থানা:");//
        hashMap.put("item_police_num3", "01713373381");

        hashMap.put("sub_text4","ঘিওর থানা:");//
        hashMap.put("item_police_num4", "01713373380");

        hashMap.put("sub_text5", "দৌলতপুর থানা:");//
        hashMap.put("item_police_num5", "01713373382");

        hashMap.put("sub_text6","হরিরামপুর থানা:");//
        hashMap.put("item_police_num6", "01713373383");

        hashMap.put("sub_text7","সাটুরিয়া থানা:");//
        hashMap.put("item_police_num7", "01713373384");
        //police station---------------------------------------
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","টাঙ্গাইল");

        //police station---------------------------------------
        hashMap.put("sub_text1","টাঙ্গাইল সদর থানা:");//
        hashMap.put("item_police_num1", "01713373454");

        hashMap.put("sub_text2","মির্জাপুর থানা:");//
        hashMap.put("item_police_num2", "01713373455");

        hashMap.put("sub_text3","ঘাটাইল থানা:");//
        hashMap.put("item_police_num3", "01713373461");

        hashMap.put("sub_text4","কালিহাতি থানা:");//
        hashMap.put("item_police_num4", "01713373462");

        hashMap.put("sub_text5", "মধুপুর থানা:");//
        hashMap.put("item_police_num5", "01713373460");

        hashMap.put("sub_text6","ধনবাড়ী থানা:");//
        hashMap.put("item_police_num6", "01713373465");

        hashMap.put("sub_text7","গোপালপুর থানা:");//
        hashMap.put("item_police_num7", "01713373466");

        hashMap.put("sub_text8","ভুয়াপুর থানা:");//
        hashMap.put("item_police_num8", "01713373463");

        hashMap.put("sub_text9","দেলদুয়ার থানা:");//
        hashMap.put("item_police_num9", "01713373459");

        hashMap.put("sub_text10","নাগরপুর থানা:");//
        hashMap.put("item_police_num10", "01713373456");

        hashMap.put("sub_text11","বাসাইল থানা:");//
        hashMap.put("item_police_num11", "01713373458");

        hashMap.put("sub_text12","সখিপুর থানা:");//
        hashMap.put("item_police_num12", "01713373457");
        //police station---------------------------------------
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","কিশোরগঞ্জ");

        //police station---------------------------------------
        hashMap.put("sub_text1","কিশোরগঞ্জ সদর থানা:");//
        hashMap.put("item_police_num1", "01713373480");

        hashMap.put("sub_text2","হোসেপুর থানা:");//
        hashMap.put("item_police_num2", "01713373483");

        hashMap.put("sub_text3","পাকুন্দিয়া থানা:");//
        hashMap.put("item_police_num3", "01713373491");

        hashMap.put("sub_text4","কটিয়াদি থানা:");//
        hashMap.put("item_police_num4", "01713373484");

        hashMap.put("sub_text5", "কুলিয়ারচর থানা:");//
        hashMap.put("item_police_num5", "01713373486");

        hashMap.put("sub_text6","ভৈরব থানা:");//
        hashMap.put("item_police_num6", "01713373487");

        hashMap.put("sub_text7","বাজিতপুর থানা:");//
        hashMap.put("item_police_num7", "01713373485");

        hashMap.put("sub_text8","ইটনা থানা:");//
        hashMap.put("item_police_num8", "01713373488");

        hashMap.put("sub_text9","মিঠামইন থানা:");//
        hashMap.put("item_police_num9", "01713373489");

        hashMap.put("sub_text10","অষ্টগ্রাম থানা:");//
        hashMap.put("item_police_num10", "01713373492");

        hashMap.put("sub_text11","করিমগঞ্জ থানা:");//
        hashMap.put("item_police_num11", "01713373481");

        hashMap.put("sub_text12","তাড়াইল থানা:");//
        hashMap.put("item_police_num12", "01713373482");

        hashMap.put("sub_text13","নিকলী থানা:");//
        hashMap.put("item_police_num13", "01713373490");
        //police station---------------------------------------
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","ফরিদপুর");

        //police station---------------------------------------
        hashMap.put("sub_text1","ফরিদপুর সদর থানা:");//
        hashMap.put("item_police_num1", "01713373556");

        hashMap.put("sub_text2","আলফাডাঙ্গা থানা:");//
        hashMap.put("item_police_num2", "01713373559");

        hashMap.put("sub_text3","ভাঙ্গা থানা:");//
        hashMap.put("item_police_num3", "01713373564");

        hashMap.put("sub_text4","বোয়ালমারী থানা:");//
        hashMap.put("item_police_num4", "01713373558");

        hashMap.put("sub_text5", "চরভদ্রাসন থানা:");//
        hashMap.put("item_police_num5", "01713373560");

        hashMap.put("sub_text6","মধুখালী থানা:");//
        hashMap.put("item_police_num6", "01713373557");

        hashMap.put("sub_text7","নগরকান্দা থানা:");//
        hashMap.put("item_police_num7", "01713373561");

        hashMap.put("sub_text8","সালথা থানা:");//
        hashMap.put("item_police_num8", "01713373563");

        hashMap.put("sub_text9","সদরপুর থানা:");//
        hashMap.put("item_police_num9", "01713373562");
        //police station---------------------------------------
        arrayList.add(hashMap);


        hashMap = new HashMap<>();
        hashMap.put("jela","মাদারিপুর");
        //police station---------------------------------------
        hashMap.put("sub_text1","মাদারিপুর সদর থানা:");//
        hashMap.put("item_police_num1", "01713373585");

        hashMap.put("sub_text2","শিবচর থানা:");//
        hashMap.put("item_police_num2", "01713373588");

        hashMap.put("sub_text3","কালকিনি থানা:");//
        hashMap.put("item_police_num3", "01713373587");

        hashMap.put("sub_text4","রাজৈর থানা:");//
        hashMap.put("item_police_num4", "01713373586");

        hashMap.put("sub_text5", "ডাসার থানা:");//
        hashMap.put("item_police_num5", "01320098474");
        //police station---------------------------------------

        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","শরিয়তপুর");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","গোপালগঞ্জ");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("jela","রাজবাড়ি");
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
}//public class