package net.cheltsov.library.dao.impl.dom;

import net.cheltsov.library.dao.EditionAbstractXMLBuilder;
import net.cheltsov.library.dao.EditionDao;
import net.cheltsov.library.domain.EditionElement;
import net.cheltsov.library.domain.entity.Edition;
import net.cheltsov.library.exception.LibraryException;
import org.apache.logging.log4j.Level;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class EditionDOMBuilder extends EditionAbstractXMLBuilder implements EditionDao {

    private EditionElement currentElement;

    @Override
    public List<Edition> readEditions() throws LibraryException {
        return readEditions(DEFAULT_FILE_NAME_XML);
    }

    @Override
    public List<Edition> readEditions(String fileName) throws LibraryException {
        DocumentBuilder documentBuilder;
        Document doc;
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = documentBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            parse(nodeList);
        } catch (SAXException e) {
            throw new LibraryException("Problem with parsing", e);
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.FATAL, "File was not found", e);
            throw new RuntimeException("File was not found", e);
        } catch (IOException e) {
            throw new LibraryException("Problem with reading from file or closing file", e);
        } catch (ParserConfigurationException e) {
            throw new LibraryException("Parser configuration error", e);
        }
        return getEditions();
    }

    private void parse(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element currentNodeElement = (Element) nodeList.item(i);
            String modifiedName = currentNodeElement.getTagName().toUpperCase().replace('-', '_');
            currentElement = EditionElement.valueOf(modifiedName);
            EditionElement savedElement;
            switch (currentElement) {
                case BOOK:
                case JOURNAL:
                    String idAttribute = currentNodeElement.getAttribute(ID_ATTRIBUTE);
                    String genreAttribute = currentNodeElement.getAttribute(GENRE_ATTRIBUTE);
                    createEditionAction(currentElement, idAttribute, genreAttribute);
                    savedElement = currentElement;
                    parse(currentNodeElement.getChildNodes());
                    endElementAction(savedElement);
                    break;
                case AUTHOR:
                    createAuthorAction();
                    savedElement = currentElement;
                    parse(currentNodeElement.getChildNodes());
                    endElementAction(savedElement);
                    break;
                default:
                    String value = currentNodeElement.getTextContent().trim();
                    charactersAction(currentElement, value);
            }
        }

    }
}
