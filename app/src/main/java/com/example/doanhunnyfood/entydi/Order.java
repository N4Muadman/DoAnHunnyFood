package com.example.doanhunnyfood.entydi;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "order",
        foreignKeys = {
                @ForeignKey(entity = Table.class,
                        parentColumns = "id",
                        childColumns = "table_id",
                        onDelete = ForeignKey.CASCADE),
//                @ForeignKey(entity = Employee.class,
//                        parentColumns = "id",
//                        childColumns = "employee_id",
//                        onDelete = ForeignKey.CASCADE)
        })
public class Order {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "table_id")
    public int table_id;
    @ColumnInfo(name = "employee_id")
    public int employee_id;
    @ColumnInfo(name = "order_time")
    public long order_time;
    @ColumnInfo(name = "total")
    public double total;
    @ColumnInfo(name = "status")
    public int status;
}
