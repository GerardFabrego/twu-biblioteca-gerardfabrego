package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class BooksDataBaseTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    BooksDataBase fakeBooksDataBase;
    User testUser1 = new User("testUser1", "111-1111", "pass1", "user1@test.com", "(+11) 111 111 111" );
    User testUser2 = new User("testUser2", "222-2222", "pass2", "user2@test.com", "(+22) 222 222 222");
    Book testBook1;
    Book testBook2;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        fakeBooksDataBase = new BooksDataBase();
        testBook1 = new Book("1984", "George Orwell", "1949");
        testBook2 = new Book("Ulysses", "James Joyce", "1920");
    }


    @Test
    public void testPrintListOfBooks() {
        testBook2.setIsCheckedOut(true);
        fakeBooksDataBase = new BooksDataBase(testBook1, testBook2);

        fakeBooksDataBase.printListOfBooks();

        String expectedString = "\nName                      Author                    Year                      Is available \n" +
                "1984                      George Orwell             1949                      Yes          \n" +
                "Ulysses                   James Joyce               1920                      No           \n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testBookIsCheckedOutCorrectly() {
        fakeBooksDataBase.listOfBooks.add(testBook1);
        BibliotecaApp.setIsLoggedIn(true);
        BibliotecaApp.setUserLoggedIn(testUser1);

        fakeBooksDataBase.lookForAndCheckOutBook(testBook1.getName());

        assertTrue(fakeBooksDataBase.getBookByName(testBook1.getName()).getIsCheckedOut());
        assertEquals(testUser1, fakeBooksDataBase.getBookByName(testBook1.getName()).getUserThatHasCheckedItOut());
        String expectedString = "You have checked out '" + testBook1.getName() + "'.\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testTryingToCheckOutBookAlreadyCheckedOut() {
        testBook1.setIsCheckedOut(true);
        fakeBooksDataBase.listOfBooks.add(testBook1);

        fakeBooksDataBase.lookForAndCheckOutBook(testBook1.getName());

        assertTrue(fakeBooksDataBase.getBookByName(testBook1.getName()).getIsCheckedOut());
        String expectedString = "'" + testBook1.getName() + "' is currently checked out\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testTryingToCheckOutNotExistingBook() {
        fakeBooksDataBase.lookForAndCheckOutBook(testBook1.getName());

        String expectedString = "We don't have '" + testBook1.getName() + "' on our book database.\n";
        assertEquals(expectedString, outContent.toString());
    }


    @Test
    public void testCheckedOutBookIsReturnedByCorrectUser() {
        testBook1.setIsCheckedOut(true);
        testBook1.setUserThatHasCheckedItOut(testUser1);
        fakeBooksDataBase.listOfBooks.add(testBook1);
        BibliotecaApp.setIsLoggedIn(true);
        BibliotecaApp.setUserLoggedIn(testUser1);

        fakeBooksDataBase.checksIfBookIsFromOurCollectionAndReturnIt(testBook1.getName());

        assertFalse(fakeBooksDataBase.getBookByName(testBook1.getName()).getIsCheckedOut());
        assertNull(fakeBooksDataBase.getBookByName(testBook1.getName()).getUserThatHasCheckedItOut());
        String expectedString = "You returned the book '" + testBook1.getName() + "' successfully.\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testCheckedOutBookIsReturnedByWrongUser() {
        testBook1.setIsCheckedOut(true);
        testBook1.setUserThatHasCheckedItOut(testUser1);
        fakeBooksDataBase.listOfBooks.add(testBook1);
        BibliotecaApp.setIsLoggedIn(true);
        BibliotecaApp.setUserLoggedIn(testUser2);

        fakeBooksDataBase.checksIfBookIsFromOurCollectionAndReturnIt(testBook1.getName());

        assertTrue(fakeBooksDataBase.getBookByName(testBook1.getName()).getIsCheckedOut());
        assertEquals(testUser1, fakeBooksDataBase.getBookByName(testBook1.getName()).getUserThatHasCheckedItOut());
        String expectedString = "You hadn't previously checked out the book '" + testBook1.getName() + "'.\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testTryToReturnNotCheckedOutBook() {
        fakeBooksDataBase.listOfBooks.add(testBook1);

        fakeBooksDataBase.checksIfBookIsFromOurCollectionAndReturnIt(testBook1.getName());

        assertFalse(fakeBooksDataBase.getBookByName(testBook1.getName()).getIsCheckedOut());
        String expectedString = "'" + testBook1.getName() + "' was not checked out.\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testTryToReturnWrongBook() {
        fakeBooksDataBase.checksIfBookIsFromOurCollectionAndReturnIt(testBook1.getName());

        String expectedString = "The book '" + testBook1.getName() + "' doesn't belong to our collection\n";
        assertEquals(expectedString, outContent.toString());
    }
}
