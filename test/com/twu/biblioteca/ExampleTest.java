package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.*;

public class ExampleTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStream() {
        System.setOut(originalOut);
    }

    @Test
    public void testPrintWelcomeMessage() {
        //BibliotecaApp.main(new String[]{});
        BibliotecaApp.printWelcomeMessage();
        assertEquals("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n", outContent.toString());
    }

    @Test
    public void testPrintListOfBooks() {
        BibliotecaApp.printListOfBooks();
        assertEquals("Books\nTo kill a mockingbird\nThe wise man's fear\n1984\nUlysses\n", outContent.toString());

    }
}
