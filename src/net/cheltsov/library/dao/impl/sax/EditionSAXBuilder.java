package net.cheltsov.library.dao.impl.sax;

import net.cheltsov.library.dao.EditionDao;
import net.cheltsov.library.domain.entity.Edition;
import net.cheltsov.library.exception.LibraryException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class EditionSAXBuilder implements EditionDao {

    private static final Logger LOGGER = LogManager.getRootLogger();

    @Override
    public List<Edition> readEditions() throws LibraryException {
        return readEditions(DEFAULT_FILE_NAME_XML);
    }

    @Override
    public List<Edition> readEditions(String fileName) throws LibraryException {
        if (fileName == null) {
            throw new LibraryException("Argument is null");
        }
        EditionHandler editionHandler = new EditionHandler();
        EditionErrorHandler editionErrorHandler = new EditionErrorHandler();
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(editionHandler);
            reader.setErrorHandler(editionErrorHandler);
            reader.parse(fileName);
        } catch (SAXException e) {
            throw new LibraryException("Problem with parsing", e);
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.FATAL, "File was not found", e);
            throw new RuntimeException("File was not found", e);
        } catch (IOException e) {
            throw new LibraryException("Problem with reading from file or closing file", e);
        }
        return editionHandler.getEditions();
    }
}
