package com.zalo.auto.enums;

public enum PositionName {
    Dev("Dev"), 
    Test("Test"), 
    Scrum_Master("Scrum Master"), 
    PM("PM");

    private String value;

    PositionName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * Chuyển đổi một chuỗi văn bản sang hằng số Enum tương ứng.
     * Phương thức này hỗ trợ tìm kiếm linh hoạt theo cả giá trị hiển thị (value)
     * và tên định danh của Enum (name).
     * 
     * @param text Chuỗi văn bản cần chuyển đổi (ví dụ: "Scrum Master" hoặc "Scrum_Master")
     * @return Đối tượng PositionName tương ứng, hoặc null nếu không tìm thấy
     */
    public static PositionName fromString(String text) {
        // Duyệt qua tất cả các hằng số Enum trong PositionName
        for (PositionName b : PositionName.values()) {
            // So sánh không phân biệt hoa thường với thuộc tính 'value' hoặc tên Enum 'name'
            if (b.value.equalsIgnoreCase(text) || b.name().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return this.value;
    }
}
