package com.rk_softwares.bdlinkhub.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_history")
public class History {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String data;
}
