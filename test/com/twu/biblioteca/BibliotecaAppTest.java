package com.twu.biblioteca;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BibliotecaAppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    static User user = mock(User.class);


    @BeforeClass
    public static void setUp() {
        BibliotecaApp.usersRepository = mock(UsersRepository.class);

        when(BibliotecaApp.usersRepository.getUserByLibraryNumber(Constants.correctUser)).thenReturn(user);

        when(user.getPassword()).thenReturn(Constants.correctPass);
        when(user.getUserName()).thenReturn(Constants.emptyString);
        when(user.getPhoneNumber()).thenReturn(Constants.emptyString);
        when(user.getEmail()).thenReturn(Constants.emptyString);
        when(user.getLibraryNumber()).thenReturn(Constants.emptyString);
    }

    @Before
    public void reset() {
        if(BibliotecaApp.getIsLoggedIn()){BibliotecaApp.logOut();}
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testPrintWelcomeMessage() {
        BibliotecaApp.printWelcomeMessage();

        String expectedString = Constants.welcomeMessage;
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testPrintOptionsWithoutLogIn() {
        BibliotecaApp.printOptions();

        assertEquals(Constants.optionsWithoutLogIn, outContent.toString());
    }

    @Test
    public void testPrintOptionsWhenLogIn() {
        BibliotecaApp.logIn(user);
        BibliotecaApp.printOptions();

        assertEquals(Constants.optionsWhenLoggedIn, outContent.toString());
    }

    @Test
    public void testPrintOptionsWhenLogOut() {
        BibliotecaApp.logIn(user);
        BibliotecaApp.logOut();
        BibliotecaApp.printOptions();

        assertEquals(Constants.optionsWithoutLogIn, outContent.toString());
    }


    @Test
    public void testIfPrintsInvalidMenuGivenIncorrectOption() {
        BibliotecaApp.doDesiredOption(Constants.invalidOption);

        assertEquals(Constants.selectValidOption, outContent.toString());
    }

    @Test
    public void testIfProgramQuitsTheAppWhenUserSelectExit() {
        BibliotecaApp.doDesiredOption(Constants.exit);

        assertEquals(Constants.sayBye, outContent.toString());
    }

    @Test
    public void testCorrectUserCorrectPasswordLogIn() {
        BibliotecaApp.tryToLogIn(Constants.correctUser, Constants.correctPass);

        assertEquals(Constants.successfulLogIn, outContent.toString());
        assertTrue(BibliotecaApp.getIsLoggedIn());
        assertEquals(user, BibliotecaApp.getUserLoggedIn());
    }

    @Test
    public void testIncorrectUserLogIn() {
        BibliotecaApp.tryToLogIn(Constants.incorrectUser, Constants.incorrectPass);

        assertEquals(Constants.invalidUser, outContent.toString());
        assertFalse(BibliotecaApp.getIsLoggedIn());
    }

    @Test
    public void testCorrectUserIncorrectPasswordLogIn() {
        BibliotecaApp.tryToLogIn(Constants.correctUser, Constants.incorrectPass);

        assertEquals(Constants.invalidPassword, outContent.toString());
        assertFalse(BibliotecaApp.getIsLoggedIn());
    }

    @Test
    public void testLogOut() {
        BibliotecaApp.setIsLoggedIn(true);
        BibliotecaApp.setUserLoggedIn(user);

        BibliotecaApp.tryToLogOut();

        assertEquals(Constants.successLogOut, outContent.toString());
    }

    @Test
    public void testPrintCheckedOutItems() {
        BibliotecaApp.booksRepository = mock(BooksRepository.class);
        BibliotecaApp.moviesRepository = mock(MoviesRepository.class);
        doAnswer(param -> null).when(BibliotecaApp.booksRepository).printCheckedOutItems();
        doAnswer(param -> null).when(BibliotecaApp.moviesRepository).printCheckedOutItems();

        BibliotecaApp.printCheckedOutItems();

        assertEquals(Constants.checkedOutItems, outContent.toString());
    }

    @Test
    public void testPrintUserPersonalInfo() {
        BibliotecaApp.logIn(user);
        BibliotecaApp.printUserPersonalInfo();

        assertEquals(Constants.printPersonalInfo, outContent.toString());
    }
}
