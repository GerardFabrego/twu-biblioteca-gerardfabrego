package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class BibliotecaAppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
        BibliotecaApp.booksDataBase =  new BooksDataBase(new Book("1984", "George Orwell", "1949"), new Book("Ulysses", "James Joyce", "1920"));
    }

    @Test
    public void testPrintWelcomeMessage() {
        BibliotecaApp.printWelcomeMessage();
        assertEquals("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n", outContent.toString());
    }

    @Test
    public void testPrintOptions() {
        BibliotecaApp.printOptions();
        assertEquals("\nOptions:   List of books   |   List of movies   |   Checkout a book   |   Return a book   |   Exit\n", outContent.toString());
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
    public void testIfProgramCheckOutABookCorrectly() {
        String option = "Exit";
        BibliotecaApp.doDesiredOption(option);
        assertEquals("Bye!\n", outContent.toString());
    }


    @Test
    public void testIfProgramQuitsTheAppWhenUserSelectExit() {
        String option = "Exit";
        BibliotecaApp.doDesiredOption(option);
        assertEquals("Bye!\n", outContent.toString());
    }


}
