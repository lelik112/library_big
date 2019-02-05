package net.cheltsov.library.dao.impl.jaxb;

import net.cheltsov.library.dao.EditionDao;
import net.cheltsov.library.domain.entity.Edition;
import net.cheltsov.library.exception.LibraryException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class EditionUnmarshaller implements EditionDao{

    private static final Logger LOGGER = LogManager.getRootLogger();

    @Override
    public List<Edition> readEditions() throws LibraryException {
        return readEditions(DEFAULT_FILE_NAME_XML);
    }

    @Override
    public List<Edition> readEditions(String fileName) throws LibraryException {
        if(fileName == null) {
            throw new LibraryException("Argument is null");
        }
        FileReader fr = null;
        try {
            JAXBContext jContext = JAXBContext.newInstance(RootElement.class);
            Unmarshaller unmarshaller = jContext.createUnmarshaller();
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File(FILE_NAME_SCHEMA_XSD));
            unmarshaller.setSchema(schema);
            fr = new FileReader(fileName);
/*
            SAXParserFactory sax = SAXParserFactory.newInstance();
            sax.setNamespaceAware(false);
            XMLReader reader = sax.newSAXParser().getXMLReader();
            Source source = new SAXSource(reader, new InputSource(fr));
*/
            return ((RootElement)unmarshaller.unmarshal(fr)).getEditions();
        } catch (JAXBException e) {
            throw new LibraryException("Problem with unmarshalling", e);
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.FATAL, "File was not found", e);
            throw new RuntimeException("Fie was not found", e);
        } catch (SAXException e) {
            throw new LibraryException("Something was wrong", e);
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                LOGGER.log(Level.WARN, "File was not closed", e);
                //Вот тут я в прошлый раз исключение бросил. Но маршалинг я не сабмитил, а оценку вы мне за это снизили
                //Я на занятии не подошел, как договаривалиь, потому как комп не включился.
            }
        }
    }
}
