package com.twu.biblioteca;

import com.twu.biblioteca.items.Item;
import com.twu.biblioteca.items.Movie;

import java.util.ArrayList;
import java.util.Collections;

public class MoviesRepository extends ItemsRepository {

    public MoviesRepository(Movie... movies){
        listOfItems = new ArrayList<>();
        Collections.addAll(listOfItems, movies);
    }


    public void printListOfMovies() {
        System.out.print(Constants.lineBreak);
        System.out.printf(Constants.listOfMoviesFormat, Constants.name, Constants.year, Constants.director, Constants.rating, Constants.isAvailable);
        for(Item item: listOfItems){
            Movie movie = (Movie) item;
            String isAvailable;
            if (!movie.getIsCheckedOut()) {isAvailable = Constants.yes;}
            else { isAvailable = Constants.no;}
            System.out.printf(Constants.listOfMoviesFormat, movie.getName(), movie.getYear(), movie.getDirector(), movie.getRating(), isAvailable);
        }
    }


    public void printCheckedOutItems() {
        for (Item item : listOfItems) {
            Movie movie = (Movie) item;
            if (movie.getIsCheckedOut()) {
                System.out.printf(Constants.checkedOutItemsFormat, movie.getUserThatHasCheckedItOut().getLibraryNumber(), Constants.movie, movie.getName(), movie.getDirector(), movie.getYear());
            }
        }
    }
}
