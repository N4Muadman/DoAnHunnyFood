package com.example.doanhunnyfood.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class OrderView implements Parcelable {
    private int orderId;
    private int tableId;
    private int foodId;
    private int foodImg;
    private int quantity;
    private double price;
    private String tableName;
    private String foodName;
    private int statusOrder;
    private int statusFood;
    private double totalFood;

    public OrderView(int orderId, int tableId, int foodId, int foodImg, int quantity, double price, String tableName, String foodName, int statusOrder, int statusFood, double totalFood) {
        this.orderId = orderId;
        this.tableId = tableId;
        this.foodId = foodId;
        this.foodImg = foodImg;
        this.quantity = quantity;
        this.price = price;
        this.tableName = tableName;
        this.foodName = foodName;
        this.statusOrder = statusOrder;
        this.statusFood = statusFood;
        this.totalFood = totalFood;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(int foodImg) {
        this.foodImg = foodImg;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(int statusOrder) {
        this.statusOrder = statusOrder;
    }

    public int getStatusFood() {
        return statusFood;
    }

    public void setStatusFood(int statusFood) {
        this.statusFood = statusFood;
    }

    public double getTotalFood() {
        return totalFood;
    }

    public void setTotalFood(double totalFood) {
        this.totalFood = totalFood;
    }

    public static final Parcelable.Creator<OrderView> CREATOR = new Parcelable.Creator<OrderView>() {
        @Override
        public OrderView createFromParcel(Parcel in) {
            return new OrderView(in);
        }

        @Override
        public OrderView[] newArray(int size) {
            return new OrderView[size];
        }
    };

    protected OrderView(Parcel in) {
        orderId = in.readInt();
        tableId = in.readInt();
        foodId = in.readInt();
        foodImg = in.readInt();
        quantity = in.readInt();
        price = in.readDouble();
        tableName = in.readString();
        foodName = in.readString();
        statusOrder = in.readInt();
        statusFood = in.readInt();
        totalFood = in.readDouble();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(orderId);
        dest.writeInt(tableId);
        dest.writeInt(foodId);
        dest.writeInt(foodImg);
        dest.writeInt(quantity);
        dest.writeDouble(price);
        dest.writeString(tableName);
        dest.writeString(foodName);
        dest.writeInt(statusOrder);
        dest.writeInt(statusFood);
        dest.writeDouble(totalFood);
    }
}
