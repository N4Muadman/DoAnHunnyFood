package com.example.doanhunnyfood.ui.DinnerTable;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.doanhunnyfood.entydi.Order;
import com.example.doanhunnyfood.entydi.Table;
import com.example.doanhunnyfood.repository.OrderRepository;
import com.example.doanhunnyfood.repository.TableRepository;

import java.util.List;

public class DinnerTableViewModel extends AndroidViewModel {

    private TableRepository mTableRepository;
    private OrderRepository orderRepository;
    private LiveData<List<Table>> mAllTable;
    private LiveData<List<Order>> mAllOrder;
    public DinnerTableViewModel(@NonNull Application application) {
        super(application);
        mTableRepository = new TableRepository(application);
        orderRepository = new OrderRepository(application);
        mAllOrder = orderRepository.getAllOrder();
        mAllTable = mTableRepository.getAllTable();
    }

    public LiveData<List<Table>> getAllTable() {
        return mAllTable;
    }

    public LiveData<List<Order>> getAllOrder() {
        return mAllOrder;
    }

    public void insert(Table table){
        mTableRepository.insert(table);
    }
}