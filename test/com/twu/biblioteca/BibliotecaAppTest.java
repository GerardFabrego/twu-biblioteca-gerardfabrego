package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class BibliotecaAppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        BibliotecaApp.booksDataBase =  new BooksDataBase(new Book("1984", "George Orwell", "1949"), new Book("Ulysses", "James Joyce", "1920"));
        BibliotecaApp.usersDatabase = new UsersDataBase(new User("Gerard", "123-4567", "hellohello"));
        BibliotecaApp.isLoggedIn = false;
        BibliotecaApp.userLoggedIn = null;
    }

    @Test
    public void testPrintWelcomeMessage() {
        BibliotecaApp.printWelcomeMessage();
        assertEquals("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n", outContent.toString());
    }

    @Test
    public void testPrintOptions() {
        BibliotecaApp.printOptions();
        assertEquals("\nOptions:   List of books   |   Checkout a book   |   Return a book   |   " +
                "List of movies   |   Checkout a movie   |   Return a movie   |   Exit\n", outContent.toString());
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
        assertTrue(BibliotecaApp.isLoggedIn);
        assertEquals("Gerard", BibliotecaApp.userLoggedIn.userName);
    }

    @Test
    public void testIncorrectUserLogIn() {
        BibliotecaApp.tryToLogIn("incorrect", "hellohello");
        assertEquals("The user introduced doesn't exist.\n", outContent.toString());
        assertFalse(BibliotecaApp.isLoggedIn);
    }

    @Test
    public void testCorrectUserIncorrectPasswordLogIn() {
        BibliotecaApp.tryToLogIn("123-4567", "Incorrect");
        assertEquals("The password introduced isn't correct.\n", outContent.toString());
        assertFalse(BibliotecaApp.isLoggedIn);
    }
}
