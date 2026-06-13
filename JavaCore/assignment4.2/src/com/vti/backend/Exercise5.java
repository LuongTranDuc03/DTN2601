package com.vti.backend;

import com.vti.entity.*;
import java.util.Scanner;

public class Exercise5 {
    private QLCB qlcb;
    private Scanner scanner;

    public Exercise5() {
        qlcb = new QLCB();
        scanner = new Scanner(System.in);
    }

    public void question2() {
        while (true) {
            System.out.println("========== MENU QLCB ==========");
            System.out.println("1. Thêm mới cán bộ");
            System.out.println("2. Tìm kiếm theo họ tên");
            System.out.println("3. Hiển thị thông tin danh sách cán bộ");
            System.out.println("4. Xóa cán bộ theo tên");
            System.out.println("5. Thoát");
            System.out.print("Mời bạn chọn chức năng: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    themMoiCanBo();
                    break;
                case 2:
                    System.out.print("Nhập tên cần tìm: ");
                    String tenTimKiem = scanner.nextLine();
                    qlcb.timKiemTheoHoTen(tenTimKiem);
                    break;
                case 3:
                    qlcb.hienThiDanhSachCanBo();
                    break;
                case 4:
                    System.out.print("Nhập tên cần xóa: ");
                    String tenXoa = scanner.nextLine();
                    qlcb.xoaCanBoTheoTen(tenXoa);
                    break;
                case 5:
                    System.out.println("Thoát chương trình!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
                    break;
            }
        }
    }

    private void themMoiCanBo() {
        System.out.println("Chọn loại cán bộ muốn thêm:");
        System.out.println("1. Công nhân");
        System.out.println("2. Kỹ sư");
        System.out.println("3. Nhân viên");
        int type = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Nhập họ tên: ");
        String hoTen = scanner.nextLine();
        System.out.print("Nhập tuổi: ");
        int tuoi = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nhập giới tính (1. Nam, 2. Nữ, 3. Khác): ");
        int genderChoice = scanner.nextInt();
        scanner.nextLine();
        Gender gioiTinh = Gender.KHAC;
        if (genderChoice == 1) gioiTinh = Gender.NAM;
        else if (genderChoice == 2) gioiTinh = Gender.NU;

        System.out.print("Nhập địa chỉ: ");
        String diaChi = scanner.nextLine();

        switch (type) {
            case 1:
                System.out.print("Nhập bậc (1-10): ");
                int bac = scanner.nextInt();
                scanner.nextLine();
                qlcb.themMoiCanBo(new CongNhan(hoTen, tuoi, gioiTinh, diaChi, bac));
                break;
            case 2:
                System.out.print("Nhập ngành đào tạo: ");
                String nganh = scanner.nextLine();
                qlcb.themMoiCanBo(new KySu(hoTen, tuoi, gioiTinh, diaChi, nganh));
                break;
            case 3:
                System.out.print("Nhập công việc: ");
                String congViec = scanner.nextLine();
                qlcb.themMoiCanBo(new NhanVien(hoTen, tuoi, gioiTinh, diaChi, congViec));
                break;
            default:
                System.out.println("Loại cán bộ không hợp lệ!");
                break;
        }
    }

    public void question3() {
        System.out.println("Question 3: Constructor Inheritance");
        HighSchoolStudent highSchoolStudent = new HighSchoolStudent(1, "Nam", "Chuyên Văn", "Đại học công nghệ");
        System.out.println(highSchoolStudent);
    }

    public void question4() {
        QuanLySach qlSach = new QuanLySach();
        while (true) {
            System.out.println("========== MENU QUẢN LÝ SÁCH ==========");
            System.out.println("1. Thêm mới tài liệu");
            System.out.println("2. Xóa tài liệu theo mã");
            System.out.println("3. Hiển thị thông tin tài liệu");
            System.out.println("4. Tìm kiếm tài liệu theo loại");
            System.out.println("5. Thoát");
            System.out.print("Mời bạn chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    themMoiTaiLieu(qlSach);
                    break;
                case 2:
                    System.out.print("Nhập mã tài liệu cần xóa: ");
                    String maXoa = scanner.nextLine();
                    qlSach.xoaTaiLieu(maXoa);
                    break;
                case 3:
                    qlSach.hienThiDanhSach();
                    break;
                case 4:
                    System.out.println("Chọn loại cần tìm: 1. Sách, 2. Tạp chí, 3. Báo");
                    int loaiTim = scanner.nextInt();
                    qlSach.timKiemTheoLoai(loaiTim);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Chọn sai!");
            }
        }
    }

    private void themMoiTaiLieu(QuanLySach qlSach) {
        System.out.println("Chọn loại tài liệu: 1. Sách, 2. Tạp chí, 3. Báo");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhập mã tài liệu: ");
        String ma = scanner.nextLine();
        System.out.print("Nhập tên NXB: ");
        String nxb = scanner.nextLine();
        System.out.print("Nhập số bản phát hành: ");
        int soBan = scanner.nextInt();
        scanner.nextLine();

        switch (type) {
            case 1:
                System.out.print("Nhập tên tác giả: ");
                String tacGia = scanner.nextLine();
                System.out.print("Nhập số trang: ");
                int soTrang = scanner.nextInt();
                qlSach.themMoiTaiLieu(new Sach(ma, nxb, soBan, tacGia, soTrang));
                break;
            case 2:
                System.out.print("Nhập số phát hành: ");
                int soPH = scanner.nextInt();
                System.out.print("Nhập tháng phát hành: ");
                int thangPH = scanner.nextInt();
                qlSach.themMoiTaiLieu(new TapChi(ma, nxb, soBan, soPH, thangPH));
                break;
            case 3:
                System.out.print("Nhập ngày phát hành: ");
                int ngayPH = scanner.nextInt();
                qlSach.themMoiTaiLieu(new Bao(ma, nxb, soBan, ngayPH));
                break;
            default:
                System.out.println("Sai loại!");
        }
    }
}
