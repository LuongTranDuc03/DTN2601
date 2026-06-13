package com.vti.entity.exercise1;

public class KhoiB extends Candidate {
    public static final String MON_TOAN = "Toan";
    public static final String MON_HOA = "Hoa";
    public static final String MON_SINH = "Sinh";

    public KhoiB(String soBaoDanh, String hoTen, String diaChi, int mucUuTien) {
        super(soBaoDanh, hoTen, diaChi, mucUuTien);
    }

    @Override
    public String toString() {
        return super.toString() + ", Khoi B (Mon: " + MON_TOAN + ", " + MON_HOA + ", " + MON_SINH + ")";
    }
}
