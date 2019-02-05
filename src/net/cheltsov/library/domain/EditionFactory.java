package net.cheltsov.library.domain;

import net.cheltsov.library.domain.entity.Edition;
import net.cheltsov.library.domain.entity.Book;
import net.cheltsov.library.domain.entity.Journal;

public class EditionFactory {
    private EditionFactory() {}
    public static Edition getEdition(TypeEdition currentType) throws EnumConstantNotPresentException {
        switch(currentType) {
            case BOOK:
                return new Book();
            case JOURNAL:
                return new Journal();
            default:
                throw new EnumConstantNotPresentException(currentType.getDeclaringClass(), currentType.name());

        }
    }
}
