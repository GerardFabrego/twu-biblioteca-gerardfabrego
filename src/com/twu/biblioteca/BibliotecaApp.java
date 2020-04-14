package com.twu.biblioteca;

public class BibliotecaApp {


    public static void main(String[] args) {
        printWelcomeMessage();
        BooksDataBase booksDataBase = new BooksDataBase();
        booksDataBase.printListOfBooks();
    }


    public static void printWelcomeMessage(){
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }




}
