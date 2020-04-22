package com.twu.biblioteca;

import com.twu.biblioteca.items.Book;
import com.twu.biblioteca.items.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    static BooksRepository booksRepository;
    static MoviesRepository moviesRepository;
    static UsersRepository usersRepository;

    static List<String> options = new ArrayList<>(Arrays.asList(Constants.listOfBooks, Constants.listOfMovies, Constants.seeCheckedOutItems, Constants.logIn, Constants.exit));

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
        booksRepository = new BooksRepository(book1, book2, book3);

        Movie movie1 = new Movie("Inception", "2010", "Christopher Nolan", "9.2/10");
        Movie movie2 = new Movie("Gran torino", "2008", "Clint Eastwood", "9.4/10");
        Movie movie3 = new Movie("Akira", "1988", "Katsuhiro Otomo", "9.2/10");
        moviesRepository = new MoviesRepository(movie1, movie2, movie3);

        User user1 = new User("Gerard", "123-4567", "hellohello", "gerard@mail.com", "(+34) 699 123 444");
        User user2 = new User("Mariano", "234-5678", "byebye", "mariano@mail.com", "(+34) 628 555 948");
        usersRepository = new UsersRepository(user1, user2);
    }

    public static String selectDesiredOption() {
        Scanner input = new Scanner(System.in);
        printOptions();
        System.out.print(Constants.selectOption);
        return input.nextLine();
    }

    public static void doDesiredOption(String desiredMenu) {
        Scanner input = new Scanner(System.in);
        if (options.contains(desiredMenu)){
            switch(desiredMenu) {
                case Constants.listOfBooks:
                    booksRepository.printListOfBooks();
                    break;
                case Constants.checkoutABook:
                    System.out.print(Constants.whatBookToCheckOut);
                    String bookToCheckout = input.nextLine();
                    booksRepository.lookForAndCheckOutItem(bookToCheckout);
                    break;
                case Constants.returnABook:
                    System.out.print(Constants.whatBookToReturn);
                    String bookToReturn = input.nextLine();
                    booksRepository.checksIfItemIsFromOurCollectionAndReturnIt(bookToReturn);
                    break;
                case Constants.listOfMovies:
                    moviesRepository.printListOfMovies();
                    break;
                case Constants.checkoutAMovie:
                    System.out.print(Constants.whatMovieToCheckOut);
                    String movieToCheckout = input.nextLine();
                    moviesRepository.lookForAndCheckOutItem(movieToCheckout);
                    break;
                case Constants.returnAmMovie:
                    System.out.print(Constants.whatMovieToReturn);
                    String movieToReturn = input.nextLine();
                    moviesRepository.checksIfItemIsFromOurCollectionAndReturnIt(movieToReturn);
                    break;
                case Constants.seeCheckedOutItems:
                    printCheckedOutItems();
                    break;
                case Constants.personalInfo:
                    printUserPersonalInfo();
                    break;
                case Constants.logIn:
                    System.out.print(Constants.askForLibraryNumber);
                    String libraryNumber = input.nextLine();
                    System.out.print(Constants.askForPassword);
                    String password = input.nextLine();
                    tryToLogIn(libraryNumber, password);
                    break;
                case Constants.logOut:
                    tryToLogOut();
                    break;
                case Constants.exit:
                    System.out.print(Constants.sayBye);
                    setExitApplication(true);
                    break;
            }
        } else {
            System.out.print(Constants.selectValidOption);
        }
    }

    public static void printWelcomeMessage(){
        System.out.print(Constants.welcomeMessage);
    }

    public static void printOptions() {
        System.out.print(Constants.lineBreak + Constants.options);
        for (String option: options) {
            System.out.print(option);
            if (options.indexOf(option) < options.size()-1){
                System.out.print(Constants.optionsSeparator);
            }
            else {
                System.out.println();
            }
        }
    }

    public static void tryToLogIn(String libraryNumber, String password) {
        User currentUser = usersRepository.getUserByLibraryNumber(libraryNumber);
        if (currentUser != null) {
            if (currentUser.getPassword().equals(password)){
                logIn(currentUser);
                System.out.print(Constants.successfulLogIn);
            } else {
                System.out.print(Constants.invalidPassword);
            }
        } else {
            System.out.print(Constants.invalidUser);
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
           System.out.print(Constants.successLogOut);
       }
    }

    public static void logOut() {
        setIsLoggedIn(false);
        setUserLoggedIn(null);
        modifyOptions();
    }

    private static void modifyOptions() {
        if (!getIsLoggedIn()) {
            options = new ArrayList<>(Arrays.asList(Constants.listOfBooks, Constants.listOfMovies, Constants.seeCheckedOutItems, Constants.logIn, Constants.exit));
        } else {
            options = new ArrayList<>(Arrays.asList(Constants.listOfBooks, Constants.checkoutABook, Constants.returnABook,
                    Constants.listOfMovies, Constants.checkoutAMovie, Constants.returnAmMovie, Constants.personalInfo, Constants.logOut, Constants.exit));
        }
    }

    public static void printCheckedOutItems() {
        System.out.print(Constants.lineBreak);
        System.out.printf(Constants.checkedOutItemsFormat, Constants.user, Constants.type, Constants.name, Constants.creator, Constants.year);
        booksRepository.printCheckedOutItems();
        moviesRepository.printCheckedOutItems();
    }

    public static void printUserPersonalInfo() {
        if (getUserLoggedIn() != null) {
            System.out.println(Constants.lineBreak + Constants.nameInfo + getUserLoggedIn().getUserName() +
                    Constants.lineBreak + Constants.libraryNumberInfo + getUserLoggedIn().getLibraryNumber() +
                    Constants.lineBreak + Constants.phoneNumberInfo + getUserLoggedIn().getPhoneNumber() +
                    Constants.lineBreak + Constants.emailInfo + getUserLoggedIn().getEmail());
        }
    }
}
