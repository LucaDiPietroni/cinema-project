package com.cinemaproject.cinemaproject.Model;

import java.time.LocalDate;
import java.util.List;

public interface OperationService {
    List<Film> findFilmByDate(LocalDate dateOfFilm);
    List<Showing> findShowingsByDateAndId(int filmid, LocalDate date);
    List<List<Seat>> findSeatsByCinemaHallId(int cinemaHallId);
    void insertReservation(String clientName, String clientMail, int token, int showingId);
    void insertReservedSeats(List<ReservedSeat> rSeatList);
    Showing findShowingById(int id);
    //void setCinemaHallId(int cinemaHallId);
}
