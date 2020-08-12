package com.cinemaproject.cinemaproject.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    OperationDao operationDao;

    @Override
    public List<Film> findFilmByDate(LocalDate dateOfFilm) {
        return operationDao.findFilmByDate(dateOfFilm);
    }

    @Override
    public List<Showing> findShowingsByDateAndId(int filmid, LocalDate date){
        return operationDao.findShowingsByDateAndId(filmid, date);
    }

    @Override
    public List<List<Seat>> findSeatsByCinemaHallId(int cinemaHallId){
        List<Integer> lines = operationDao.findRowsByCinemaHall(cinemaHallId);
        List<List<Seat>> result = new ArrayList<List<Seat>>();
        
        for (Integer line:lines) {
            List<Seat> seats = operationDao.findSeatsInLineByCinemaHallId(cinemaHallId, line);
            result.add(seats);
        }

        return result;
    }

    @Override
    public void insertReservation(String clientName, String clientSecondName, String clientMail, String token, int showingId){
        operationDao.insertReservation(clientName, clientSecondName, clientMail, token, showingId);
    }

    @Override
    public void insertReservedSeats(List<ReservedSeat> rSeatList){
        operationDao.insertReservedSeats(rSeatList);
    }

    @Override
    public Showing findShowingById(int id){
        return operationDao.findShowingById(id);
    }

    @Override
    public Integer takenSeat(int showingId, int seatId) {return operationDao.takenSeat(showingId, seatId); }

    @Override
    public List<Date> findDatesOfShowings(){
        return operationDao.findDatesOfShowings();
    }

    @Override
    public Seat findSeatById(int id){
        return operationDao.findSeatById(id);
    }

    @Override
    public Reservation findReservationByMailAndToken(String email, String token){return operationDao.findReservationByMailAndToken(email, token); }

    @Override
    public List<ReservedSeat> findReservedSeatsByToken(String token){return operationDao.findReservedSeatsByToken(token);}

    @Override
    public Film findFilmById(int id){return operationDao.findFilmById(id);}

    @Override
    public void deleteReservation(int id){operationDao.deleteReservation(id);}
}
