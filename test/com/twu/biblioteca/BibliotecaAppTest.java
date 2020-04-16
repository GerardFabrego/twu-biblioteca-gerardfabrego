package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class BibliotecaAppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    User testUser = new User("Gerard", "123-4567", "hellohello");
    User testUser2 = new User("Mariano", "987-6543", "byebye");


    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        BibliotecaApp.usersDatabase = new UsersDataBase(new User("Gerard", "123-4567", "hellohello"));
        BibliotecaApp.options = new ArrayList<>(Arrays.asList("List of books", "List of movies", "See checked out books", "Log in", "Exit"));
        BibliotecaApp.setIsLoggedIn(false);
        BibliotecaApp.setUserLoggedIn(null);
    }

    @Test
    public void testPrintWelcomeMessage() {
        BibliotecaApp.printWelcomeMessage();
        assertEquals("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n", outContent.toString());
    }

    @Test
    public void testPrintOptionsWithoutLogIn() {
        BibliotecaApp.printOptions();
        assertEquals("\nOptions:   List of books   |   List of movies   |   See checked out books   |   Log in   |   Exit\n", outContent.toString());
    }

    @Test
    public void testPrintOptionsWhenLogIn() {
        BibliotecaApp.logIn(new User("Gerard", "123-4567", "hellohello"));
        BibliotecaApp.printOptions();
        assertEquals("\nOptions:   List of books   |   Checkout a book   |   Return a book   |   " +
                "List of movies   |   Checkout a movie   |   Return a movie   |   Log out   |   Exit\n", outContent.toString());
    }

    @Test
    public void testPrintOptionsWhenLogOut() {
        BibliotecaApp.logIn(new User("Gerard", "123-4567", "hellohello"));
        BibliotecaApp.logOut();
        BibliotecaApp.printOptions();
        assertEquals("\nOptions:   List of books   |   List of movies   |   See checked out books   |   Log in   |   Exit\n", outContent.toString());
    }

    @Test
    public void testPrintTheCorrectMenuGivenCorrectOption() {
        String option = "List of books";
        BibliotecaApp.doDesiredOption(option);
        assertEquals("\nName                      Author                    Year\n" +
                "1984                      George Orwell             1949\n" +
                "Ulysses                   James Joyce               1920\n", outContent.toString());
    }

    @Test
    public void testIfPrintsInvalidMenuGivenIncorrectOption() {
        String option = "Hola";
        BibliotecaApp.doDesiredOption(option);
        assertEquals("Please select a valid option!\n", outContent.toString());
    }

    @Test
    public void testIfProgramQuitsTheAppWhenUserSelectExit() {
        String option = "Exit";
        BibliotecaApp.doDesiredOption(option);
        assertEquals("Bye!\n", outContent.toString());
    }

    @Test
    public void testCorrectUserCorrectPasswordLogIn() {
        BibliotecaApp.tryToLogIn("123-4567", "hellohello");
        assertEquals("You successfully logged in.\n", outContent.toString());
        assertTrue(BibliotecaApp.getIsLoggedIn());
        assertEquals("Gerard", BibliotecaApp.getUserLoggedIn().getUserName());
    }

    @Test
    public void testIncorrectUserLogIn() {
        BibliotecaApp.tryToLogIn("incorrect", "hellohello");
        assertEquals("The user introduced doesn't exist.\n", outContent.toString());
        assertFalse(BibliotecaApp.getIsLoggedIn());
    }

    @Test
    public void testCorrectUserIncorrectPasswordLogIn() {
        BibliotecaApp.tryToLogIn("123-4567", "Incorrect");
        assertEquals("The password introduced isn't correct.\n", outContent.toString());
        assertFalse(BibliotecaApp.getIsLoggedIn());
    }

    @Test
    public void testLogOut() {
        BibliotecaApp.setIsLoggedIn(true);
        BibliotecaApp.setUserLoggedIn(testUser);
        BibliotecaApp.tryToLogOut();
        assertEquals("You successfully logged out.\n", outContent.toString());
    }

    @Test
    public void testPrintCheckedOutItems() {
        //create a checked out book
        Book testBook = new Book("Book 10", "Bruce Lee", "2020");
        testBook.setIsCheckedOut(true);
        testBook.setUserThatHasCheckedItOut(testUser);
        BibliotecaApp.booksDataBase.listOfBooks.add(testBook);
        //create a checked out movie
        Movie testMovie = new Movie("Movie2010", "2000", "Donald Trump", "3.0/10");
        testMovie.setIsCheckedOut(true);
        testMovie.setUserThatHasCheckedItOut(testUser2);
        BibliotecaApp.moviesDataBase.listOfMovies.add(testMovie);

        BibliotecaApp.printCheckedOutItems();
        String expectedString = "\nUser                      Type                      Name                      Author/Director           Year\n" +
                "123-4567                  Book                      Book 10                   Bruce Lee                 2020\n" +
                "987-6543                  Movie                     Movie2010                 Donald Trump              2000\n";
        assertEquals(expectedString, outContent.toString());
    }
}
