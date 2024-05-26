package com.example.doanhunnyfood.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.doanhunnyfood.entity.Order_detail;

import java.util.List;

@Dao
public interface Order_detailDao {
    @Query("SELECT * FROM order_detail")
    LiveData<List<Order_detail>> findAll();

    @Insert
    void insert(Order_detail orderDetail);

    @Update
    void update(Order_detail orderDetail);
}
