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

public class SortEditionTest {
    List<Edition> sortedListActual = new ArrayList<>();
    List<Edition> sortedListExpected = new ArrayList<>();
    @Test
    public void testExecuteAction() throws Exception {
        sortedListExpected.add(editionListExpected.get(8));
        sortedListExpected.add(editionListExpected.get(11));
        sortedListExpected.add(editionListExpected.get(7));
        sortedListExpected.add(editionListExpected.get(6));
        sortedListExpected.add(editionListExpected.get(9));
        sortedListExpected.add(editionListExpected.get(10));
        sortedListExpected.add(editionListExpected.get(4));
        sortedListExpected.add(editionListExpected.get(2));
        sortedListExpected.add(editionListExpected.get(0));
        sortedListExpected.add(editionListExpected.get(5));
        sortedListExpected.add(editionListExpected.get(1));
        sortedListExpected.add(editionListExpected.get(3));
        sortedListActual.addAll(editionListExpected);

        LibraryRequest request = new LibraryRequest();
        request.setParameter(RequestParameter.INPUT_EDITION_LIST, editionListExpected);
        request.setParameter(RequestParameter.EDITION_FIELD, EditionField.PAGE_COUNT);
        BasicAction action = CommandChooser.performAction(ActionType.SORT_EDITIONS);
        LibraryResponse response = action.executeAction(request);
        List<Edition> sortedListActual = (List<Edition>) response.getParameter(ResponseParameter.OUTPUT_EDITION_LIST);
        Assert.assertEquals(sortedListActual, sortedListExpected, "Not correct");
    }
    @Test(expectedExceptions = LibraryException.class)
    public void testExecuteActionWrongDataException() throws Exception {
        LibraryRequest request = new LibraryRequest();
        request.setParameter(RequestParameter.EDITION_FIELD, EditionField.PAGE_COUNT);
        BasicAction action = CommandChooser.performAction(ActionType.SORT_EDITIONS);
        LibraryResponse response = action.executeAction(request);
    }
}