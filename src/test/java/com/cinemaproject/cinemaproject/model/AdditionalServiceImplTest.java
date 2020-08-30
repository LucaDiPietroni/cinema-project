package com.cinemaproject.cinemaproject.model;

import com.cinemaproject.cinemaproject.service.AdditionalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Klasa testująca AdditionalServiceImpl
 * @author Rafal Rybarczyk
 * @version 1.0
 */

@SpringBootTest
class AdditionalServiceImplTest{


    @Autowired
    AdditionalService additionalService;


    @Test
    void isTokenNull() throws Exception {
    assertNotEquals(null, additionalService.createToken());
    }

    @Test
    void isSeatNextTo() throws Exception {

        ReservedSeat reservedSeat1 = new ReservedSeat(1, "ASDFWER", 1, true);

        List<ReservedSeat> reservedSeat = new ArrayList<ReservedSeat>() ;
        reservedSeat.add(reservedSeat1);

        assertFalse( additionalService.isSeatNextTo(reservedSeat,1));
    }

    @Test
    void isSeatReservedAlready() throws Exception {

        Seat seat1 = new Seat(1,1,1,20,40,1,1);
        List<Seat> seat = new ArrayList<Seat>() ;
        seat.add(seat1);

        List<List<Seat>> seats = new ArrayList<List<Seat>>() ;
        seats.add(0,seat);

        assertFalse(additionalService.isSeatReservedAlready( seats ,1));
    }
}