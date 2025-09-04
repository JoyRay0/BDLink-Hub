package com.rk_softwares.bdlinkhub.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.rk_softwares.bdlinkhub.Adapter.Favorite_item;
import com.rk_softwares.bdlinkhub.Database.Favorite;
import com.rk_softwares.bdlinkhub.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Fg_favorite extends Fragment {

    // XML id's---------------------------------------------------

    private FrameLayout fl_add_btn, fl_list;
    private RecyclerView rv_fav;
    private AppCompatTextView tv_text;
    private CardView cv_user_link;
    private long lastTime = 0;
    private boolean isOpen = false;
    private Favorite favorite;
    private Favorite_item favoriteItem;

    List<HashMap<String, String>> show_list = new ArrayList<>();
    HashMap<String, String> show_map;



    // XML id's---------------------------------------------------


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fg_favorite, container, false);

        //identity period--------------------------------------

        fl_add_btn = view.findViewById(R.id.fl_add_btn);
        tv_text = view.findViewById(R.id.tv_text);
        fl_list = view.findViewById(R.id.fl_list);
        cv_user_link = view.findViewById(R.id.cv_user_link);
        rv_fav = view.findViewById(R.id.rv_fav);

        //identity period--------------------------------------


        check_user();
        favoriteItem = new Favorite_item(requireActivity(), show_list);
        rv_fav.setAdapter(favoriteItem);

        show_data();


        if (show_list.isEmpty()){

            tv_text.setVisibility(View.VISIBLE);

        }else {

            tv_text.setVisibility(View.GONE);

        }


        return view;
    }//on create ======================

    //checking user id
    private void check_user(){

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String user_id = sharedPreferences.getString("user_id", "No id");

        if (user_id.equals("No id")){

            Toast.makeText(requireActivity(), "অনুগ্রহ করে প্রথমে লগইন বা রেজিষ্ট্রেশন করুন", Toast.LENGTH_SHORT).show();

        }else {

            Animation up = AnimationUtils.loadAnimation(requireActivity(), R.anim.scale_up);
            Animation down = AnimationUtils.loadAnimation(requireActivity(), R.anim.scale_down);
            cv_user_link.setOnClickListener(view1 -> {

                if (isOpen){

                    fl_list.setVisibility(View.GONE);
                    fl_add_btn.setVisibility(View.GONE);

                    fl_list.startAnimation(down);
                    fl_add_btn.startAnimation(down);

                    isOpen = false;

                }else {

                    fl_add_btn.setVisibility(View.VISIBLE);
                    fl_list.setVisibility(View.VISIBLE);

                    fl_add_btn.startAnimation(up);
                    fl_list.startAnimation(up);
                    isOpen = true;
                }

            });

            fl_list.setOnClickListener(view2 -> {

                user_save_links();


            });

            user_custom_link();

        }

    }

    //user custom link
    private void user_custom_link(){

        fl_add_btn.setOnClickListener(view -> {


            Dialog dialog1 = new Dialog(requireActivity());
            dialog1.setContentView(R.layout.lay_user_add_link);
            dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            Window window = dialog1.getWindow();
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


            RadioGroup rd_group = dialog1.findViewById(R.id.rd_group);
            AppCompatAutoCompleteTextView act_link_title = dialog1.findViewById(R.id.act_link_title);
            AppCompatAutoCompleteTextView act_link_description = dialog1.findViewById(R.id.act_link_description);
            AppCompatAutoCompleteTextView act_user_link = dialog1.findViewById(R.id.act_user_link);
            CardView cd_save = dialog1.findViewById(R.id.cd_save);
            CardView cd_cancel = dialog1.findViewById(R.id.cd_cancel);

            cd_save.setOnClickListener(view1 -> {

                int selectedCat = rd_group.getCheckedRadioButtonId();

                String ed_title = act_link_title.getText().toString().trim();
                String ed_des = act_link_description.getText().toString().trim();
                String ed_link = act_user_link.getText().toString().trim();

                if (selectedCat == -1){

                    Toast.makeText(requireActivity(), "ক্যাটাগরি সিলেক্ট করুন", Toast.LENGTH_SHORT).show();
                    
                } else if (ed_title.isEmpty()) {

                    Toast.makeText(requireActivity(), "লিংকের টাইটেল দিন", Toast.LENGTH_SHORT).show();

                } else if (ed_des.isEmpty()) {

                    Toast.makeText(requireActivity(), "লিংকের ডেসক্রিপশন দিন", Toast.LENGTH_SHORT).show();

                } else if (ed_link.isEmpty()) {

                    Toast.makeText(requireActivity(), "লিংক দিন", Toast.LENGTH_SHORT).show();

                }else {

                    try {

                        AppCompatRadioButton selectedRadio = rd_group.findViewById(selectedCat);

                        String cat = selectedRadio.getText().toString();

                        dialog1.dismiss();

                        SharedPreferences user_link1 = requireActivity().getSharedPreferences("user_link1", Context.MODE_PRIVATE);
                        SharedPreferences user_link2 = requireActivity().getSharedPreferences("user_link2", Context.MODE_PRIVATE);

                        boolean l1 = user_link1.getAll().isEmpty();
                        boolean l2 = user_link2.getAll().isEmpty();


                        if (!l1 && !l2){

                            Toast.makeText(requireActivity(), "দুঃখিত, আপনি ইতোমধ্যে ২টি লিংক সেভ করেছেন", Toast.LENGTH_SHORT).show();

                        }else if (l1){

                            user_link1.edit()
                            .putString("cat", cat)
                            .putString("title", ed_title)
                            .putString("description",ed_des)
                            .putString("link", "https://"+ed_link)
                            .apply();


                            Toast.makeText(requireActivity(), "লিংক সেভ হয়েছে", Toast.LENGTH_SHORT).show();
                            
                        } else if (l2) {

                            user_link2.edit()
                            .putString("cat", cat)
                            .putString("title", ed_title)
                            .putString("description",ed_des)
                            .putString("link", "https://"+ed_link)
                            .apply();

                            Toast.makeText(requireActivity(), "লিংক সেভ হয়েছে", Toast.LENGTH_SHORT).show();
                            
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }


            });

            cd_cancel.setOnClickListener(view2 -> {

                dialog1.dismiss();

            });
            dialog1.show();

        });


    }
    private void user_save_links(){

        Dialog dialog1 = new Dialog(requireActivity());
        dialog1.setContentView(R.layout.lay_user_link);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        Window window = dialog1.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        AppCompatTextView tv_cat1, tv_title1, tv_description1,tv_cat2, tv_title2, tv_description2;
        LinearLayout  ll_view;
        AppCompatImageView iv_delete1, iv_delete2;
        CardView cv_view1,cv_view2;

        tv_cat1 = dialog1.findViewById(R.id.tv_cat1);
        tv_title1 = dialog1.findViewById(R.id.tv_title1);
        tv_description1 = dialog1.findViewById(R.id.tv_description1);
        tv_cat2 = dialog1.findViewById(R.id.tv_cat2);
        tv_title2 = dialog1.findViewById(R.id.tv_title2);
        tv_description2 = dialog1.findViewById(R.id.tv_description2);
        cv_view1 = dialog1.findViewById(R.id.cv_view1);
        cv_view2 = dialog1.findViewById(R.id.cv_view2);
        iv_delete1 = dialog1.findViewById(R.id.iv_delete1);
        iv_delete2 = dialog1.findViewById(R.id.iv_delete2);
        ll_view = dialog1.findViewById(R.id.ll_view);

        //others---------------------------------------------------
        SharedPreferences user_link1 = requireActivity().getSharedPreferences("user_link1", Context.MODE_PRIVATE);
        SharedPreferences user_link2 = requireActivity().getSharedPreferences("user_link2", Context.MODE_PRIVATE);

        boolean l1 = user_link1.getAll().isEmpty();
        boolean l2 = user_link2.getAll().isEmpty();

        //others---------------------------------------------------

        String cat1 = user_link1.getString("cat","No cat");
        String title1 = user_link1.getString("title","No title");
        String description1 = user_link1.getString("description","No description");
        String link1 = user_link1.getString("link","No link");
        String cat2 = user_link2.getString("cat","No cat");
        String title2 = user_link2.getString("title","No title");
        String description2 = user_link2.getString("description","No description");
        String link2 = user_link2.getString("link","No link");

        ll_view.setVisibility(View.GONE);
        cv_view1.setVisibility(View.GONE);
        cv_view2.setVisibility(View.GONE);
        iv_delete1.setVisibility(View.GONE);
        iv_delete2.setVisibility(View.GONE);

        if (l1 && l2) {

            Toast.makeText(requireActivity(), "কোন লিংক নেই", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(() -> {
                ll_view.setVisibility(View.VISIBLE);
                dialog1.dismiss();

            }, 1000);

        }
        if (!l1){

            ll_view.setVisibility(View.VISIBLE);
            cv_view1.setVisibility(View.VISIBLE);
            iv_delete1.setVisibility(View.VISIBLE);
            tv_cat1.setText(cat1);
            tv_title1.setText(title1);
            tv_description1.setText(description1);

            cv_view1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(link1)));

                }
            });

            iv_delete1.setOnClickListener(view -> {

                user_link1.edit()
                        .clear()
                        .apply();


                dialog1.dismiss();
            });


        }
        if (!l2){

            ll_view.setVisibility(View.VISIBLE);
            cv_view2.setVisibility(View.VISIBLE);
            iv_delete2.setVisibility(View.VISIBLE);
            tv_cat2.setText(cat2);
            tv_title2.setText(title2);
            tv_description2.setText(description2);

            cv_view2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(link2)));

                }
            });

            iv_delete2.setOnClickListener(view -> {

                user_link2.edit()
                        .clear()
                        .apply();

                dialog1.dismiss();
            });


        }
        dialog1.show();

    }

    @Override
    public void onPause() {
        super.onPause();

        lastTime = System.currentTimeMillis();
    }

    @Override
    public void onResume() {
        super.onResume();


        // যদি ৫ সেকেন্ড এর বেশি সময় অ্যাপ বাইরে ছিল, তাহলে রিস্টার্ট করাও
        if (lastTime != 0 && (System.currentTimeMillis() - lastTime > 5000)) {
            Intent intent = new Intent(requireActivity(), Act_Home.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            requireActivity().finish(); // পুরনো অ্যাক্টিভিটি শেষ
        }
    }

    //sqlite -----------------------------------------------------

    private void show_data(){

        favorite = new Favorite(requireActivity());

        List<HashMap<String, String>> list = favorite.getAlldata();

       show_list.addAll(list);

    }


}// public class ======================