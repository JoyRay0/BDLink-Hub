package com.rk_softwares.bdlinkhub.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryDao {

    @Insert
    void insert(History history);

    @Query("SELECT * FROM user_history")
    List<History> getAll();

   @Query("DELETE  FROM user_history")
    void DeleteAll();

}
