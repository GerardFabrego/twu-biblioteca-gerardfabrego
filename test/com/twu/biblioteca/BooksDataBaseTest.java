package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class BooksDataBaseTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    BooksDataBase fakeBooksDataBase = new BooksDataBase();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }


    @Test
    public void testPrintListOfBooks() {
        fakeBooksDataBase = new BooksDataBase(new Book("1984", "George Orwell", "1949")
                , new Book("Ulysses", "James Joyce", "1920"));
        fakeBooksDataBase.printListOfBooks();
        assertEquals("\nName                      Author                    Year\n" +
                "1984                      George Orwell             1949\n" +
                "Ulysses                   James Joyce               1920\n", outContent.toString());
    }

    @Test
    public void testIfBookIsCheckedOutCorrectly() {
        String bookName = "TestBook";
        fakeBooksDataBase.listOfBooks.add(new Book(bookName, "Gerard", "2000", false));

        fakeBooksDataBase.lookForAndCheckOutBook(bookName);
        assertTrue(fakeBooksDataBase.getBookByName(bookName).getIsCheckedOut());
        assertEquals("You have checked out '" + bookName + "'.\n", outContent.toString());
    }

    @Test
    public void testTryingToCheckOutBookAlreadyCheckedOut() {
        String bookName = "TestBook";
        fakeBooksDataBase.listOfBooks.add(new Book(bookName, "Gerard", "2000", true));

        fakeBooksDataBase.lookForAndCheckOutBook(bookName);
        assertTrue(fakeBooksDataBase.getBookByName(bookName).getIsCheckedOut());
        assertEquals("'" + bookName + "' is currently checked out\n", outContent.toString());
    }

    @Test
    public void testTryingToCheckOutNotExistingBook() {
        String bookName = "TestBook";

        fakeBooksDataBase.lookForAndCheckOutBook(bookName);
        assertEquals("We don't have '" + bookName + "' on our book database.\n", outContent.toString());
    }


    @Test
    public void testCheckedOutBookIsReturned() {
        String bookName = "TestBook";
        fakeBooksDataBase.listOfBooks.add(new Book(bookName, "Gerard", "2000", true));

        fakeBooksDataBase.checksIfBookIsFromOurCollectionAndReturnIt(bookName);
        assertFalse(fakeBooksDataBase.getBookByName(bookName).getIsCheckedOut());
        assertEquals("You returned the book '" + bookName + "' successfully.\n", outContent.toString());
    }

    @Test
    public void testTryToReturnNotCheckedOutBook() {
        String bookName = "TestBook";
        fakeBooksDataBase.listOfBooks.add(new Book(bookName, "Gerard", "2000", false));

        fakeBooksDataBase.checksIfBookIsFromOurCollectionAndReturnIt(bookName);
        assertFalse(fakeBooksDataBase.getBookByName(bookName).getIsCheckedOut());
        assertEquals("'" + bookName + "' was not checked out.\n", outContent.toString());
    }

    @Test
    public void testTryToReturnWrongBook() {
        String bookName = "TestBook";

        fakeBooksDataBase.checksIfBookIsFromOurCollectionAndReturnIt(bookName);
        assertEquals("The book '" + bookName + "' doesn't belong to our collection\n", outContent.toString());
    }
}
