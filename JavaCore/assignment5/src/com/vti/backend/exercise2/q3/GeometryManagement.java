package com.vti.backend.exercise2.q3;

import com.vti.entity.exercise2.q3.HinhChuNhat;
import com.vti.entity.exercise2.q3.HinhVuong;

public class GeometryManagement {
    public void demo() {
        System.out.println("--- Demo Hinh Chu Nhat ---");
        HinhChuNhat hcn = new HinhChuNhat(5, 4);
        System.out.println("Chu vi: " + hcn.tinhChuVi());
        System.out.println("Dien tich: " + hcn.tinhDienTich());

        System.out.println("\n--- Demo Hinh Vuong ---");
        HinhVuong hv = new HinhVuong(5);
        System.out.println("Chu vi: " + hv.tinhChuVi());
        System.out.println("Dien tich: " + hv.tinhDienTich());
    }
}
