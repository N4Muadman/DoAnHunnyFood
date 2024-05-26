package com.example.doanhunnyfood.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.doanhunnyfood.dao.AppDatabase;
import com.example.doanhunnyfood.dao.UserDao;
import com.example.doanhunnyfood.entity.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserRepository {
    private LiveData<List<User>> userAll;

    private UserDao userDao;

    private ExecutorService executorService;

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.userDao = db.userDao();
        this.userAll = userDao.findAll();
        this.executorService = Executors.newFixedThreadPool(4);
    }

    public LiveData<List<User>> getUserAll() {
        return userAll;
    }


    public Single<User> login(String username, String password) {
        return userDao.login(username, password)
                .subscribeOn(Schedulers.io()); // Thực hiện trên background thread
    }

    public void insert(User user){
        executorService.execute(()-> userDao.insert(user));
    }
}
