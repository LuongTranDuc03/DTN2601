package com.zalo.auto.dto.csv;

public class DepartmentCsv {
    private String name;
    private int lineNumber;

    public DepartmentCsv(String name, int lineNumber) {
        this.name = name;
        this.lineNumber = lineNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
