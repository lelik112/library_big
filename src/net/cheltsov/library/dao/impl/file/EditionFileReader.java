package net.cheltsov.library.dao.impl.file;

import net.cheltsov.library.exception.LibraryException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

    class EditionFileReader {

    private static final Logger LOGGER = LogManager.getRootLogger();

    static List<String> readStrings(String fileName) throws LibraryException {
        if(fileName == null) {
            throw new LibraryException("Argument is null");
        }
        List<String> stringList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (br.ready()) {
                stringList.add(br.readLine());
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.FATAL, "File not found", e);
            throw new RuntimeException("Fie not found", e);
        } catch (IOException e) {
            throw new LibraryException("Problem with closing resources", e);
        }
        LOGGER.log(Level.INFO, "Number of " + stringList.size() + " were read and added to stringList");
        return stringList;
    }
}
