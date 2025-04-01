package com.rk_softwares.bdlinkhub.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class Request_limit {

    private SharedPreferences sharedPreferences;

    private static final long TIME_LIMIT = 60 * 1000;
    private static final int MAX_REQUEST = 3;

    // Constructor to initialize SharedPreferences
    public Request_limit(Context context) {
        sharedPreferences = context.getSharedPreferences("RequestPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
    }

    // Method to check request limit and update count
    public boolean canMakeRequest(Context context) {
        
        long currentTime = System.currentTimeMillis();
        long lastRequestTime = sharedPreferences.getLong("lastRequestTime", 0);
        int requestCount = sharedPreferences.getInt("requestCount", 0);

        // 1 মিনিট পেরিয়েছে কিনা চেক করুন
        if (currentTime - lastRequestTime > TIME_LIMIT) {
            // যদি 1 মিনিট পেরিয়ে গিয়ে থাকে, কাউন্ট reset করুন
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("requestCount", 1);
            editor.putLong("lastRequestTime", currentTime);
            editor.apply();
            return true; // এখন API রিকুয়েস্ট পাঠানো যাবে
        } else {
            // 1 মিনিটের মধ্যে যদি 5 এর বেশি রিকুয়েস্ট হয়ে থাকে, false রিটার্ন করুন
            if (requestCount >= MAX_REQUEST) {

                Toast.makeText(context, "কিছুক্ষণ পর আবার চেষ্টা করুন", Toast.LENGTH_SHORT).show();
                return false; // Too many requests
            }

            // 1 মিনিটের মধ্যে রিকুয়েস্ট সংখ্যা বাড়ানো হচ্ছে
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("requestCount", requestCount + 1);
            editor.apply();
            return true; // এখন API রিকুয়েস্ট পাঠানো যাবে
        }
    }

}
