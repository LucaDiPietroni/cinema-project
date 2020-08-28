package com.cinemaproject.cinemaproject.model;

/**
 * Pomocnicza klasa przechowująca wartość logiczną.
 * Służy przechowaniu wyniku zapytania SQL do bazy danych.
 * @author Marcin Pietroń
 * @version 1.0
 */
public class Exists {

    /**
     * Pole wartości logicznej.
     */
    private boolean exists;

    /**
     * Getter pola "exists".
     * @author Marcin Pietroń
     * @return Wartość logiczna wskazująca na istnienie "true" lub nieistnienie "false".
     */
    public boolean isExists() {
        return exists;
    }

    /**
     * Setter pola "exists".
     * @author Marcin Pietroń
     * @param exists nowa wartość logiczna.
     */
    public void setExists(boolean exists) {
        this.exists = exists;
    }
}
