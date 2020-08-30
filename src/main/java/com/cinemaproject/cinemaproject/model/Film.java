package com.cinemaproject.cinemaproject.model;

/**
 * Klasa filmu.
 * @author Rafał Rybarczyk
 * @version 1.1
 */
public class Film {

    /**
     * Pole identyfikatora filmu.
     */
    private int id;

    /**
     * Pole tytułu filmu.
     */
    private String title;

    /**
     * Pole roku premiery filmu.
     */
    private int yearOfPremiere;

    /**
     * Pole reżysera filmu.
     */
    private String director;

    /**
     * Pole aktora odgrywającego główną rolę.
     */
    private String mainRole;

    /**
     * Pole gatunku filmowego.
     */
    private String filmGenre;

    /**
     * Pole scenażysty filmu.
     */
    private String scenarist;

    /**
     * Pole producenta filmu.
     */
    private String production;

    /**
     * Pole ścieżki do okładki filmu.
     */
    private String image;

    public Film(){

    }

    public Film(int id, String title, int yearOfPremiere, String director, String mainRole, String filmGenre, String scenarist, String production, String image) {
        this.id = id;
        this.title = title;
        this.yearOfPremiere = yearOfPremiere;
        this.director = director;
        this.mainRole = mainRole;
        this.filmGenre = filmGenre;
        this.scenarist = scenarist;
        this.production = production;
        this.image = image;
    }

    /**
     * Getter pola "id".
     * @author Rafał Rybarczyk
     * @return Identyfikator filmu.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter pola "title".
     * @author Rafał Rybarczyk
     * @return Tytuł filmu.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter pola "yearOfPremiere".
     * @author Rafał Rybarczyk
     * @return Rok premiery filmu.
     */
    public int getYearOfPremiere() {
        return yearOfPremiere;
    }

    /**
     * Getter pola "director".
     * @author Rafał Rybarczyk
     * @return Reżyser filmu.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Getter pola "mainRole".
     * @author Rafał Rybarczyk
     * @return Aktor odgrywający główną rolę.
     */
    public String getMainRole() {
        return mainRole;
    }

    /**
     * Getter pola "filmGenre".
     * @author Rafał Rybarczyk
     * @return Gatunek filmu.
     */
    public String getFilmGenre() {
        return filmGenre;
    }

    /**
     * Getter pola "scenarist".
     * @author Rafał Rybarczyk
     * @return Scenażysta filmu.
     */
    public String getScenarist() {
        return scenarist;
    }

    /**
     * Getter pola "production".
     * @author Rafał Rybarczyk
     * @return Producent filmu.
     */
    public String getProduction() {
        return production;
    }

    /**
     * Setter pola "id".
     * @author Rafał Rybarczyk
     * @param id Nowy identyfikator filmu.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter pola "title".
     * @author Rafał Rybarczyk
     * @param title Nowy tytuł filmu.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Setter pola "yearOfPremiere".
     * @author Rafał Rybarczyk
     * @param yearOfPremiere Nowy rok premiery filmu.
     */
    public void setYearOfPremiere(int yearOfPremiere) {
        this.yearOfPremiere = yearOfPremiere;
    }

    /**
     * Setter pola "director".
     * @author Rafał Rybarczyk
     * @param director Nowy reżyser filmu.
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Setter pola "mainRole".
     * @author Rafał Rybarczyk
     * @param mainRole Nowy aktor odgrywający główną rolę.
     */
    public void setMainRole(String mainRole) {
        this.mainRole = mainRole;
    }

    /**
     * Setter pola "filmGenre".
     * @author Rafał Rybarczyk
     * @param filmGenre Nowy gatunek filmowy.
     */
    public void setFilmGenre(String filmGenre) {
        this.filmGenre = filmGenre;
    }

    /**
     * Setter pola "scenarist".
     * @author Rafał Rybarczyk
     * @param scenarist Nowy scenarzysta filmu.
     */
    public void setScenarist(String scenarist) {
        this.scenarist = scenarist;
    }

    /**
     * Setter pola "production".
     * @author Rafał Rybarczyk
     * @param production Nowy producent filmu.
     */
    public void setProduction(String production) {
        this.production = production;
    }

    /**
     * Getter pola "image".
     * @author Rafał Rybarczyk
     * @return Lokalizacja okładki filmu.
     */
    public String getImage() {
        return image;
    }

    /**
     * Setter pola "image".
     * @author Rafał Rybarczyk
     * @param image Nowa lokalizacja okłądki filmu.
     */
    public void setImage(String image) {
        this.image = image;
    }
}
