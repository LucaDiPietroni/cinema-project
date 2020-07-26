package com.cinemaproject.cinemaproject.Model;

import java.time.LocalDate;
import java.util.List;

public interface OperationDao {
    List<Film> findFilmByDate(LocalDate dateOfShowing);
    List<Showing> findShowingsByDateAndId(int filmid, LocalDate date);
    Showing findShowingById(int id);
    List<Seat> findSeatsInLineByCinemaHallId(int cinemaHallId, int line);
    List<Integer> findRowsByCinemaHall(int cinemaHallId);
    void insertReservation(String clientName, String clientMail, int token, int showingId);
    void insertReservedSeats(List<ReservedSeat> rSeatList);
}
