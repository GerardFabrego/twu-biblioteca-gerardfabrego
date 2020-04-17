package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class MoviesDataBaseTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    MoviesDataBase fakeMoviesDataBase;
    User testUser1 = new User("testUser1", "111-1111", "pass1", "user1@test.com", "(+11) 111 111 111" );
    User testUser2 = new User("testUser2", "222-2222", "pass2", "user2@test.com", "(+22) 222 222 222");
    Movie testMovie1;
    Movie testMovie2;


    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        fakeMoviesDataBase = new MoviesDataBase();
        testMovie1 = new Movie("Fight club", "1999", "David Fincher", "8.8/10");
        testMovie2 = new Movie("Jojo Rabbit", "2019", "Taika Waititi", "9.3/10");
    }


    @Test
    public void testPrintListOMovies() {
        fakeMoviesDataBase = new MoviesDataBase(testMovie1, testMovie2);
        fakeMoviesDataBase.printListOfMovies();

        String expectedString = "\nName                      Year                      Director                  Rating  \n" +
                "Fight club                1999                      David Fincher             8.8/10  \n" +
                "Jojo Rabbit               2019                      Taika Waititi             9.3/10  \n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testIfMovieIsCheckedOutCorrectly() {
        fakeMoviesDataBase.listOfMovies.add(testMovie1);
        BibliotecaApp.setIsLoggedIn(true);
        BibliotecaApp.setUserLoggedIn(testUser1);

        fakeMoviesDataBase.lookForAndCheckOutMovie(testMovie1.getName());

        String expectedString ="You have checked out '" + testMovie1.getName() + "'.\n";
        assertTrue(fakeMoviesDataBase.getMovieByName(testMovie1.getName()).getIsCheckedOut());
        assertEquals(testUser1, fakeMoviesDataBase.getMovieByName(testMovie1.getName()).getUserThatHasCheckedItOut());
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testTryingToCheckOutMovieAlreadyCheckedOut() {
        testMovie1.setIsCheckedOut(true);
        fakeMoviesDataBase.listOfMovies.add(testMovie1);

        fakeMoviesDataBase.lookForAndCheckOutMovie(testMovie1.getName());

        String expectedString = "'" + testMovie1.getName() + "' is currently checked out\n";
        assertTrue(fakeMoviesDataBase.getMovieByName(testMovie1.getName()).getIsCheckedOut());
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testTryingToCheckOutNotExistingMovie() {
        fakeMoviesDataBase.lookForAndCheckOutMovie(testMovie1.getName());

        String expectedString ="We don't have '" + testMovie1.getName() + "' on our movie database.\n";
        assertEquals(expectedString, outContent.toString());
    }


    @Test
    public void testCheckedOutMovieIsReturnedByCorrectUser() {
        testMovie1.setIsCheckedOut(true);
        testMovie1.setUserThatHasCheckedItOut(testUser1);
        fakeMoviesDataBase.listOfMovies.add(testMovie1);
        BibliotecaApp.setIsLoggedIn(true);
        BibliotecaApp.setUserLoggedIn(testUser1);

        fakeMoviesDataBase.checksIfMovieIsFromOurCollectionAndReturnIt(testMovie1.getName());

        String expectedString = "You returned the movie '" + testMovie1.getName() + "' successfully.\n";
        assertFalse(fakeMoviesDataBase.getMovieByName(testMovie1.getName()).getIsCheckedOut());
        assertNull(fakeMoviesDataBase.getMovieByName(testMovie1.getName()).getUserThatHasCheckedItOut());
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testCheckedOutMovieIsReturnedByWrongUser() {
        testMovie1.setIsCheckedOut(true);
        testMovie1.setUserThatHasCheckedItOut(testUser1);
        fakeMoviesDataBase.listOfMovies.add(testMovie1);
        BibliotecaApp.setIsLoggedIn(true);
        BibliotecaApp.setUserLoggedIn(testUser2);

        fakeMoviesDataBase.checksIfMovieIsFromOurCollectionAndReturnIt(testMovie1.getName());

        String expectedString = "You hadn't previously checked out the movie '" + testMovie1.getName() + "'.\n";
        assertTrue(fakeMoviesDataBase.getMovieByName(testMovie1.getName()).getIsCheckedOut());
        assertEquals(testUser1, fakeMoviesDataBase.getMovieByName(testMovie1.getName()).getUserThatHasCheckedItOut());
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testTryToReturnNotCheckedOutMovie() {
        fakeMoviesDataBase.listOfMovies.add(testMovie1);

        fakeMoviesDataBase.checksIfMovieIsFromOurCollectionAndReturnIt(testMovie1.getName());

        String expectedString = "'" + testMovie1.getName() + "' was not checked out.\n";
        assertFalse(fakeMoviesDataBase.getMovieByName(testMovie1.getName()).getIsCheckedOut());
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testTryToReturnWrongMovie() {
        fakeMoviesDataBase.checksIfMovieIsFromOurCollectionAndReturnIt(testMovie1.getName());

        String expectedString = "The movie '" + testMovie1.getName() + "' doesn't belong to our collection\n";
        assertEquals(expectedString, outContent.toString());
    }
}
