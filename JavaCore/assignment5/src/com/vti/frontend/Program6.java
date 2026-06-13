package com.vti.frontend;

import com.vti.backend.exercise2.q4.MyMath;

public class Program6 {
    public static void main(String[] args) {
        MyMath math = new MyMath();

        // Test int
        System.out.println("Sum of 2 integers (5, 10): " + math.sum(5, 10));

        // Test byte
        byte b1 = 3;
        byte b2 = 7;
        System.out.println("Sum of 2 bytes (3, 7): " + math.sum(b1, b2));

        // Test float
        System.out.println("Sum of 2 floats (5.5f, 4.5f): " + math.sum(5.5f, 4.5f));

        // Test mixed
        System.out.println("Sum of int and float (10, 2.5f): " + math.sum(10, 2.5f));
    }
}
