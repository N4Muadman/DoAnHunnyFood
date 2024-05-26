package com.example.doanhunnyfood.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Food {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "price")
    public double price;

    @ColumnInfo(name = "image")
    public int image;

    @ColumnInfo(name = "status")
    public int status;

    public Food(String name, String description, double price, int image, int status) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.status = status;
    }

}
