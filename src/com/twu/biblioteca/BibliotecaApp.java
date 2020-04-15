package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    static BooksDataBase booksDataBase = new BooksDataBase(new Book("To kill a mockingbird", "Harper Lee", "1960"),
            new Book("The alchemist", "Paula Coelho", "1980"),
            new Book("Ender's game", "Orson Scott", "1985"));

    static List<String> options = new ArrayList<String>(Arrays.asList("List of books", "Exit"));

    static String currentMenu = "";

    public static void main(String[] args) {

        printWelcomeMessage();
        while (true) {
            String desiredOption = selectDesiredOption();
            doDesiredOption(desiredOption);
        }
    }


    public static void printWelcomeMessage(){
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public static void printOptions() {
        System.out.print("Options:   ");
        for (String option: options) {
            System.out.print(option);
            if (options.indexOf(option) < options.size()-1){
                System.out.print("   |   ");
            }
            else {
                System.out.println();
            }
        }
    }


    public static String selectDesiredOption() {
        Scanner myObj = new Scanner(System.in);
        printOptions();
        System.out.println("Select option: ");
        String desiredOption = myObj.nextLine();
        return desiredOption;
    }

    public static void doDesiredOption(String desiredMenu) {
        switch(desiredMenu) {
            case "List of books":
                booksDataBase.printListOfBooks();
                currentMenu = desiredMenu;
                break;
            case "Exit":
                System.out.println("Bye!");
                System.exit(0);
            default:
                System.out.println("Please select a valid option!");
                break;
        }
    }

}
