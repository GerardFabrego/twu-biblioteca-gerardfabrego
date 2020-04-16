package com.twu.biblioteca;

public class Book {
    private String name;
    private String author;
    private String year;
    private boolean isCheckedOut;


    public Book(String name, String author, String year) {
        setName(name);
        setAuthor(author);
        setYear(year);
        setIsCheckedOut(false);
    }

    public Book(String name, String author, String year, boolean isCheckedOut) {
        setName(name);
        setAuthor(author);
        setYear(year);
        setIsCheckedOut(isCheckedOut);
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

    public void setIsCheckedOut(boolean isCheckedOut) {this.isCheckedOut = isCheckedOut; }


    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }

    public boolean getIsCheckedOut() {return isCheckedOut; }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Book) {
            final Book other = (Book) obj;
            return this.name.equals(other.name) && this.author.equals(other.author) && this.year.equals(other.year);
        }
        return false;
    }
}
