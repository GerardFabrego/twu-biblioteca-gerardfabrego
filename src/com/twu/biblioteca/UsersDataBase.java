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

}
