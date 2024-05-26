package com.example.doanhunnyfood.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "UserName")
    public String userName;

    @ColumnInfo(name = "Password")
    public String password;

    @ColumnInfo(name = "fullName")
    public String fullName;

    @ColumnInfo(name = "Email")
    public String email;

    @ColumnInfo(name = "phoneNumber")
    public String phoneNumber;

    @ColumnInfo(name = "Address")
    public String address;

    @ColumnInfo(name = "status")
    public int status;

    public User(String userName, String password, String fullName, String email, String phoneNumber, String address, int status) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.status = status;
    }
}
