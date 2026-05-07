package com.vti.entity.exercise2.q3;

public class HinhChuNhat {
    protected float chieuDai;
    protected float chieuRong;

    public HinhChuNhat(float chieuDai, float chieuRong) {
        this.chieuDai = chieuDai;
        this.chieuRong = chieuRong;
    }

    public float tinhChuVi() {
        System.out.println("Tính chu vi theo Hình Chữ Nhật");
        return (chieuDai + chieuRong) * 2;
    }

    public float tinhDienTich() {
        System.out.println("Tính diện tích theo Hình Chữ Nhật");
        return chieuDai * chieuRong;
    }
}
