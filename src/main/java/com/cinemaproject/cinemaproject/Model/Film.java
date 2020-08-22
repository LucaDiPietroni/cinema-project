package com.cinemaproject.cinemaproject.Model;

/**
 * Klasa filmu.
 * @author Marcin Pietroń
 * @version 1.0
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
     * @author Marcin Pietroń
     * @return Identyfikator filmu.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter pola "title".
     * @author Marcin Pietroń
     * @return Tytuł filmu.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter pola "yearOfPremiere".
     * @author Marcin Pietroń
     * @return Rok premiery filmu.
     */
    public int getYearOfPremiere() {
        return yearOfPremiere;
    }

    /**
     * Getter pola "director".
     * @author Marcin Pietroń
     * @return Reżyser filmu.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Getter pola "mainRole".
     * @author Marcin Pietroń
     * @return Aktor odgrywający główną rolę.
     */
    public String getMainRole() {
        return mainRole;
    }

    /**
     * Getter pola "filmGenre".
     * @author Marcin Pietroń
     * @return Gatunek filmu.
     */
    public String getFilmGenre() {
        return filmGenre;
    }

    /**
     * Getter pola "scenarist".
     * @author Marcin Pietroń
     * @return Scenażysta filmu.
     */
    public String getScenarist() {
        return scenarist;
    }

    /**
     * Getter pola "production".
     * @author Marcin Pietroń
     * @return Producent filmu.
     */
    public String getProduction() {
        return production;
    }

    /**
     * Setter pola "id".
     * @author Marcin Pietroń
     * @param id Nowy identyfikator filmu.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter pola "title".
     * @author Marcin Pietroń
     * @param title Nowy tytuł filmu.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Setter pola "yearOfPremiere".
     * @author Marcin Pietroń
     * @param yearOfPremiere Nowy rok premiery filmu.
     */
    public void setYearOfPremiere(int yearOfPremiere) {
        this.yearOfPremiere = yearOfPremiere;
    }

    /**
     * Setter pola "director".
     * @author Marcin Pietroń
     * @param director Nowy reżyser filmu.
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Setter pola "mainRole".
     * @author Marcin Pietroń
     * @param mainRole Nowy aktor odgrywający główną rolę.
     */
    public void setMainRole(String mainRole) {
        this.mainRole = mainRole;
    }

    /**
     * Setter pola "filmGenre".
     * @author Marcin Pietroń
     * @param filmGenre Nowy gatunek filmowy.
     */
    public void setFilmGenre(String filmGenre) {
        this.filmGenre = filmGenre;
    }

    /**
     * Setter pola "scenarist".
     * @author Marcin Pietroń
     * @param scenarist Nowy scenarzysta filmu.
     */
    public void setScenarist(String scenarist) {
        this.scenarist = scenarist;
    }

    /**
     * Setter pola "production".
     * @author Marcin Pietroń
     * @param production Nowy producent filmu.
     */
    public void setProduction(String production) {
        this.production = production;
    }

    /**
     * Getter pola "production".
     * @author Marcin Pietroń
     * @return Link do okładki filmu.
     */
    public String getImage() { return image; }

    /**
     * Setter pola "production".
     * @author Marcin Pietroń
     * @param image Nowy link do okładki filmu.
     */
    public void setImage(String image) { this.image = image; }
}
