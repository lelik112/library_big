package net.cheltsov.library.dao;

import net.cheltsov.library.domain.EditionElement;
import net.cheltsov.library.domain.EditionFactory;
import net.cheltsov.library.domain.Genre;
import net.cheltsov.library.domain.TypeEdition;
import net.cheltsov.library.domain.entity.Author;
import net.cheltsov.library.domain.entity.Edition;
import net.cheltsov.library.domain.entity.Book;
import net.cheltsov.library.domain.entity.Journal;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class EditionAbstractXMLBuilder {

    protected static final Logger LOGGER = LogManager.getRootLogger();

    protected final String ID_ATTRIBUTE = "id";
    protected final String GENRE_ATTRIBUTE = "genre";

    private Edition edition;
    private Author author;
    private List<Edition> editions = new ArrayList<>();

    protected List<Edition> getEditions() {
        return editions;
    }

    protected void createEditionAction(EditionElement currentElement, String idAttribute, String genreAttribute) {
        edition = EditionFactory.getEdition(TypeEdition.valueOf(currentElement.toString()));
        edition.setIdXml(idAttribute);
        if (genreAttribute != null && !genreAttribute.isEmpty()) {
            edition.setGenre(Genre.valueOf(genreAttribute.toUpperCase().replace('-', '_')));
        }
        LOGGER.log(Level.INFO, "Edition was created");
    }

    protected void createAuthorAction() {
        author = new Author();
        LOGGER.log(Level.INFO, "Author was created");
    }

    protected void endElementAction(EditionElement currentElement) {
        switch (currentElement) {
            case BOOK:
            case JOURNAL:
                editions.add(edition);
                LOGGER.log(Level.INFO, "Edition was added to list");
                break;
            case AUTHOR:
                ((Book) edition).setAuthor(author);
                LOGGER.log(Level.INFO, "Author was set to book");
                break;
        }
    }

    protected void charactersAction(EditionElement currentElement, String value) {
        if (value.isEmpty() || value == null) {
            return;
        }
        switch (currentElement) {
            case TITLE:
                edition.setTitle(value);
                LOGGER.log(Level.INFO, "Title was set to edition");
                break;
            case PAGE_COUNT:
                edition.setPageCount(Integer.parseInt(value));
                LOGGER.log(Level.INFO, "Page count was set to edition");
                break;
            case YEAR:
                edition.setYear(Integer.parseInt(value));
                LOGGER.log(Level.INFO, "Year was set to edition");
                break;
            case FIRST_NAME:
                author.setFirstName(value);
                LOGGER.log(Level.INFO, "Name was set to author");
                break;
            case LAST_NAME:
                author.setLastName(value);
                LOGGER.log(Level.INFO, "Family name was set to author");
                break;
            case NUMBER:
                ((Journal) edition).setNumber(Integer.parseInt(value));
                LOGGER.log(Level.INFO, "Number of pages was set to journal");
                break;
        }
    }
}
