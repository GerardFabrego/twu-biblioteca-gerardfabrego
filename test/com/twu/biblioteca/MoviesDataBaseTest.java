package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class MoviesDataBaseTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    MoviesDataBase fakeMoviesDataBase;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        fakeMoviesDataBase = new MoviesDataBase(new Movie("Fight club", "1999", "David Fincher", "8.8/10")
                , new Movie("Jojo Rabbit", "2019", "Taika Waititi", "9.3/10"));
    }


    @Test
    public void testPrintListOMovies() {
        fakeMoviesDataBase.printListOfMovies();
        assertEquals("\nName                      Year                      Director                  Rating  \n" +
                "Fight club                1999                      David Fincher             8.8/10  \n" +
                "Jojo Rabbit               2019                      Taika Waititi             9.3/10  \n", outContent.toString());
    }

    @Test
    public void testIfMovieIsCheckedOutCorrectly() {
        fakeMoviesDataBase.lookForAndCheckOutMovie("Fight club");

        assertEquals(1, fakeMoviesDataBase.listOfMovies.size());
        assertEquals(1, fakeMoviesDataBase.listOfCheckedOutMovies.size());
        assertEquals("You have checked out 'Fight club'.\n", outContent.toString());
    }

    @Test
    public void testIfDatabaseIsNotModifiedWhenCheckOutWrongMovie() {
        fakeMoviesDataBase.lookForAndCheckOutMovie("Wrong book");

        assertEquals(2, fakeMoviesDataBase.listOfMovies.size());
        assertEquals(0, fakeMoviesDataBase.listOfCheckedOutMovies.size());
        assertEquals("We don't have 'Wrong book' on our book database.\n", outContent.toString());
    }


    @Test
    public void testCorrectMovieIsReturned() {
        fakeMoviesDataBase.listOfCheckedOutMovies.add(new Movie("Titanic", "1997", "James Cameron", "7.8/10"));
        fakeMoviesDataBase.checksIfMovieIsFromOurCollectionAndReturnIt("Titanic");

        assertEquals(3, fakeMoviesDataBase.listOfMovies.size());
        assertEquals(0, fakeMoviesDataBase.listOfCheckedOutMovies.size());
        assertEquals("You returned the movie 'Titanic' successfully.\n", outContent.toString());
    }

    @Test
    public void testWrongMovieIsReturned() {
        fakeMoviesDataBase.listOfCheckedOutMovies.add(new Movie("Titanic", "1997", "James Cameron", "7.8/10"));
        fakeMoviesDataBase.checksIfMovieIsFromOurCollectionAndReturnIt("Wrong movie");

        assertEquals(2, fakeMoviesDataBase.listOfMovies.size());
        assertEquals(1, fakeMoviesDataBase.listOfCheckedOutMovies.size());
        assertEquals("The movie 'Wrong movie' doesn't belong to our collection\n", outContent.toString());
    }
}
