package com.vti.entity.exercise1;

public class KhoiA extends Candidate {
    public static final String MON_TOAN = "Toan";
    public static final String MON_LY = "Ly";
    public static final String MON_HOA = "Hoa";

    public KhoiA(String soBaoDanh, String hoTen, String diaChi, int mucUuTien) {
        super(soBaoDanh, hoTen, diaChi, mucUuTien);
    }

    @Override
    public String toString() {
        return super.toString() + ", Khoi A (Mon: " + MON_TOAN + ", " + MON_LY + ", " + MON_HOA + ")";
    }
}
