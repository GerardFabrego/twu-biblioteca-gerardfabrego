package com.twu.biblioteca;

public class Book extends Item{

    private String author;


    public Book(String name, String author, String year) {
        setName(name);
        setAuthor(author);
        setYear(year);
        setIsCheckedOut(false);
    }


    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

}
