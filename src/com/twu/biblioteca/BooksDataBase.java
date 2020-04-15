package com.twu.biblioteca;

import java.util.*;

public class BooksDataBase {

    List<Book> listOfBooks;

    public BooksDataBase(Book... books){
        listOfBooks = new ArrayList<Book>();
        for(Book book: books ){
            listOfBooks.add(book);
        }
    }

    public void printListOfBooks() {
        System.out.printf("%-25s %-25s %-4s\n", "Name", "Author", "Year");
        for(Book book: listOfBooks){
            System.out.printf("%-25s %-25s %-4s\n", book.getName(), book.getAuthor(), book.getYear());
        }
    }

    public void lookForAndCheckOutBook(String name) {
        boolean bookExist = false;
        for (Book book: listOfBooks){
            if (book.getName().equals(name)) {
                checkOutBook(book);
                bookExist = true;
                break;
            }
        }
        if (bookExist == false) {
            System.out.println("We don't have \'" + name + "\' on our book database.");
        }
    }

    public void checkOutBook(Book book) {
        listOfBooks.remove(book);
        System.out.println("You have checked out \'" + book.getName() + "\'.");
    }
}
