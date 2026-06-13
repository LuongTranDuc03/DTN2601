package assignment.exercise5;

import entity.Department;

import java.util.ArrayList;
import java.util.Arrays;

public class exercise5 {
    public void question1() {
//        ArrayList<Department> listDe = new ArrayList<>();
//        // Gán dữ liệu vào
//        System.out.println(listDe[0].toString());
    }

    public void question2() {
        // Dùng for each rồi lấy từng phòng ban ra
    }

    public void question3() {
        // ListDe[0].getAddress()
    }

    public void question4() {
        // ListDe[0].getName().equals("Phòng A")
    }

    public void question5() {
        // ListDe[0].getName().equals(ListDe[1].getName());
    }

    public void question6() {
        String[] departments = {
                "Sale",
                "marketing",
                "Accounting",
                "Waiting room",
                "boss of director"
        };

        Arrays.sort(departments);

        System.out.println("Danh sách phòng ban sau khi sắp xếp (A-Z):");
        for (String dept : departments) {
            System.out.println(dept);
        }
    }

    public void question7() {
        String[] departments = {
                "Accounting",
                "boss of director",
                "Marketing",
                "waiting room",
                "Sale"
        };

        Arrays.sort(departments, String.CASE_INSENSITIVE_ORDER);

        System.out.println("Danh sách phòng ban sau khi sắp xếp (A-Z):");
        for (String dept : departments) {
            System.out.println("- " + dept);
        }
    }
}
