package com.cinemaproject.cinemaproject.dao;

import java.io.File;

/**
 * Interfejs DAO obsługujący warunkowe tworzenie bazy danych.
 * @author Marcin Pietroń
 * @version 1.0
 */
public interface BuildDatabaseDao {
    /**
     * Metoda sprawdzająca istnienie odpowiedniego schematu w bazie danych.
     * @author Marcin Pietroń
     * @throws Exception Jakikolwiek błąd.
     * @return Wartość logiczna "true", jeżeli istnieje schemat o właściwej nazwie lub "false" w przeciwnym wypadku.
     */
    boolean checkSchema() throws Exception;

    /**
     * Metoda tworząca odpowiedni schemat w bazie danych.
     * @author Marcin Pietroń
     * @param file Struktura bazy danych zapisana w pliku .sql
     * @throws Exception Jakikolwiek błąd.
     */
    void createDatabase(File file) throws Exception;
}
