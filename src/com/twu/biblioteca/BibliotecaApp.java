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

    static UsersDataBase usersDatabase = new UsersDataBase(new User("Gerard", "123-4567", "hellohello"));


    static List<String> options = new ArrayList<>(Arrays.asList("List of books", "List of movies", "Log in", "Exit"));

    static boolean exitApplication = false;
    static boolean isLoggedIn = false;
    static User userLoggedIn = null;

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
        if (options.contains(desiredMenu)){
            switch(desiredMenu) {
                case "List of books":
                    booksDataBase.printListOfBooks();
                    break;
                case "Checkout a book":
                    System.out.print("What book do you want to checkout? ");
                    String bookToCheckout = input.nextLine();
                    booksDataBase.lookForAndCheckOutBook(bookToCheckout);
                    break;
                case "Return a book":
                    System.out.print("What book do you want to return? ");
                    String bookToReturn = input.nextLine();
                    booksDataBase.checksIfBookIsFromOurCollectionAndReturnIt(bookToReturn);
                    break;
                case "List of movies":
                    moviesDataBase.printListOfMovies();
                    break;
                case "Checkout a movie":
                    System.out.print("What movie do you want to checkout? ");
                    String movieToCheckout = input.nextLine();
                    moviesDataBase.lookForAndCheckOutMovie(movieToCheckout);
                    break;
                case "Return a movie":
                    System.out.print("What movie do you want to return? ");
                    String movieToReturn = input.nextLine();
                    moviesDataBase.checksIfMovieIsFromOurCollectionAndReturnIt(movieToReturn);
                    break;
                case "Log in":
                    System.out.print("Please introduce your Library Number: ");
                    String libraryNumber = input.nextLine();
                    System.out.print("Please introduce your password: ");
                    String password = input.nextLine();
                    tryToLogIn(libraryNumber, password);
                    break;
                case "Log out":
                    logOut();
                    break;
                case "Exit":
                    System.out.println("Bye!");
                    exitApplication = true;
                    break;
                default:
                    System.out.println("Please select a valid option!");
                    break;
            }
        } else {
            System.out.println("Please select a valid option!");
        }
    }

    public static void tryToLogIn(String libraryNumber, String password) {
        User currentUser = usersDatabase.getUserByLibraryNumber(libraryNumber);
        if (currentUser != null) {
            if (currentUser.getPassword().equals(password)){
                logIn(currentUser);
                System.out.println("You successfully logged in.");
            } else {
                System.out.println("The password introduced isn't correct.");
            }
        } else {
            System.out.println("The user introduced doesn't exist.");
        }
    }

    public static void logIn(User user){
        isLoggedIn = true;
        userLoggedIn = user;
        modifyOptions();
    }

    public static void logOut() {
        isLoggedIn = false;
        userLoggedIn = null;
        modifyOptions();
        System.out.println("You successfully logged out.");
    }

    private static void modifyOptions() {
        if (isLoggedIn) {
            options.remove("Log in");
            options.add(1, "Checkout a book");
            options.add(2, "Return a book");
            options.add(4, "Checkout a movie");
            options.add(5, "Return a movie");
            options.add(6, "Log out");
        } else {
            options.remove("Checkout a book");
            options.remove("Return a book");
            options.remove("Checkout a movie");
            options.remove("Return a movie");
            options.remove("Log out");
            options.add(2, "Log in");
        }
    }


}
