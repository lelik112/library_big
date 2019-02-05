package net.cheltsov.library.dao.impl.file;

import net.cheltsov.library.exception.LibraryException;
import org.testng.annotations.Test;

import java.util.List;

import static net.cheltsov.library.LibraryBeforeTest.TEMP_FILE_NAME;
import static net.cheltsov.library.LibraryBeforeTest.stringListExpected;
import static org.testng.Assert.assertEquals;

public class EditionFileReaderTest {
    List<String> stringListActual;

    EditionFileBuilderManager fm = new EditionFileBuilderManager();

    @Test()
    public void testReadStrings() throws LibraryException {
        stringListActual = fm.readStrings(TEMP_FILE_NAME);
        assertEquals(stringListActual, stringListExpected, "Data was read not correct");
    }

    @Test(expectedExceptions = LibraryException.class, expectedExceptionsMessageRegExp = "Argument is null")
    public void testReadStringNullArgumentException() throws Exception {
        fm.readStrings(null);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testReadStringFileNotFoundException() throws Exception {
        fm.readStrings("There_is_not_a_file");
    }
}