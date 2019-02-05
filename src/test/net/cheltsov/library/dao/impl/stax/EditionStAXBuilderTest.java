package net.cheltsov.library.dao.impl.stax;

import net.cheltsov.library.domain.entity.Edition;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static net.cheltsov.library.LibraryBeforeTest.editionListExpected;

public class EditionStAXBuilderTest {
    List<Edition> editionListActual;
    @Test
    public void testReadEditions() throws Exception {
        editionListActual = new EditionStAXBuilder().readEditions();
        Assert.assertEquals(editionListActual, editionListExpected, "Pursing was wrong");
    }

}