package com.vti.entity;

public class Student {
    private int id;
    private String name;
    private String hometown;
    private double gpa;

    public Student() {
    }

    public Student(String name, String hometown) {
        this.name = name;
        this.hometown = hometown;
        this.gpa = 0;
    }

    public Student(int id, String name, String hometown, double gpa) {
        this.id = id;
        this.name = name;
        this.hometown = hometown;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHometown() {
        return hometown;
    }

    public double getGpa() {
        return gpa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void addGpa(double points) {
        this.gpa += points;
    }

    private String getRankString() {
        if (gpa < 4.0) {
            return "Yếu";
        } else if (gpa >= 4.0 && gpa < 6.0) {
            return "Trung bình";
        } else if (gpa >= 6.0 && gpa < 8.0) {
            return "Khá";
        } else {
            return "Giỏi";
        }
    }

    public void displayInfo() {
        System.out.println("Sinh viên: " + this.name);
        System.out.println("Quê quán: " + this.hometown);
        System.out.println("Điểm học lực: " + this.gpa + " (" + getRankString() + ")");
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hometown='" + hometown + '\'' +
                ", gpa=" + gpa +
                ", rank='" + getRankString() + '\'' +
                '}';
    }
}
