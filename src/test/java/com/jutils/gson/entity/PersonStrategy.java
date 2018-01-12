package com.jutils.gson.entity;

import java.util.List;

/**
 * Created by mi on 18-1-12.
 */
public class PersonStrategy {
    private long id;

    private String name;

    private String intro;

    private int age;

    private Car car;

    private List<Grade> gradeList;

    public PersonStrategy(long id, String name, String intro, int age) {
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }
}
