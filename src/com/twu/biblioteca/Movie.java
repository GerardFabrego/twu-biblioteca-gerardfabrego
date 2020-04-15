package com.twu.biblioteca;

public class Movie {

    private String name;
    private String year;
    private String director;
    private String rating;


    public Movie(String name, String year, String director, String rating) {
        setName(name);
        setYear(year);
        setDirector(director);
        setRating(rating);
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public String getRating() {
        return rating;
    }



}
