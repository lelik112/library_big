package net.cheltsov.library.dao.impl.sax;

import net.cheltsov.library.domain.entity.Edition;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static net.cheltsov.library.LibraryBeforeTest.editionListExpected;

public class EditionSAXBuilderTest {
    List<Edition> editionListActual;
    @Test
    public void testReadEditions() throws Exception {
        editionListActual = new EditionSAXBuilder().readEditions();
        Assert.assertEquals(editionListActual, editionListExpected, "Pursing was wrong");
    }

}