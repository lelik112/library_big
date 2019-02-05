package net.cheltsov.library.dao.impl.jaxb;

import net.cheltsov.library.dao.EditionDao;
import net.cheltsov.library.dao.impl.sax.EditionSAXBuilder;
import net.cheltsov.library.domain.entity.Edition;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static net.cheltsov.library.LibraryBeforeTest.editionListExpected;

public class EditionMarshallerTest {
    List<Edition> editionListActual;
    @Test
    public void testWriteEdition() throws Exception {
        new EditionMarshaller().writeEdition(editionListExpected);
        editionListActual = new EditionSAXBuilder().readEditions(EditionDao.TEMP_FILE_NAME);
        Assert.assertEquals(editionListActual, editionListExpected, "Pursing was wrong");
    }

}