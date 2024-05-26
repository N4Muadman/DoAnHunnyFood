package com.example.doanhunnyfood.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.doanhunnyfood.entity.Food;

import java.util.List;

@Dao
public interface FoodDao {

    @Query("SELECT * FROM Food")
    LiveData<List<Food>> FindAll();

    @Insert
    void insert(Food food);

    @Update
    void update(Food food);
}
