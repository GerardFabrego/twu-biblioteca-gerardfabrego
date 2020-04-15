package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BooksDataBase {

    List<Book> listOfCheckedOutBooks;
    List<Book> listOfBooks;

    public BooksDataBase(Book... books){
        listOfCheckedOutBooks = new ArrayList<>();
        listOfBooks = new ArrayList<>();
        Collections.addAll(listOfBooks, books);
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
        if (!bookExist) {
            System.out.println("We don't have '" + name + "' on our book database.");
        }
    }

    private void checkOutBook(Book book) {
        listOfBooks.remove(book);
        listOfCheckedOutBooks.add(book);
        System.out.println("You have checked out '" + book.getName() + "'.");
    }

    public void looksIfBookIsFromOurCollectionAndReturnIt(String name) {
        boolean isFromOurCollection = false;
        for (Book book : listOfCheckedOutBooks) {
            if (book.getName().equals(name)) {
                returnBook(book);
                isFromOurCollection = true;
                break;
            }
        }
        if (!isFromOurCollection) {
            System.out.println("The book '" + name + "' doesn't belong to our collection");
        }
    }


    public void returnBook (Book book) {
        listOfBooks.add(book);
        listOfCheckedOutBooks.remove(book);
        System.out.println("You returned the book '" + book.getName() + "' successfully.");
    }
}
