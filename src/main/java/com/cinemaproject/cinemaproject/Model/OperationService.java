package com.cinemaproject.cinemaproject.Model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface OperationService {
    List<Film> findFilmByDate(LocalDate dateOfFilm);
    List<Showing> findShowingsByDateAndId(int filmid, LocalDate date);
    List<List<Seat>> findSeatsByCinemaHallId(int cinemaHallId);
    void insertReservation(String clientName, String clientSecondName, String clientMail, String token, int showingId);
    void insertReservedSeats(List<ReservedSeat> rSeatList);
    Showing findShowingById(int id);
    Integer takenSeat(int showingId, int seatId);
    List<Date> findDatesOfShowings();
    Seat findSeatById(int id);
    Reservation findReservationByMailAndToken(String email, String token);
    List<ReservedSeat> findReservedSeatsByToken(String token);
    Film findFilmById(int id);
    void deleteReservation(int id);
}
