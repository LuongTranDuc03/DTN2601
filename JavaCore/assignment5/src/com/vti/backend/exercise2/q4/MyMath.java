package com.vti.backend.exercise2.q4;

public class MyMath {
    
    public int sum(int a, int b) {
        return a + b;
    }

    public int sum(byte a, byte b) {
        return a + b;
    }

    public float sum(float a, float b) {
        return a + b;
    }

    // Overloading for mixed types if needed
    public float sum(int a, float b) {
        return a + b;
    }

    public float sum(float a, int b) {
        return a + b;
    }
}
