package repository.exercise1;

import entity.*;

import java.time.LocalDate;

public class Exercise1 {

    // Question 1: Kiểm tra phòng ban (IF-ELSE)
    public void question1(Account acc) {
        System.out.println("--- Question 1 ---");
        if (acc.department == null) {
            System.out.println("Nhân viên này chưa có phòng ban");
        } else {
            System.out.println("Phòng ban của nhân viên này là: " + acc.department.name);
        }
    }

    // Question 2: Kiểm tra số lượng group qua bảng trung gian (IF-ELSE)
    public void question2(Account acc, GroupAccount[] allGroupAccounts) {
        System.out.println("--- Question 2 ---");
        int countGroup = 0;
        if (allGroupAccounts != null) {
            for (GroupAccount ga : allGroupAccounts) {
                if (ga.getAccount().getId() == acc.getId()) {
                    countGroup++;
                }
            }
        }

        if (acc == null || countGroup == 0) {
            System.out.println("Nhân viên này chưa có group");
        } else if (countGroup == 1 || countGroup == 2) {
            System.out.println("Group của nhân viên này là Java Fresher, C# Fresher");
        } else if (countGroup == 3) {
            System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
        } else {
            System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
        }
    }

    // Question 3: Sử dụng toán tử Ternary cho Question 1
    public void question3(Account acc) {
        System.out.println("--- Question 3 ---");
        String result = (acc.department == null)
                ? "Nhân viên này chưa có phòng ban"
                : "Phòng ban của nhân viên này là: " + acc.department.name;
        System.out.println(result);
    }

    // Question 4: Sử dụng toán tử Ternary kiểm tra Position
    public void question4(Account acc) {
        System.out.println("--- Question 4 ---");
        String result = (acc.position.name == Position.PositionName.DEV)
                ? "Đây là Developer"
                : "Người này không phải là Developer";
        System.out.println(result);
    }

    // Question 5: Kiểm tra số lượng account trong group (SWITCH-CASE)
    public void question5(Group group) {
        System.out.println("--- Question 5 ---");
        int countAcc = (group.accounts == null) ? 0 : group.accounts.length;
        switch (countAcc) {
            case 1:
                System.out.println("Nhóm có một thành viên");
                break;
            case 2:
                System.out.println("Nhóm có hai thành viên");
                break;
            case 3:
                System.out.println("Nhóm có ba thành viên");
                break;
            default:
                System.out.println("Nhóm có nhiều thành viên");
                break;
        }
    }

    // Question 6: Làm lại Question 2 bằng SWITCH-CASE
    public void question6(Account acc, GroupAccount[] allGroupAccounts) {
        System.out.println("--- Question 6 ---");
        int countGroup = 0;
        if (allGroupAccounts != null) {
            for (GroupAccount ga : allGroupAccounts) {
                if (ga.getAccount().getId() == acc.getId()) {
                    countGroup++;
                }
            }
        }

        switch (countGroup) {
            case 0:
                System.out.println("Nhân viên này chưa có group");
                break;
            case 1:
            case 2:
                System.out.println("Group của nhân viên này là Java Fresher, C# Fresher");
                break;
            case 3:
                System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
                break;
            default:
                System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
                break;
        }
    }

    // Question 7: Làm lại Question 4 bằng SWITCH-CASE
    public void question7(Account acc) {
        System.out.println("--- Question 7 ---");

        // Kiểm tra nếu position hoặc name bị null để tránh lỗi Runtime
        if (acc.position == null || acc.position.name == null) {
            System.out.println("Nhân viên này chưa có chức vụ");
            return;
        }

        switch (acc.position.name) {
            case DEV:
                System.out.println("Đây là Developer");
                break;
            default:
                System.out.println("Người này không phải là Developer");
                break;
        }
    }

    // Question 8: In thông tin các Account (Sử dụng foreach)
    public void question8(Account[] accounts) {
        System.out.println("--- Question 8 ---");
        for (Account acc : accounts) {
            System.out.println("Email: " + acc.getEmail());
            System.out.println("Full Name: " + acc.getFullName());
            System.out.println("Phòng ban: " + (acc.getDepartment() != null ? acc.getDepartment().getName() : "Trống"));
            System.out.println("--------------------");
        }
    }

