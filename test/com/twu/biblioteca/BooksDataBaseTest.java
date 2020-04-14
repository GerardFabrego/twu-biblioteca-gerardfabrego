package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.*;

public class BooksDataBaseTest {
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
    public void testPrintListOfBooks() {
        BooksDataBase fakeBooksDataBase = new BooksDataBase(new Book("1984"), new Book("Ulysses"));
        fakeBooksDataBase.printListOfBooks();
        assertEquals("1984\nUlysses\n", outContent.toString());
    }
}
