package com.cinemaproject.cinemaproject.Model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface OperationDao {
    List<Film> findFilmByDate(LocalDate dateOfShowing) throws Exception;
    List<Showing> findShowingsByDateAndId(int filmid, LocalDate date) throws Exception;
    Showing findShowingById(int id) throws Exception;
    List<Seat> findSeatsInLineByCinemaHallId(int cinemaHallId, int line) throws Exception;
    List<Integer> findRowsByCinemaHall(int cinemaHallId) throws Exception;
    void insertReservation(String clientName, String clientSecondName, String clientMail, String token, int showingId) throws Exception;
    void insertReservedSeats(List<ReservedSeat> rSeatList) throws Exception;
    Integer takenSeat(int showingId, int seatId) throws Exception;
    List<Date> findDatesOfShowings() throws Exception;
    Seat findSeatById(int id) throws Exception;
    Reservation findReservationByMailAndToken(String email, String token) throws Exception;
    List<ReservedSeat> findReservedSeatsByToken(String token) throws Exception;
    Film findFilmById(int id) throws Exception;
    void deleteReservation(int id) throws Exception;

}
