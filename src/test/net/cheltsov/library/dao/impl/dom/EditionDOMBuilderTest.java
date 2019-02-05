package net.cheltsov.library.dao.impl.dom;

import net.cheltsov.library.domain.entity.Edition;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static net.cheltsov.library.LibraryBeforeTest.editionListExpected;

public class EditionDOMBuilderTest {
    List<Edition> editionListActual;
    @Test
    public void testReadEditions() throws Exception {
        editionListActual = new EditionDOMBuilder().readEditions();
        Assert.assertEquals(editionListActual, editionListExpected, "Pursing was wrong");
    }

}