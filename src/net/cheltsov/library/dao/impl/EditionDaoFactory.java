package net.cheltsov.library.dao.impl;

import net.cheltsov.library.dao.EditionDao;
import net.cheltsov.library.dao.impl.dom.EditionDOMBuilder;
import net.cheltsov.library.dao.impl.file.EditionFileBuilderManager;
import net.cheltsov.library.dao.impl.jaxb.EditionUnmarshaller;
import net.cheltsov.library.dao.impl.sax.EditionSAXBuilder;
import net.cheltsov.library.dao.impl.stax.EditionStAXBuilder;

public class EditionDaoFactory {
    private EditionDaoFactory() {
    }
    public static EditionDao getEditionDao (EditionDaoType type) {
        switch (type) {
            case FILE:
                return new EditionFileBuilderManager();
            case JAXB:
                return new EditionUnmarshaller();
            case SAX:
                return new EditionSAXBuilder();
            case STAX:
                return new EditionStAXBuilder();
            case DOM:
                return new EditionDOMBuilder();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
