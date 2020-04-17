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
    User testUser1 = new User("testUser1", "111-1111", "pass1", "user1@test.com", "(+11) 111 111 111" );
    User testUser2 = new User("testUser2", "222-2222", "pass2", "user2@test.com", "(+22) 222 222 222");


    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        BibliotecaApp.usersDatabase = new UsersDataBase(testUser1);
        BibliotecaApp.options = new ArrayList<>(Arrays.asList("List of books", "List of movies", "See checked out items", "Log in", "Exit"));
        BibliotecaApp.setIsLoggedIn(false);
        BibliotecaApp.setUserLoggedIn(null);
    }

    @Test
    public void testPrintWelcomeMessage() {
        BibliotecaApp.printWelcomeMessage();

        String expectedString = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testPrintOptionsWithoutLogIn() {
        BibliotecaApp.printOptions();

        String expectedString = "\nOptions:   List of books   |   List of movies   |   See checked out items   |   Log in   |   Exit\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testPrintOptionsWhenLogIn() {
        BibliotecaApp.logIn(testUser1);

        BibliotecaApp.printOptions();

        String expectedString = "\nOptions:   List of books   |   Checkout a book   |   Return a book   |   " +
                "List of movies   |   Checkout a movie   |   Return a movie   |   Personal info   |   Log out   |   Exit\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testPrintOptionsWhenLogOut() {
        BibliotecaApp.logIn(testUser1);
        BibliotecaApp.logOut();

        BibliotecaApp.printOptions();

        String expectedString = "\nOptions:   List of books   |   List of movies   |   See checked out items   |   Log in   |   Exit\n";
        assertEquals(expectedString, outContent.toString());
    }


    @Test
    public void testIfPrintsInvalidMenuGivenIncorrectOption() {
        String option = "Hola";
        BibliotecaApp.doDesiredOption(option);

        String expectedString = "Please select a valid option!\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testIfProgramQuitsTheAppWhenUserSelectExit() {
        String option = "Exit";
        BibliotecaApp.doDesiredOption(option);

        String expectedString = "Bye!\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testCorrectUserCorrectPasswordLogIn() {
        BibliotecaApp.tryToLogIn("111-1111", "pass1");

        assertEquals("You successfully logged in.\n", outContent.toString());
        assertTrue(BibliotecaApp.getIsLoggedIn());
        String expectedString = "testUser1";
        assertEquals(expectedString, BibliotecaApp.getUserLoggedIn().getUserName());
    }

    @Test
    public void testIncorrectUserLogIn() {
        BibliotecaApp.tryToLogIn("222-2222", "pass2");

        String expectedString = "The user introduced doesn't exist.\n";
        assertEquals(expectedString, outContent.toString());
        assertFalse(BibliotecaApp.getIsLoggedIn());
    }

    @Test
    public void testCorrectUserIncorrectPasswordLogIn() {
        BibliotecaApp.tryToLogIn("111-1111", "pass2");

        String expectedString = "The password introduced isn't correct.\n";
        assertEquals(expectedString, outContent.toString());
        assertFalse(BibliotecaApp.getIsLoggedIn());
    }

    @Test
    public void testLogOut() {
        BibliotecaApp.setIsLoggedIn(true);
        BibliotecaApp.setUserLoggedIn(testUser1);

        BibliotecaApp.tryToLogOut();

        String expectedString = "You successfully logged out.\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testPrintCheckedOutItems() {
        //create a checked out book
        Book testBook = new Book("Book 10", "Bruce Lee", "2020");
        testBook.setIsCheckedOut(true);
        testBook.setUserThatHasCheckedItOut(testUser1);
        BibliotecaApp.booksDataBase.listOfBooks.add(testBook);
        //create a checked out movie
        Movie testMovie = new Movie("Movie2010", "2000", "Donald Trump", "3.0/10");
        testMovie.setIsCheckedOut(true);
        testMovie.setUserThatHasCheckedItOut(testUser2);
        BibliotecaApp.moviesDataBase.listOfMovies.add(testMovie);

        BibliotecaApp.printCheckedOutItems();

        String expectedString = "\nUser                      Type                      Name                      Author/Director           Year\n" +
                "111-1111                  Book                      Book 10                   Bruce Lee                 2020\n" +
                "222-2222                  Movie                     Movie2010                 Donald Trump              2000\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testPrintUserPersonalInfo() {
        BibliotecaApp.logIn(testUser1);

        BibliotecaApp.printUserPersonalInfo();

        String expectedString = "\nName: testUser1\nLibrary number: 111-1111\nPhone number: (+11) 111 111 111\nEmail: user1@test.com\n";
        assertEquals(expectedString, outContent.toString());
    }
}
