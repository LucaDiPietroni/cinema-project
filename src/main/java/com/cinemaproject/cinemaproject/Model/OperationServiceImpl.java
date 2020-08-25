package com.cinemaproject.cinemaproject.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

/**
 * Serwis implemetujący interfejs obsługujący moduł DAO.
 * @author Marcin Pietroń
 * @version 1.1
 */
@Service
public class OperationServiceImpl implements OperationService {

    /**
     * Wstrzyknięcie interfejsu ApplicationContext.
     * Umożliwia on korzystanie z interfejsów obsługujących pobieranie zasobów z bazy danych oraz zapisywanie w niej nowych rekordów.
     */
    @Autowired
    OperationDao operationDao;

    /**
     * Implementacja metody pobierającej z bazy danych listę filmów wyświetlanych w kinie danego dnia.
     * @author Marcin Pietroń
     * @param dateOfFilm Data seansu.
     * @throws Exception Jakikolwiek błąd.
     * @return Lista filmów wyświetlanych danego dnia.
     */
    @Override
    public List<Film> findFilmByDate(LocalDate dateOfFilm) throws Exception {
        return operationDao.findFilmByDate(dateOfFilm);
    }

    /**
     * Implementacja metody pobierającej z bazy danych listę seansów danego filmu i odbywających się danego dnia.
     * Po wywołaniu metody z interfejsu DAO wyniki są sortowane.
     * @author Marcin Pietroń
     * @param filmid Identyfikator filmu.
     * @param date Data seansu.
     * @throws Exception Jakikolwiek błąd.
     * @return Lista seansów danego filmu i odbywających się danego dnia.
     */
    @Override
    public List<Showing> findShowingsByDateAndId(int filmid, LocalDate date) throws Exception {
        List<Showing> result = operationDao.findShowingsByDateAndId(filmid, date);
        Collections.sort(result);
        return result;
    }

    /**
     * Implementacja metody pobierającej z bazy danych miejsca na podanej sali kinowej.
     * Wpierw pobierana jest z bazy danych lista numerów rzędów na sali kinowej.
     * Następnie pobierane są listy miejsc w poszczególnych rzędach, a te z kolei dodawane są kolejnej, wcześniej zadeklarowanej listy.
     * @author Marcin Pietroń
     * @param cinemaHallId Identyfikator sali kinowej.
     * @throws Exception Jakikolwiek błąd.
     * @return Lista list miejsc w rzędach na określonej sali kinowej.
     */
    @Override
    public List<List<Seat>> findSeatsByCinemaHallId(int cinemaHallId) throws Exception {
        List<Integer> lines = operationDao.findRowsByCinemaHall(cinemaHallId);
        List<List<Seat>> result = new ArrayList<List<Seat>>();
        
        for (Integer line:lines) {
            List<Seat> seats = operationDao.findSeatsInLineByCinemaHallId(cinemaHallId, line);
            result.add(seats);
        }

        return result;
    }

    /**
     * Implementacja metody zapisującej do bazy informacje o rezerwacji użytkownika.
     * @author Marcin Pietroń
     * @param clientName Imię klienta.
     * @param clientSecondName Nazwisko klienta.
     * @param clientMail Adres e-mail klienta.
     * @param token Kod rezerwacji.
     * @param showingId Identyfikator wybranego seansu.
     * @throws Exception Jakikolwiek błąd.
     */
    @Override
    public void insertReservation(String clientName, String clientSecondName, String clientMail, String token, int showingId) throws Exception {
        operationDao.insertReservation(clientName, clientSecondName, clientMail, token, showingId);
    }

    /**
     * Implementacja metody zapisującej do bazy listę wybranych przez użytkownika miejsc.
     * @author Marcin Pietroń
     * @param rSeatList Lista wybranych miejsc.
     * @throws Exception Jakikolwiek błąd.
     */
    @Override
    public void insertReservedSeats(List<ReservedSeat> rSeatList) throws Exception {
        operationDao.insertReservedSeats(rSeatList);
    }

