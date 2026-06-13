package com.vti.entity.exercise1;

public class News implements INews {
    private int id;
    private String title;
    private String publishDate;
    private String author;
    private String content;
    private float averageRate;
    private int[] rates;

    public News() {
        this.rates = new int[3];
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getPublishDate() { return publishDate; }
    public void setPublishDate(String publishDate) { this.publishDate = publishDate; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public float getAverageRate() { return averageRate; }
    public int[] getRates() { return rates; }
    public void setRates(int[] rates) { this.rates = rates; }

    @Override
    public void Display() {
        System.out.println("Title: " + title);
        System.out.println("Publish Date: " + publishDate);
        System.out.println("Author: " + author);
        System.out.println("Content: " + content);
        System.out.println("Average Rate: " + averageRate);
        System.out.println("----------------------------");
    }

    @Override
    public float Calculate() {
        if (rates != null && rates.length > 0) {
            float sum = 0;
            for (int rate : rates) {
                sum += rate;
            }
            averageRate = sum / rates.length;
        }
        return averageRate;
    }
}
