package com.example.doanhunnyfood.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.doanhunnyfood.entity.User;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<User>> findAll();
    @Insert
    void insert(User user);

    @Query("SELECT * FROM user WHERE userName = :username AND password = :password")
    Single<User> login(String username, String password);

    @Update
    void update(User user);

}