    /**
     * Implementacja metody pobierającej z bazy danych seans określony przez podany identyfikator.
     * @author Marcin Pietroń
     * @param id Identyfikator seansu.
     * @throws Exception Jakikolwiek błąd.
     * @return Seans określony przez podany identyfikator.
     */
    @Override
    public Showing findShowingById(int id) throws Exception {
        return operationDao.findShowingById(id);
    }

    /**
     * Implementacja metody pobierającej z bazy danych identyfikator miejsca zarezerwowanego na konkretny seans.
     * @author Marcin Pietroń
     * @param showingId Identyfikator seansu.
     * @param seatId Identyfikator miejsca.
     * @throws Exception Jakikolwiek błąd.
     * @return Identyfikator zarezerwowanego miejsca.
     */
    @Override
    public Integer takenSeat(int showingId, int seatId) throws Exception {return operationDao.takenSeat(showingId, seatId); }

    /**
     * Implementacja metody pobierającej z bazy danych listę dni, gdy odbywają się jakieś seanse.
     * @author Marcin Pietroń
     * @throws Exception Jakikolwiek błąd.
     * @return Lista dni, gdy odbywają się jakieś seanse.
     */
    @Override
    public List<Date> findDatesOfShowings() throws Exception {
        return operationDao.findDatesOfShowings();
    }

    /**
     * Implementacja metody pobierającej z bazy danych miejsce na sali kinowej określone przez podany identyfikator.
     * @author Marcin Pietroń
     * @param id Identyfikator miejsca na sali kinowej.
     * @throws Exception Jakikolwiek błąd.
     * @return Miejsce na sali kinowej.
     */
    @Override
    public Seat findSeatById(int id) throws Exception {
        return operationDao.findSeatById(id);
    }

    /**
     * Implementacja metody pobierającej z bazy danych rezerwację identyfikowaną przez adres e-mail i kod rezerwacji.
     * @author Marcin Pietroń
     * @param email Adres e-mail użytkownika.
     * @param token Kod rezerwacji.
     * @throws Exception Jakikolwiek błąd.
     * @return Rezerwacja dokonana przez konkretnego użytkownika.
     */
    @Override
    public Reservation findReservationByMailAndToken(String email, String token) throws Exception {return operationDao.findReservationByMailAndToken(email, token); }

    /**
     * Implementacja metody pobierającej z bazy danych listę zarezerwowanych miejsc na sali kinowej.
     * @author Marcin Pietroń
     * @param token Kod rezerwacji.
     * @throws Exception Jakikolwiek błąd.
     * @return Lista zarezerwowanych miejsc na sali kinowej.
     */
    @Override
    public List<ReservedSeat> findReservedSeatsByToken(String token) throws Exception {return operationDao.findReservedSeatsByToken(token);}

    /**
     * Implementacja metody pobierającej z bazy danych film określony przez identyfikator.
     * @author Marcin Pietroń
     * @param id Identyfikator filmu.
     * @throws Exception Jakikolwiek błąd.
     * @return Film.
     */
    @Override
    public Film findFilmById(int id) throws Exception {return operationDao.findFilmById(id);}

    /**
     * Implementacja metody usuwającej z bazy danych rezerwację o podanym identyfikatorze.
     * @author Marcin Pietroń
     * @param id Identyfikator rezerwacji.
     * @throws Exception Jakikolwiek błąd.
     */
    @Override
    public void deleteReservation(int id) throws Exception {operationDao.deleteReservation(id);}

    /**
     * Implementacja metody pobierającej z bazy danych listę wszystkich filmów wyświetlanych w kinie.
     * @author Marcin Pietroń
     * @throws Exception Jakikolwiek błąd.
     * @return Lista wszystkich filmów wyświetlanych w kinie.
     */
    @Override
    public List<Film> findAllFilms() throws Exception {return operationDao.findAllFilms();}
}
