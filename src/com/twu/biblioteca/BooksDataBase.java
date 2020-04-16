package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BooksDataBase {

    List<Book> listOfBooks;

    public BooksDataBase(Book... books){
        listOfBooks = new ArrayList<>();
        Collections.addAll(listOfBooks, books);
    }

    public Book getBookByName (String bookName){
        for (Book book : listOfBooks) {
            if (book.getName().equals(bookName)) {
                return book;
            }
        }
        return null;
    }

    public void printListOfBooks() {
        System.out.println();
        System.out.printf("%-25s %-25s %-4s\n", "Name", "Author", "Year");
        for(Book book: listOfBooks){
            System.out.printf("%-25s %-25s %-4s\n", book.getName(), book.getAuthor(), book.getYear());
        }
    }

    public void lookForAndCheckOutBook(String name) {
        Book book = getBookByName((name));
        if (book != null) {
            if (!book.getIsCheckedOut()) {
                checkOutBook(book);
            } else {
                System.out.println("'" + name + "' is currently checked out");
            }
        }else {
            System.out.println("We don't have '" + name + "' on our book database.");
        }
    }

    private void checkOutBook(Book book) {
        book.setIsCheckedOut(true);
        book.setUserThatHasCheckedItOut(BibliotecaApp.userLoggedIn);
        System.out.println("You have checked out '" + book.getName() + "'.");
    }


    public void checksIfBookIsFromOurCollectionAndReturnIt(String name) {
        Book book = getBookByName((name));
        if (book != null) {
            if (book.getIsCheckedOut()) {
                returnBook(book);
            } else {
                System.out.println("'" + name + "' was not checked out.");
            }
        }else {
            System.out.println("The book '" + name + "' doesn't belong to our collection");
        }
    }


    private void returnBook (Book book) {
        book.setIsCheckedOut(false);
        book.setUserThatHasCheckedItOut(null);
        System.out.println("You returned the book '" + book.getName() + "' successfully.");
    }




}
