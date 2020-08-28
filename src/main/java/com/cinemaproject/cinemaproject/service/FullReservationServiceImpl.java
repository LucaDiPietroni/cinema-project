package com.cinemaproject.cinemaproject.service;

import com.cinemaproject.cinemaproject.model.Reservation;
import com.cinemaproject.cinemaproject.model.ReservedSeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Serwis implemetujący interfejs obsługi transakcyjnego zapisywania danych do bazy.
 * @author Marcin Pietroń
 * @version 1.0
 */
@Service
public class FullReservationServiceImpl implements FullReservationService {

    /**
     * Wstrzyknięcie interfejsu OperationService.
     * Umożliwia on korzystanie z interfejsów obsługujących pobieranie zasobów z bazy danych oraz zapisywanie w niej nowych rekordów.
     */
    @Autowired
    OperationService operationService;

    /**
     * Implementacja metody zapisującej dane o rezerwacji użytkownika i zarezerwowane przez niego miejsca do bazy danych.
     * @author Marcin Pietroń
     * @param reservation Dane o rezerwacji użytkownika.
     * @param reservedList Miejsca zarezerwowane przez użytkownika.
     * @throws Exception Jakikolwiek błąd.
     */
    @Override
    @Transactional
    public void makeFullReservation(Reservation reservation, List<ReservedSeat> reservedList) throws Exception {
        operationService.insertReservation(reservation.getClientName(),
                reservation.getClientSecondName(),
                reservation.getClientMail(),
                reservation.getToken(),
                reservation.getShowingId());

        operationService.insertReservedSeats(reservedList);
    }
}
