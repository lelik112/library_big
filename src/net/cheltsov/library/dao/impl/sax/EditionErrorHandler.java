package net.cheltsov.library.dao.impl.sax;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class EditionErrorHandler implements ErrorHandler {

    private static final Logger LOGGER = LogManager.getRootLogger();
    @Override
    public void warning(SAXParseException exception) throws SAXException {
        LOGGER.log(Level.WARN, getLineAddress(exception) + "-" + exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        LOGGER.log(Level.ERROR, getLineAddress(exception) + "-" + exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        LOGGER.log(Level.FATAL, getLineAddress(exception) + "-" + exception.getMessage());
    }

    private String getLineAddress(SAXParseException exception) {
        return exception.getLineNumber() + " : " + exception.getColumnNumber();
    }
}
