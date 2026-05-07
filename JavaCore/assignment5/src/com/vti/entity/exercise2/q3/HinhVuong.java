package com.vti.entity.exercise2.q3;

public class HinhVuong extends HinhChuNhat {
    
    public HinhVuong(float canh) {
        super(canh, canh);
    }

    @Override
    public float tinhChuVi() {
        System.out.println("Tính chu vi theo Hình Vuông");
        return super.tinhChuVi();
    }

    @Override
    public float tinhDienTich() {
        System.out.println("Tính diện tích theo Hình Vuông");
        return super.tinhDienTich();
    }
}
