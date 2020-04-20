package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;

public class MoviesDataBase extends ItemsDataBase {

    public MoviesDataBase(Movie... movies){
        listOfItems = new ArrayList<>();
        Collections.addAll(listOfItems, movies);
    }


    public void printListOfMovies() {
        System.out.println();
        System.out.printf("%-25s %-25s %-25s %-25s %-13s\n", "Name", "Year", "Director", "Rating", "Is available");
        for(Item item: listOfItems){
            Movie movie = (Movie) item;
            String isAvailable;
            if (!movie.getIsCheckedOut()) {isAvailable = "Yes";}
            else { isAvailable = "No";}
            System.out.printf("%-25s %-25s %-25s %-25s %-13s\n", movie.getName(), movie.getYear(), movie.getDirector(), movie.getRating(), isAvailable);
        }
    }


    public void printCheckedOutItems() {
        for (Item item : listOfItems) {
            Movie movie = (Movie) item;
            if (movie.getIsCheckedOut()) {
                System.out.printf("%-25s %-25s %-25s %-25s %-4s\n", movie.getUserThatHasCheckedItOut().getLibraryNumber(), "Movie", movie.getName(), movie.getDirector(), movie.getYear());
            }
        }
    }
}
