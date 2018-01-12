package com.jutils.gson.entity;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by mi on 18-1-10.
 */
public class PersonTransient {

    private long id;

    @Expose
    private String name;

    @Expose
    private String intro;

    private transient int age;

    private List<Grade> gradeList;

    public PersonTransient(long id, String name, String intro, int age) {
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.age = age;
    }

    public PersonTransient(long id, String name, String intro) {
        this.id = id;
        this.name = name;
        this.intro = intro;
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
}
