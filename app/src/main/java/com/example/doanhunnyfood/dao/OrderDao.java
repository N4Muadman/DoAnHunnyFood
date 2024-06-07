package com.example.doanhunnyfood.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.doanhunnyfood.entity.Order;
import com.example.doanhunnyfood.entity.OrderView;

import java.util.List;

@Dao
public interface OrderDao {
    @Query("SELECT * FROM `order`")
    LiveData<List<Order>> findAll();
    @Query("SELECT COUNT(*) FROM `order`")
    LiveData<Integer> getOrderCount();


    @Query("SELECT o.id AS orderId, t.id AS tableId, f.id AS foodId, f.image AS foodImg, od.qtt AS quantity, f.price AS price , t.name AS tableName, f.name AS foodName, o.status AS statusOrder, od.status AS statusFood, od.TotalFood AS totalFood " +
            "FROM `order` AS o " +
            "INNER JOIN order_detail AS od ON o.id = od.order_id " +
            "INNER JOIN `table` AS t ON o.table_id = t.id " +
            "INNER JOIN food AS f ON od.food_id = f.id")
    LiveData<List<OrderView>> getOrderDetailWithTableAndFood();

    @Insert
    long insert(Order order);
    @Update
    void update(Order order);
}
