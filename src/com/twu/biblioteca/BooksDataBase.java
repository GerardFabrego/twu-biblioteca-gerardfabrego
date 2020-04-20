package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;

public class BooksDataBase extends ItemsDataBase {


    public BooksDataBase(Book... books){
        listOfItems = new ArrayList<>();
        Collections.addAll(listOfItems, books);
    }

    public void printListOfItems() {
        System.out.println();
        System.out.printf("%-25s %-25s %-25s %-13s\n", "Name", "Author", "Year", "Is available");
        for(Item item: listOfItems){
            Book book = (Book) item;
            String isAvailable;
            if (!book.getIsCheckedOut()) {isAvailable = "Yes";}
            else { isAvailable = "No";}
            System.out.printf("%-25s %-25s %-25s %-13s\n", book.getName(), book.getAuthor(), book.getYear(), isAvailable);
        }
    }


    public void printCheckedOutItems() {
        for (Item item: listOfItems){
            Book book = (Book) item;
            if (book.getIsCheckedOut()) {
                System.out.printf("%-25s %-25s %-25s %-25s %-4s\n", book.getUserThatHasCheckedItOut().getLibraryNumber(),"Book", book.getName(), book.getAuthor(), book.getYear());
            }
        }
    }
}
