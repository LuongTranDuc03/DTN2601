package com.vti.backend.exercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class News {
    private List<com.vti.entity.exercise1.News> newsList;
    private Scanner scanner;

    public News() {
        newsList = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void insertNews() {
        com.vti.entity.exercise1.News news = new com.vti.entity.exercise1.News();
        System.out.println("Enter Title:");
        news.setTitle(scanner.nextLine());
        System.out.println("Enter Publish Date:");
        news.setPublishDate(scanner.nextLine());
        System.out.println("Enter Author:");
        news.setAuthor(scanner.nextLine());
        System.out.println("Enter Content:");
        news.setContent(scanner.nextLine());

        int[] rates = new int[3];
        System.out.println("Enter 3 rates:");
        for (int i = 0; i < 3; i++) {
            System.out.print("Rate " + (i + 1) + ": ");
            try {
                rates[i] = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, setting rate to 0.");
                rates[i] = 0;
            }
        }
        news.setRates(rates);
        newsList.add(news);
        System.out.println("News inserted successfully!");
    }

    public void viewListNews() {
        if (newsList.isEmpty()) {
            System.out.println("News list is empty.");
            return;
        }
        for (com.vti.entity.exercise1.News news : newsList) {
            news.Display();
        }
    }

    public void averageRate() {
        if (newsList.isEmpty()) {
            System.out.println("News list is empty.");
            return;
        }
        for (com.vti.entity.exercise1.News news : newsList) {
            news.Calculate();
            news.Display();
        }
    }
}
