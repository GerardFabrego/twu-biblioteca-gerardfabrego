package com.twu.biblioteca;

public class User {
    String userName;
    String libraryNumber;
    String password;

    public User(String username, String libraryNumber, String password) {
        setUserName(username);
        setLibraryNumber(libraryNumber);
        setPassword(password);
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLibraryNumber(String libraryNumber) {
        this.libraryNumber = libraryNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }
}
