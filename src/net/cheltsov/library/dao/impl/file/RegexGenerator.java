package net.cheltsov.library.dao.impl.file;

import net.cheltsov.library.domain.Genre;
import net.cheltsov.library.domain.TypeEdition;

public class RegexGenerator {
    public static String generateGenreRegex() {
        String regex = "(";
        for(Genre t: Genre.values()) {
            regex += t + "|";
        }
        regex = regex.replaceFirst("\\|$", ")");
        return regex;
    }
    public static String generateTypeEditionRegex() {
        String regex = "(";
        for(TypeEdition t: TypeEdition.values()) {
            regex += t + "|";
        }
        regex = regex.replaceFirst("\\|$", ")");
        return regex;
    }
}
