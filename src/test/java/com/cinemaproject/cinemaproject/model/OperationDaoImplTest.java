package com.cinemaproject.cinemaproject.model;

import com.cinemaproject.cinemaproject.dao.OperationDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Klasa testująca OperationDaoImpl
 * @author Rafal Rybarczyk
 * @version 1.0
 */

@SpringBootTest
class OperationDaoImplTest {


    @Autowired
    OperationDao op ;

    LocalDate data = LocalDate.of(2020,9,8);

    @Test
    void findFilmByDateIsNotEmpty() throws Exception {

      List<Film> film = op.findFilmByDate(data);
      assertTrue(!film.isEmpty());

    }



    @Test
    void findShowingsByDateAndIdIsNotEmpty() throws Exception {

        List<Showing> show = op.findShowingsByDateAndId(1,data);
        assertTrue(!show.isEmpty());

    }

    @Test
    void findSeatsInLineByCinemaHallIdIsNotEmpty() throws Exception {

        List<Seat> seat = op.findSeatsInLineByCinemaHallId(1,1);
        assertTrue(!seat.isEmpty());

    }

    @Test
    void findRowsByCinemaHallIsNotEmpty() throws Exception {

        List<Integer> row = op.findRowsByCinemaHall(1);
        assertTrue(!row.isEmpty());

    }


    @Test
    void isShowingIdCorrect() throws Exception {

       Showing show = op.findShowingById(1);
       assertEquals(1,show.getId());

    }

    @Test
    void isSeatTaken() throws Exception {

        int taken = op.takenSeat(1,1);
        assertEquals(1, taken );

    }

    @Test
    void findDatesOfShowingsIsNotEmpty () throws Exception {

        List<Date> date = op.findDatesOfShowings();
        assertTrue(!date.isEmpty());

    }

    @Test
    void hasSeatByIdCorrectNumber() throws Exception {

        Seat seat = op.findSeatById(5);
        assertEquals(5,seat.getNumber());

    }


    @Test
    void findReservedSeatsByTokenIsEmpty() throws Exception {

        List<ReservedSeat> res = op.findReservedSeatsByToken("009P34Z1SB");
        assertTrue(res.isEmpty());

    }

    @Test
    void findFilmByIdIsCorrectTitle() throws Exception {

        Film film = op.findFilmById(1);
        assertEquals("Shrek",film.getTitle());

    }


    @Test
    void findAllFilmsIsNotEmpty() throws Exception {

        List<Film> film = op.findAllFilms();
        assertTrue(!film.isEmpty());

    }
}