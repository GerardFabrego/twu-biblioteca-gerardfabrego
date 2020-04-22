package com.twu.biblioteca;

import com.twu.biblioteca.items.Item;

import java.util.List;

public abstract class ItemsRepository {

    List<Item> listOfItems;


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
                System.out.println(Constants.itemCurrentlyCheckedOut1 + name + Constants.itemCurrentlyCheckedOut2);
            }
        }else {
            System.out.println(Constants.checkOutItemNotInDataBase1 + name + Constants.checkOutItemNotInDataBase2);
        }
    }

    private void checkOutItem(Item item) {
        item.setIsCheckedOut(true);
        item.setUserThatHasCheckedItOut(BibliotecaApp.getUserLoggedIn());
        System.out.println(Constants.itemCorrectlyCheckedOut1 + item.getName() + Constants.itemCorrectlyCheckedOut2);
    }


    public void checksIfItemIsFromOurCollectionAndReturnIt(String name) {
        Item item = getItemByName((name));
        if (item != null) {
            if (item.getIsCheckedOut() && item.getUserThatHasCheckedItOut() == BibliotecaApp.getUserLoggedIn()) {
                returnItem(item);
            } else {
                System.out.println(Constants.returnItemNotCheckedOutByCurrentUser1 + name + Constants.returnItemNotCheckedOutBySCurrentUser2);
            }
        }else {
            System.out.println(Constants.returnItemNotInDataBase1 + name + Constants.returnItemNotInDataBase2);
        }
    }

    private void returnItem (Item item) {
        item.setIsCheckedOut(false);
        item.setUserThatHasCheckedItOut(null);
        System.out.println(Constants.itemReturnedCorrectly1 + item.getName() + Constants.itemReturnedCorrectly2);
    }

}
