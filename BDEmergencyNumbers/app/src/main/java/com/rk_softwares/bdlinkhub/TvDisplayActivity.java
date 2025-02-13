package com.rk_softwares.bdlinkhub;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;



public class TvDisplayActivity extends AppCompatActivity {




    //XML id's----------------------------------------------

    RelativeLayout item_rel1, item_rel2, item_rel3,item_rel4,item_rel5,item_rel6,item_rel7,item_rel8,item_rel9,item_rel10,item_rel11,item_rel12;

    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11, img12;

    TextView item_police_numb1, item_police_numb2,  item_police_numb3,  item_police_numb4,  item_police_numb5,  item_police_numb6,  item_police_numb7,  item_police_numb8,  item_police_numb9,  item_police_numb10;
    TextView item_police_numb11, item_police_numb12,  item_police_numb13,  item_police_numb14,  item_police_numb15,  item_police_numb16,  item_police_numb17,  item_police_numb18,  item_police_numb19,  item_police_numb20;
    TextView item_police_numb21, item_police_numb22,  item_police_numb23,  item_police_numb24,  item_police_numb25,  item_police_numb26,  item_police_numb27,  item_police_numb28,  item_police_numb29,  item_police_numb30;
    TextView item_police_numb31, item_police_numb32,  item_police_numb33,  item_police_numb34,  item_police_numb35,  item_police_numb36,  item_police_numb37,  item_police_numb38,  item_police_numb39,  item_police_numb40;
    TextView item_police_numb41, item_police_numb42,  item_police_numb43,  item_police_numb44,  item_police_numb45,  item_police_numb46,  item_police_numb47,  item_police_numb48,  item_police_numb49,  item_police_numb50;

    LinearLayout ler1, ler2, ler3, ler4, ler5, ler6, ler7, ler8, ler9, ler10, ler11, ler12;

    ImageButton back;



    //police------------------------------------------------


    public static String sub_text1 = "";
    public static String item_police_num1 = "";

    public static String sub_text2 = "";
    public static String item_police_num2 = "";

    public static String sub_text3 = "";
    public static String item_police_num3 = "";

    public static String sub_text4 = "";
    public static String item_police_num4 = "";

    public static String sub_text5 = "";
    public static String item_police_num5 = "";

    public static String sub_text6 = "";
    public static String item_police_num6 = "";

    public static String sub_text7 = "";
    public static String item_police_num7 = "";

    public static String sub_text8 = "";
    public static String item_police_num8 = "";

    public static String sub_text9 = "";
    public static String item_police_num9 = "";

    public static String sub_text10 = "";
    public static String item_police_num10 = "";

    public static String sub_text11 = "";
    public static String item_police_num11 = "";

    public static String sub_text12 = "";
    public static String item_police_num12 = "";

    public static String sub_text13 = "";
    public static String item_police_num13 = "";

    public static String sub_text14 = "";
    public static String item_police_num14 = "";

    public static String sub_text15 = "";
    public static String item_police_num15 = "";

    public static String sub_text16 = "";
    public static String item_police_num16 = "";

    public static String sub_text17 = "";
    public static String item_police_num17 = "";

    public static String sub_text18 = "";
    public static String item_police_num18 = "";

    public static String sub_text19 = "";
    public static String item_police_num19 = "";

    public static String sub_text20 = "";
    public static String item_police_num20 = "";

    public static String sub_text21 = "";
    public static String item_police_num21 = "";

    public static String sub_text22 = "";
    public static String item_police_num22 = "";

    public static String sub_text23 = "";
    public static String item_police_num23 = "";

    public static String sub_text24 = "";
    public static String item_police_num24 = "";

    public static String sub_text25 = "";
    public static String item_police_num25 = "";

    public static String sub_text26 = "";
    public static String item_police_num26 = "";

    public static String sub_text27 = "";
    public static String item_police_num27 = "";

    public static String sub_text28 = "";
    public static String item_police_num28 = "";

    public static String sub_text29 = "";
    public static String item_police_num29 = "";

