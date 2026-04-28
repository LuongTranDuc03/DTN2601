package assignment.exercise1;

import java.util.Random;

public class datatypeCasting {
    public void question1(){
        float luongAcc1, luongAcc2;
        int luongAcc1Int, luongAcc2Int;

        luongAcc1 = 5240.5f;
        luongAcc2 = 10970.055f;

        luongAcc1Int = (int) luongAcc1;
        luongAcc2Int = (int) luongAcc2;

        System.out.println("luong acc1: " + luongAcc1Int);
        System.out.println("luong acc2: " + luongAcc2Int);
    }

    public void question2(){
        Random rd = new Random();
        int number = rd.nextInt(100000);
        String formattedNumber = String.format("%05d", number);

        System.out.println("Số ngãu nhiên là: " + formattedNumber);
    }

    public void question3(){
        Random rd = new Random();
        int number = rd.nextInt(100000);
        String formattedNumber = String.format("%05d", number);

        System.out.println("Số gốc (5 chữ số): " + formattedNumber);

        // --- CÁCH 1: Convert sang String và cắt chuỗi ---
        // Substring để lấy từ index 3 đến hết
        String lastTwoStr = formattedNumber.substring(3);
        System.out.println("Cách 1 (String): 2 số cuối là " + lastTwoStr);

        // --- CÁCH 2: Chia lấy dư cho 100 ---
        // Lấy phần dư của phép chia
        int lastTwoInt = number % 100;

        // String.format để in ra "01" thay vì "1"
        System.out.format("Cách 2 (Toán tử): 2 số cuối là %02d\n", lastTwoInt);
    }

    public int question4 (int a, int b) {
        return (int) a/b;
    }
}
