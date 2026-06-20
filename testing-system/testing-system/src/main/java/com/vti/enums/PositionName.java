package com.vti.enums;

public enum PositionName {
    Dev("D"),
    Test("T"),
    Scrum_Master("S"),
    PM("P");

    /*
    còn có kieu Dev("D", 0), Test("T", 1), Scrum_Master("S", 0)
    dùng để hiển thị giá trị cho từng role cụ thể thôi

     */

    private final String value;

    PositionName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PositionName fromValue(String value) {
        if (value == null) {
            return null;
        }
        for (PositionName positionName : PositionName.values()) {
            if (positionName.getValue().equalsIgnoreCase(value) 
                || positionName.name().equalsIgnoreCase(value)) {
                return positionName;
            }
        }
        throw new IllegalArgumentException("Unknown position name: " + value);
    }
}
