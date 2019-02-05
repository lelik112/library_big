package net.cheltsov.library.comparator;

import net.cheltsov.library.domain.entity.Edition;

import java.util.Comparator;

public class ComparatorFactory {
    public static Comparator<Edition> getComparator(EditionField field) {
        Comparator additionComparator = Comparator.comparing(Edition::getGenre).thenComparing(Edition::getTitle);
        switch (field) {
            case ID:
                return Comparator.comparing(Edition::getId).thenComparing(additionComparator);
            case TITLE:
                return Comparator.comparing(Edition::getTitle).thenComparing(Edition::getGenre);
            case PAGE_COUNT:
                return Comparator.comparing(Edition::getPageCount).thenComparing(additionComparator);
            case YEAR:
                return Comparator.comparing(Edition::getYear).thenComparing(additionComparator);
            case GENRE:
                return additionComparator;
            default:
                throw new EnumConstantNotPresentException(field.getDeclaringClass(), field.name());

        }
    }
}