    public static String sub_text30 = "";
    public static String item_police_num30 = "";

    public static String sub_text31 = "";
    public static String item_police_num31 = "";

    public static String sub_text32 = "";
    public static String item_police_num32 = "";

    public static String sub_text33 = "";
    public static String item_police_num33 = "";

    public static String sub_text34 = "";
    public static String item_police_num34 = "";

    public static String sub_text35 = "";
    public static String item_police_num35 = "";

    public static String sub_text36 = "";
    public static String item_police_num36 = "";

    public static String sub_text37 = "";
    public static String item_police_num37 = "";

    public static String sub_text38 = "";
    public static String item_police_num38 = "";

    public static String sub_text39 = "";
    public static String item_police_num39 = "";

    public static String sub_text40 = "";
    public static String item_police_num40 = "";

    public static String sub_text41 = "";
    public static String item_police_num41 = "";

    public static String sub_text42 = "";
    public static String item_police_num42 = "";

    public static String sub_text43 = "";
    public static String item_police_num43 = "";

    public static String sub_text44 = "";
    public static String item_police_num44 = "";

    public static String sub_text45 = "";
    public static String item_police_num45 = "";

    public static String sub_text46 = "";
    public static String item_police_num46 = "";

    public static String sub_text47 = "";
    public static String item_police_num47 = "";

    public static String sub_text48 = "";
    public static String item_police_num48 = "";

    public static String sub_text49 = "";
    public static String item_police_num49 = "";

    public static String sub_text50 = "";
    public static String item_police_num50 = "";




    //police------------------------------------------------


    //XML id's----------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv_display);

        //identity period------------------------------------

        item_rel1 = findViewById(R.id.item_rel1);
        item_rel2 = findViewById(R.id.item_rel2);
        item_rel3 = findViewById(R.id.item_rel3);
        item_rel4 = findViewById(R.id.item_rel4);
        item_rel5 = findViewById(R.id.item_rel5);
        item_rel6 = findViewById(R.id.item_rel6);
        item_rel7 = findViewById(R.id.item_rel7);
        item_rel8 = findViewById(R.id.item_rel8);
        item_rel9 = findViewById(R.id.item_rel9);
        item_rel10 = findViewById(R.id.item_rel10);
        item_rel11 = findViewById(R.id.item_rel11);
        item_rel12 = findViewById(R.id.item_rel12);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        img7 = findViewById(R.id.img7);
        img8 = findViewById(R.id.img8);
        img9 = findViewById(R.id.img9);
        img10 = findViewById(R.id.img10);
        img11 = findViewById(R.id.img11);
        img12 = findViewById(R.id.img12);

