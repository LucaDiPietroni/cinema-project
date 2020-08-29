package com.cinemaproject.cinemaproject.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeatTest {

    Seat seat = new Seat(1,99,3,40,20,10,1);
    Seat test = new Seat();

    @Test
    void getId() {
        assertEquals(1, seat.getId());
    }

    @Test
    void setId() {
        test.setId(5);
        assertEquals(5,test.getId());
    }

    @Test
    void getNumber() {
        assertEquals(99, seat.getNumber());

    }

    @Test
    void setNumber() {
        test.setNumber(15);
        assertEquals(15,test.getNumber());
    }

    @Test
    void getCinemahallid() {
        assertEquals(3, seat.getCinemahallid());

    }

    @Test
    void setCinemahallid() {
        test.setCinemahallid(1);
        assertEquals(1,test.getCinemahallid());
    }

    @Test
    void getLine() {
        assertEquals(10, seat.getLine());

    }

    @Test
    void setLine() {
        test.setLine(3);
        assertEquals(3,test.getLine());
    }

    @Test
    void getTaken() {
        assertEquals(1, seat.getTaken());

    }

    @Test
    void setTaken() {
        test.setTaken(1);
        assertEquals(1,test.getTaken());
    }

    @Test
    void compareTo() {
        //test.compareTo();
    }
}