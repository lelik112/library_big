package net.cheltsov.library.dao;


import net.cheltsov.library.domain.entity.Edition;
import net.cheltsov.library.exception.LibraryException;

import java.util.List;

public interface EditionDao {
    String DEFAULT_FILE_NAME_XML = "data/xmldocs/library.xml";
    String TEMP_FILE_NAME = "temp/temp.xml";
    String FILE_NAME_SCHEMA_XSD = "data/xmldocs/librarySchema.xsd";
    String LIBRARY_NAME_SPACE = "http://www.cheltsov.net/library";
    List<Edition> readEditions() throws LibraryException;
    List<Edition> readEditions(String fileName) throws LibraryException;
}
