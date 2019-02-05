package net.cheltsov.library.action.impl;

import net.cheltsov.library.action.*;
import net.cheltsov.library.domain.entity.Edition;
import net.cheltsov.library.exception.LibraryException;

import java.util.List;

public class CalculateAmountOfPages implements BasicAction {
    @Override
    public LibraryResponse executeAction(LibraryRequest request) throws LibraryException {

        if (! RequestVerifier.isCorrect(request, ActionType.CALCULATE_AMOUNT_OF_PAGES)) {
            throw new LibraryException("Data is not correct");
        }

        Integer amount = 0;
        for(Edition e: (List<Edition>) request.getParameter(RequestParameter.INPUT_EDITION_LIST)) {
            if (e.getGenre().equals(request.getParameter(RequestParameter.GENRE))) {
                amount += e.getPageCount();
            }
        }
        LibraryResponse response = new LibraryResponse();
        response.setParameter(ResponseParameter.AMOUNT_OF_PAGES, amount);
        return response;
    }
}
