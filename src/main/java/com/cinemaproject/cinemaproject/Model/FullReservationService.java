package com.cinemaproject.cinemaproject.Model;

import java.util.List;

/**
 * Interfejs obsługi transakcyjnego zapisywania danych do bazy.
 * @author Marcin Pietroń
 * @version 1.0
 */
public interface FullReservationService {

    /**
     * Metoda zapisująca dane o rezerwacji użytkownika i zarezerwowane przez niego miejsca do bazy danych.
     * @author Marcin Pietroń
     * @param reservation Dane o rezerwacji użytkownika.
     * @param reservedList Miejsca zarezerwowane przez użytkownika.
     * @throws Exception Jakikolwiek błąd.
     */
    void makeFullReservation(Reservation reservation, List<ReservedSeat> reservedList) throws Exception;
}
