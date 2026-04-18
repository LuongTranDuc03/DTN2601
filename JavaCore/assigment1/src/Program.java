import java.time.LocalDate;

public class Program {
    public static void main(String[] args) {

        // --- 1. Department ---
        Department dep1 = new Department(1, "Sale");
        Department dep2 = new Department(2, "Marketing");
        Department dep3 = new Department(3, "Infrastructure");

        System.out.println("--------- DEPARTMENT ---------");
        System.out.println(dep1);
        System.out.println(dep2);
        System.out.println(dep3);

        // --- 2. Position ---
        Position pos1 = new Position(1, Position.PositionName.DEV);
        Position pos2 = new Position(2, Position.PositionName.TEST);
        Position pos3 = new Position(3, Position.PositionName.PM);

        System.out.println("\n--------- POSITION ---------");
        System.out.println(pos1);
        System.out.println(pos2);
        System.out.println(pos3);

        // --- 3. Account ---
        Account acc1 = new Account(1, "vti1@gmail.com", "user1", "Nguyen Van A", dep1, pos1, LocalDate.now());
        Account acc2 = new Account(2, "vti2@gmail.com", "user2", "Tran Van B", dep2, pos2, LocalDate.of(2021, 5, 20));
        Account acc3 = new Account(3, "vti3@gmail.com", "user3", "Le Van C", dep3, pos3, LocalDate.of(2022, 1, 15));

        System.out.println("\n--------- ACCOUNT ---------");
        System.out.println(acc1);
        System.out.println(acc2);
        System.out.println(acc3);

        // --- 4. Group ---
        Group group1 = new Group(1, "Java Fresher", acc1, LocalDate.now());
        Group group2 = new Group(2, "C# Fresher", acc2, LocalDate.now());
        Group group3 = new Group(3, "Database Management", acc1, LocalDate.now());

        System.out.println("\n--------- GROUP ---------");
        System.out.println(group1);
        System.out.println(group2);
        System.out.println(group3);

        // --- 5. TypeQuestion ---
        TypeQuestion type1 = new TypeQuestion(1, TypeQuestion.TypeName.ESSAY);
        TypeQuestion type2 = new TypeQuestion(2, TypeQuestion.TypeName.MULTIPLE_CHOICE);
        // (Nếu đề bài yêu cầu 3, ta có thể lặp lại hoặc tạo thêm loại mới nếu có)
        TypeQuestion type3 = new TypeQuestion(3, TypeQuestion.TypeName.ESSAY);

        System.out.println("\n--------- TYPE QUESTION ---------");
        System.out.println(type1);
        System.out.println(type2);
        System.out.println(type3);

        // --- 6. CategoryQuestion ---
        CategoryQuestion cat1 = new CategoryQuestion(1, "Java");
        CategoryQuestion cat2 = new CategoryQuestion(2, ".NET");
        CategoryQuestion cat3 = new CategoryQuestion(3, "SQL");

        System.out.println("\n--------- CATEGORY QUESTION ---------");
        System.out.println(cat1);
        System.out.println(cat2);
        System.out.println(cat3);

        // --- 7. Question ---
        Question q1 = new Question(1, "Java là gì?", cat1, type1, acc1, LocalDate.now());
        Question q2 = new Question(2, ".NET là gì?", cat2, type2, acc2, LocalDate.now());
        Question q3 = new Question(3, "SQL dùng để làm gì?", cat3, type1, acc3, LocalDate.now());

        System.out.println("\n--------- QUESTION ---------");
        System.out.println(q1);
        System.out.println(q2);
        System.out.println(q3);

        // --- 8. Answer ---
        Answer ans1 = new Answer(1, "Là ngôn ngữ lập trình", q1, true);
        Answer ans2 = new Answer(2, "Là một loại phần cứng", q1, false);
        Answer ans3 = new Answer(3, "Dùng để quản lý dữ liệu", q3, true);

        System.out.println("\n--------- ANSWER ---------");
        System.out.println(ans1);
        System.out.println(ans2);
        System.out.println(ans3);

        // --- 9. Exam ---
        Question[] questionsExam1 = { q1, q2 };

        Exam exam1 = new Exam(1, "VTI01", "Đề thi Java", cat1, 60, acc1, LocalDate.now(), questionsExam1);
        Exam exam2 = new Exam(2, "VTI02", "Đề thi .NET", cat2, 90, acc2, LocalDate.now());
        Exam exam3 = new Exam(3, "VTI03", "Đề thi SQL", cat3, 45, acc3, LocalDate.now());

        System.out.println("\n--------- EXAM ---------");
        System.out.println(exam1);
        System.out.println(exam2);
        System.out.println(exam3);
    }
}