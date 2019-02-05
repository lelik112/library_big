package net.cheltsov.library.dao.impl.file;

import net.cheltsov.library.domain.EditionFactory;
import net.cheltsov.library.domain.Genre;
import net.cheltsov.library.domain.TypeEdition;
import net.cheltsov.library.domain.entity.Edition;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

    class EditionStringBuilder {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String REGEX = "(?i)" +
            RegexGenerator.generateTypeEditionRegex() +
            "(.*id\\W?\\s*)(\\d++)" +
            "(,\\s*title\\W?\\s*)(.+)" +
            "(,\\s*pageCount\\W?\\s*)(\\d++)" +
            "(,\\s*year\\W?\\s*)(\\d{4})" +
            "(,\\s*genre\\W?\\s*)" +
            RegexGenerator.generateGenreRegex();

        List<Edition> buildEditions(List<String> data) {
        List<Edition> editionList = new ArrayList<>();
        if(data == null) {
            return editionList;
        }
        Pattern pat = Pattern.compile(REGEX);
        Matcher mat;
        ListIterator<String> it = data.listIterator();
        while (it.hasNext()) {
            String s = it.next();
            mat = pat.matcher(s);
            if (mat.find()) {
                TypeEdition te = TypeEdition.valueOf(mat.group(1).toUpperCase());
                Edition edition = EditionFactory.getEdition(te);
                Pattern anotherPat = Pattern.compile(edition.getRegex());
                Matcher anotherMat = anotherPat.matcher(s);
                if(anotherMat.find()) {
                    edition.setAdditionalFields(anotherMat.group());
                } else {
                    LOGGER.log(Level.WARN, "Data from listStringData[" + it.previousIndex() +
                            "] is not correct and string was not parsed");
                    continue;
                }
                edition.setId(Integer.parseInt(mat.group(3)));
                edition.setTitle(mat.group(5));
                edition.setPageCount(Integer.parseInt(mat.group(7)));
                edition.setYear(Integer.parseInt(mat.group(9)));
                edition.setGenre(Genre.valueOf(mat.group(11).toUpperCase()));
                editionList.add(edition);
            } else {
                LOGGER.log(Level.WARN, "Data from listStringData[" + it.previousIndex() +
                        "] is not correct and string was not parsed");
            }
        }
        LOGGER.log(Level.INFO, "Number of " + editionList.size() +
                " were parsed and added to intList");
        return editionList;
    }


}
