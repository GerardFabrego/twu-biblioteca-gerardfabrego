package com.twu.biblioteca;

public class BibliotecaApp {
    static String[] booksList = new String[]{"Books", "To kill a mockingbird", "The wise man's fear", "1984", "Ulysses"};

    public static void main(String[] args) {
        printWelcomeMessage();
        printListOfBooks();
    }


    public static void printWelcomeMessage(){
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public static void printListOfBooks() {
        for(String book: booksList){
            System.out.println(book);
        }
    }
}
