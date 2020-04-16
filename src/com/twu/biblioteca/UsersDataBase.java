package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsersDataBase {
    List<User> listOfUsers;

    public UsersDataBase(User... user) {
        listOfUsers = new ArrayList<>();
        Collections.addAll(listOfUsers, user);
    }

    public User getUserByLibraryNumber (String libraryNumber){
        for (User user : listOfUsers) {
            if (user.getLibraryNumber().equals(libraryNumber)) {
                return user;
            }
        }
        return null;
    }

    public boolean checkIfCredentialsAreCorrect(String libraryNumber, String password) {
        User currentUser = getUserByLibraryNumber(libraryNumber);
        if (currentUser != null) {
            if (currentUser.getPassword().equals(password)){
                System.out.println("You successfully logged in.");
                return true;
            } else {
                System.out.println("The password introduced isn't correct.");
            }
        } else {
            System.out.println("The user introduced doesn't exist.");
        }
        return false;
    }
}
