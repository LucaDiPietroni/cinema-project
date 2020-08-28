package com.cinemaproject.cinemaproject.service;

import com.cinemaproject.cinemaproject.model.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * Interfejs obsługujący moduł DAO.
 * @author Marcin Pietroń
 * @version 1.1
 */
public interface OperationService {

    /**
     * Metoda pobierająca z bazy danych listę filmów wyświetlanych w kinie danego dnia.
     * @author Marcin Pietroń
     * @param dateOfFilm Data seansu.
     * @throws Exception Jakikolwiek błąd.
     * @return Lista filmów wyświetlanych danego dnia.
     */
    List<Film> findFilmByDate(LocalDate dateOfFilm) throws Exception;

    /**
     * Metoda pobierająca z bazy danych listę seansów danego filmu i odbywających się danego dnia.
     * @author Marcin Pietroń
     * @param filmid Identyfikator filmu.
     * @param date Data seansu.
     * @throws Exception Jakikolwiek błąd.
     * @return Lista seansów danego filmu i odbywających się danego dnia.
     */
    List<Showing> findShowingsByDateAndId(int filmid, LocalDate date) throws Exception;

    /**
     * Metoda pobierająca z bazy danych miejsca na podanej sali kinowej.
     * @author Marcin Pietroń
     * @param cinemaHallId Identyfikator sali kinowej.
     * @throws Exception Jakikolwiek błąd.
     * @return Lista list miejsc w rzędach na określonej sali kinowej.
     */
    List<List<Seat>> findSeatsByCinemaHallId(int cinemaHallId) throws Exception;

    /**
     * Metoda zapisująca do bazy informacje o rezerwacji użytkownika.
     * @author Marcin Pietroń
     * @param clientName Imię klienta.
     * @param clientSecondName Nazwisko klienta.
     * @param clientMail Adres e-mail klienta.
     * @param token Kod rezerwacji.
     * @param showingId Identyfikator wybranego seansu.
     * @throws Exception Jakikolwiek błąd.
     */
    void insertReservation(String clientName, String clientSecondName, String clientMail, String token, int showingId) throws Exception;

    /**
     * Metoda zapisująca do bazy listę wybranych przez użytkownika miejsc.
     * @author Marcin Pietroń
     * @param rSeatList Lista wybranych miejsc.
     * @throws Exception Jakikolwiek błąd.
     */
    void insertReservedSeats(List<ReservedSeat> rSeatList) throws Exception;

    /**
     * Metoda pobierająca z bazy danych seans określony przez podany identyfikator.
     * @author Marcin Pietroń
     * @param id Identyfikator seansu.
     * @throws Exception Jakikolwiek błąd.
     * @return Seans określony przez podany identyfikator.
     */
    Showing findShowingById(int id) throws Exception;

    /**
     * Metoda pobierająca z bazy danych identyfikator miejsca zarezerwowanego na konkretny seans.
     * @author Marcin Pietroń
     * @param showingId Identyfikator seansu.
     * @param seatId Identyfikator miejsca.
     * @throws Exception Jakikolwiek błąd.
     * @return Identyfikator zarezerwowanego miejsca.
     */
    Integer takenSeat(int showingId, int seatId) throws Exception;

    /**
     * Metoda pobierająca z bazy danych listę dni, gdy odbywają się jakieś seanse.
     * @author Marcin Pietroń
     * @throws Exception Jakikolwiek błąd.
     * @return Lista dni, gdy odbywają się jakieś seanse.
     */
    List<Date> findDatesOfShowings() throws Exception;

    /**
     * Metoda pobierająca z bazy danych miejsce na sali kinowej określone przez podany identyfikator.
     * @author Marcin Pietroń
     * @param id Identyfikator miejsca na sali kinowej.
     * @throws Exception Jakikolwiek błąd.
     * @return Miejsce na sali kinowej.
     */
    Seat findSeatById(int id) throws Exception;

    /**
     * Metoda pobierająca z bazy danych rezerwację identyfikowaną przez adres e-mail i kod rezerwacji.
     * @author Marcin Pietroń
     * @param email Adres e-mail użytkownika.
     * @param token Kod rezerwacji.
     * @throws Exception Jakikolwiek błąd.
     * @return Rezerwacja dokonana przez konkretnego użytkownika.
     */
    Reservation findReservationByMailAndToken(String email, String token) throws Exception;

    /**
     * Metoda pobierająca z bazy danych listę zarezerwowanych miejsc na sali kinowej.
     * @author Marcin Pietroń
     * @param token Kod rezerwacji.
     * @throws Exception Jakikolwiek błąd.
     * @return Lista zarezerwowanych miejsc na sali kinowej.
     */
    List<ReservedSeat> findReservedSeatsByToken(String token) throws Exception;

    /**
     * Metoda pobierająca z bazy danych film określony przez identyfikator.
     * @author Marcin Pietroń
     * @param id Identyfikator filmu.
     * @throws Exception Jakikolwiek błąd.
     * @return Film.
     */
    Film findFilmById(int id) throws Exception;

    /**
     * Metoda usuwająca z bazy danych rezerwację o podanym identyfikatorze.
     * @author Marcin Pietroń
     * @param id Identyfikator rezerwacji.
     * @throws Exception Jakikolwiek błąd.
     */
    void deleteReservation(int id) throws Exception;

    /**
     * Metoda pobierająca z bazy danych listę wszystkich filmów wyświetlanych w kinie.
     * @author Marcin Pietroń
     * @throws Exception Jakikolwiek błąd.
     * @return Lista wszystkich filmów wyświetlanych w kinie.
     */
    List<Film> findAllFilms() throws Exception;
}