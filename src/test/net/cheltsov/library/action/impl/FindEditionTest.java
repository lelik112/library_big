package net.cheltsov.library.action.impl;

import net.cheltsov.library.action.BasicAction;
import net.cheltsov.library.action.CommandChooser;
import net.cheltsov.library.action.ActionType;
import net.cheltsov.library.action.LibraryRequest;
import net.cheltsov.library.action.LibraryResponse;
import net.cheltsov.library.action.RequestParameter;
import net.cheltsov.library.action.ResponseParameter;
import net.cheltsov.library.comparator.EditionField;
import net.cheltsov.library.domain.entity.Edition;
import net.cheltsov.library.exception.LibraryException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static net.cheltsov.library.LibraryBeforeTest.editionListExpected;


public class FindEditionTest {
    @Test
    public void testExecuteAction() throws Exception {
        List<Edition> foundListExpexted = new ArrayList<>();
        foundListExpexted.add(editionListExpected.get(1));
        foundListExpexted.add(editionListExpected.get(3));
        foundListExpexted.add(editionListExpected.get(5));
        LibraryRequest request = new LibraryRequest();
        request.setParameter(RequestParameter.INPUT_EDITION_LIST, editionListExpected);
        request.setParameter(RequestParameter.MIN_INT, 900);
        request.setParameter(RequestParameter.MAX_INT, 1000);
        request.setParameter(RequestParameter.EDITION_FIELD, EditionField.PAGE_COUNT);
        BasicAction action = CommandChooser.performAction(ActionType.FIND_EDITIONS);
        LibraryResponse response = action.executeAction(request);
        List<Edition> foundListActual = (List<Edition>) response.getParameter(ResponseParameter.OUTPUT_EDITION_LIST);
        Assert.assertEquals(foundListActual, foundListExpexted);
    }
    @Test(expectedExceptions = LibraryException.class)
    public void testExecuteActionWrongDataException() throws Exception {
        LibraryRequest request = new LibraryRequest();
        request.setParameter(RequestParameter.INPUT_EDITION_LIST, editionListExpected);
        request.setParameter(RequestParameter.MIN_INT, 900);
        request.setParameter(RequestParameter.MAX_INT, 1000);
        request.setParameter(RequestParameter.EDITION_FIELD, EditionField.GENRE);
        BasicAction action = CommandChooser.performAction(ActionType.FIND_EDITIONS);
        LibraryResponse response = action.executeAction(request);
    }

}