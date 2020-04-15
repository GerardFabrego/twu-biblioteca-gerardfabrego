package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.*;

public class BooksDataBaseTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    BooksDataBase fakeBooksDataBase;

    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
        fakeBooksDataBase = new BooksDataBase(new Book("1984", "George Orwell", "1949")
                , new Book("Ulysses", "James Joyce", "1920"));
    }
    @After
    public void restoreStream() {
        System.setOut(originalOut);
    }

    @Test
    public void testPrintListOfBooks() {
        fakeBooksDataBase.printListOfBooks();
        assertEquals("Name                      Author                    Year\n" +
                "1984                      George Orwell             1949\n" +
                "Ulysses                   James Joyce               1920\n", outContent.toString());
    }

    @Test
    public void testIfBookIsCheckedOutCorrectly() {
        BooksDataBase expectedBooksDatabase = new BooksDataBase(new Book("Ulysses", "James Joyce", "1920"));
        fakeBooksDataBase.lookForAndCheckOutBook("Ulysses");
        assertEquals(expectedBooksDatabase.listOfBooks.size(), fakeBooksDataBase.listOfBooks.size());
    }

    @Test
    public void testIfDatabaseIsNotModifiedWhenCheckOutWrongBook() {
        BooksDataBase expectedBooksDatabase = new BooksDataBase(new Book("1984", "George Orwell", "1949")
                , new Book("Ulysses", "James Joyce", "1920"));
        fakeBooksDataBase.lookForAndCheckOutBook("Hello");
        assertEquals(expectedBooksDatabase.listOfBooks.size(), fakeBooksDataBase.listOfBooks.size());
    }

    @Test
    public void testMessageWhenSuccesfullyCheckedOutABook(){
        fakeBooksDataBase.lookForAndCheckOutBook("Ulysses");
        assertEquals("You have checked out \'Ulysses\'.\n", outContent.toString());
    }

    @Test
    public void testMessageWhenCouldntCheckOutABook(){
        fakeBooksDataBase.lookForAndCheckOutBook("Wrong book");
        assertEquals("We don't have \'Wrong book\' on our book database.\n", outContent.toString());
    }
}
