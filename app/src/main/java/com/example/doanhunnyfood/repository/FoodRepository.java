package com.example.doanhunnyfood.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.doanhunnyfood.dao.AppDatabase;
import com.example.doanhunnyfood.dao.FoodDao;
import com.example.doanhunnyfood.entity.Food;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FoodRepository {
    private FoodDao mFoodDao;

    private LiveData<List<Food>> AllDish;
    ExecutorService executorService;


    public FoodRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.mFoodDao = db.foodDao();
        this.AllDish = mFoodDao.FindAll();
        this.executorService = Executors.newFixedThreadPool(4);
    }

    public LiveData<List<Food>> getAllDish() {
        return AllDish;
    }

    public void insert(Food food){
        executorService.execute(() -> mFoodDao.insert(food));
    }
}
