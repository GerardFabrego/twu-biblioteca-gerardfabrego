package com.twu.biblioteca;

public class Movie extends Item{

    private String director;
    private String rating;


    public Movie(String name, String year, String director, String rating) {
        setName(name);
        setYear(year);
        setDirector(director);
        setRating(rating);
        setIsCheckedOut(false);
    }


    public void setDirector(String director) {
        this.director = director;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    public String getDirector() {
        return director;
    }

    public String getRating() {
        return rating;
    }

}
