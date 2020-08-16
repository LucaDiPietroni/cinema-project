package com.cinemaproject.cinemaproject.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

/**
 * Serwis implemetujący interfejs obsługujący moduł DAO.
 * @author Marcin Pietroń
 * @version 1.0
 */
@Service
public class OperationServiceImpl implements OperationService {

    /**
     * Wstrzyknięcie interfejsu ApplicationContext.
     * Umożliwia on korzystanie z interfejsów obsługujących pobieranie zasobów z bazy danych oraz zapisywanie w niej nowych rekordów.
     */
    @Autowired
    OperationDao operationDao;

    @Override
    public List<Film> findFilmByDate(LocalDate dateOfFilm) throws Exception {
        return operationDao.findFilmByDate(dateOfFilm);
    }

    @Override
    public List<Showing> findShowingsByDateAndId(int filmid, LocalDate date) throws Exception {
        List<Showing> result = operationDao.findShowingsByDateAndId(filmid, date);
        Collections.sort(result);
        return result;
    }

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

    @Override
    public void insertReservation(String clientName, String clientSecondName, String clientMail, String token, int showingId) throws Exception {
        operationDao.insertReservation(clientName, clientSecondName, clientMail, token, showingId);
    }

    @Override
    public void insertReservedSeats(List<ReservedSeat> rSeatList) throws Exception {
        operationDao.insertReservedSeats(rSeatList);
    }

    @Override
    public Showing findShowingById(int id) throws Exception {
        return operationDao.findShowingById(id);
    }

    @Override
    public Integer takenSeat(int showingId, int seatId) throws Exception {return operationDao.takenSeat(showingId, seatId); }

    @Override
    public List<Date> findDatesOfShowings() throws Exception {
        return operationDao.findDatesOfShowings();
    }

    @Override
    public Seat findSeatById(int id) throws Exception {
        return operationDao.findSeatById(id);
    }

    @Override
    public Reservation findReservationByMailAndToken(String email, String token) throws Exception {return operationDao.findReservationByMailAndToken(email, token); }

    @Override
    public List<ReservedSeat> findReservedSeatsByToken(String token) throws Exception {return operationDao.findReservedSeatsByToken(token);}

    @Override
    public Film findFilmById(int id) throws Exception {return operationDao.findFilmById(id);}

    @Override
    public void deleteReservation(int id) throws Exception {operationDao.deleteReservation(id);}
}
