package com.cinemaproject.cinemaproject.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilmTest {

    Film shrek = new Film(1,"Shrek",2001,"Andrew", "Mike", "komedia", "Joe", "USA", "image");
    Film test = new Film();

    @Test
    void getId() {
        assertEquals(1,shrek.getId());
    }

    @Test
    void getTitle() {
        assertEquals("Shrek",shrek.getTitle());
    }

    @Test
    void getYearOfPremiere() {
        assertEquals(2001,shrek.getYearOfPremiere());
    }

    @Test
    void getDirector() {
        assertEquals("Andrew",shrek.getDirector());
    }

    @Test
    void getMainRole() {
        assertEquals("Mike",shrek.getMainRole());

    }

    @Test
    void getFilmGenre() {
        assertEquals("komedia",shrek.getFilmGenre());

    }

    @Test
    void getScenarist() {
        assertEquals("Joe",shrek.getScenarist());

    }

    @Test
    void getProduction() {
        assertEquals("USA",shrek.getProduction());

    }

    @Test
    void getImage() {
        assertEquals("image",shrek.getImage());
    }

    @Test
    void setId() {
        test.setId(2);
        assertEquals(2,test.getId());
    }

    @Test
    void setTitle() {
        test.setTitle("Psy");
        assertEquals("Psy",test.getTitle());
    }

    @Test
    void setYearOfPremiere() {
        test.setYearOfPremiere(2020);
        assertEquals(2020,test.getYearOfPremiere());
    }

    @Test
    void setDirector() {
        test.setDirector("James");
        assertEquals("James",test.getDirector());
    }

    @Test
    void setMainRole() {
        test.setMainRole("Steve");
        assertEquals("Steve",test.getMainRole());
    }

    @Test
    void setFilmGenre() {
        test.setFilmGenre("dramat");
        assertEquals("dramat",test.getFilmGenre());
    }

    @Test
    void setScenarist() {
        test.setScenarist("Josh");
        assertEquals("Josh",test.getScenarist());
    }

    @Test
    void setProduction() {
        test.setProduction("USA");
        assertEquals("USA",test.getProduction());
    }

    @Test
    void setImage() {
        test.setImage("image");
        assertEquals("image",test.getImage());
    }
}