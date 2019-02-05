package net.cheltsov.library;

import net.cheltsov.library.domain.Genre;
import net.cheltsov.library.domain.entity.Author;
import net.cheltsov.library.domain.entity.Book;
import net.cheltsov.library.domain.entity.Edition;
import net.cheltsov.library.domain.entity.Journal;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class LibraryBeforeTest {
    public static String TEMP_FILE_NAME = "temp/tempData.txt";

    public static List<Edition> editionListExpected = new ArrayList();
    public static List<String> stringListExpected = new ArrayList();

    @BeforeTest()
    public void init() throws Exception {
        Author Dostoevskiy = new Author("Fedor", "Dostoevskiy");
        Author Bulgakov = new Author("Mihail", "Bulgakov");
        Author Pelevin = new Author("Viktor", "Pelevin");
        Author Lingred = new Author("Astred", "Lingred");
        Author Robin = new Author("Kristofer", "Robin");

        Edition[] e = new Edition[12];
        e[0] = new Book(1, "Idiot", 699, 1999, Genre.SATIRE, Dostoevskiy);
        e[1] = new Book(2, "Master and Margarite", 966, 1979, Genre.DRAMA, Bulgakov);
        e[2] = new Book(36, "Love to three of Tsukeberins", 666, 2015, Genre.FICTION, Pelevin);
        e[3] = new Book(669, "iPhuck 10", 999, 2017, Genre.INDEFINITE, Pelevin);
        e[4] = new Book(99, "Karlson which lives on the roof", 609, 1996, Genre.COMICS, Lingred);
        e[5] = new Book(66, "Vinny Puh", 906, 1993, Genre.HISTORY, Robin);
        e[6] = new Journal(9996, "Cosmopolitan", 90, 1999, Genre.INDEFINITE, 2);
        e[7] = new Journal(9999, "Cosmopolitan", 69, 1999, Genre.INDEFINITE, 3);
        e[8] = new Journal(99936, "Cosmopolitan", 33, 1999, Genre.INDEFINITE, 4);
        e[9] = new Journal(99963, "Hustler", 90, 1996, Genre.EROTIC, 6);
        e[10] = new Journal(99939, "Hustler", 96, 1996, Genre.EROTIC, 9);
        e[11] = new Journal(99993, "Hustler", 33, 1996, Genre.EROTIC, 3);

        String[] s = new String[12];
        s[0] = "Journal{id: 5, title: Hustler, pageCvt: 96, year: 2015, genre: EROTIC, number: 2}";
        s[1] = "Journal{id: 5, title: Hustler, pageCount: 96, year: 2015, genre: ERC, number: 1}";
        s[2] = "Journal{id: 2, title: Rabbits, pageCount: 996, year: 2018, genre: DRAMA, author: Zaitsev Pavel";
        s[3] = "what{id: 5, title: Hustler, pageCount: 96, year: 2015, genre: EROTIC, number: 1}";
        s[4] = "Book{id: -9, title: Hustler, pageCount: 96, year: 2015, genre: EROTIC, number: 1}";
        s[5] = "ddd";
        s[6] = "Book{id: -1, title: iPhuck X, pageCount: 669, year: 2017, genre: RELIGION, author: Pelevin Victor}";
        s[7] = "Book{id: 2, title: Rabbits, pageCount: 996, year: -2018, genre: DRAMA, author: Zaitsev Pavel}";
        s[8] = "1";
        s[9] = "Journal{id: 5, title: Hustler, pageCount: QQ, year: 2015, genre: EROTIC, number: 1}";
        s[10] = "Journal{id: 5, tkjkjnkj 2015, genre: EROTIC, number: 1}";
        s[11] = "6516";

        File file = new File(TEMP_FILE_NAME);
        file.createNewFile();
        FileWriter fw = new FileWriter(file);

        for(int i = 0; i < e.length; i++) {
            editionListExpected.add(e[i]);
            stringListExpected.add(e[i].toString());
            stringListExpected.add(s[i]);
            fw.write(e[i].toString() + System.lineSeparator());
            fw.write(s[i] + System.lineSeparator());
        }
        fw.close();
    }
}
