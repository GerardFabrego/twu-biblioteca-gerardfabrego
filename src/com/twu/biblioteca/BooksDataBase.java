package com.twu.biblioteca;

import java.util.*;

public class BooksDataBase {
    /*String[] book1 = new String[]{"To kill a mockingbird", "Harper Lee", "1960"};
    String[] book3 = new String[]{"1984", "George Orwell", "1949"};
    String[] book2 = new String[]{"The wise man's fear", "Patrick Rothfuss", "2011"};
    String[] book4 = new String[]{"Ulysses", "James Joyce", "1920"};
    String[][] listOfBooks = {book1, book2, book3, book4};*/

    List<Book> listOfBooks;

    public BooksDataBase(Book... books){
        listOfBooks = new ArrayList<Book>();
        for(Book book: books ){
            listOfBooks.add(book);
        }
    }

    public void printListOfBooks() {
        for(Book book: listOfBooks){
            System.out.println(book);
        }
    }
}
