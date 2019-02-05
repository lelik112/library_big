package net.cheltsov.library.action.impl;

import net.cheltsov.library.action.*;
import net.cheltsov.library.comparator.EditionField;
import net.cheltsov.library.domain.entity.Edition;
import net.cheltsov.library.exception.LibraryException;

import java.util.ArrayList;
import java.util.List;

public class FindEdition implements BasicAction {
    @Override
    public LibraryResponse executeAction(LibraryRequest request) throws LibraryException {
        if (! RequestVerifier.isCorrect(request, ActionType.FIND_EDITIONS)) {
            throw new LibraryException("Data is not correct");
        }
        List<Edition> foundList = new ArrayList<>();
        Integer min = (Integer) request.getParameter(RequestParameter.MIN_INT);
        Integer max = (Integer) request.getParameter(RequestParameter.MAX_INT);
        EditionField field = (EditionField) request.getParameter(RequestParameter.EDITION_FIELD);
        for(Edition e: (List<Edition>) request.getParameter(RequestParameter.INPUT_EDITION_LIST)) {
            if((field.equals(EditionField.PAGE_COUNT) &&
                    e.getPageCount() >= Math.min(min, max) && e.getPageCount() <= Math.max(min, max)) ||
                    (field.equals(EditionField.ID) &&
                            e.getId() >= Math.min(min, max) && e.getId() <= Math.max(min, max)) ||
                    (field.equals(EditionField.YEAR) &&
                            e.getYear() >= Math.min(min, max) && e.getYear() <= Math.max(min, max))) {
                foundList.add(e);
            }
        }
        LibraryResponse response = new LibraryResponse();
        response.setParameter(ResponseParameter.OUTPUT_EDITION_LIST, foundList);
        return response;
    }
}
