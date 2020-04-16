package com.twu.biblioteca;

public class Movie {

    private String name;
    private String year;
    private String director;
    private String rating;
    private boolean isCheckedOut;


    private User userThatHasCheckedItOut;


    public Movie(String name, String year, String director, String rating) {
        setName(name);
        setYear(year);
        setDirector(director);
        setRating(rating);
        setIsCheckedOut(false);
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

    public void setIsCheckedOut(boolean isCheckedOut) {this.isCheckedOut = isCheckedOut; }

    public void setUserThatHasCheckedItOut(User userThatHasCheckedItOut) {
        this.userThatHasCheckedItOut = userThatHasCheckedItOut;
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

    public boolean getIsCheckedOut() {return isCheckedOut; }

    public User getUserThatHasCheckedItOut() {
        return userThatHasCheckedItOut;
    }
}
