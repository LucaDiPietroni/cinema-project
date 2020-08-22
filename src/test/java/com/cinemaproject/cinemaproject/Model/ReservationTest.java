package com.cinemaproject.cinemaproject.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    Reservation reservation = new Reservation(5, "John", "Bean", "bean@gmail.com", "HFDGWERT", 99);
    Reservation test = new Reservation();

    @Test
    void getId() {
        assertEquals(5, reservation.getId());
    }

    @Test
    void setId() {
        test.setId(1);
        assertEquals(1,test.getId());
    }

    @Test
    void getClientName() {
        assertEquals("John", reservation.getClientName());

    }

    @Test
    void setClientName() {
        test.setClientName("Steve");
        assertEquals("Steve",test.getClientName());
    }

    @Test
    void getClientMail() {
        assertEquals("bean@gmail.com", reservation.getClientMail());

    }

    @Test
    void setClientMail() {
        test.setClientMail("Steve@g.com");
        assertEquals("Steve@g.com",test.getClientMail());
    }

    @Test
    void getToken() {
        assertEquals("HFDGWERT", reservation.getToken());

    }

    @Test
    void setToken() {
        test.setToken("FASDYRTE");
        assertEquals("FASDYRTE",test.getToken());
    }

    @Test
    void getShowingId() {
        assertEquals(99, reservation.getShowingId());

    }

    @Test
    void setShowingId() {
        test.setShowingId(77);
        assertEquals(77,test.getShowingId());
    }

    @Test
    void getClientSecondName() {
        assertEquals("Bean", reservation.getClientSecondName());

    }

    @Test
    void setClientSecondName() {
        test.setClientSecondName("Kerr");
        assertEquals("Kerr",test.getClientSecondName());
    }
}