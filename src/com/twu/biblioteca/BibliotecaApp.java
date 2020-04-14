package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    static BooksDataBase booksDataBase = new BooksDataBase(new Book("To kill a mockingbird", "Harper Lee", "1960"),
            new Book("The alchemist", "Paula Coelho", "1980"),
            new Book("Ender's game", "Orson Scott", "1985"));

    static String[] options = {"List of books"};
    static String currentMenu = "";

    public static void main(String[] args) {

        printWelcomeMessage();
        selectOptionAndGoToDesiredMenu();

    }


    public static void printWelcomeMessage(){
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public static void printOptions() {
        for (String option: options) {
            System.out.println(option);
        }
    }

    public static void selectOptionAndGoToDesiredMenu() {

        String desiredOption = selectDesiredOption();

        if (isAValidOption(desiredOption)) {
            goToDesiredMenu(desiredOption);
        }
        else {
            System.out.println("Please select a valid option!");
            selectOptionAndGoToDesiredMenu();
        }
    }

    public static String selectDesiredOption() {
        Scanner myObj = new Scanner(System.in);
        printOptions();
        System.out.println("Select option: ");
        String desiredOption = myObj.nextLine();
        return desiredOption;
    }

    public static void goToDesiredMenu(String desiredMenu) {
        switch(desiredMenu) {
            case "List of books":
                booksDataBase.printListOfBooks();
                currentMenu = desiredMenu;
                break;
            default:
                break;
        }
    }

    public static boolean isAValidOption(String userOption) {
        boolean isAValidOption = false;
        for (String option: options) {
            if (option.equals(userOption)) {
                isAValidOption = true;
                break;
            }
        }
        return isAValidOption;
    }
}
