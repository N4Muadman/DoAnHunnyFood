package com.example.doanhunnyfood.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.doanhunnyfood.dao.AppDatabase;
import com.example.doanhunnyfood.dao.OrderDao;
import com.example.doanhunnyfood.entydi.Order;
import com.example.doanhunnyfood.entydi.OrderView;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderRepository {
    private OrderDao orderDao;

    private LiveData<List<Order>> AllOrder;

    ExecutorService executorService;

    public OrderRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.orderDao = db.orderDao();
        this.AllOrder = orderDao.findAll();
        this.executorService = Executors.newFixedThreadPool(4);
    }

    public LiveData<List<Order>> getAllOrder() {
        return AllOrder;
    }

    public LiveData<List<OrderView>> getAllOrderView(){
        return orderDao.getOrderDetailWithTableAndFood();
    }

    public void insert(Order order, InsertOrderCallback callback){
        executorService.execute(() -> {
            long orderId = orderDao.insert(order);
            callback.onOrderInserted(orderId);
        });
    }
    public interface InsertOrderCallback{
        void onOrderInserted(long orderId);
    }
}
