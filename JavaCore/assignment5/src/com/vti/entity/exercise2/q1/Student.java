package com.vti.entity.exercise2.q1;

public class Student implements IStudent {
    private int id;
    private String name;
    private int group;

    public Student(int id, String name, int group) {
        this.id = id;
        this.name = name;
        this.group = group;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getGroup() { return group; }

    @Override
    public void diemDanh() {
        System.out.println(name + " diem danh");
    }

    @Override
    public void hocBai() {
        System.out.println(name + " đang học bài");
    }

    @Override
    public void diDonVeSinh() {
        System.out.println(name + " đi dọn vệ sinh");
    }
}
