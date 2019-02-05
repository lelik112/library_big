package net.cheltsov.library.action;

import net.cheltsov.library.comparator.EditionField;
import net.cheltsov.library.domain.Genre;
import net.cheltsov.library.domain.entity.Edition;

import java.util.List;

public class RequestVerifier {
    public static boolean isCorrect(LibraryRequest request, ActionType action) {
        Object list = request.getParameter(RequestParameter.INPUT_EDITION_LIST);
        switch (action) {
            case CALCULATE_AMOUNT_OF_PAGES:
                Object genre = request.getParameter(RequestParameter.GENRE);
                return isListEditions(list) && genre instanceof Genre;
            case FIND_EDITIONS:
                Object minInt = request.getParameter(RequestParameter.MIN_INT);
                Object maxInt = request.getParameter(RequestParameter.MAX_INT);
                Object f = request.getParameter(RequestParameter.EDITION_FIELD);
                return isListEditions(list) && minInt instanceof Integer && maxInt instanceof Integer &&
                        (f.equals(EditionField.PAGE_COUNT) || f.equals(EditionField.ID) || f.equals(EditionField.YEAR));
            case SORT_EDITIONS:
                Object field = request.getParameter(RequestParameter.EDITION_FIELD);
                return isListEditions(list) && field instanceof EditionField;
            default:
                throw new EnumConstantNotPresentException(action.getDeclaringClass(), action.name());
        }
    }
    private static boolean isListEditions(Object obj) { //INFO: просто не знаю как проще это сделать
        if(!(obj instanceof List)) {
            return false;
        }
        List list = (List) obj;
        if(list.size() == 0) {
            return false;
        }
        return list.get(0) instanceof Edition;
    }
}