    public static void question8_1(Account[] acc) {
        // 1. In đường kẻ phía trên tiêu đề
        System.out.println("+------+------------------------+------------------------------+---------------+");

        // 2. In hàng tiêu đề (Header)
        // Lưu ý: Các con số %5s, %20s... phải khớp với độ dài các đoạn thẳng ở bước 1
        System.out.printf("|%-6s|%-24s|%-30s|%-15s|\n", "ID", "FullName", "Position", "Department");

        // 3. In đường kẻ phân cách giữa tiêu đề và nội dung
        System.out.println("+------+------------------------+------------------------------+---------------+");

        // 4. Duyệt mảng và in từng Account
        for (Account a : acc) {
            if (a != null) { // Kiểm tra để tránh lỗi NullPointerException
                System.out.printf("|%-6d|%-24s|%-30s|%-15s|\n",
                        a.getId(),
                        a.getFullName(),
                        (a.getPosition() != null ? a.getPosition().getName() : "N/A"),
                        (a.getDepartment() != null ? a.getDepartment().getName() : "N/A")
                );
            }
        }

        // 5. In đường kẻ kết thúc bảng
        System.out.println("+------+------------------------+------------------------------+---------------+");
    }

    public static void main(String[] args) {
        Account acc1 = new Account(1, "vti1@gmail.com", "user1", "Nguyen Van A", null, null, LocalDate.now());
        Account acc2 = new Account(2, "vti2@gmail.com", "user2", "Tran Van B", null, null, LocalDate.of(2021, 5, 20));
        Account acc3 = new Account(3, "vti3@gmail.com", "user3", "Le Van C",null,null, LocalDate.of(2022, 1, 15));

        Account[] allAccounts = new Account[]{acc1, acc2, acc3};
        question8_1(allAccounts);
    }

    // Question 9: In thông tin các phòng ban (Sử dụng foreach)
    public void question9(Department[] departments) {
        System.out.println("--- Question 9 ---");
        for (Department dep : departments) {
            System.out.println("ID: " + dep.getId());
            System.out.println("Name: " + dep.getName());
            System.out.println("--------------------");
        }
    }

    // Question 10: In thông tin Account bằng vòng lặp for (Cổ điển)
    public void question10(Account[] accounts) {
        System.out.println("--- Question 10 ---");
        for (int i = 0; i < accounts.length; i++) {
            System.out.println("Thông tin account thứ " + (i + 1) + " là:");
            System.out.println("Email: " + accounts[i].getEmail());
            System.out.println("Full Name: " + accounts[i].getFullName());
            System.out.println("Phòng ban: " + (accounts[i].department != null ? accounts[i].department.name : "Trống"));
        }
    }

    // Question 11: In thông tin các phòng ban bằng vòng lặp for
    public void question11(Department[] departments) {
        System.out.println("--- Question 11 ---");
        for (int i = 0; i < departments.length; i++) {
            System.out.println("Thông tin department thứ " + (i + 1) + " là:");
            System.out.println("ID: " + departments[i].getId());
            System.out.println("Name: " + departments[i].getName());
        }
    }

    // Question 12: In thông tin 2 phòng ban đầu tiên
    public void question12(Department[] departments) {
        System.out.println("--- Question 12 ---");
        for (int i = 0; i < 2; i++) {
            if (i < departments.length) {
                System.out.println("Thông tin department thứ " + (i + 1) + " là:");
                System.out.println("ID: " + departments[i].getId());
                System.out.println("Name: " + departments[i].getName());
            }
        }
    }

    // Question 13: In thông tin tất cả account ngoại trừ account thứ 2
    public void question13(Account[] accounts) {
        System.out.println("--- Question 13 ---");
        for (int i = 0; i < accounts.length; i++) {
            if (i != 1) { // Index 1 là account thứ 2
                System.out.println("Thông tin account thứ " + (i + 1) + " là:");
                System.out.println("Email: " + accounts[i].getEmail());
                System.out.println("Full Name: " + accounts[i].getFullName());
            }
        }
    }

    // Question 14: In thông tin tất cả account có id < 4
    public void question14(Account[] accounts) {
        System.out.println("--- Question 14 ---");
        for (Account acc : accounts) {
            if (acc.getId() < 4) {
                System.out.println("Email: " + acc.getEmail());
                System.out.println("Full Name: " + acc.getFullName());
            }
        }
    }

    // Question 15: In các số chẵn <= 20
    public void question15() {
        System.out.println("--- Question 15 ---");
        for (int i = 0; i <= 20; i++) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    // Question 16: Làm lại Question 10 bằng WHILE
    public void question16(Account[] accounts) {
        System.out.println("--- Question 16 ---");
        int i = 0;
        while (i < accounts.length) {
            System.out.println("Thông tin account thứ " + (i + 1) + " là:");
            System.out.println("Email: " + accounts[i].getEmail());
            System.out.println("Full Name: " + accounts[i].getEmail());
            i++;
        }
    }

    // Question 17: Làm lại Question 11 bằng DO-WHILE
    public void question17(Department[] departments) {
        System.out.println("--- Question 17 ---");
        int i = 0;
        if (departments.length > 0) {
            do {
                System.out.println("Thông tin department thứ " + (i + 1) + " là:");
                System.out.println("ID: " + departments[i].getName());
                System.out.println("Name: " + departments[i].getName());
                i++;
            } while (i < departments.length);
        }
    }
}