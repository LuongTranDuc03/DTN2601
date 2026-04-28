package assignment.exercise4;

import java.util.Scanner;

public class exercise4 {
    public void question1() {
        Scanner sc = new Scanner(System.in);
        System.out.println("question1");
        String s;

        System.out.print("Nhập chuỗi bất kỳ từ bàn phím: ");
        s = sc.nextLine();

        if (s == null || s.trim().isEmpty()) {
            System.out.println("Số lượng từ: 0");
            return;
        }

        String[] words = s.trim().split("\\s+");

        System.out.println("Số lượng các từ trong câu là: " + words.length);
    }

    public void question2() {
        Scanner sc = new Scanner(System.in);
        System.out.println("question2");
        System.out.println("Nhập xâu s1: ");
        String s1 = sc.nextLine();
        System.out.println("Nhập xâu s2: ");
        String s2 = sc.nextLine();

        System.out.println("Nối xâu s2 vào sau xâu s1 là: " + s1.concat(s2));
    }

    public void question3() {
        System.out.println("question3");
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập vào tên người dùng: ");
        String input = sc.nextLine();

        if (input == null || input.trim().isEmpty()) {
            System.out.println("Tên không hợp lệ!");
            return;
        }

        String[] words1 = input.trim().split("\\s+");
        String[] words2 = input.trim().toLowerCase().split("\\s+");

        for (int i = 0; i < words1.length; i++) {
            if (!words1[i].equals(words2[i])) {
                words1[i] = words1[i].toUpperCase();
            }
        }

        String result = String.join(" ", words1);
        System.out.println(result);
    }

    public void question4() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập vào tên của bạn: ");
        String name = sc.nextLine();

        name = name.trim();

        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);

            if (Character.isWhitespace(c)) {
                System.out.println("Ký tự thứ " + (i + 1) + " là: [Khoảng trắng]");
            } else {
                System.out.println("Ký tự thứ " + (i + 1) + " là: " + c);
            }
        }
    }

    public void question5() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập vào Họ của bạn: ");
        String ho = sc.nextLine().trim();
        System.out.print("Nhập vào Tên của bạn: ");
        String ten = sc.nextLine().trim();

        String hoVaTen = ho + " " + ten;

        System.out.println("Họ và tên đầy đủ của bạn là: " + hoVaTen);
    }

    public void question6() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập vào họ và tên đầy đủ: ");
        String input = sc.nextLine();

        String[] words = input.trim().split("\\s+");

        if (words.length < 2) {
            System.out.println("Vui lòng nhập đầy đủ cả họ và tên!");
            return;
        }
        String ho = words[0];
        String ten = words[words.length - 1];
        String tenDem = "";
        for (int i = 1; i < words.length - 1; i++) {
            tenDem += words[i] + " ";
        }
        tenDem = tenDem.trim();

        System.out.println("Họ là: " + ho);
        System.out.println("Tên đệm là: " + (tenDem.isEmpty() ? "[Không có]" : tenDem));
        System.out.println("Tên là: " + ten);
    }

    public void question7() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập vào họ và tên đầy đủ: ");
        String input = sc.nextLine();

        if (input == null || input.trim().isEmpty()) {
            System.out.println("Dữ liệu nhập vào không hợp lệ!");
            return;
        }

        String[] words = input.trim().toLowerCase().split("\\s+");

        StringBuilder normalizedName = new StringBuilder();
        for (String word : words) {
            // Viết hoa chữ cái đầu + phần còn lại của từ
            String capitalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1);

            // Thêm vào kết quả và kèm một dấu cách duy nhất ở giữa
            normalizedName.append(capitalizedWord).append(" ");
        }

       System.out.println("Tên sau khi chuẩn hóa: " + normalizedName.toString().trim());
    }

    public void question8() {
        /*
        Danh sách chứa Group cho sẵn (phải có name)
        Dùng for each duyệt qua các phần tử .name trong Group
        Sử dụng .contains("Java") để kiểm tra có Java trong name không
         */
    }

    public void question9() {
        // Tương tự question8 nhưng sử dụng .equals hoặc .equalIgnore("Java")
    }

    public void question10() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập chuỗi thứ nhất: ");
        String s1 = sc.nextLine();
        System.out.print("Nhập chuỗi thứ hai: ");
        String s2 = sc.nextLine();

        //Sử dụng StringBuilder để reverse
        String reversedS1 = new StringBuilder(s1).reverse().toString();

        if (s1.length() == s2.length() && reversedS1.equals(s2)) {
            System.out.println("OK");
        } else {
            System.out.println("KO");
        }
    }

    public void question11() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập vào một chuỗi: ");
        String input = sc.nextLine();

        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'a') {
                count++;
            }
        }

        System.out.println("Số lần xuất hiện của ký tự 'a' là: " + count);
    }

    public void question12() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập chuỗi cần đảo ngược: ");
        String input = sc.nextLine();

        String reversed = "";
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed += input.charAt(i);
        }

        System.out.println("Chuỗi sau khi đảo ngược là: " + reversed);
    }

    public void question13() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập chuỗi kiểm tra số: ");
        String input = sc.nextLine();

        if (input == null || input.isEmpty()) {
            System.out.println("false");
            return;
        }

        boolean hasDigit = false;
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                hasDigit = true;
                break;
            }
        }

        // Nếu có số (hasDigit = true) thì in false, ngược lại in true
        System.out.println(!hasDigit);
    }

    public void question14() {
        String str = "VTI Academy";
        char oldChar = 'e';
        char newChar = '*';

        String result = str.replace(oldChar, newChar);

        System.out.println("Chuỗi gốc: " + str);
        System.out.println("Chuỗi sau khi thay thế: " + result);
    }

    public void question15() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập vào chuỗi (VD: I am developer): ");
        String input = sc.nextLine();

        String[] words = input.trim().split("\\s+");

        StringBuilder result = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);

            if (i > 0) {
                result.append(" ");
            }
        }

        System.out.println("Chuỗi sau khi đảo ngược từ: \"" + result.toString() + "\"");

    }

    public void question16() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập vào chuỗi: ");
        String str = sc.nextLine();
        System.out.print("Nhập số nguyên n: ");
        int n = sc.nextInt();

        if (n <= 0 || str.length() % n != 0) {
            System.out.println("KO");
        } else {
            System.out.println("Kết quả chia chuỗi:");

            for (int i = 0; i < str.length(); i += n) {
                // Dùng substring để lấy đoạn từ i đến i + n
                String part = str.substring(i, i + n);
                System.out.println(part);
            }
        }
    }
}
