package com.vti.entity;

public abstract class StudentEx5 extends Person {
    private int id;

    public StudentEx5(int id, String name) {
        super(name);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
