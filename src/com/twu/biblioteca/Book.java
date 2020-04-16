package com.twu.biblioteca;

public class Book {
    private String name;
    private String author;
    private String year;
    private boolean isCheckedOut;
    public User userThatHasCheckedItOut;


    public Book(String name, String author, String year) {
        setName(name);
        setAuthor(author);
        setYear(year);
        setIsCheckedOut(false);
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

    public void setUserThatHasCheckedItOut(User userThatHasCheckedItOut) {this.userThatHasCheckedItOut = userThatHasCheckedItOut;}

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

    public User getUserThatHasCheckedItOut() {return userThatHasCheckedItOut;}

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Book) {
            final Book other = (Book) obj;
            return this.name.equals(other.name) && this.author.equals(other.author) && this.year.equals(other.year);
        }
        return false;
    }
}
