package net.cheltsov.library.domain.entity;

import net.cheltsov.library.domain.Genre;

import javax.xml.bind.annotation.XmlElement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.cheltsov.library.dao.EditionDao.LIBRARY_NAME_SPACE;

public class Journal extends Edition {
    private static final String REGEX = "(,\\s*number\\W?\\s*)(\\d+)";
    private int number;

    public Journal() {
    }

    public Journal(int id, String title, int pageCount, int year, Genre genre, int number) {
        super(id, title, pageCount, year, genre);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @XmlElement(namespace = LIBRARY_NAME_SPACE)
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Journal journal = (Journal) o;

        return number == journal.number;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + number;
        return result;
    }

    @Override
    public String toString() {
        return "Journal{" +
                super.toString() +
                "number: " + number +
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
            number = Integer.parseInt(mat.group(2));
        }
    }
}
