package com.example.doanhunnyfood.ui.UnpainOrder;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.doanhunnyfood.entity.Order;
import com.example.doanhunnyfood.entity.OrderView;
import com.example.doanhunnyfood.repository.OrderRepository;
import com.example.doanhunnyfood.repository.Order_detailRepository;

import java.util.List;

public class UnpaidOrderDetailViewModel extends AndroidViewModel {

    private LiveData<List<OrderView>> mAllOrderView;
    private OrderRepository orderRepository;

    private Order_detailRepository orderDetailRepository;


    public UnpaidOrderDetailViewModel(@NonNull Application application) {
        super(application);
        orderRepository = new OrderRepository(application);
        orderDetailRepository = new Order_detailRepository(application);
        this.mAllOrderView = orderRepository.getAllOrderView();
    }

    public LiveData<List<OrderView>> getAllOrderView() {
        return mAllOrderView;
    }

    public void update(Order order){
        orderRepository.update(order);
    }
}