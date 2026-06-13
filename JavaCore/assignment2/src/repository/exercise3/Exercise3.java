package repository.exercise3;

import entity.Exam;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Exercise3 {

    // Question 1: In ra thông tin Exam thứ 1 và Create Date định dạng Vietnamese
    public void question1(Exam exam) {
        System.out.println("--- Question 1 ---");
        // Locale Vietnamese để hiển thị tên tháng bằng tiếng Việt
        Locale locale = new Locale("vi", "VN");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", locale);

        System.out.println("Exam ID: " + exam.getId());
        System.out.println("Title: " + exam.getTitle());
        System.out.println("Ngày tạo: " + exam.getCreateDate().format(formatter));
    }

    // Question 2: In ra thông tin tất cả các Exam với Create Date định dạng "yyyy-MM-dd HH:mm:ss"
    public void question2(Exam[] exams) {
        System.out.println("--- Question 2 ---");
        // Vì trong entity Exam dùng LocalDate nên chỉ format được yyyy-MM-dd
        // Nếu muốn có HH:mm:ss thì thực thể phải là LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (Exam exam : exams) {
            System.out.println("Exam ID: " + exam.getTitle() + " | Title: " + exam.getTitle());
            // Giả sử in mặc định giờ phút giây là 00:00:00 nếu dùng LocalDate
            System.out.println("Ngày tạo: " + exam.getCreateDate().atStartOfDay().format(formatter));
        }
    }

    // Question 3: Chỉ in ra năm của Create Date
    public void question3(Exam exam) {
        System.out.println("--- Question 3 ---");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        System.out.println("Năm tạo đề thi: " + exam.getCreateDate().format(formatter));
    }

    // Question 4: Chỉ in ra tháng và năm của Create Date
    public void question4(Exam exam) {
        System.out.println("--- Question 4 ---");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
        System.out.println("Tháng-Năm tạo đề thi: " + exam.getCreateDate().format(formatter));
    }

    // Question 5: Chỉ in ra "MM-dd" của Create Date
    public void question5(Exam exam) {
        System.out.println("--- Question 5 ---");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
        System.out.println("Tháng-Ngày tạo đề thi: " + exam.getCreateDate().format(formatter));
    }
}