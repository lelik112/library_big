package net.cheltsov.library.action.impl;

import net.cheltsov.library.action.*;
import net.cheltsov.library.comparator.ComparatorFactory;
import net.cheltsov.library.comparator.EditionField;
import net.cheltsov.library.domain.entity.Edition;
import net.cheltsov.library.exception.LibraryException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortEdition implements BasicAction {
    @Override
    public LibraryResponse executeAction(LibraryRequest request) throws LibraryException {
        if (! RequestVerifier.isCorrect(request, ActionType.SORT_EDITIONS)) {
            throw new LibraryException("Data is not correct");
        }
        List<Edition> sortedEditionList = new ArrayList<>();

        List<Edition> inputEditionList = (List<Edition>) request.getParameter(RequestParameter.INPUT_EDITION_LIST);
        EditionField field = (EditionField)  request.getParameter(RequestParameter.EDITION_FIELD);
        Boolean isRevers = (Boolean) request.getParameter(RequestParameter.IS_REVERS);
        sortedEditionList.addAll(inputEditionList);
        Comparator comparator = ComparatorFactory.getComparator(field);
        sortedEditionList.sort(isRevers? comparator.reversed(): comparator);
        LibraryResponse response = new LibraryResponse();
        response.setParameter(ResponseParameter.OUTPUT_EDITION_LIST, sortedEditionList);
        return response;
    }
}
