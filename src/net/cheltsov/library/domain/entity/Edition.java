package net.cheltsov.library.domain.entity;

import net.cheltsov.library.domain.Genre;

import javax.xml.bind.annotation.*;

import static net.cheltsov.library.dao.EditionDao.LIBRARY_NAME_SPACE;

@XmlType(propOrder = {"title", "pageCount", "year"})
public abstract class Edition {

    private int id;
    private String title;
    private int pageCount;   
    private int year; // INFO: 28.09.2017 Изначально тип был Year. Но у класса Year нет конструктора по умолчанию. Анмаршаллеру это не нравится

    private Genre genre;

    public Edition() {
        genre = Genre.INDEFINITE;
    }

    public Edition(int id, String title, int pageCount, int year, Genre genre) {

        this.id = id;
        this.title = title;
        this.pageCount = pageCount;
        this.year = year;
        this.genre = genre;
    }

    @XmlTransient
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @XmlID
    @XmlAttribute(name = "id")
    public String getIdXml() {
        return "e" + id;
    }
    public void setIdXml(String id) {
        if(id == null || !id.matches(".[\\d]+")) {
            return;
        }
        this.id = Integer.parseInt(id.substring(1));
    }

    @XmlElement(namespace = LIBRARY_NAME_SPACE)
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(namespace = LIBRARY_NAME_SPACE, name = "page-count")
    public int getPageCount() {
        return pageCount;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @XmlElement(namespace = LIBRARY_NAME_SPACE)
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    @XmlAttribute()
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public abstract String getRegex();

    public abstract void setAdditionalFields(String data);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edition edition = (Edition) o;

        if (id != edition.id) return false;
        if (pageCount != edition.pageCount) return false;
        if (year != edition.year) return false;
        if (title != null ? !title.equals(edition.title) : edition.title != null) return false;
        return genre == edition.genre;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + pageCount;
        result = 31 * result + year;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return  "id: " + id +
                ", title: " + title +
                ", pageCount: " + pageCount +
                ", year: " + year +
                ", genre: " + genre +
                ", ";
    }
}
