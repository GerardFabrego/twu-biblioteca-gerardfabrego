package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class MoviesDataBaseTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    MoviesDataBase fakeBooksDataBase;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        fakeBooksDataBase = new MoviesDataBase(new Movie("Fight club", "1999", "David Fincher", "8.8/10")
                , new Movie("Jojo Rabbit", "2019", "Taika Waititi", "9.3/10"));
    }


    @Test
    public void testPrintListOMovies() {
        fakeBooksDataBase.printListOfMovies();
        assertEquals("\nName                      Year                      Director                  Rating  \n" +
                "Fight club                1999                      David Fincher             8.8/10  \n" +
                "Jojo Rabbit               2019                      Taika Waititi             9.3/10  \n", outContent.toString());
    }


}
