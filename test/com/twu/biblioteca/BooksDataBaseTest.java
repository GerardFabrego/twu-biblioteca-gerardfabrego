package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class BooksDataBaseTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    BooksDataBase fakeBooksDataBase;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        fakeBooksDataBase = new BooksDataBase(new Book("1984", "George Orwell", "1949")
                , new Book("Ulysses", "James Joyce", "1920"));
    }


    @Test
    public void testPrintListOfBooks() {
        fakeBooksDataBase.printListOfBooks();
        assertEquals("\nName                      Author                    Year\n" +
                "1984                      George Orwell             1949\n" +
                "Ulysses                   James Joyce               1920\n", outContent.toString());
    }

    @Test
    public void testIfBookIsCheckedOutCorrectly() {
        fakeBooksDataBase.lookForAndCheckOutBook("Ulysses");

        assertEquals(1, fakeBooksDataBase.listOfBooks.size());
        assertEquals(1, fakeBooksDataBase.listOfCheckedOutBooks.size());
        assertEquals("You have checked out 'Ulysses'.\n", outContent.toString());
    }

    @Test
    public void testIfDatabaseIsNotModifiedWhenCheckOutWrongBook() {
        fakeBooksDataBase.lookForAndCheckOutBook("Wrong book");

        assertEquals(2, fakeBooksDataBase.listOfBooks.size());
        assertEquals(0, fakeBooksDataBase.listOfCheckedOutBooks.size());
        assertEquals("We don't have 'Wrong book' on our book database.\n", outContent.toString());
    }


    @Test
    public void testCorrectBookIsReturned() {
        fakeBooksDataBase.listOfCheckedOutBooks.add(new Book("Ender's game", "Orson Scott", "1985"));
        fakeBooksDataBase.returnBook(new Book("Ender's game", "Orson Scott", "1985"));

        assertEquals(3, fakeBooksDataBase.listOfBooks.size());
        assertEquals(0, fakeBooksDataBase.listOfCheckedOutBooks.size());
        assertEquals("You returned the book 'Ender's game' successfully.\n", outContent.toString());
    }

    @Test
    public void testWrongBookIsReturned() {
        fakeBooksDataBase.listOfCheckedOutBooks.add(new Book("Ender's game", "Orson Scott", "1985"));
        fakeBooksDataBase.looksIfBookIsFromOurCollectionAndReturnIt("Wrong book!");

        assertEquals(2, fakeBooksDataBase.listOfBooks.size());
        assertEquals(1, fakeBooksDataBase.listOfCheckedOutBooks.size());
        assertEquals("The book 'Wrong book!' doesn't belong to our collection\n", outContent.toString());
    }
}
