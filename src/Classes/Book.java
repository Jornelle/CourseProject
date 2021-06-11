package Classes;

import java.time.LocalDate;

public class Book {
    private int id;
    private String author;
    private String title;
    private int year;
    private int count;

    public Book(int id, String author, String title, int year, int count) throws Exception {
        if(count < 0){
            throw new Exception("Не верное кол-во");
        }
        if(author.replaceAll(" ", " ").length() == 0){
            throw new Exception("Вы не ввели автора");
        }
        if(title.replaceAll(" ", " ").length() == 0){
            throw new Exception("Вы не ввели название");
        }
        LocalDate date = LocalDate.now();
        int y = date.getYear();
        if(year > y){
            throw new Exception("Не верный год");
        }
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
        this.count = count;
    }

    public Book() {
        this.id = 0;
        this.author = "author";
        this.title = "title";
        this.year = 0;
        this.count = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) throws Exception {
        if(author.replaceAll(" ", " ").length() == 0){
            throw new Exception("Вы не ввели автора");
        }
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws Exception {
        if(title.replaceAll(" ", " ").length() == 0){
            throw new Exception("Вы не ввели название");
        }
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) throws Exception {
        LocalDate date = LocalDate.now();
        int y = date.getYear();
        if(year > y){
            throw new Exception("Не верный год");
        }
        this.year = year;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) throws Exception {
        if(count < 1){
            throw new Exception("Не верное кол-во");
        }
        this.count = count;
    }

    @Override
    public String toString() {
        String info = "Номер книги: " + id;
        info += "\nАвтор: " + author;
        info += "\nНазвание: \"" + title+ "\"";
        info += "\nГод издания: " + year;
        info += "\nКоличество: " + count;
        return info;
    }
}
