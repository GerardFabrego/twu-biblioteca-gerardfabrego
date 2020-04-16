package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    static BooksDataBase booksDataBase = new BooksDataBase(new Book("To kill a mockingbird", "Harper Lee", "1960"),
            new Book("The alchemist", "Paula Coelho", "1980"),
            new Book("Ender's game", "Orson Scott", "1985"));
    static MoviesDataBase moviesDataBase = new MoviesDataBase(new Movie("Inception", "2010", "Christopher Nolan", "9.2/10"),
            new Movie("Gran torino", "2008", "Clint Eastwood", "9.4/10"),
            new Movie("Akira", "1988", "Katsuhiro Otomo", "9.2/10"));

    static List<String> options = new ArrayList<>(Arrays.asList("List of books", "List of movies", "Checkout a book", "Return a book", "Exit"));

    static boolean exitApplication = false;

    public static void main(String[] args) {

        printWelcomeMessage();
        while (!exitApplication) {
            String desiredOption = selectDesiredOption();
            doDesiredOption(desiredOption);
        }
    }


    public static void printWelcomeMessage(){
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public static void printOptions() {
        System.out.print("\nOptions:   ");
        for (String option: options) {
            System.out.print(option);
            if (options.indexOf(option) < options.size()-1){
                System.out.print("   |   ");
            }
            else {
                System.out.println();
            }
        }
    }

    public static String selectDesiredOption() {
        Scanner input = new Scanner(System.in);
        printOptions();
        System.out.print("Select option: ");
        return input.nextLine();
    }

    public static void doDesiredOption(String desiredMenu) {
        Scanner input = new Scanner(System.in);
        switch(desiredMenu) {
            case "List of books":
                booksDataBase.printListOfBooks();
                break;
            case "List of movies":
                moviesDataBase.printListOfMovies();
                break;
            case "Checkout a book":
                System.out.println("What book do you want to checkout?");
                String bookToCheckout = input.nextLine();
                booksDataBase.lookForAndCheckOutBook(bookToCheckout);
                break;
            case "Return a book":
                System.out.println("What book do you want to return?");
                String bookToReturn = input.nextLine();
                booksDataBase.checksIfBookIsFromOurCollectionAndReturnIt(bookToReturn);
                break;
            case "Exit":
                System.out.println("Bye!");
                exitApplication = true;
                break;
            default:
                System.out.println("Please select a valid option!");
                break;
        }
    }

}
