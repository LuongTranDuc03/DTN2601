package com.vti.backend;

import com.vti.entity.TaiLieu;
import com.vti.entity.Sach;
import com.vti.entity.TapChi;
import com.vti.entity.Bao;
import java.util.ArrayList;
import java.util.List;

public class QuanLySach {
    private List<TaiLieu> danhSachTaiLieu;

    public QuanLySach() {
        this.danhSachTaiLieu = new ArrayList<>();
    }

    public void themMoiTaiLieu(TaiLieu taiLieu) {
        this.danhSachTaiLieu.add(taiLieu);
        System.out.println("Thêm mới thành công!");
    }

    public void xoaTaiLieu(String maTaiLieu) {
        boolean removed = danhSachTaiLieu.removeIf(t -> t.getMaTaiLieu().equalsIgnoreCase(maTaiLieu));
        if (removed) {
            System.out.println("Xóa thành công tài liệu: " + maTaiLieu);
        } else {
            System.out.println("Không tìm thấy mã tài liệu để xóa.");
        }
    }

    public void hienThiDanhSach() {
        if (danhSachTaiLieu.isEmpty()) {
            System.out.println("Danh sách trống.");
        } else {
            for (TaiLieu t : danhSachTaiLieu) {
                System.out.println(t);
            }
        }
    }

    public void timKiemTheoLoai(int loai) {
        boolean found = false;
        for (TaiLieu t : danhSachTaiLieu) {
            if (loai == 1 && t instanceof Sach) {
                System.out.println(t);
                found = true;
            } else if (loai == 2 && t instanceof TapChi) {
                System.out.println(t);
                found = true;
            } else if (loai == 3 && t instanceof Bao) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không có tài liệu thuộc loại đã chọn.");
        }
    }
}
