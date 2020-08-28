package com.cinemaproject.cinemaproject.model;

import java.sql.Date;
import java.sql.Time;

/**
 * Klasa seansu.
 * @author Marcin Pietroń
 * @version 1.0
 */
public class Showing implements Comparable<Showing>{

    /**
     * Pole identyfikatora seansu.
     */
    private int id;

    /**
     * Pole identyfikatora filmu wyświetlanego w trakcie seansu.
     */
    private int filmid;

    /**
     * Pole sali kinowej, w której odbywa się seans.
     */
    private int cinemahallid;

    /**
     * Pole daty seansu.
     */
    private Date dateOfShowing;

    /**
     * Pole godziny rozpoczęcia seansu.
     */
    private Time timeOfStart;

    public Showing(){ }

    /**
     * Getter pola "id".
     * @author Marcin Pietroń
     * @return Identyfikator seansu.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter pola "id".
     * @author Marcin Pietroń
     * @param id Nowy idnentyfikator seansu.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter pola "filmid".
     * @author Marcin Pietroń
     * @return Identyfikator filmu wyświetlanego w trakcie seansu.
     */
    public int getFilmid() {
        return filmid;
    }

    /**
     * Setter pola "filmid".
     * @author Marcin Pietroń
     * @param filmid Nowy identyfikator filmu wyświetlanego w trakcie seansu.
     */
    public void setFilmid(int filmid) {
        this.filmid = filmid;
    }

    /**
     * Getter pola "cinemahallid".
     * @author Marcin Pietroń
     * @return Identyfikator sali kinowej, w której odbywa się seans.
     */
    public int getCinemahallid() {
        return cinemahallid;
    }

    /**
     * Setter pola "cinemahallid".
     * @author Marcin Pietroń
     * @param cinemahallid Nowy identyfikator sali kinowej, w której odbywa się seans.
     */
    public void setCinemahallid(int cinemahallid) {
        this.cinemahallid = cinemahallid;
    }

    /**
     * Getter pola "dateOfShowing".
     * @author Marcin Pietroń
     * @return Data seansu.
     */
    public Date getDateOfShowing() {
        return dateOfShowing;
    }

    /**
     * Setter pola "dateOfShowing".
     * @author Marcin Pietroń
     * @param dateOfShowing Nowa data seansu.
     */
    public void setDateOfShowing(Date dateOfShowing) {
        this.dateOfShowing = dateOfShowing;
    }

    /**
     * Getter pola "timeOfStart".
     * @author Marcin Pietroń
     * @return Godzina rozpoczęcia seansu.
     */
    public Time getTimeOfStart() {
        return timeOfStart;
    }

    /**
     * Setter pola "timeOfStart".
     * @author Marcin Pietroń
     * @param timeOfStart Nowa godzina rozpoczęcia seansu.
     */
    public void setTimeOfStart(Time timeOfStart) {
        this.timeOfStart = timeOfStart;
    }

    /**
     * Metoda obsługująca porównywanie obiektów klasy "Showing"
     * @author Marcin Pietroń
     * @param showing Nowy status ulgi.
     * @return Wartość 0 jeżeli obiekty są takie same; Wartość mniejszą od 0 jeżeli jeden obiekt jest mniejszy od drugiego; Wartość większą niż 0 jeżeli drugi obiekt jest mniejszy od pierwszego.
     */
    @Override
    public int compareTo(Showing showing) {
        int result = this.timeOfStart.compareTo(showing.timeOfStart);
        return result;
    }
}
