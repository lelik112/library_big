package net.cheltsov.library.dao.impl.sax;

import net.cheltsov.library.dao.EditionAbstractXMLBuilder;
import net.cheltsov.library.domain.EditionElement;
import net.cheltsov.library.domain.entity.Edition;
import org.apache.logging.log4j.Level;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import java.util.List;

public class EditionHandler extends EditionAbstractXMLBuilder implements ContentHandler {

    private EditionElement currentElement;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = EditionElement.valueOf(localName.toUpperCase().replace('-', '_'));
        switch (currentElement) {
            case BOOK:
            case JOURNAL:
                String idAttribute = (attributes.getValue(ID_ATTRIBUTE));
                String GenreAttribute = attributes.getValue(GENRE_ATTRIBUTE);
                createEditionAction(currentElement, idAttribute, GenreAttribute);
                break;
            case AUTHOR:
                createAuthorAction();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        currentElement = EditionElement.valueOf(localName.toUpperCase().replace('-', '_'));
        endElementAction(currentElement);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();
        charactersAction(currentElement, value);
    }

    public List<Edition> getEditions() {
        return super.getEditions();
    }

    @Override
    public void setDocumentLocator(Locator locator) {
    }
    @Override
    public void startDocument() throws SAXException {
        LOGGER.log(Level.INFO, "Parsing started");
    }
    @Override
    public void endDocument() throws SAXException {
        LOGGER.log(Level.INFO, "Parsing ended");
    }
    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
    }
    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
    }
    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
    }
    @Override
    public void processingInstruction(String target, String data) throws SAXException {
    }
    @Override
    public void skippedEntity(String name) throws SAXException {
    }
}
