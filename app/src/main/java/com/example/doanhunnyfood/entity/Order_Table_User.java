package com.example.doanhunnyfood.entity;

public class Order_Table_User {
    public int id;
    public String tableName;
    public String userName;
    public long orderTime;
    public int status;

    public Order_Table_User(int id, String tableName, String userName, long orderTime, int status) {
        this.id = id;
        this.tableName = tableName;
        this.userName = userName;
        this.orderTime = orderTime;
        this.status = status;
    }
}
