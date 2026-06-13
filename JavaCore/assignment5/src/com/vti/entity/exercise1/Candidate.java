package com.vti.entity.exercise1;

public class Candidate {
    protected String soBaoDanh;
    protected String hoTen;
    protected String diaChi;
    protected int mucUuTien;

    public Candidate(String soBaoDanh, String hoTen, String diaChi, int mucUuTien) {
        this.soBaoDanh = soBaoDanh;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.mucUuTien = mucUuTien;
    }

    public String getSoBaoDanh() { return soBaoDanh; }

    @Override
    public String toString() {
        return "SBD: " + soBaoDanh + ", Ho Ten: " + hoTen + ", Dia Chi: " + diaChi + ", Muc Uu Tien: " + mucUuTien;
    }
}
