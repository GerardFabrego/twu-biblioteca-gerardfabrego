package com.twu.biblioteca;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BibliotecaAppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    static User user = mock(User.class);
    static UsersRepository usersRepository = mock(UsersRepository.class);
    static BooksRepository booksRepository = mock(BooksRepository.class);
    static MoviesRepository moviesRepository = mock(MoviesRepository.class);

    @BeforeClass
    public static void setUp() {
        BibliotecaApp.usersRepository = usersRepository;
        BibliotecaApp.booksRepository = booksRepository;
        BibliotecaApp.moviesRepository = moviesRepository;

        when(usersRepository.getUserByLibraryNumber("correctUser")).thenReturn(user);

        when(user.getPassword()).thenReturn("correctPass");
        when(user.getUserName()).thenReturn("username");
        when(user.getPhoneNumber()).thenReturn("phoneNumber");
        when(user.getEmail()).thenReturn("email");
        when(user.getLibraryNumber()).thenReturn("libraryNumber");
    }

    @Before
    public void reset() {
        if(BibliotecaApp.getIsLoggedIn()){BibliotecaApp.logOut();}
        System.setOut(new PrintStream(outContent));
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
        BibliotecaApp.logIn(user);
        BibliotecaApp.printOptions();

        String expectedString = "\nOptions:   List of books   |   Checkout a book   |   Return a book   |   " +
                "List of movies   |   Checkout a movie   |   Return a movie   |   Personal info   |   Log out   |   Exit\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testPrintOptionsWhenLogOut() {
        BibliotecaApp.logIn(user);
        BibliotecaApp.logOut();
        BibliotecaApp.printOptions();

        String expectedString = "\nOptions:   List of books   |   List of movies   |   See checked out items   |   Log in   |   Exit\n";
        assertEquals(expectedString, outContent.toString());
    }


    @Test
    public void testIfPrintsInvalidMenuGivenIncorrectOption() {
        BibliotecaApp.doDesiredOption("invalidOption");

        String expectedString = "Please select a valid option!\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testIfProgramQuitsTheAppWhenUserSelectExit() {
        BibliotecaApp.doDesiredOption("Exit");

        String expectedString = "Bye!\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testCorrectUserCorrectPasswordLogIn() {

        BibliotecaApp.tryToLogIn("correctUser", "correctPass");

        assertEquals("You successfully logged in.\n", outContent.toString());
        assertTrue(BibliotecaApp.getIsLoggedIn());
        assertEquals(user, BibliotecaApp.getUserLoggedIn());
    }

    @Test
    public void testIncorrectUserLogIn() {
        BibliotecaApp.tryToLogIn("incorrectUser", "incorrectPass");

        String expectedString = "The user introduced doesn't exist.\n";
        assertEquals(expectedString, outContent.toString());
        assertFalse(BibliotecaApp.getIsLoggedIn());
    }

    @Test
    public void testCorrectUserIncorrectPasswordLogIn() {
        BibliotecaApp.tryToLogIn("correctUser", "incorrectPassword");

        String expectedString = "The password introduced isn't correct.\n";
        assertEquals(expectedString, outContent.toString());
        assertFalse(BibliotecaApp.getIsLoggedIn());
    }

    @Test
    public void testLogOut() {
        BibliotecaApp.setIsLoggedIn(true);
        BibliotecaApp.setUserLoggedIn(user);

        BibliotecaApp.tryToLogOut();

        String expectedString = "You successfully logged out.\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testPrintCheckedOutItems() {
        doAnswer(param -> {System.out.print("checkedOutBooks\n"); return null;}).when(booksRepository).printCheckedOutItems();
        doAnswer(param -> {System.out.print("checkedOutMovies\n"); return null;}).when(moviesRepository).printCheckedOutItems();

        BibliotecaApp.printCheckedOutItems();

        String expectedString = "\nUser                      Type                      Name                      Author/Director           Year\n" + "checkedOutBooks\n" + "checkedOutMovies\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testPrintUserPersonalInfo() {
        BibliotecaApp.logIn(user);
        BibliotecaApp.printUserPersonalInfo();

        String expectedString = "\nName: username\nLibrary number: libraryNumber\nPhone number: phoneNumber\nEmail: email\n";
        assertEquals(expectedString, outContent.toString());
    }
}
