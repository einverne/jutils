package com.jutils.gson.entity;

/**
 * Created by mi on 18-1-12.
 */
public class Car {
    private String brand;

    private int price;

    public Car(String brand) {
        this.brand = brand;
    }

    public Car(String brand, int price) {
        this.brand = brand;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
