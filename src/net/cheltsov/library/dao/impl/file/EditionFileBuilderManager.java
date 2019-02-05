package net.cheltsov.library.dao.impl.file;

import net.cheltsov.library.dao.EditionDao;
import net.cheltsov.library.domain.entity.Edition;
import net.cheltsov.library.exception.LibraryException;

import java.util.List;

public class EditionFileBuilderManager implements EditionDao {
    public static final String DEFAULT_FILE_NAME = "data/text_data.txt";

    @Override
    public List<Edition> readEditions() throws LibraryException {
        return readEditions(DEFAULT_FILE_NAME);
    }

    @Override
    public List<Edition> readEditions(String fileName) throws LibraryException {
        List<String> data = EditionFileReader.readStrings(fileName);
        return new EditionStringBuilder().buildEditions(data);
    }

    public List<String> readStrings() throws LibraryException {
        return EditionFileReader.readStrings(DEFAULT_FILE_NAME);
    }

    public List<String> readStrings(String fileName) throws LibraryException {
        return EditionFileReader.readStrings(fileName);
    }

    public List<Edition> buildEditions(List<String> data) {
        return new EditionStringBuilder().buildEditions(data);
    }
}
