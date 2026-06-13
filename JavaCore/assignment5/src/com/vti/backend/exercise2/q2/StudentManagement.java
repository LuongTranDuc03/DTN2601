package com.vti.backend.exercise2.q2;

import com.vti.entity.exercise2.q2.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {
    private List<Student> students;
    private Scanner scanner;

    public StudentManagement() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addStudent() {
        Student student = new Student();
        student.inputInfo();
        students.add(student);
    }

    public void showAll() {
        for (Student s : students) {
            s.showInfo();
            if (s.hasScholarship()) {
                System.out.println("=> Duoc hoc bong!");
            } else {
                System.out.println("=> Khong duoc hoc bong.");
            }
            System.out.println("-------------------------");
        }
    }
}
