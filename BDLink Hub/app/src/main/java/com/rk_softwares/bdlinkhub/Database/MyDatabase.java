package com.rk_softwares.bdlinkhub.Database;

import android.app.Activity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {History.class}, version = 2)
public abstract class MyDatabase extends RoomDatabase {

    private static MyDatabase database;

    public abstract HistoryDao historyDao();

    public static synchronized MyDatabase getInstance(Activity activity){

        if (database == null){

            database = Room.databaseBuilder(activity, MyDatabase.class, "DB").build();

        }

        return database;

    }

}
