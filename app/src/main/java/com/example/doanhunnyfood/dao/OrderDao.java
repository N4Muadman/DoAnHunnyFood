package com.example.doanhunnyfood.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.doanhunnyfood.entity.Order;
import com.example.doanhunnyfood.entity.OrderView;
import com.example.doanhunnyfood.entity.Order_Table_User;

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

    @Query("SELECT o.id, t.name AS tableName, u.fullName AS userName, o.order_time as orderTime, o.status AS status " +
            "FROM `order` AS o " +
            "INNER JOIN `table` AS t ON o.table_id = t.id " +
            "INNER JOIN `user` as u ON o.user_id = u.id")
    LiveData<List<Order_Table_User>> getOrder_table_user();

    @Insert
    long insert(Order order);
    @Update
    void update(Order order);
}
