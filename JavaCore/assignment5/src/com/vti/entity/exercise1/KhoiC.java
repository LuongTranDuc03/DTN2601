package com.vti.entity.exercise1;

public class KhoiC extends Candidate {
    public static final String MON_VAN = "Van";
    public static final String MON_SU = "Su";
    public static final String MON_DIA = "Dia";

    public KhoiC(String soBaoDanh, String hoTen, String diaChi, int mucUuTien) {
        super(soBaoDanh, hoTen, diaChi, mucUuTien);
    }

    @Override
    public String toString() {
        return super.toString() + ", Khoi C (Mon: " + MON_VAN + ", " + MON_SU + ", " + MON_DIA + ")";
    }
}
