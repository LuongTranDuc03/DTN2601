package com.vti.frontend;

import java.util.Scanner;

public class Program1 {
    public static void main(String[] args) {
        com.vti.backend.exercise1.News backend = new com.vti.backend.exercise1.News();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("========= MENU =========");
            System.out.println("1. Insert news");
            System.out.println("2. View list news");
            System.out.println("3. Average rate");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = 0;
                continue;
            }

            switch (choice) {
                case 1:
                    backend.insertNews();
                    break;
                case 2:
                    backend.viewListNews();
                    break;
                case 3:
                    backend.averageRate();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Please choose between 1 and 4.");
                    break;
            }
        } while (choice != 4);

        scanner.close();
    }
}