        ler1 = findViewById(R.id.ler1);
        ler2 = findViewById(R.id.ler2);
        ler3 = findViewById(R.id.ler3);
        ler4 = findViewById(R.id.ler4);
        ler5 = findViewById(R.id.ler5);
        ler6 = findViewById(R.id.ler6);
        ler7 = findViewById(R.id.ler7);
        ler8 = findViewById(R.id.ler8);
        ler9 = findViewById(R.id.ler9);
        ler10 = findViewById(R.id.ler10);
        ler11 = findViewById(R.id.ler11);
        ler12 = findViewById(R.id.ler12);
/*
        item_police_numb1 = findViewById(R.id.item_police_numb1);
        item_police_numb2 = findViewById(R.id.item_police_numb2);
        item_police_numb3 = findViewById(R.id.item_police_numb3);
        item_police_numb4 = findViewById(R.id.item_police_numb4);
        item_police_numb5 = findViewById(R.id.item_police_numb5);
        item_police_numb6 = findViewById(R.id.item_police_numb6);
        item_police_numb7 = findViewById(R.id.item_police_numb7);
        item_police_numb8 = findViewById(R.id.item_police_numb8);
        item_police_numb9 = findViewById(R.id.item_police_numb9);
        item_police_numb10 = findViewById(R.id.item_police_numb10);
        item_police_numb11 = findViewById(R.id.item_police_numb11);
        item_police_numb12 = findViewById(R.id.item_police_numb12);
        item_police_numb13 = findViewById(R.id.item_police_numb13);
        item_police_numb14 = findViewById(R.id.item_police_numb14);
        item_police_numb15 = findViewById(R.id.item_police_numb15);
        item_police_numb16 = findViewById(R.id.item_police_numb16);
        item_police_numb17 = findViewById(R.id.item_police_numb17);
        item_police_numb18 = findViewById(R.id.item_police_numb18);
        item_police_numb19 = findViewById(R.id.item_police_numb19);
        item_police_numb20 = findViewById(R.id.item_police_numb20);
        item_police_numb21 = findViewById(R.id.item_police_numb21);
        item_police_numb22 = findViewById(R.id.item_police_numb22);
        item_police_numb23 = findViewById(R.id.item_police_numb23);
        item_police_numb24 = findViewById(R.id.item_police_numb24);
        item_police_numb25 = findViewById(R.id.item_police_numb25);
        item_police_numb26 = findViewById(R.id.item_police_numb26);
        item_police_numb27 = findViewById(R.id.item_police_numb27);
        item_police_numb28 = findViewById(R.id.item_police_numb28);
        item_police_numb29 = findViewById(R.id.item_police_numb29);
        item_police_numb30 = findViewById(R.id.item_police_numb30);
        item_police_numb31 = findViewById(R.id.item_police_numb31);
        item_police_numb32 = findViewById(R.id.item_police_numb32);
        item_police_numb33 = findViewById(R.id.item_police_numb33);
        item_police_numb34 = findViewById(R.id.item_police_numb34);
        item_police_numb35 = findViewById(R.id.item_police_numb35);
        item_police_numb36 = findViewById(R.id.item_police_numb36);
        item_police_numb37 = findViewById(R.id.item_police_numb37);
        item_police_numb38 = findViewById(R.id.item_police_numb38);
        item_police_numb39 = findViewById(R.id.item_police_numb39);
        item_police_numb40 = findViewById(R.id.item_police_numb40);
        item_police_numb41 = findViewById(R.id.item_police_numb41);
        item_police_numb42 = findViewById(R.id.item_police_numb42);
        item_police_numb43 = findViewById(R.id.item_police_numb43);
        item_police_numb44 = findViewById(R.id.item_police_numb44);
        item_police_numb45 = findViewById(R.id.item_police_numb45);
        item_police_numb46 = findViewById(R.id.item_police_numb46);
        item_police_numb47 = findViewById(R.id.item_police_numb47);
        item_police_numb48 = findViewById(R.id.item_police_numb48);
        item_police_numb49 = findViewById(R.id.item_police_numb49);
        item_police_numb50 = findViewById(R.id.item_police_numb50);


 */






        back = findViewById(R.id.back);

        //identity period------------------------------------



        back.setOnClickListener(new View.OnClickListener() {    //back button
            @Override
            public void onClick(View view) {

                startActivity(new Intent(TvDisplayActivity.this, MainActivity.class));
            }
        });

