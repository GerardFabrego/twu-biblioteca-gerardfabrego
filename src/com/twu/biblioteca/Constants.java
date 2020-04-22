package com.twu.biblioteca;

public class Constants {
    final static String lineBreak = "\n";
    final static String emptyString = "";
    final static String yes = "Yes";
    final static String no = "No";

    final static String welcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";

    //Options
    final static String options = "Options:   ";
    final static String optionsSeparator = "   |   ";
    final static String listOfBooks = "List of books";
    final static String listOfMovies = "List of movies";
    final static String seeCheckedOutItems = "See checked out items";
    final static String logIn = "Log in";
    final static String exit = "Exit";
    final static String checkoutABook = "Checkout a book";
    final static String returnABook = "Return a book";
    final static String checkoutAMovie = "Checkout a movie";
    final static String returnAmMovie = "Return a movie";
    final static String personalInfo = "Personal info";
    final static String logOut = "Log out";

    //message for/when input
    final static String selectOption = "Select option: ";
    final static String whatBookToCheckOut = "What book do you want to checkout? ";
    final static String whatBookToReturn = "What book do you want to return? ";
    final static String whatMovieToCheckOut = "What movie do you want to checkout? ";
    final static String whatMovieToReturn = "What movie do you want to return? ";
    final static String askForLibraryNumber = "Please introduce your Library Number: ";
    final static String askForPassword = "Please introduce your password: ";
    final static String sayBye = "Bye!\n";
    final static String selectValidOption = "Please select a valid option!\n";
    final static String itemCurrentlyCheckedOut1 = "'";
    final static String itemCurrentlyCheckedOut2 = "' is currently checked out";
    final static String checkOutItemNotInDataBase1 = "We don't have '";
    final static String checkOutItemNotInDataBase2 = "' on our database.";
    final static String itemCorrectlyCheckedOut1 = "You have checked out '";
    final static String itemCorrectlyCheckedOut2 =  "'.";
    final static String returnItemNotInDataBase1 = "The item '";
    final static String returnItemNotInDataBase2 = "' doesn't belong to our collection";
    final static String itemReturnedCorrectly1 = "You returned the '";
    final static String itemReturnedCorrectly2 = "' successfully.";
    final static String returnItemNotCheckedOutByCurrentUser1 = "You hadn't previously checked out the '";
    final static String returnItemNotCheckedOutBySCurrentUser2 = "'.";

    //message log in and log out
    final static String successfulLogIn = "You successfully logged in.\n";
    final static String invalidPassword = "The password introduced isn't correct.\n";
    final static String invalidUser = "The user introduced doesn't exist.\n";
    final static String successLogOut = "You successfully logged out.\n";

    //message personal info
    final static String nameInfo = "Name: ";
    final static String libraryNumberInfo = "Library number: ";
    final static String phoneNumberInfo = "Phone number: ";
    final static String emailInfo = "Email: ";

    //print items
    final static String checkedOutItemsFormat = "%-25s %-25s %-25s %-25s %-4s\n";
    final static String user = "User";
    final static String type = "Type";
    final static String name = "Name";
    final static String creator = "Author/Director";
    final static String year = "Year";
    final static String listOfBooksFormat = "%-25s %-25s %-25s %-13s\n";
    final static String listOfMoviesFormat = "%-25s %-25s %-25s %-25s %-13s\n";
    final static String author = "Author";
    final static String director = "Director";
    final static String rating = "Rating";
    final static String isAvailable = "Is available";
    final static String book = "Book";
    final static String movie = "Movie";


    //tests
    final static String optionsWithoutLogIn = "\nOptions:   List of books   |   List of movies   |   See checked out items   |   Log in   |   Exit\n";
    final static String optionsWhenLoggedIn = "\nOptions:   List of books   |   Checkout a book   |   Return a book   |   " +
            "List of movies   |   Checkout a movie   |   Return a movie   |   Personal info   |   Log out   |   Exit\n";
    final static String invalidOption = "invalidOption";
    final static String correctUser = "correctUser";
    final static String incorrectUser = "incorrectUser";
    final static String correctPass = "correctPass";
    final static String incorrectPass = "incorrectPass";

    final static String checkedOutItems = "\nUser                      Type                      Name                      Author/Director           Year\n";
    final static String printPersonalInfo = "\nName: \nLibrary number: \nPhone number: \nEmail: \n";
}
