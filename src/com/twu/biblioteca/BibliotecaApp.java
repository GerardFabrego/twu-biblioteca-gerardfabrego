package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    static BooksDataBase booksDataBase;
    static MoviesDataBase moviesDataBase;
    static UsersDataBase usersDatabase;

    static List<String> options = new ArrayList<>(Arrays.asList("List of books", "List of movies", "See checked out items", "Log in", "Exit"));

    private static boolean exitApplication = false;
    private static boolean isLoggedIn = false;
    private static User userLoggedIn = null;


    public static void main(String[] args) {
        setUp();
        printWelcomeMessage();
        while (!getExitApplication()) {
            String desiredOption = selectDesiredOption();
            doDesiredOption(desiredOption);
        }
    }


    //Getters and setters
    public static boolean getExitApplication() {
        return exitApplication;
    }

    public static boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public static User getUserLoggedIn() {
        return userLoggedIn;
    }

    public static void setExitApplication(boolean exitApplication) {
        BibliotecaApp.exitApplication = exitApplication;
    }

    public static void setIsLoggedIn(boolean isLoggedIn) {
        BibliotecaApp.isLoggedIn = isLoggedIn;
    }

    public static void setUserLoggedIn(User userLoggedIn) {
        BibliotecaApp.userLoggedIn = userLoggedIn;
    }



    private static void setUp() {
        Book book1 = new Book("To kill a mockingbird", "Harper Lee", "1960");
        Book book2 = new Book("The alchemist", "Paula Coelho", "1980");
        Book book3 = new Book("Ender's game", "Orson Scott", "1985");
        booksDataBase = new BooksDataBase(book1, book2, book3);

        Movie movie1 = new Movie("Inception", "2010", "Christopher Nolan", "9.2/10");
        Movie movie2 = new Movie("Gran torino", "2008", "Clint Eastwood", "9.4/10");
        Movie movie3 = new Movie("Akira", "1988", "Katsuhiro Otomo", "9.2/10");
        moviesDataBase = new MoviesDataBase(movie1, movie2, movie3);

        User user1 = new User("Gerard", "123-4567", "hellohello", "gerard@mail.com", "(+34) 699 123 444");
        User user2 = new User("Mariano", "234-5678", "byebye", "mariano@mail.com", "(+34) 628 555 948");
        usersDatabase = new UsersDataBase(user1, user2);
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
                    booksDataBase.printListOfItems();
                    break;
                case "Checkout a book":
                    System.out.print("What book do you want to checkout? ");
                    String bookToCheckout = input.nextLine();
                    booksDataBase.lookForAndCheckOutItem(bookToCheckout);
                    break;
                case "Return a book":
                    System.out.print("What book do you want to return? ");
                    String bookToReturn = input.nextLine();
                    booksDataBase.checksIfItemIsFromOurCollectionAndReturnIt(bookToReturn);
                    break;
                case "List of movies":
                    moviesDataBase.printListOfMovies();
                    break;
                case "Checkout a movie":
                    System.out.print("What movie do you want to checkout? ");
                    String movieToCheckout = input.nextLine();
                    moviesDataBase.lookForAndCheckOutItem(movieToCheckout);
                    break;
                case "Return a movie":
                    System.out.print("What movie do you want to return? ");
                    String movieToReturn = input.nextLine();
                    moviesDataBase.checksIfItemIsFromOurCollectionAndReturnIt(movieToReturn);
                    break;
                case "See checked out items":
                    printCheckedOutItems();
                    break;
                case "Personal info":
                    printUserPersonalInfo();
                    break;
                case "Log in":
                    System.out.print("Please introduce your Library Number: ");
                    String libraryNumber = input.nextLine();
                    System.out.print("Please introduce your password: ");
                    String password = input.nextLine();
                    tryToLogIn(libraryNumber, password);
                    break;
                case "Log out":
                    tryToLogOut();
                    break;
                case "Exit":
                    System.out.println("Bye!");
                    setExitApplication(true);
                    break;
            }
        } else {
            System.out.println("Please select a valid option!");
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
        setIsLoggedIn(true);
        setUserLoggedIn(user);
        modifyOptions();
    }

    public static void tryToLogOut() {
       if (getIsLoggedIn()) {
           logOut();
           System.out.println("You successfully logged out.");
       }
    }

    public static void logOut() {
        setIsLoggedIn(false);
        setUserLoggedIn(null);
        modifyOptions();
    }

    private static void modifyOptions() {
        if (getIsLoggedIn()) {
            options.remove("Log in");
            options.remove("See checked out items");
            options.add(1, "Checkout a book");
            options.add(2, "Return a book");
            options.add(4, "Checkout a movie");
            options.add(5, "Return a movie");
            options.add(6, "Personal info");
            options.add(7, "Log out");
        } else {
            options.remove("Checkout a book");
            options.remove("Return a book");
            options.remove("Checkout a movie");
            options.remove("Return a movie");
            options.remove("Personal info");
            options.remove("Log out");
            options.add(2, "See checked out items");
            options.add(3, "Log in");
        }
    }

    public static void printCheckedOutItems() {
        System.out.printf("\n%-25s %-25s %-25s %-25s %-4s\n", "User","Type", "Name", "Author/Director", "Year");
        booksDataBase.printCheckedOutItems();
        moviesDataBase.printCheckedOutItems();
    }

    public static void printUserPersonalInfo() {
        if (getUserLoggedIn() != null) {
            System.out.println("\nName: " + getUserLoggedIn().getUserName() +
                    "\nLibrary number: " + getUserLoggedIn().getLibraryNumber() +
                    "\nPhone number: " + getUserLoggedIn().getPhoneNumber() +
                    "\nEmail: " + getUserLoggedIn().getEmail());
        }
    }
}
