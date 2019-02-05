package net.cheltsov.library.domain.entity;

import net.cheltsov.library.domain.Genre;

import javax.xml.bind.annotation.XmlElement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.cheltsov.library.dao.EditionDao.LIBRARY_NAME_SPACE;

public class Book extends Edition {
    private static final String REGEX = "(,\\s*author\\W?\\s*)(\\w+)(\\s+)(\\w+)";

    private Author author;

    public Book() {
    }

    public Book(int id, String title, int pageCount, int year, Genre genre, Author author) {
        super(id, title, pageCount, year, genre);
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    @XmlElement(namespace = LIBRARY_NAME_SPACE)
    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Book book = (Book) o;

        return author != null ? author.equals(book.author) : book.author == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                super.toString() +
                "author: " + author +
                '}';
    }
    @Override
    public String getRegex() {
        return REGEX;
    }

    @Override
    public void setAdditionalFields(String data) {
        Pattern pat = Pattern.compile(REGEX);
        Matcher mat = pat.matcher(data);
        if(mat.find()) {
            author = new Author(mat.group(2), mat.group(4));
        }
    }
}
