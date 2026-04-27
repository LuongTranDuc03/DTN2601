package repository.exercise4;

import java.time.LocalDate;
import java.util.Random;

public class Exercise4 {

    // Question 1: In ngẫu nhiên 1 số nguyên
    public void question1() {
        System.out.println("--- Question 1 ---");
        Random random = new Random();
        int n = random.nextInt(10);
        System.out.println("Số ngẫu nhiên: " + n);
    }

//    public static void main(String[] args) {
//        Exercise4 obj = new Exercise4();
//        obj.question1();
//    }

    // Question 2: In ngẫu nhiên 1 số thực
    public void question2() {
        System.out.println("--- Question 2 ---");
        Random random = new Random();
        float f = random.nextFloat();
        System.out.println("Số thực ngẫu nhiên: " + f);
    }

    // Question 3: Khai báo 1 mảng tên và in ngẫu nhiên 1 tên
    public void question3() {
        System.out.println("--- Question 3 ---");
        String[] names = { "Lương", "Nam", "Duy", "Sơn", "Hùng" };
        Random random = new Random();
        int index = random.nextInt(names.length); // Lấy index ngẫu nhiên từ 0 đến chiều dài mảng - 1
        System.out.println("Tên ngẫu nhiên được chọn: " + names[index]);
    }

    // Question 4: Lấy ngẫu nhiên 1 ngày trong khoảng từ 24/07/1995 đến 20/12/1995
    public void question4() {
        System.out.println("--- Question 4 ---");
        Random random = new Random();

        // Chuyển ngày về số ngày (epoch day) để tính toán
        int minDay = (int) LocalDate.of(1995, 7, 24).toEpochDay();
        int maxDay = (int) LocalDate.of(1995, 12, 20).toEpochDay();

        // Random 1 số trong khoảng cách giữa 2 ngày rồi cộng lại vào minDay
        int randomInt = minDay + random.nextInt(maxDay - minDay + 1);
        LocalDate randomDate = LocalDate.ofEpochDay(randomInt);

        System.out.println("Ngày ngẫu nhiên: " + randomDate);
    }

    // Question 5: Lấy ngẫu nhiên 1 ngày trong khoảng 1 năm trở lại đây
    public void question5() {
        System.out.println("--- Question 5 ---");
        Random random = new Random();

        int now = (int) LocalDate.now().toEpochDay();
        int lastYear = now - 365;

        int randomInt = lastYear + random.nextInt(now - lastYear + 1);
        LocalDate randomDate = LocalDate.ofEpochDay(randomInt);

        System.out.println("Ngày ngẫu nhiên trong 1 năm qua: " + randomDate);
    }

    // Question 6: Lấy ngẫu nhiên 1 ngày trong quá khứ
    public void question6() {
        System.out.println("--- Question 6 ---");
        Random random = new Random();

        int now = (int) LocalDate.now().toEpochDay();
        // Giả sử lấy ngẫu nhiên 1 ngày trong khoảng 100 năm (36500 ngày) về trước
        int randomInt = random.nextInt(now);
        LocalDate randomDate = LocalDate.ofEpochDay(randomInt);

        System.out.println("Ngày ngẫu nhiên trong quá khứ: " + randomDate);
    }

    // Question 7: Lấy ngẫu nhiên 1 số có 3 chữ số
    public void question7() {
        System.out.println("--- Question 7 ---");
        Random random = new Random();
        // Khoảng số có 3 chữ số là từ 100 đến 999
        int n = 100 + random.nextInt(900); // 900 là độ dài của khoảng [100, 999]
        System.out.println("Số ngẫu nhiên có 3 chữ số: " + n);
    }
}