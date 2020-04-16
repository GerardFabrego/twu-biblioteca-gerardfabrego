package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoviesDataBase {

    List<Movie> listOfMovies;

    public MoviesDataBase(Movie... movies){
        listOfMovies = new ArrayList<>();
        Collections.addAll(listOfMovies, movies);
    }

    public Movie getMovieByName (String movieName){
        for (Movie movie : listOfMovies) {
            if (movie.getName().equals(movieName)) {
                return movie;
            }
        }
        return null;
    }

    public void printListOfMovies() {
        System.out.println();
        System.out.printf("%-25s %-25s %-25s %-8s\n", "Name", "Year", "Director", "Rating");
        for(Movie movie: listOfMovies){
            System.out.printf("%-25s %-25s %-25s %-8s\n", movie.getName(), movie.getYear(), movie.getDirector(), movie.getRating());
        }
    }

    public void lookForAndCheckOutMovie(String name) {
        Movie movie = getMovieByName((name));
        if (movie != null) {
            if (!movie.getIsCheckedOut()) {
                checkOutMovie(movie);
            } else {
                System.out.println("'" + name + "' is currently checked out");
            }
        }else {
            System.out.println("We don't have '" + name + "' on our movie database.");
        }
    }

    private void checkOutMovie(Movie movie) {
        movie.setIsCheckedOut(true);
        System.out.println("You have checked out '" + movie.getName() + "'.");
    }


    public void checksIfMovieIsFromOurCollectionAndReturnIt(String name) {
        Movie movie = getMovieByName((name));
        if (movie != null) {
            if (movie.getIsCheckedOut()) {
                returnMovie(movie);
            } else {
                System.out.println("'" + name + "' was not checked out.");
            }
        }else {
            System.out.println("The movie '" + name + "' doesn't belong to our collection");
        }
    }


    private void returnMovie (Movie movie) {
        movie.setIsCheckedOut(false);
        System.out.println("You returned the movie '" + movie.getName() + "' successfully.");
    }
}
