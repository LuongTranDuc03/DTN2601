package assignment.exercise2;

import entity.Account;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static java.time.LocalDate.now;

public class defaultValue {
    public void question1(){
        Scanner sc = new Scanner(System.in);

        int id;
        String email;
        String username;
        String fullname;
        LocalDate createDate;
        ArrayList<Account> listAccount = new ArrayList<>();

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for(int i = 0; i<=4; i++){
            int stt = i + 1;
            System.out.println("Enter account " + stt);
            System.out.print("ID " + stt +": ");
            id = Integer.parseInt(sc.nextLine());
            System.out.print("Email " + stt +": ");
            email = sc.nextLine();
            System.out.print("Username " + stt +": ");
            username = sc.nextLine();
            System.out.print("Fullname " + stt +": ");
            fullname = sc.nextLine();
            System.out.print("Create Date " + stt +": " + now.format(formatter));
            System.out.println();

            listAccount.add(new Account(id,email,username,fullname, now));
        }

        listAccount.forEach(System.out::println);
    }

}
