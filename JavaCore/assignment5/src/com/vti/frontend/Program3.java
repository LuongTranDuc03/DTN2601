package com.vti.frontend;

import com.vti.backend.exercise2.q1.StudentManagement;

public class Program3 {
    public static void main(String[] args) {
        StudentManagement management = new StudentManagement();
        
        // a) Tạo 10 học sinh, chia thành 3 nhóm
        management.initStudents();
        System.out.println();

        // b) Kêu gọi cả lớp điểm danh
        management.allDiemDanh();
        System.out.println();

        // c) Gọi nhóm 1 đi học bài
        management.groupHocBai(1);
        System.out.println();

        // d) Gọi nhóm 2 đi dọn vệ sinh
        management.groupDonVeSinh(2);
    }
}
