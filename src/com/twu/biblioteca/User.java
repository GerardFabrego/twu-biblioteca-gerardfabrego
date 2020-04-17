package com.twu.biblioteca;

public class User {
    private String userName;
    private String libraryNumber;
    private String password;
    private String email;
    private String phoneNumber;

    public User(String username, String libraryNumber, String password, String email, String phoneNumber) {
        setUserName(username);
        setLibraryNumber(libraryNumber);
        setPassword(password);
        setEmail(email);
        setPhoneNumber(phoneNumber);
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

    public void setEmail(String email) { this.email = email; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getUserName() {
        return userName;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() { return email; }

    public String getPhoneNumber() { return phoneNumber; }
}
