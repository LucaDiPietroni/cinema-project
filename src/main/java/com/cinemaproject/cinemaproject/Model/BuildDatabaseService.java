package com.cinemaproject.cinemaproject.Model;

/**
 * Interfejs wykorzystujący moduł DAO obsługujący warunkowe tworzenie bazy danych.
 * @author Marcin Pietroń
 * @version 1.0
 */
public interface BuildDatabaseService {

    /**
     * Metoda tworząca niezbędny schemat w bazie danych w przypadku gdy nie istnieje.
     * @author Marcin Pietroń
     * @throws Exception Jakikolwiek błąd.
     */
    void createDatabase() throws Exception;
}
