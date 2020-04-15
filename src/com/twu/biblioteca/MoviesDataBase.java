package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoviesDataBase {
    List<Movie> listOfCheckedOutMovies;
    List<Movie> listOfMovies;

    public MoviesDataBase(Movie... movie){
        listOfCheckedOutMovies = new ArrayList<>();
        listOfMovies = new ArrayList<>();
        Collections.addAll(listOfMovies, movie);
    }

    public void printListOfMovies() {
        System.out.println();
        System.out.printf("%-25s %-25s %-25s %-8s\n", "Name", "Year", "Director", "Rating");
        for(Movie movie: listOfMovies){
            System.out.printf("%-25s %-25s %-25s %-8s\n", movie.getName(), movie.getYear(), movie.getDirector(), movie.getRating());
        }
    }

    public void lookForAndCheckOutMovie(String name) {
        boolean movieExist = false;
        for (Movie movie: listOfMovies){
            if (movie.getName().equals(name)) {
                checkOutMovie(movie);
                movieExist = true;
                break;
            }
        }
        if (!movieExist) {
            System.out.println("We don't have '" + name + "' on our book database.");
        }
    }

    private void checkOutMovie(Movie movie) {
        listOfMovies.remove(movie);
        listOfCheckedOutMovies.add(movie);
        System.out.println("You have checked out '" + movie.getName() + "'.");
    }
}
