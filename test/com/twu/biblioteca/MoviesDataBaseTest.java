package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class MoviesDataBaseTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    MoviesDataBase fakeMoviesDataBase = new MoviesDataBase();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testPrintListOMovies() {
        fakeMoviesDataBase = new MoviesDataBase(new Movie("Fight club", "1999", "David Fincher", "8.8/10")
                , new Movie("Jojo Rabbit", "2019", "Taika Waititi", "9.3/10"));
        fakeMoviesDataBase.printListOfMovies();
        assertEquals("\nName                      Year                      Director                  Rating  \n" +
                "Fight club                1999                      David Fincher             8.8/10  \n" +
                "Jojo Rabbit               2019                      Taika Waititi             9.3/10  \n", outContent.toString());
    }
    @Test
    public void testIfMovieIsCheckedOutCorrectly() {
        String movieName = "TestMovie";
        fakeMoviesDataBase.listOfMovies.add(new Movie(movieName, "2000", "Gerard", "10/10", false));

        fakeMoviesDataBase.lookForAndCheckOutMovie(movieName);
        assertTrue(fakeMoviesDataBase.getMovieByName(movieName).getIsCheckedOut());
        assertEquals("You have checked out '" + movieName + "'.\n", outContent.toString());
    }

    @Test
    public void testTryingToCheckOutMovieAlreadyCheckedOut() {
        String movieName = "TestMovie";
        fakeMoviesDataBase.listOfMovies.add(new Movie(movieName, "2000", "Gerard", "10/10", true));

        fakeMoviesDataBase.lookForAndCheckOutMovie(movieName);
        assertTrue(fakeMoviesDataBase.getMovieByName(movieName).getIsCheckedOut());
        assertEquals("'" + movieName + "' is currently checked out\n", outContent.toString());
    }

    @Test
    public void testTryingToCheckOutNotExistingMovie() {
        String movieName = "TestMovie";

        fakeMoviesDataBase.lookForAndCheckOutMovie(movieName);
        assertEquals("We don't have '" + movieName + "' on our movie database.\n", outContent.toString());
    }


    @Test
    public void testCheckedOutMovieIsReturned() {
        String movieName = "TestMovie";
        fakeMoviesDataBase.listOfMovies.add(new Movie(movieName, "2000", "Gerard", "10/10", true));

        fakeMoviesDataBase.checksIfMovieIsFromOurCollectionAndReturnIt(movieName);
        assertFalse(fakeMoviesDataBase.getMovieByName(movieName).getIsCheckedOut());
        assertEquals("You returned the movie '" + movieName + "' successfully.\n", outContent.toString());
    }

    @Test
    public void testTryToReturnNotCheckedOutMovie() {
        String movieName = "TestMovie";
        fakeMoviesDataBase.listOfMovies.add(new Movie(movieName, "2000", "Gerard", "10/10", false));

        fakeMoviesDataBase.checksIfMovieIsFromOurCollectionAndReturnIt(movieName);
        assertFalse(fakeMoviesDataBase.getMovieByName(movieName).getIsCheckedOut());
        assertEquals("'" + movieName + "' was not checked out.\n", outContent.toString());
    }

    @Test
    public void testTryToReturnWrongMovie() {
        String movieName = "TestMovie";

        fakeMoviesDataBase.checksIfMovieIsFromOurCollectionAndReturnIt(movieName);
        assertEquals("The movie '" + movieName + "' doesn't belong to our collection\n", outContent.toString());
    }
}
