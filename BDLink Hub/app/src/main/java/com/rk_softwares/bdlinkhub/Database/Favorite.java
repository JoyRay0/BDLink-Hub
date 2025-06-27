package com.rk_softwares.bdlinkhub.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Favorite extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "favorite.db";
    public static final String TABLE_NAME = "fav_table";
    public static final int VERSION = 3;
    public final Context app_context;

    public Favorite(@Nullable Context context) {
        super(context.getApplicationContext(), DATABASE_NAME, null, VERSION);
        this.app_context = context.getApplicationContext();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_sql = "CREATE TABLE " +TABLE_NAME+ "(id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "category TEXT, title TEXT UNIQUE, description VARCHAR, link VARCHAR)";

        db.execSQL(create_sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String update = "DROP TABLE IF EXISTS " +TABLE_NAME;

        db.execSQL(update);
        onCreate(db);
    }

    public void user_insert(String category, String title, String description, String link){

        SQLiteDatabase db = null;
        Cursor cursor = null;


        try {

            db = this.getWritableDatabase();

            //checking data limit--------------------------------------------
            int count = 0;
             cursor = db.rawQuery("SELECT COUNT(*) FROM "+TABLE_NAME, null);

            if (cursor.moveToFirst()){

                count = cursor.getInt(0);

            }

            if (count >= 10){

                new Handler(Looper.getMainLooper()).post(() -> {

                    Toast.makeText(app_context, "সর্বোচ্চ ১০টি লিংক সংরক্ষণ করা যাবে", Toast.LENGTH_SHORT).show();

                });

                return;
            }

            //checking data limit--------------------------------------------


            boolean cat = category != null && !category.trim().isEmpty();
            boolean til = title != null && !title.trim().isEmpty();
            boolean des = description != null && !description.trim().isEmpty();
            boolean lin = link != null && !link.trim().isEmpty();

            if (cat && til && des && lin){

                if (!isDuplicate(db,title.trim(), link.trim())){

                    ContentValues values = new ContentValues();

                    values.put("category", category.trim());
                    values.put("title", title.trim());
                    values.put("description", description.trim());
                    values.put("link", link.trim());

                    db.insert(TABLE_NAME, null, values);

                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        }finally {

            if (cursor != null) cursor.close();
            if (db != null && db.isOpen()) db.close();

        }


    }

    public boolean delete(String title, String link){

        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(TABLE_NAME, "title = ? AND link = ?", new String[]{title.trim(), link.trim()});


        db.close();


        return result > 0;
    }

    public List<HashMap<String, String>> getAlldata(){

        List<HashMap<String, String>> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " +TABLE_NAME, null);

        while (cursor.moveToNext()){

            HashMap<String, String> hashMap = new HashMap<>();

            hashMap.put("category", cursor.getString(cursor.getColumnIndexOrThrow("category")));
            hashMap.put("title", cursor.getString(cursor.getColumnIndexOrThrow("title")));
            hashMap.put("description", cursor.getString(cursor.getColumnIndexOrThrow("description")));
            hashMap.put("link", cursor.getString(cursor.getColumnIndexOrThrow("link")));
            list.add(hashMap);

        }

        cursor.close();
        db.close();

        return list;
    }

    public boolean isDuplicate(SQLiteDatabase db ,String title, String link){

        boolean exits = false;
        Cursor cursor = null;

        try  {

            cursor = db.rawQuery("SELECT title FROM " + TABLE_NAME + " WHERE title = ? AND link = ?", new String[]{title, link});
            exits = cursor.moveToFirst();

        } catch (Exception e) {

            e.printStackTrace();

        }finally {
            if (cursor != null) cursor.close();
        }

        return exits;
    }

}
