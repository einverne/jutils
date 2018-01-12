package com.jutils.gson.entity;

/**
 * Created by mi on 18-1-12.
 */
public class Grade {
    private int id;
    private String name;
    private float number;

    public Grade(int id, int number) {
        this.id = id;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
