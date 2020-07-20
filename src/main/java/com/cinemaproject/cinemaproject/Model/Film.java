package com.cinemaproject.cinemaproject.Model;

public class Film {
    private int id;
    private String title;
    private int yearOfPremiere;
    private String director;
    private String mainRole;
    private String filmGenre;
    private String scenarist;
    private String production;

    public Film(){

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYearOfPremiere() {
        return yearOfPremiere;
    }

    public String getDirector() {
        return director;
    }

    public String getMainRole() {
        return mainRole;
    }

    public String getFilmGenre() {
        return filmGenre;
    }

    public String getScenarist() {
        return scenarist;
    }

    public String getProduction() {
        return production;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYearOfPremiere(int yearOfPremiere) {
        this.yearOfPremiere = yearOfPremiere;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setMainRole(String mainRole) {
        this.mainRole = mainRole;
    }

    public void setFilmGenre(String filmGenre) {
        this.filmGenre = filmGenre;
    }

    public void setScenarist(String scenarist) {
        this.scenarist = scenarist;
    }

    public void setProduction(String production) {
        this.production = production;
    }
}
