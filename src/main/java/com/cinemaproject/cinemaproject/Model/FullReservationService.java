package com.cinemaproject.cinemaproject.Model;

import java.util.List;

public interface FullReservationService {
    public void makeFullReservation(Reservation reservation, List<ReservedSeat> reservedList) throws Exception;
}
