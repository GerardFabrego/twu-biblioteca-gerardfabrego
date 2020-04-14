package com.twu.biblioteca;

public class BibliotecaApp {

    static BooksDataBase booksDataBase = new BooksDataBase(new Book("To kill a mockingbird", "Harper Lee", "1960"),
            new Book("The alchemist", "Paula Coelho", "1980"),
            new Book("Ender's game", "Orson Scott", "1985"));

    public static void main(String[] args) {
        printWelcomeMessage();
        System.out.println();
        booksDataBase.printListOfBooks();
    }


    public static void printWelcomeMessage(){
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }




}
