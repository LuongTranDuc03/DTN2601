package com.vti.entity;

public class HighSchoolStudent extends StudentEx5 {
    private String className;
    private String desiredUniversity;

    public HighSchoolStudent(int id, String name, String className, String desiredUniversity) {
        super(id, name);
        this.className = className;
        this.desiredUniversity = desiredUniversity;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDesiredUniversity() {
        return desiredUniversity;
    }

    public void setDesiredUniversity(String desiredUniversity) {
        this.desiredUniversity = desiredUniversity;
    }

    @Override
    public String toString() {
        return "HighSchoolStudent{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", className='" + className + '\'' +
                ", desiredUniversity='" + desiredUniversity + '\'' +
                '}';
    }
}
