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
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EditionMarshaller {

    private static final Logger LOGGER = LogManager.getRootLogger();

    public void writeEdition(List<Edition> editions) throws LibraryException {
        writeEdition (editions, EditionDao.FILE_NAME_SCHEMA_XSD);
    }

    public void writeEdition(List<Edition> editions, String fileName) throws LibraryException {
        FileWriter fileWriter = null;
        try {
            JAXBContext jContext = JAXBContext.newInstance(RootElement.class);
            Marshaller marshaller = jContext.createMarshaller();
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File(fileName));
            marshaller.setSchema(schema);
            RootElement rootElement = new RootElement(editions);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            File file = new File(EditionDao.TEMP_FILE_NAME);
            file.createNewFile();
            fileWriter = new FileWriter(file);
            marshaller.marshal(rootElement, fileWriter);

        } catch (JAXBException e) {
            throw new LibraryException("Problem with unmarshalling", e);
        } catch (SAXException e) {
            throw new LibraryException("Something was wrong", e);
        } catch (IOException e) {
            throw new LibraryException("File was not created", e);
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                LOGGER.log(Level.WARN, "File was not closed", e);
            }
        }
    }
}
