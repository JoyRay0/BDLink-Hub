package com.rk_softwares.bdlinkhub.Activity;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rk_softwares.bdlinkhub.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class HospitalNumberActivity extends AppCompatActivity {

    //XML id's----------------------------------------------

    AppCompatButton button_refresh;

    AppCompatAutoCompleteTextView hospital_search;

    Toolbar toolbar;

    LinearLayout hospital_offline_linerlayout, hospital_online_linerlayout;

    ImageButton back;

    ListView hospital_listview;

    ProgressBar hospital_progressbar;

    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap ;

    Myadapter adapter;

     boolean is_searchVisible = true;


    //XML id's----------------------------------------------


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_number);

        //identity period----------------------------------------------

        hospital_search = findViewById(R.id.hospital_search);
        hospital_progressbar = findViewById(R.id.hospital_progressbar);
        toolbar = findViewById(R.id.toolbar);
        hospital_listview = findViewById(R.id.hospital_listview);
        button_refresh = findViewById(R.id.button_refresh);

        hospital_offline_linerlayout = findViewById(R.id.hospital_offline_linerlayout);
        hospital_online_linerlayout = findViewById(R.id.hospital_online_linerlayout);

        //identity period----------------------------------------------

        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle(" ");

        data_from_server();

        adapter = new Myadapter(this,arrayList);
        hospital_listview.setAdapter(adapter);







        if (Home_activity.INTERNET == true){


            hospital_offline_linerlayout.setVisibility(View.GONE);
            hospital_online_linerlayout.setVisibility(View.VISIBLE);

        }else {

            hospital_online_linerlayout.setVisibility(View.GONE);
            hospital_offline_linerlayout.setVisibility(View.VISIBLE);

            button_refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onRestart();
                    //finishAffinity();
                    //System.exit(0);

                }
            });

        }







        hospital_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence , int i, int i1, int i2) {

                adapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

                //do nothing
            }
        });






    }//on create============================================




    //@Override               //menu function
    //public boolean onCreateOptionsMenu(Menu menu) {

       // MenuInflater menuInflater = getMenuInflater();
       // menuInflater.inflate(R.menu.hospital_menu, menu);

        // search = menu.findItem(R.id.search);



        //search item created------------------------------------------------
        //search item created------------------------------------------------

       // return true;

   // }

    private void data_from_server(){

        String url = getString(R.string.hospital_info_json);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override

                    public void onResponse(JSONObject response) {

                        hospital_progressbar.setVisibility(View.GONE);


                        Log.d("Server_response",response.toString());

                        try {

                            JSONArray jsonArray = response.getJSONArray("hospital_info");

                            for (int x = 0; x < jsonArray.length(); x++){

                                JSONObject jsonObject = jsonArray.getJSONObject(x);

                                String hospital_name = jsonObject.getString("hospital_name");
                                String hospital_quality = jsonObject.getString("hospital_quality");
                                String hospital_address = jsonObject.getString("hospital_address");
                                String hospital_num1 = jsonObject.getString("hospital_num1");
                                String hospital_num2 = jsonObject.getString("hospital_num2");


                                JSONObject hospital_other_info = jsonObject.getJSONObject("hospital_others_info");

                                String facebook_id = hospital_other_info.getString("facebook_id");
                                String email_id = hospital_other_info.getString("email_id");
                                String website = hospital_other_info.getString("website");
                                String location = hospital_other_info.getString("location");

                                //string resources =====================================
                                String facebook_img = getString(R.string.facebook_img);
                                String email_img = getString(R.string.email_img);
                                String website_img = getString(R.string.website_img);
                                String location_img = getString(R.string.location_img);


                                hashMap = new HashMap<>();
                                hashMap.put("hospital_quality",hospital_quality);

                                hashMap.put("hospital_name",hospital_name);
                                hashMap.put("hospital_address",hospital_address);
                                hashMap.put("hospital_num1",hospital_num1);
                                hashMap.put("hospital_num2",hospital_num2);

                                hashMap.put("facebook_id", facebook_img);
                                hashMap.put("facebook",facebook_id);

                                hashMap.put("mail", email_img);
                                hashMap.put("email",email_id);

                                hashMap.put("website", website_img);
                                hashMap.put("website1",website);

                                hashMap.put("location", location_img);
                                hashMap.put("location1",location);
                                
                                arrayList.add(hashMap);


                            }
                            if (adapter != null){

                                adapter.update_list();
                                adapter.notifyDataSetChanged();

                            }



                        } catch (JSONException ex) {
                            throw new RuntimeException(ex);
                        }





                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                        hospital_progressbar.setVisibility(View.GONE);




                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(HospitalNumberActivity.this);
        requestQueue.add(jsonObjectRequest);



    }//data_from_server



    private class Myadapter extends BaseAdapter implements Filterable {//list adpter

        public ArrayList<HashMap<String, String>>data_list;
        public ArrayList<HashMap<String, String>> filtered_data_list;
        public Context context;
        private CustomFilter filter;


        public Myadapter(Context context,ArrayList<HashMap<String, String>>arrayList){
            this.context = context;
            this.data_list = arrayList;
            this.filtered_data_list = new ArrayList<>(data_list);

        }


        @Override
        public int getCount() {
            return filtered_data_list.size();
        }

        @Override
        public Object getItem(int position) {
            return filtered_data_list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view1 = layoutInflater.inflate(R.layout.hospital_card_view, viewGroup, false);

            Animation animation = AnimationUtils.loadAnimation(HospitalNumberActivity.this, R.anim.scale_up);
            view1.startAnimation(animation);

            TextView hospital_name = view1.findViewById(R.id.hospital_name);
            TextView hospital_address = view1.findViewById(R.id.hospital_address);
            TextView hospital_num1 = view1.findViewById(R.id.hospital_num1);
            TextView hospital_num2 = view1.findViewById(R.id.hospital_num2);
            TextView hospital_quality = view1.findViewById(R.id.hospital_quality);
            AppCompatImageView hospital_facebook_id = view1.findViewById(R.id.hospital_facebook_id);
            AppCompatImageView hospital_mail = view1.findViewById(R.id.hospital_mail);
            AppCompatImageView hospital_website = view1.findViewById(R.id.hospital_website);
            AppCompatImageView hospital_location = view1.findViewById(R.id.hospital_location);
            //CardView cardview = view1.findViewById(R.id.cardview);

            //random colors--------------------------------------------------------------------
            //Random rnd = new Random();
            //int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            //cardview.setBackgroundColor(color);
            //random colors--------------------------------------------------------------------

            HashMap<String, String> hashMap1 = filtered_data_list.get(position);






            String hospital_name1 = hashMap1.get("hospital_name");
            String hospital_address1 = hashMap1.get("hospital_address");
            String hospital_num11 = hashMap1.get("hospital_num1");
            String hospital_num12 = hashMap1.get("hospital_num2");
            String hospital_quality1 = hashMap1.get("hospital_quality");

            String hospital_facebook_id1 = hashMap1.get("facebook_id");
            String hospital_mail1 = hashMap1.get("mail");
            String hospital_website1 = hashMap1.get("website");
            String hospital_location1 = hashMap1.get("location");




            /*
            if (MainActivity.PERMISSION == true){


                hospital_num1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent call_intent = new Intent(Intent.ACTION_DIAL);
                        call_intent.setData(Uri.parse("tel:"+hospital_num11));
                        startActivity(call_intent);



                    }
                });

                hospital_num2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent call_intent = new Intent(Intent.ACTION_DIAL);
                        call_intent.setData(Uri.parse("tel:"+hospital_num12));
                        startActivity(call_intent);



                    }
                });
            }else {

                Toast.makeText(HospitalNumberActivity.this, "Please allow call permission", Toast.LENGTH_SHORT).show();

            }

             */

            Picasso.get().load(hospital_facebook_id1).into(hospital_facebook_id);
            Picasso.get().load(hospital_mail1).into(hospital_mail);
            Picasso.get().load(hospital_website1).into(hospital_website);
            Picasso.get().load(hospital_location1).into(hospital_location);



            hospital_quality.setText(hospital_quality1);
            hospital_name.setText(hospital_name1);
            hospital_address.setText("ঠিকানা: "+hospital_address1);
            hospital_num1.setText(hospital_num11);
            hospital_num2.setText(hospital_num12);

            hospital_facebook_id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    String facebook1 = hashMap1.get("facebook");


                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://www.facebook.com/"+facebook1));
                    startActivity(i);




                }
            });

            hospital_mail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String email1 = hashMap1.get("email");

                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    String uriText = "mailto:"+ Uri.encode(email1);
                    Uri uri = Uri.parse(uriText);
                    intent.setData(uri);
                    startActivity(Intent.createChooser(intent," "));



                }
            });

            hospital_location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String location11 = hashMap1.get("location1");


                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("http://maps.google.co.in/maps?q="+location11));
                    startActivity(i);



                }
            });

            hospital_website.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String website2 = hashMap1.get("website1");

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(website2)));


                }
            });

            return view1;
        }

        @Override
        public Filter getFilter() {

            if (filter == null){
                filter = new CustomFilter();
            }

            return filter;
        }

        private class CustomFilter extends Filter{

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                FilterResults results = new FilterResults();


                if (charSequence != null || charSequence.length() > 0){

                    ArrayList<HashMap<String, String>> filtered_data = new ArrayList<>();

                    String filter_data_pattern  = charSequence.toString().toLowerCase().trim();

                    for (HashMap<String, String> item : arrayList){

                        if (item.get("hospital_name").toLowerCase().contains(filter_data_pattern)){

                            filtered_data.add(item);

                        }


                    }
                    results.values = filtered_data;
                    results.count = filtered_data.size();

                }else {

                    results.values = data_list;
                    results.count = data_list.size();
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {


                filtered_data_list = (ArrayList<HashMap<String, String>>) filterResults.values;

                notifyDataSetChanged();
            }
        }

        public void update_list(){
            this.filtered_data_list = new ArrayList<>(arrayList);
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();

        Intent intent = new Intent(this, Home_activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}//public class ===============================