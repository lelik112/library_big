package net.cheltsov.library.dao.impl.jaxb;

import net.cheltsov.library.domain.entity.Edition;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static net.cheltsov.library.LibraryBeforeTest.editionListExpected;

public class EditionUnmarhallerTest {
    List<Edition> editionListActual;
    @Test
    public void testReadEditions() throws Exception {
        editionListActual = new EditionUnmarshaller().readEditions();
        Assert.assertEquals(editionListActual, editionListExpected, "Pursing was wrong");
    }

}