        if (MainActivity.PERMISSION == true){ //checking the permission

            police();

        }






    }//on create =====================================







    //police-------------------------------------------------------------------------------
    private void police(){

        item_rel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (ler1.getVisibility() == View.GONE){

                    ler1.setVisibility(View.VISIBLE);

                    //police-------------------------------------------------------------------

                    if (item_police_num1 == null && sub_text1 == null){

                        item_police_numb1.setVisibility(View.GONE);
                    }else {

                        item_police_numb1.setText(sub_text1 + "\n" +item_police_num1);
                        item_police_numb1.setVisibility(View.VISIBLE);

                    }


                    item_police_numb1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num1));
                            startActivity(call_intent);

                        }
                    });

                    //----------------------------------------------------------------
                    if (item_police_num2 == null && sub_text2 == null){

                        item_police_numb2.setVisibility(View.GONE);
                    }else {

                        item_police_numb2.setText(sub_text2 + "\n" +item_police_num2);
                        item_police_numb2.setVisibility(View.VISIBLE);

                    }
                    item_police_numb2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num2));
                            startActivity(call_intent);

                        }
                    });
                    //-----------------------------------------------------------------

                    //------------------------------------------------------------

                    if (item_police_num3 == null && sub_text3 == null){

                        item_police_numb3.setVisibility(View.GONE);
                    }else {

                        item_police_numb3.setText(sub_text3 + "\n" +item_police_num3);
                        item_police_numb3.setVisibility(View.VISIBLE);

                    }
                    item_police_numb3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num3));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------


                    //------------------------------------------------------------------

                    if (item_police_num4 == null && sub_text4 == null){

                        item_police_numb4.setVisibility(View.GONE);
                    }else {

                        item_police_numb4.setText(sub_text4 + "\n" +item_police_num4);
                        item_police_numb4.setVisibility(View.VISIBLE);

                    }
                    item_police_numb4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num4));
                            startActivity(call_intent);

                        }
                    });
                    //-----------------------------------------------------------------------------

                    //-----------------------------------------------------------------------

                    if (item_police_num5 == null && sub_text5 == null){

                        item_police_numb5.setVisibility(View.GONE);
                    }else {

                        item_police_numb5.setText(sub_text5 + "\n" +item_police_num5);
                        item_police_numb5.setVisibility(View.VISIBLE);

                    }
                    item_police_numb5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num5));
                            startActivity(call_intent);

                        }
                    });
                    //-------------------------------------------------------------------

                    //--------------------------------------------------------------------

                    if (item_police_num6 == null && sub_text6 == null){

                        item_police_numb6.setVisibility(View.GONE);
                    }else {

                        item_police_numb6.setText(sub_text6 + "\n" +item_police_num6);
                        item_police_numb6.setVisibility(View.VISIBLE);

                    }
                    item_police_numb6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num6));
                            startActivity(call_intent);

                        }
                    });
                    //-------------------------------------------------------------

                    //--------------------------------------------------------------

                    if (item_police_num7 == null && sub_text7 == null){

                        item_police_numb7.setVisibility(View.GONE);
                    }else {

                        item_police_numb7.setText(sub_text7 + "\n" +item_police_num7);
                        item_police_numb7.setVisibility(View.VISIBLE);

                    }
                    item_police_numb7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num7));
                            startActivity(call_intent);

                        }
                    });
                    //-------------------------------------------------------

                    //---------------------------------------------------------

                    if (item_police_num8 == null && sub_text8 == null){

                        item_police_numb8.setVisibility(View.GONE);
                    }else {

                        item_police_numb8.setText(sub_text8 + "\n" +item_police_num8);
                        item_police_numb8.setVisibility(View.VISIBLE);

                    }
                    item_police_numb8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num8));
                            startActivity(call_intent);

                        }
                    });
                    //----------------------------------------------------------

                    //----------------------------------------------------------
                    if (item_police_num9 == null && sub_text9 == null){

                        item_police_numb9.setVisibility(View.GONE);
                    }else {

                        item_police_numb9.setText(sub_text9 + "\n" +item_police_num9);
                        item_police_numb9.setVisibility(View.VISIBLE);

                    }
                    item_police_numb9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num9));
                            startActivity(call_intent);

                        }
                    });
                    //----------------------------------------------------------------------

                    //---------------------------------------------------------------------

                    if (item_police_num10 == null && sub_text10 == null){

                        item_police_numb10.setVisibility(View.GONE);
                    }else {

                        item_police_numb10.setText(sub_text10 + "\n" +item_police_num10);
                        item_police_numb10.setVisibility(View.VISIBLE);

                    }
                    item_police_numb10.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num10));
                            startActivity(call_intent);

                        }
                    });
                    //---------------------------------------------------------------------

                    //--------------------------------------------------------------------

                    if (item_police_num11 == null && sub_text11 == null){

                        item_police_numb11.setVisibility(View.GONE);
                    }else {

                        item_police_numb11.setText(sub_text11 + "\n" +item_police_num11);
                        item_police_numb11.setVisibility(View.VISIBLE);

                    }
                    item_police_numb11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num11));
                            startActivity(call_intent);

                        }
                    });
                    //----------------------------------------------------------------------

                    //---------------------------------------------------------------------

                    if (item_police_num12 == null && sub_text12 == null){

                        item_police_numb12.setVisibility(View.GONE);
                    }else {

                        item_police_numb12.setText(sub_text12 + "\n" +item_police_num12);
                        item_police_numb12.setVisibility(View.VISIBLE);

                    }
                    item_police_numb12.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num12));
                            startActivity(call_intent);

                        }
                    });
                    //----------------------------------------------------------------

                    //----------------------------------------------------------------
                    if (item_police_num13 == null && sub_text13 == null){

                        item_police_numb13.setVisibility(View.GONE);
                    }else {

                        item_police_numb13.setText(sub_text13 + "\n" +item_police_num13);
                        item_police_numb13.setVisibility(View.VISIBLE);

                    }
                    item_police_numb13.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num13));
                            startActivity(call_intent);

                        }
                    });
                    //----------------------------------------------------------------------

                    //--------------------------------------------------------------------

                    if (item_police_num14 == null && sub_text14 == null){

                        item_police_numb14.setVisibility(View.GONE);
                    }else {

                        item_police_numb14.setText(sub_text14 + "\n" +item_police_num14);
                        item_police_numb14.setVisibility(View.VISIBLE);

                    }
                    item_police_numb14.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num14));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------

                    //-------------------------------------------------------------------

                    if (item_police_num15 == null && sub_text15 == null){

                        item_police_numb15.setVisibility(View.GONE);
                    }else {

                        item_police_numb15.setText(sub_text15 + "\n" +item_police_num15);
                        item_police_numb15.setVisibility(View.VISIBLE);

                    }
                    item_police_numb15.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num15));
                            startActivity(call_intent);

                        }
                    });
                    //-----------------------------------------------------------------------------

                    //----------------------------------------------------------------------------

                    if (item_police_num16 == null && sub_text16 == null){

                        item_police_numb16.setVisibility(View.GONE);
                    }else {

                        item_police_numb16.setText(sub_text16 + "\n" +item_police_num16);
                        item_police_numb16.setVisibility(View.VISIBLE);

                    }
                    item_police_numb16.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num16));
                            startActivity(call_intent);

                        }
                    });
                    //---------------------------------------------------------------------------


                    //---------------------------------------------------------------------------

                    if (item_police_num17 == null && sub_text17 == null){

                        item_police_numb17.setVisibility(View.GONE);
                    }else {

                        item_police_numb17.setText(sub_text17 + "\n" +item_police_num17);
                        item_police_numb17.setVisibility(View.VISIBLE);

                    }
                    item_police_numb17.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num17));
                            startActivity(call_intent);

                        }
                    });
                    //-------------------------------------------------------------------------

                    //-------------------------------------------------------------------------

                    if (item_police_num18 == null && sub_text18 == null){

                        item_police_numb18.setVisibility(View.GONE);
                    }else {

                        item_police_numb18.setText(sub_text18 + "\n" +item_police_num18);
                        item_police_numb18.setVisibility(View.VISIBLE);

                    }
                    item_police_numb18.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num18));
                            startActivity(call_intent);

                        }
                    });
                    //--------------------------------------------------------------------------

                    //-------------------------------------------------------------------------

                    if (item_police_num19 == null && sub_text19 == null){

                        item_police_numb19.setVisibility(View.GONE);
                    }else {

                        item_police_numb19.setText(sub_text19 + "\n" +item_police_num19);
                        item_police_numb19.setVisibility(View.VISIBLE);

                    }
                    item_police_numb19.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num19));
                            startActivity(call_intent);

                        }
                    });
                    //----------------------------------------------------------------------------

                    //----------------------------------------------------------------------------

                    if (item_police_num20 == null && sub_text20 == null){

                        item_police_numb20.setVisibility(View.GONE);
                    }else {

                        item_police_numb20.setText(sub_text20 + "\n" +item_police_num20);
                        item_police_numb20.setVisibility(View.VISIBLE);

                    }
                    item_police_numb20.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num20));
                            startActivity(call_intent);

                        }
                    });
                    //-------------------------------------------------------------------------

                    //---------------------------------------------------------------------------
                    if (item_police_num21 == null && sub_text21 == null){

                        item_police_numb21.setVisibility(View.GONE);
                    }else {

                        item_police_numb21.setText(sub_text21 + "\n" +item_police_num21);
                        item_police_numb21.setVisibility(View.VISIBLE);

                    }
                    item_police_numb21.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num21));
                            startActivity(call_intent);

                        }
                    });
                    //--------------------------------------------------------------------------

                    //--------------------------------------------------------------------------

                    if (item_police_num22 == null && sub_text22 == null){

                        item_police_numb22.setVisibility(View.GONE);
                    }else {

                        item_police_numb22.setText(sub_text22 + "\n" +item_police_num22);
                        item_police_numb22.setVisibility(View.VISIBLE);

                    }
                    item_police_numb22.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num22));
                            startActivity(call_intent);

                        }
                    });
                    //---------------------------------------------------------------------------

                    //---------------------------------------------------------------------------

                    if (item_police_num23 == null && sub_text23 == null){

                        item_police_numb23.setVisibility(View.GONE);
                    }else {

                        item_police_numb23.setText(sub_text23 + "\n" +item_police_num23);
                        item_police_numb23.setVisibility(View.VISIBLE);

                    }
                    item_police_numb23.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num23));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num24 == null && sub_text24 == null){

                        item_police_numb24.setVisibility(View.GONE);
                    }else {

                        item_police_numb24.setText(sub_text24 + "\n" +item_police_num24);
                        item_police_numb24.setVisibility(View.VISIBLE);

                    }
                    item_police_numb24.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num24));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------


                    //------------------------------------------------------------------------------

                    if (item_police_num25 == null && sub_text25 == null){

                        item_police_numb25.setVisibility(View.GONE);
                    }else {

                        item_police_numb25.setText(sub_text25 + "\n" +item_police_num25);
                        item_police_numb25.setVisibility(View.VISIBLE);

                    }
                    item_police_numb25.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num25));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num26 == null && sub_text26 == null){

                        item_police_numb26.setVisibility(View.GONE);
                    }else {

                        item_police_numb26.setText(sub_text26 + "\n" +item_police_num26);
                        item_police_numb26.setVisibility(View.VISIBLE);

                    }
                    item_police_numb26.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num26));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num27 == null && sub_text27 == null){

                        item_police_numb27.setVisibility(View.GONE);
                    }else {

                        item_police_numb27.setText(sub_text27 + "\n" +item_police_num27);
                        item_police_numb27.setVisibility(View.VISIBLE);

                    }
                    item_police_numb27.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num27));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num28 == null && sub_text28 == null){

                        item_police_numb28.setVisibility(View.GONE);
                    }else {

                        item_police_numb28.setText(sub_text28 + "\n" +item_police_num28);
                        item_police_numb28.setVisibility(View.VISIBLE);

                    }
                    item_police_numb28.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num28));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num29 == null && sub_text29 == null){

                        item_police_numb29.setVisibility(View.GONE);
                    }else {

                        item_police_numb29.setText(sub_text29 + "\n" +item_police_num29);
                        item_police_numb29.setVisibility(View.VISIBLE);

                    }
                    item_police_numb29.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num29));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num30 == null && sub_text30 == null){

                        item_police_numb30.setVisibility(View.GONE);
                    }else {

                        item_police_numb30.setText(sub_text30 + "\n" +item_police_num30);
                        item_police_numb30.setVisibility(View.VISIBLE);

                    }
                    item_police_numb30.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num30));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num31 == null && sub_text31 == null){

                        item_police_numb31.setVisibility(View.GONE);
                    }else {

                        item_police_numb31.setText(sub_text31 + "\n" +item_police_num31);
                        item_police_numb31.setVisibility(View.VISIBLE);

                    }
                    item_police_numb31.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num31));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num32 == null && sub_text32 == null){

                        item_police_numb32.setVisibility(View.GONE);
                    }else {

                        item_police_numb32.setText(sub_text32 + "\n" +item_police_num32);
                        item_police_numb32.setVisibility(View.VISIBLE);

                    }
                    item_police_numb32.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num32));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num33 == null && sub_text33 == null){

                        item_police_numb33.setVisibility(View.GONE);
                    }else {

                        item_police_numb33.setText(sub_text33 + "\n" +item_police_num33);
                        item_police_numb33.setVisibility(View.VISIBLE);

                    }
                    item_police_numb33.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num33));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num34 == null && sub_text34 == null){

                        item_police_numb34.setVisibility(View.GONE);
                    }else {

                        item_police_numb34.setText(sub_text34 + "\n" +item_police_num34);
                        item_police_numb34.setVisibility(View.VISIBLE);

                    }
                    item_police_numb34.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num34));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num35 == null && sub_text35 == null){

                        item_police_numb35.setVisibility(View.GONE);
                    }else {

                        item_police_numb35.setText(sub_text35 + "\n" +item_police_num35);
                        item_police_numb35.setVisibility(View.VISIBLE);

                    }
                    item_police_numb35.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num35));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num36 == null && sub_text36 == null){

                        item_police_numb36.setVisibility(View.GONE);
                    }else {

                        item_police_numb36.setText(sub_text36 + "\n" +item_police_num36);
                        item_police_numb36.setVisibility(View.VISIBLE);

                    }
                    item_police_numb36.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num36));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num37 == null && sub_text37 == null){

                        item_police_numb37.setVisibility(View.GONE);
                    }else {

                        item_police_numb37.setText(sub_text37 + "\n" +item_police_num37);
                        item_police_numb37.setVisibility(View.VISIBLE);

                    }
                    item_police_numb37.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num37));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num38 == null && sub_text38 == null){

                        item_police_numb38.setVisibility(View.GONE);
                    }else {

                        item_police_numb38.setText(sub_text38 + "\n" +item_police_num38);
                        item_police_numb38.setVisibility(View.VISIBLE);

                    }
                    item_police_numb38.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num38));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num39 == null && sub_text39 == null){

                        item_police_numb39.setVisibility(View.GONE);
                    }else {

                        item_police_numb39.setText(sub_text39 + "\n" +item_police_num39);
                        item_police_numb39.setVisibility(View.VISIBLE);

                    }
                    item_police_numb39.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num39));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------
                    if (item_police_num40 == null && sub_text40 == null){

                        item_police_numb40.setVisibility(View.GONE);
                    }else {

                        item_police_numb40.setText(sub_text40 + "\n" +item_police_num40);
                        item_police_numb40.setVisibility(View.VISIBLE);

                    }
                    item_police_numb40.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num40));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num41 == null && sub_text41 == null){

                        item_police_numb41.setVisibility(View.GONE);
                    }else {

                        item_police_numb41.setText(sub_text41 + "\n" +item_police_num41);
                        item_police_numb41.setVisibility(View.VISIBLE);

                    }
                    item_police_numb41.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num41));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num42 == null && sub_text42 == null){

                        item_police_numb42.setVisibility(View.GONE);
                    }else {

                        item_police_numb42.setText(sub_text42 + "\n" +item_police_num42);
                        item_police_numb42.setVisibility(View.VISIBLE);

                    }
                    item_police_numb42.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num42));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num43 == null && sub_text43 == null){

                        item_police_numb43.setVisibility(View.GONE);
                    }else {

                        item_police_numb43.setText(sub_text43 + "\n" +item_police_num43);
                        item_police_numb43.setVisibility(View.VISIBLE);

                    }
                    item_police_numb43.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num43));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num44 == null && sub_text44 == null){

                        item_police_numb44.setVisibility(View.GONE);
                    }else {

                        item_police_numb44.setText(sub_text44 + "\n" +item_police_num44);
                        item_police_numb44.setVisibility(View.VISIBLE);

                    }
                    item_police_numb44.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num44));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num45 == null && sub_text45 == null){

                        item_police_numb45.setVisibility(View.GONE);
                    }else {

                        item_police_numb45.setText(sub_text45 + "\n" +item_police_num45);
                        item_police_numb45.setVisibility(View.VISIBLE);

                    }
                    item_police_numb45.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num45));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num46 == null && sub_text46 == null){

                        item_police_numb46.setVisibility(View.GONE);
                    }else {

                        item_police_numb46.setText(sub_text46 + "\n" +item_police_num46);
                        item_police_numb46.setVisibility(View.VISIBLE);

                    }
                    item_police_numb46.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num46));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num47 == null && sub_text47 == null){

                        item_police_numb47.setVisibility(View.GONE);
                    }else {

                        item_police_numb47.setText(sub_text47 + "\n" +item_police_num47);
                        item_police_numb47.setVisibility(View.VISIBLE);

                    }
                    item_police_numb47.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num47));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num48 == null && sub_text48 == null){

                        item_police_numb48.setVisibility(View.GONE);
                    }else {

                        item_police_numb48.setText(sub_text48 + "\n" +item_police_num48);
                        item_police_numb48.setVisibility(View.VISIBLE);

                    }
                    item_police_numb48.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num48));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num49 == null && sub_text49 == null){

                        item_police_numb49.setVisibility(View.GONE);
                    }else {

                        item_police_numb49.setText(sub_text49 + "\n" +item_police_num49);
                        item_police_numb49.setVisibility(View.VISIBLE);

                    }
                    item_police_numb49.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num49));
                            startActivity(call_intent);

                        }
                    });
                    //------------------------------------------------------------------------------

                    //------------------------------------------------------------------------------

                    if (item_police_num50 == null && sub_text50 == null){

                        item_police_numb50.setVisibility(View.GONE);
                    }else {

                        item_police_numb50.setText(sub_text50 + "\n" +item_police_num50);
                        item_police_numb50.setVisibility(View.VISIBLE);

                    }
                    item_police_numb50.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent call_intent = new Intent(Intent.ACTION_DIAL);
                            call_intent.setData(Uri.parse("tel:"+item_police_num50));
                            startActivity(call_intent);

                        }
                    });


                    //police-------------------------------------------------------------------

                    img1.setImageResource(R.drawable.baseline_keyboard_arrow_up_24);
                }else {

                    ler1.setVisibility(View.GONE);
                    img1.setImageResource(R.drawable.baseline_keyboard_arrow_down_24);
                }

            }
        });

    }//police method
    /*
    private void rab(){

        if (ler2.getVisibility() == View.GONE) {

            ler2.setVisibility(View.VISIBLE);

            //police-------------------------------------------------------------------

            if (item_rab_num1 == null && sub_text101 == null) {

                item_rab_numb1.setVisibility(View.GONE);
            } else {

                item_rab_numb1.setText(sub_text101 + "\n" + item_rab_num1);
                item_rab_numb1.setVisibility(View.VISIBLE);

            }


            item_rab_numb1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent call_intent = new Intent(Intent.ACTION_DIAL);
                    call_intent.setData(Uri.parse("tel:" + item_rab_num1));
                    startActivity(call_intent);

                }
            });
            img2.setImageResource(R.drawable.baseline_keyboard_arrow_up_24);
        }else {

            ler2.setVisibility(View.GONE);
            img2.setImageResource(R.drawable.baseline_keyboard_arrow_down_24);


        }
    }

    */

    @Override
    protected void onRestart() {
        super.onRestart();

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}//public class =====================================