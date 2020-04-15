package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.*;

public class BibliotecaAppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
        BibliotecaApp.booksDataBase =  new BooksDataBase(new Book("1984", "George Orwell", "1949"), new Book("Ulysses", "James Joyce", "1920"));
    }
    @After
    public void restoreStream() {
        System.setOut(originalOut);
    }


    @Test
    public void testPrintWelcomeMessage() {
        BibliotecaApp.printWelcomeMessage();
        assertEquals("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n", outContent.toString());
    }

   @Test
    public void testPrintOptions() {
        BibliotecaApp.printOptions();
        assertEquals("Options:   List of books   |   Checkout a book   |   Return a book   |   Exit\n", outContent.toString());
    }

    @Test
    public void testIfPrintsTheCorrectMenuGivenCorrectOption() {
        String option = "List of books";
        BibliotecaApp.doDesiredOption(option);
        assertEquals("Name                      Author                    Year\n" +
                "1984                      George Orwell             1949\n" +
                "Ulysses                   James Joyce               1920\n", outContent.toString());
    }

    @Test
    public void testIfPrintsTheCorrectMenuGivenIncorrectOption() {
        String option = "Hola";
        BibliotecaApp.doDesiredOption(option);
        assertEquals("Please select a valid option!\n", outContent.toString());
    }



    @Test
    public void testIfProgramQuitsTheAppWhenUserSelectExit() {
        String option = "Exit";
        BibliotecaApp.doDesiredOption(option);
        assertEquals("Bye!", outContent.toString());
    }
}
