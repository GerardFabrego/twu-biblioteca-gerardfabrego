package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class BooksDataBaseTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    BooksDataBase fakeBooksDataBase = new BooksDataBase();
    User testUser = new User("Gerard", "123-4567", "hellohello");
    User testUser2 = new User("Mariano", "987-6543", "byebye");


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
    public void testBookIsCheckedOutCorrectly() {
        String bookName = "TestBook";
        Book testBook = new Book(bookName, "Gerard", "2000");
        fakeBooksDataBase.listOfBooks.add(testBook);

        BibliotecaApp.setIsLoggedIn(true);
        BibliotecaApp.setUserLoggedIn(testUser);

        fakeBooksDataBase.lookForAndCheckOutBook(bookName);
        assertTrue(fakeBooksDataBase.getBookByName(bookName).getIsCheckedOut());
        assertEquals(testUser, fakeBooksDataBase.getBookByName(bookName).getUserThatHasCheckedItOut());
        assertEquals("You have checked out '" + bookName + "'.\n", outContent.toString());
    }

    @Test
    public void testTryingToCheckOutBookAlreadyCheckedOut() {
        String bookName = "TestBook";
        Book testBook = new Book(bookName, "Gerard", "2000");
        testBook.setIsCheckedOut(true);
        fakeBooksDataBase.listOfBooks.add(testBook);

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
    public void testCheckedOutBookIsReturnedByCorrectUser() {
        String bookName = "TestBook";
        Book testBook = new Book(bookName, "Gerard", "2000");
        testBook.setIsCheckedOut(true);
        testBook.setUserThatHasCheckedItOut(testUser);
        fakeBooksDataBase.listOfBooks.add(testBook);
        BibliotecaApp.setIsLoggedIn(true);
        BibliotecaApp.setUserLoggedIn(testUser);

        fakeBooksDataBase.checksIfBookIsFromOurCollectionAndReturnIt(bookName);

        assertFalse(fakeBooksDataBase.getBookByName(bookName).getIsCheckedOut());
        assertNull(fakeBooksDataBase.getBookByName(bookName).getUserThatHasCheckedItOut());
        assertEquals("You returned the book '" + bookName + "' successfully.\n", outContent.toString());
    }

    @Test
    public void testCheckedOutBookIsReturnedByWrongUser() {
        String bookName = "TestBook";
        Book testBook = new Book(bookName, "Gerard", "2000");
        testBook.setIsCheckedOut(true);
        testBook.setUserThatHasCheckedItOut(testUser);
        fakeBooksDataBase.listOfBooks.add(testBook);
        BibliotecaApp.setIsLoggedIn(true);
        BibliotecaApp.setUserLoggedIn(testUser2);

        fakeBooksDataBase.checksIfBookIsFromOurCollectionAndReturnIt(bookName);

        assertTrue(fakeBooksDataBase.getBookByName(bookName).getIsCheckedOut());
        assertEquals(testUser, fakeBooksDataBase.getBookByName(bookName).getUserThatHasCheckedItOut());
        assertEquals("You hadn't previously checked out the book '" + bookName + "'.\n", outContent.toString());
    }

    @Test
    public void testTryToReturnNotCheckedOutBook() {
        String bookName = "TestBook";
        Book testBook = new Book(bookName, "Gerard", "2000");
        fakeBooksDataBase.listOfBooks.add(testBook);

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
