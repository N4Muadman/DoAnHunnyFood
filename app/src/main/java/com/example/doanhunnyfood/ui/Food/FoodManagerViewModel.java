package com.example.doanhunnyfood.ui.Food;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.doanhunnyfood.entity.Food;
import com.example.doanhunnyfood.repository.FoodRepository;

import java.util.List;

public class FoodManagerViewModel extends AndroidViewModel {

    private FoodRepository mFoodManagerRepository;
    private LiveData<List<Food>> mAllFoodManager;

    public FoodManagerViewModel(@NonNull Application application) {
        super(application);
        mFoodManagerRepository = new FoodRepository(application);
        mAllFoodManager = mFoodManagerRepository.getAllDish();
    }
    public LiveData<List<Food>> getAllFoodManager() {
        return mAllFoodManager;
    }
    public void insert(Food FoodManager){
        mFoodManagerRepository.insert(FoodManager);
    }
}
