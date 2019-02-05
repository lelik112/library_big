package net.cheltsov.library.action;

import net.cheltsov.library.action.impl.CalculateAmountOfPages;
import net.cheltsov.library.action.impl.FindEdition;
import net.cheltsov.library.action.impl.SortEdition;

public class CommandChooser {
    private CommandChooser() {}
    public static BasicAction performAction(ActionType action) {
        switch (action) {
            case CALCULATE_AMOUNT_OF_PAGES:
                return new CalculateAmountOfPages();
            case FIND_EDITIONS:
                return new FindEdition();
            case SORT_EDITIONS:
                return new SortEdition();
            default:
                throw new EnumConstantNotPresentException(action.getDeclaringClass(), action.name());
        }
    }
}
