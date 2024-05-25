package com.example.doanhunnyfood.entydi;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "order_detail",
        primaryKeys = {"order_id", "food_id"},
        foreignKeys = {
                @ForeignKey(entity = Order.class,
                        parentColumns = "id",
                        childColumns = "order_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Food.class,
                        parentColumns = "id",
                        childColumns = "food_id",
                        onDelete = ForeignKey.CASCADE)
        }
)
public class Order_detail {

    @ColumnInfo(name = "order_id")
    public int order_id;

    @ColumnInfo(name = "food_id")
    public int food_id;

    @ColumnInfo(name = "qtt")
    public int qtt;

    @ColumnInfo(name = "status")
    public int status;

    @ColumnInfo(name = "TotalFood")
    public double TotalFood;
}
