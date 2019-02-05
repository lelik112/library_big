package net.cheltsov.library.dao.impl.stax;

import net.cheltsov.library.dao.EditionAbstractXMLBuilder;
import net.cheltsov.library.dao.EditionDao;
import net.cheltsov.library.domain.EditionElement;
import net.cheltsov.library.domain.entity.Edition;
import net.cheltsov.library.exception.LibraryException;
import org.apache.logging.log4j.Level;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EditionStAXBuilder extends EditionAbstractXMLBuilder implements EditionDao {

    private EditionElement currentElement;
    private XMLStreamReader reader;

    @Override
    public List<Edition> readEditions() throws LibraryException {
        return readEditions(DEFAULT_FILE_NAME_XML);
    }

    @Override
    public List<Edition> readEditions(String fileName) throws LibraryException {
        InputStream input = null;
        try {
            input = new FileInputStream(fileName);
            reader = XMLInputFactory.newInstance().createXMLStreamReader(input);
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_DOCUMENT:
                        LOGGER.log(Level.INFO, "Parsing started");
                        break;
                    case XMLStreamConstants.END_DOCUMENT:
                        LOGGER.log(Level.INFO, "Parsing ended");
                        break;
                    case XMLStreamConstants.START_ELEMENT:
                        startElementAction();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        currentElement = EditionElement.valueOf(reader.getLocalName().toUpperCase().replace('-', '_'));
                        endElementAction(currentElement);
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        String value = reader.getText().trim();
                        charactersAction(currentElement, value);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.FATAL, "File was not found", e);
            throw new RuntimeException("File was not found", e);
        } catch (XMLStreamException e) {
            throw new LibraryException("Something was wrong", e);
        }  finally {
            try {
                reader.close();
                input.close();
            } catch (XMLStreamException | IOException e) {
                LOGGER.log(Level.WARN, "Problem with closing resources", e);
            }
        }
        return getEditions();
    }

    private void startElementAction() {
        String modifiedName = reader.getLocalName().toUpperCase().replace('-', '_');
        currentElement = EditionElement.valueOf(modifiedName);
        switch (currentElement) {
            case BOOK:
            case JOURNAL:
                String idAttribute = reader.getAttributeValue("", ID_ATTRIBUTE);
                String GenreAttribute = reader.getAttributeValue("", GENRE_ATTRIBUTE);
                createEditionAction(currentElement, idAttribute, GenreAttribute);
                break;
            case AUTHOR:
                createAuthorAction();
                break;
        }
    }
}
