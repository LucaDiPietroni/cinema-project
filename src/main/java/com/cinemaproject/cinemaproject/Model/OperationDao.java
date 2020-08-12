package com.cinemaproject.cinemaproject.Model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface OperationDao {
    List<Film> findFilmByDate(LocalDate dateOfShowing);
    List<Showing> findShowingsByDateAndId(int filmid, LocalDate date);
    Showing findShowingById(int id);
    List<Seat> findSeatsInLineByCinemaHallId(int cinemaHallId, int line);
    List<Integer> findRowsByCinemaHall(int cinemaHallId);
    void insertReservation(String clientName, String clientSecondName, String clientMail, String token, int showingId);
    void insertReservedSeats(List<ReservedSeat> rSeatList);
    Integer takenSeat(int showingId, int seatId);
    List<Date> findDatesOfShowings();
    Seat findSeatById(int id);
    Reservation findReservationByMailAndToken(String email, String token);
    List<ReservedSeat> findReservedSeatsByToken(String token);
    Film findFilmById(int id);
    void deleteReservation(int id);

}
