package com.jutils.gson;

import com.google.gson.annotations.Expose;

/**
 * Created by mi on 18-1-10.
 */
public class Person {

    private long id;

    @Expose
    private String name;

    @Expose
    private String intro;

    public Person(long id, String name, String intro) {
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
}
