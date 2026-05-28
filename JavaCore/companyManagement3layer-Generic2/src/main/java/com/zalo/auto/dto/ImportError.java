package com.zalo.auto.dto;

public class ImportError {
    private int lineNumber;
    private String value;
    private String error;

    public ImportError() {}

    public ImportError(int lineNumber, String value, String error) {
        this.lineNumber = lineNumber;
        this.value = value;
        this.error = error;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // Tương thích ngược với DepartmentCsvImport
    public String getDepartmentName() {
        return value;
    }

    public void setDepartmentName(String departmentName) {
        this.value = departmentName;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return String.format("Line %d: '%s' -> Lỗi: %s", lineNumber, value, error);
    }
}
