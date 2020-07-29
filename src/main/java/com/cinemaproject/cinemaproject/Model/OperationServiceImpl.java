package com.cinemaproject.cinemaproject.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void insertReservation(String clientName, String clientMail, String token, int showingId){
        operationDao.insertReservation(clientName, clientMail, token, showingId);
    }

    @Override
    public void insertReservedSeats(List<ReservedSeat> rSeatList){
        operationDao.insertReservedSeats(rSeatList);
    }

    @Override
    public Showing findShowingById(int id){
        return operationDao.findShowingById(id);
    }
}
