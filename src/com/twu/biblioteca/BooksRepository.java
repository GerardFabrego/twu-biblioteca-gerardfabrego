package com.twu.biblioteca;


import com.twu.biblioteca.items.Book;
import com.twu.biblioteca.items.Item;

import java.util.ArrayList;
import java.util.Collections;

public class BooksRepository extends ItemsRepository {

    public BooksRepository(Book... books){
        listOfItems = new ArrayList<>();
        Collections.addAll(listOfItems, books);
    }


    public void printListOfBooks() {
        System.out.print(Constants.lineBreak);
        System.out.printf(Constants.listOfBooksFormat, Constants.name, Constants.author, Constants.year, Constants.isAvailable);
        for(Item item: listOfItems){
            Book book = (Book) item;
            String isAvailable;
            if (!book.getIsCheckedOut()) {isAvailable = Constants.yes;}
            else { isAvailable = Constants.no;}
            System.out.printf(Constants.listOfBooksFormat, book.getName(), book.getAuthor(), book.getYear(), isAvailable);
        }
    }


    public void printCheckedOutItems() {
        for (Item item: listOfItems){
            Book book = (Book) item;
            if (book.getIsCheckedOut()) {
                System.out.printf(Constants.checkedOutItemsFormat, book.getUserThatHasCheckedItOut().getLibraryNumber(), Constants.book, book.getName(), book.getAuthor(), book.getYear());
            }
        }
    }
}
