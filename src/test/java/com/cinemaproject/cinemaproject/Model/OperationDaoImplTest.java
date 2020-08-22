package com.cinemaproject.cinemaproject.Model;

import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OperationDaoImplTest {
    DataSource dataSource ;
    LocalDate data ;

    OperationDaoImpl op = new OperationDaoImpl(dataSource);

    @Test
    void findFilmByDate() throws Exception {

       // op.findFilmByDate(data);
    }

    @Test
    void findShowingsByDateAndId() {
    }

    @Test
    void findSeatsInLineByCinemaHallId() throws Exception {

        //op.findSeatsInLineByCinemaHallId(1,1);
        //assertEquals();

    }

    @Test
    void findRowsByCinemaHall() {
    }

    @Test
    void insertReservation() {
    }

    @Test
    void insertReservedSeats() {
    }

    @Test
    void findShowingById() throws Exception {

        op.findShowingById(1);
    }

    @Test
    void takenSeat() {
    }

    @Test
    void findDatesOfShowings() {
    }

    @Test
    void findSeatById() {
    }

    @Test
    void findReservationByMailAndToken() {
    }

    @Test
    void findReservedSeatsByToken() {
    }

    @Test
    void findFilmById() {
    }

    @Test
    void deleteReservation() {
    }

    @Test
    void findAllFilms() {
    }
}