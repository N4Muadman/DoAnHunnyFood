package com.example.doanhunnyfood.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
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
                @ForeignKey(entity = User.class,
                        parentColumns = "id",
                        childColumns = "user_id",
                        onDelete = ForeignKey.CASCADE),
        })
public class Order implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "table_id")
    public int table_id;
    @ColumnInfo(name = "user_id")
    public int user_id;
    @ColumnInfo(name = "order_time")
    public long order_time;
    @ColumnInfo(name = "total")
    public double total;
    @ColumnInfo(name = "status")
    public int status;

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {
            return new Order();
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(table_id);
        dest.writeInt(user_id);
        dest.writeLong(order_time);
        dest.writeDouble(total);
        dest.writeInt(status);
    }
}
