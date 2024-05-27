package com.example.doanhunnyfood.ui.user;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.doanhunnyfood.entity.User;
import com.example.doanhunnyfood.repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private LiveData<List<User>> mAllUser;
    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        this.mAllUser = userRepository.getUserAll();

    }

    public LiveData<List<User>> getAllUser() {
        return mAllUser;
    }

    // TODO: Implement the ViewModel
}