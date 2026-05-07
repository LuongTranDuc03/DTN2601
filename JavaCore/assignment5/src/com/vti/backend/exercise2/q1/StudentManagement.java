package com.vti.backend.exercise2.q1;

import com.vti.entity.exercise2.q1.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentManagement {
    private List<Student> students;

    public StudentManagement() {
        students = new ArrayList<>();
    }

    public void initStudents() {
        students.add(new Student(1, "Nguyễn Văn A", 1));
        students.add(new Student(2, "Nguyễn Văn B", 1));
        students.add(new Student(3, "Nguyễn Văn C", 1));
        students.add(new Student(4, "Nguyễn Văn D", 2));
        students.add(new Student(5, "Nguyễn Văn E", 2));
        students.add(new Student(6, "Nguyễn Văn F", 2));
        students.add(new Student(7, "Nguyễn Văn G", 3));
        students.add(new Student(8, "Nguyễn Văn H", 3));
        students.add(new Student(9, "Nguyễn Văn I", 3));
        students.add(new Student(10, "Nguyễn Văn K", 3));
        System.out.println("Đã tạo 10 học sinh chia thành 3 nhóm.");
    }

    public void allDiemDanh() {
        System.out.println("Cả lớp điểm danh:");
        for (Student s : students) {
            s.diemDanh();
        }
    }

    public void groupHocBai(int group) {
        System.out.println("Nhóm " + group + " đi học bài:");
        for (Student s : students) {
            if (s.getGroup() == group) {
                s.hocBai();
            }
        }
    }

    public void groupDonVeSinh(int group) {
        System.out.println("Nhóm " + group + " đi dọn vệ sinh:");
        for (Student s : students) {
            if (s.getGroup() == group) {
                s.diDonVeSinh();
            }
        }
    }
}
