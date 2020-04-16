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
    User testUser = new User("Gerard", "123-4567", "hellohello");
    User testUser2 = new User("Mariano", "987-6543", "byebye");



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
        Movie testMovie = new Movie(movieName, "2000", "Gerard", "10/10");
        fakeMoviesDataBase.listOfMovies.add(testMovie);

        BibliotecaApp.setIsLoggedIn(true);
        BibliotecaApp.setUserLoggedIn(testUser);

        fakeMoviesDataBase.lookForAndCheckOutMovie(movieName);
        assertTrue(fakeMoviesDataBase.getMovieByName(movieName).getIsCheckedOut());
        assertEquals(testUser, fakeMoviesDataBase.getMovieByName(movieName).getUserThatHasCheckedItOut());
        assertEquals("You have checked out '" + movieName + "'.\n", outContent.toString());
    }

    @Test
    public void testTryingToCheckOutMovieAlreadyCheckedOut() {
        String movieName = "TestMovie";
        Movie testMovie = new Movie(movieName, "2000", "Gerard", "10/10");
        testMovie.setIsCheckedOut(true);
        fakeMoviesDataBase.listOfMovies.add(testMovie);

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
    public void testCheckedOutMovieIsReturnedByCorrectUser() {
        String movieName = "TestMovie";
        Movie testMovie = new Movie(movieName, "2000", "Gerard", "10/10");
        testMovie.setIsCheckedOut(true);
        testMovie.setUserThatHasCheckedItOut(testUser);
        fakeMoviesDataBase.listOfMovies.add(testMovie);
        BibliotecaApp.setIsLoggedIn(true);
        BibliotecaApp.setUserLoggedIn(testUser);

        fakeMoviesDataBase.checksIfMovieIsFromOurCollectionAndReturnIt(movieName);

        assertFalse(fakeMoviesDataBase.getMovieByName(movieName).getIsCheckedOut());
        assertNull(fakeMoviesDataBase.getMovieByName(movieName).getUserThatHasCheckedItOut());
        assertEquals("You returned the movie '" + movieName + "' successfully.\n", outContent.toString());
    }

    @Test
    public void testCheckedOutMovieIsReturnedByWrongUser() {
        String movieName = "TestMovie";
        Movie testMovie = new Movie(movieName, "2000", "Gerard", "10/10");
        testMovie.setIsCheckedOut(true);
        testMovie.setUserThatHasCheckedItOut(testUser);
        fakeMoviesDataBase.listOfMovies.add(testMovie);
        BibliotecaApp.setIsLoggedIn(true);
        BibliotecaApp.setUserLoggedIn(testUser2);

        fakeMoviesDataBase.checksIfMovieIsFromOurCollectionAndReturnIt(movieName);

        assertTrue(fakeMoviesDataBase.getMovieByName(movieName).getIsCheckedOut());
        assertEquals(testUser, fakeMoviesDataBase.getMovieByName(movieName).getUserThatHasCheckedItOut());
        assertEquals("You hadn't previously checked out the movie '" + movieName + "'.\n", outContent.toString());
    }

    @Test
    public void testTryToReturnNotCheckedOutMovie() {
        String movieName = "TestMovie";
        Movie testMovie = new Movie(movieName, "2000", "Gerard", "10/10");
        fakeMoviesDataBase.listOfMovies.add(testMovie);

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
