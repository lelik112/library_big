package net.cheltsov.library.dao.impl.jaxb;

import net.cheltsov.library.domain.entity.Book;
import net.cheltsov.library.domain.entity.Edition;
import net.cheltsov.library.domain.entity.Journal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import static net.cheltsov.library.dao.EditionDao.LIBRARY_NAME_SPACE;

@XmlRootElement(namespace = LIBRARY_NAME_SPACE, name = "library")
class RootElement {
    @XmlElements({@XmlElement(namespace = LIBRARY_NAME_SPACE, name = "book", type = Book.class),
            @XmlElement(namespace = LIBRARY_NAME_SPACE, name = "journal", type = Journal.class)})
    private List<Edition> editions;

    public RootElement() {
    }

    public RootElement(List<Edition> editions) {
        this.editions = editions;
    }

    List<Edition> getEditions() {
        return editions;
    }
}
