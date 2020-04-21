package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsersRepository {
    List<User> listOfUsers;

    public UsersRepository(User... user) {
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
