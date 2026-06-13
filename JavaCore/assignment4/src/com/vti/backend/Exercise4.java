package com.vti.backend;

import com.vti.entity.Student;

public class Exercise4 {
    public void question1() {
        System.out.println("Question 1: Tạo Student và Set Điểm");
        System.out.println("==========================================");
        
        Student student1 = new Student("Nguyễn Văn A", "Hà Nội");
        Student student2 = new Student("Trần Thị B", "Hồ Chí Minh");
        
        student1.setGpa(3.5);
        student2.setGpa(7.5);
        
        System.out.println("Student 1:");
        student1.displayInfo();
        
        System.out.println("\nStudent 2:");
        student2.displayInfo();
    }
    
    public void question2() {
        System.out.println("\nQuestion 2: Cộng Thêm Điểm");
        System.out.println("==========================================");
        
        Student student = new Student("Phạm Văn C", "Đà Nẵng");
        System.out.println("Điểm ban đầu:");
        student.displayInfo();
        
        // Cộng thêm điểm
        student.addGpa(2.0);
        System.out.println("\nSau khi cộng thêm 2.0 điểm:");
        student.displayInfo();
        
        // Cộng thêm điểm lần nữa
        student.addGpa(1.5);
        System.out.println("\nSau khi cộng thêm 1.5 điểm:");
        student.displayInfo();
    }
    
    public void question3() {
        System.out.println("\nQuestion 3: Xếp Hạng Học Lực");
        System.out.println("==========================================");
        
        Student yeuStudent = new Student(1, "Lê Văn D", "Huế", 3.0);
        Student trungBinhStudent = new Student(2, "Võ Thị E", "Hải Phòng", 5.0);
        Student khaStudent = new Student(3, "Dương Văn F", "Cần Thơ", 7.0);
        Student gioiStudent = new Student(4, "Hoàng Thị G", "Quảng Ninh", 8.5);
        
        System.out.println("Sinh viên Yếu (gpa < 4.0):");
        yeuStudent.displayInfo();
        
        System.out.println("\nSinh viên Trung Bình (4.0 <= gpa < 6.0):");
        trungBinhStudent.displayInfo();
        
        System.out.println("\nSinh viên Khá (6.0 <= gpa < 8.0):");
        khaStudent.displayInfo();
        
        System.out.println("\nSinh viên Giỏi (gpa >= 8.0):");
        gioiStudent.displayInfo();
    }
}
