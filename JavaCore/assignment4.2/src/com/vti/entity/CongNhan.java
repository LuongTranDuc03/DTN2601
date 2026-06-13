package com.vti.entity;

public class CongNhan extends CanBo {
    private int bac;

    public CongNhan() {
    }

    public CongNhan(String hoTen, int tuoi, Gender gioiTinh, String diaChi, int bac) {
        super(hoTen, tuoi, gioiTinh, diaChi);
        this.bac = bac;
    }

    public int getBac() {
        return bac;
    }

    public void setBac(int bac) {
        if (bac >= 1 && bac <= 10) {
            this.bac = bac;
        } else {
            System.out.println("Bậc phải từ 1 đến 10!");
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", CongNhan{" +
                "bac=" + bac +
                '}';
    }
}
