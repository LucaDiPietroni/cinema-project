package com.cinemaproject.cinemaproject.Model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AllSeatsTest {

    @Autowired
    AllSeats allSeats;

    @Autowired
    OperationService seatService;

    @Test
    void getSeatsIsNotEmpty() {

        Seat seat1 = new Seat(1,1,1,20,40,1,1);
        List<Seat> seat = new ArrayList<Seat>() ;
        seat.add(seat1);
        List<List<Seat>> seats = new ArrayList<List<Seat>>() ;
        seats.add(0,seat);

        allSeats.setSeats(seats);
        assertTrue(!allSeats.getSeats().isEmpty());

    }

    @Test
    void setSeatsIsNotEmpty() {

        Seat seat1 = new Seat(1,1,1,20,40,1,1);
        List<Seat> seat = new ArrayList<Seat>() ;
        seat.add(seat1);
        List<List<Seat>> seats = new ArrayList<List<Seat>>() ;
        seats.add(0,seat);

        allSeats.setSeats(seats);
        assertTrue(!seats.isEmpty());

    }

    @Test
    void setAllSeatsAvailable() throws Exception {

        Seat seat1 = new Seat(1,1,1,20,40,1,1);
        List<Seat> seat = new ArrayList<Seat>() ;
        seat.add(seat1);
        List<List<Seat>> seats = new ArrayList<List<Seat>>() ;
        seats.add(0,seat);

        allSeats.setSeats(seats);
        allSeats.setAllSeatsAvailable();

        assertEquals(0, seat1.getTaken());

    }

    @Test
    void setSeatsSelected() throws Exception {

        Seat seat1 = new Seat();
        List<Seat> seat = new ArrayList<Seat>() ;
        seat.add(seat1);
        List<List<Seat>> seats = new ArrayList<List<Seat>>() ;
        seats.add(0,seat);

        allSeats.setSeats(seats);
        allSeats.setSeatsSelected(0);

        assertEquals(2, seat1.getTaken());

    }

    @Test
    void setSeatsNotAvailableToZero() throws Exception {

        Seat seat1 = new Seat();
        List<Seat> seat = new ArrayList<Seat>() ;
        seat.add(seat1);
        List<List<Seat>> seats = new ArrayList<List<Seat>>() ;
        seats.add(0,seat);

        allSeats.setSeats(seats);

        Showing show = new Showing();
        allSeats.setSeatsNotAvailable(show);

        assertEquals(0, seat1.getTaken());

    }

    @Test
    void setSeatsNotAvailableToOne() throws Exception {

        Seat seat1 = new Seat(0,1,1,20,40,1,2);
        List<Seat> seat = new ArrayList<Seat>() ;
        seat.add(seat1);
        List<List<Seat>> seats = new ArrayList<List<Seat>>() ;
        seats.add(0,seat);

        allSeats.setSeats(seats);

        Showing show = new Showing();
        allSeats.setSeatsNotAvailable(show);

     //   assertEquals(1, seat1.getTaken());

    }
}