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
        BooksDataBase fakeBooksDataBase = new BooksDataBase(new Book("1984", "George Orwell", "1949")
                , new Book("Ulysses", "James Joyce", "1920"));
        fakeBooksDataBase.printListOfBooks();
        assertEquals("Name                      Author                    Year\n" +
                "1984                      George Orwell             1949\n" +
                "Ulysses                   James Joyce               1920\n", outContent.toString());
    }
}
