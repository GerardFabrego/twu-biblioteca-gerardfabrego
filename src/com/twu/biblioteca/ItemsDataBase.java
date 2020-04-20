package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public abstract class ItemsDataBase {

    List<Item> listOfItems = new ArrayList<>();


    public Item getItemByName (String itemName){
        for (Item item : listOfItems) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void lookForAndCheckOutItem(String name) {
        Item item = getItemByName((name));
        if (item != null) {
            if (!item.getIsCheckedOut()) {
                checkOutItem(item);
            } else {
                System.out.println("'" + name + "' is currently checked out");
            }
        }else {
            System.out.println("We don't have '" + name + "' on our database.");
        }
    }

    private void checkOutItem(Item item) {
        item.setIsCheckedOut(true);
        item.setUserThatHasCheckedItOut(BibliotecaApp.getUserLoggedIn());
        System.out.println("You have checked out '" + item.getName() + "'.");
    }


    public void checksIfItemIsFromOurCollectionAndReturnIt(String name) {
        Item item = getItemByName((name));
        if (item != null) {
            if (item.getIsCheckedOut()) {
                returnItem(item);
            } else {
                System.out.println("'" + name + "' was not checked out.");
            }
        }else {
            System.out.println("The item '" + name + "' doesn't belong to our collection");
        }
    }


    private void returnItem (Item item) {
        if (item.getUserThatHasCheckedItOut() == BibliotecaApp.getUserLoggedIn()) {
            item.setIsCheckedOut(false);
            item.setUserThatHasCheckedItOut(null);
            System.out.println("You returned the '" + item.getName() + "' successfully.");
        } else {
            System.out.println("You hadn't previously checked out the '" + item.getName() + "'.");
        }
    }

}
