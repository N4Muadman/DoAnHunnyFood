package com.example.doanhunnyfood.ui.Food;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.doanhunnyfood.entity.Food;
import com.example.doanhunnyfood.entity.Order;
import com.example.doanhunnyfood.entity.Order_detail;
import com.example.doanhunnyfood.repository.FoodRepository;
import com.example.doanhunnyfood.repository.OrderRepository;
import com.example.doanhunnyfood.repository.Order_detailRepository;

import java.util.List;

public class FoodViewModel extends AndroidViewModel {

    private FoodRepository foodRepository;
    private LiveData<List<Food>> mAllDish;
    private OrderRepository orderRepository;
    private Order_detailRepository orderDetailRepository;

    public FoodViewModel(@NonNull Application application) {
        super(application);
        foodRepository = new FoodRepository(application);
        orderRepository = new OrderRepository(application);
        orderDetailRepository = new Order_detailRepository(application);
        mAllDish = foodRepository.getAllDish();
    }


    public LiveData<List<Food>> getAllDish() {
        return mAllDish;
    }

    public void insertOrder(Order order, OrderRepository.InsertOrderCallback callback){
        orderRepository.insert(order, callback);
    }

    public void insertOrderDetail(List<Order_detail> orderDetails, OrderDetailInsertCallback callback) {
        orderDetailRepository.insertOrderDetails(orderDetails, new Order_detailRepository.OrderDetailInsertCallback() {
            @Override
            public void onOrderDetailInserted() {
                callback.onOrderDetailInserted();
            }

            @Override
            public void onOrderDetailInsertFailed() {
                callback.onOrderDetailInsertFailed();
            }
        });
    }

    public interface OrderDetailInsertCallback {
        void onOrderDetailInserted();
        void onOrderDetailInsertFailed();
    }

}