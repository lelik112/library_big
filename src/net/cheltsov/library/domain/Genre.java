package net.cheltsov.library.domain;


import javax.xml.bind.annotation.XmlEnumValue;

public enum Genre {

    @XmlEnumValue(value = "indefinite")
    INDEFINITE,
    @XmlEnumValue(value = "fiction")
    FICTION,
    @XmlEnumValue(value = "satire")
    SATIRE,
    @XmlEnumValue(value = "drama")
    DRAMA,
    @XmlEnumValue(value = "action")
    ACTION,
    @XmlEnumValue(value = "mystery")
    MYSTERY,
    @XmlEnumValue(value = "horror")
    HORROR,
    @XmlEnumValue(value = "religion")
    RELIGION,
    @XmlEnumValue(value = "science")
    SCIENCE,
    @XmlEnumValue(value = "history")
    HISTORY,
    @XmlEnumValue(value = "poetry")
    POETRY,
    @XmlEnumValue(value = "erotic")
    EROTIC,
    @XmlEnumValue(value = "comics")
    COMICS,
    @XmlEnumValue(value = "politics")
    POLITICS

}
