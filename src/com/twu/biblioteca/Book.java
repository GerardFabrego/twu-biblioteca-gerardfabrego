package com.twu.biblioteca;

public class Book {
    private String name;
    private String author;
    private String year;

    public Book(String name, String author, String year) {
        setName(name);
        setAuthor(author);
        setYear(year);
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }


    @Override
    public String toString(){
        return this.name;
    }
}
