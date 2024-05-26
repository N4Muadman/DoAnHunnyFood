package com.example.doanhunnyfood.entity;

public class Cart {
    private int id, img, qtt;
    private double price;
    private String name;

    public Cart(int id, int img, int qtt, double price, String name) {
        this.id = id;
        this.img = img;
        this.qtt = qtt;
        this.price = price;
        this.name = name;
    }

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getQtt() {
        return qtt;
    }

    public void setQtt(int qtt) {
        this.qtt = qtt;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
