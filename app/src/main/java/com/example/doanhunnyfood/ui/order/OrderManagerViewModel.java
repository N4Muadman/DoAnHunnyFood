package com.example.doanhunnyfood.ui.order;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.doanhunnyfood.entity.Order;
import com.example.doanhunnyfood.entity.OrderView;
import com.example.doanhunnyfood.entity.Order_Table_User;
import com.example.doanhunnyfood.repository.OrderRepository;

import java.util.List;

public class OrderManagerViewModel extends AndroidViewModel {
    private OrderRepository repository;
    private LiveData<List<Order_Table_User>> otuList;
    private LiveData<List<OrderView>> orderView;

    public OrderManagerViewModel(@NonNull Application application) {
        super(application);
        repository = new OrderRepository(application);
        this.otuList = repository.getAllOrder_tb_u();
        this.orderView = repository.getAllOrderView();
    }


    public LiveData<List<Order_Table_User>> getOtuList() {
        return otuList;
    }

    public LiveData<List<OrderView>> getOrderView() {
        return orderView;
    }
}