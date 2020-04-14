package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.*;

public class MainAppTest {
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
        assertEquals("List of books\n", outContent.toString());
    }

    @Test
    public void testIfPrintsTheCorrectMenuGivenAnOption() {
        String option = "List of books";
        BibliotecaApp.goToDesiredMenu(option);
        assertEquals("Name                      Author                    Year\n" +
                "1984                      George Orwell             1949\n" +
                "Ulysses                   James Joyce               1920\n", outContent.toString());
    }

    @Test
    public void testCheckIfInputIsValidOption() {
        assertEquals(false, BibliotecaApp.isAValidOption("hola"));
        assertEquals(true, BibliotecaApp.isAValidOption("List of books"));
    }

    @Test
    public void testGoToDesiredMenu() {
        ByteArrayInputStream input = new ByteArrayInputStream("List of books".getBytes());
        System.setIn(input);
        BibliotecaApp.selectOptionAndGoToDesiredMenu();
        assertEquals("List of books\nSelect option: \nName                      Author                    Year\n" +
                "1984                      George Orwell             1949\n" +
                "Ulysses                   James Joyce               1920\n", outContent.toString());
    }
}
