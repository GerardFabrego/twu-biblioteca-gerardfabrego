package com.twu.biblioteca.items;

import com.twu.biblioteca.User;

public abstract class Item {
    private String name;
    private String year;
    private boolean isCheckedOut;
    private User userThatHasCheckedItOut;

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setIsCheckedOut(boolean isCheckedOut) {this.isCheckedOut = isCheckedOut; }

    public void setUserThatHasCheckedItOut(User userThatHasCheckedItOut) {this.userThatHasCheckedItOut = userThatHasCheckedItOut;}

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public boolean getIsCheckedOut() {return isCheckedOut; }

    public User getUserThatHasCheckedItOut() {return userThatHasCheckedItOut;}

}
