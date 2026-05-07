package com.vti.backend;

import com.vti.entity.CanBo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLCB {
    private List<CanBo> danhSachCanBo;

    public QLCB() {
        danhSachCanBo = new ArrayList<>();
    }

    public void themMoiCanBo(CanBo canBo) {
        danhSachCanBo.add(canBo);
        System.out.println("Thêm mới cán bộ thành công!");
    }

    public void timKiemTheoHoTen(String hoTen) {
        boolean found = false;
        for (CanBo canBo : danhSachCanBo) {
            if (canBo.getHoTen().equalsIgnoreCase(hoTen)) {
                System.out.println(canBo);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy cán bộ có tên: " + hoTen);
        }
    }

    public void hienThiDanhSachCanBo() {
        if (danhSachCanBo.isEmpty()) {
            System.out.println("Danh sách cán bộ trống.");
        } else {
            for (CanBo canBo : danhSachCanBo) {
                System.out.println(canBo);
            }
        }
    }

    public void xoaCanBoTheoTen(String hoTen) {
        boolean removed = danhSachCanBo.removeIf(canBo -> canBo.getHoTen().equalsIgnoreCase(hoTen));
        if (removed) {
            System.out.println("Đã xóa cán bộ có tên: " + hoTen);
        } else {
            System.out.println("Không tìm thấy cán bộ có tên: " + hoTen + " để xóa.");
        }
    }
}
