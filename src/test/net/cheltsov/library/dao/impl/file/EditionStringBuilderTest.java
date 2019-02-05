package net.cheltsov.library.dao.impl.file;

import net.cheltsov.library.domain.entity.Edition;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static net.cheltsov.library.LibraryBeforeTest.editionListExpected;
import static net.cheltsov.library.LibraryBeforeTest.stringListExpected;



public class EditionStringBuilderTest {
    List<Edition> editionListActual;

    EditionFileBuilderManager fm = new EditionFileBuilderManager();

    @Test()
    public void testBuildEditions() throws Exception {
        editionListActual = fm.buildEditions(stringListExpected);
        Assert.assertEquals(editionListActual, editionListExpected);
    }

    @Test()
    public void testBuildEditionsNullArgumentEmptyList() {
        List<Edition> ListActual = fm.buildEditions(null);
        if(ListActual == null) {
            Assert.fail("Method returned null");
        }
        Assert.assertTrue(ListActual.isEmpty(), "list is not empty");
    }

}