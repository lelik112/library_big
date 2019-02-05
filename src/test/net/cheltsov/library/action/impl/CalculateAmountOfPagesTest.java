package net.cheltsov.library.action.impl;

import net.cheltsov.library.action.BasicAction;
import net.cheltsov.library.action.CommandChooser;
import net.cheltsov.library.action.ActionType;
import net.cheltsov.library.action.LibraryRequest;
import net.cheltsov.library.action.LibraryResponse;
import net.cheltsov.library.action.RequestParameter;
import net.cheltsov.library.action.ResponseParameter;
import net.cheltsov.library.domain.Genre;
import net.cheltsov.library.exception.LibraryException;
import org.testng.Assert;
import org.testng.annotations.Test;

import static net.cheltsov.library.LibraryBeforeTest.editionListExpected;

public class CalculateAmountOfPagesTest {
    @Test
    public void testExecuteAction() throws Exception {
        Integer pageCountExpected = 90 + 96 + 33;
        LibraryRequest request = new LibraryRequest();
        request.setParameter(RequestParameter.INPUT_EDITION_LIST, editionListExpected);
        request.setParameter(RequestParameter.GENRE, Genre.EROTIC);
        BasicAction action = CommandChooser.performAction(ActionType.CALCULATE_AMOUNT_OF_PAGES);
        LibraryResponse response = action.executeAction(request);
        Integer pageCountActual = (Integer) response.getParameter(ResponseParameter.AMOUNT_OF_PAGES);
        Assert.assertEquals(pageCountActual, pageCountExpected);
    }

    @Test(expectedExceptions = LibraryException.class)
    public void testExecuteActionWrongDataException() throws Exception {
        Integer pageCountExpected = 90 + 96 + 33;
        LibraryRequest request = new LibraryRequest();
        request.setParameter(RequestParameter.INPUT_EDITION_LIST, editionListExpected);
        BasicAction action = CommandChooser.performAction(ActionType.CALCULATE_AMOUNT_OF_PAGES);
        LibraryResponse response = action.executeAction(request);
    }

}