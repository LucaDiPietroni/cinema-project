package com.cinemaproject.cinemaproject.Model;

import java.util.List;

public interface AdditionalService {
    public String createToken() throws Exception;
    public boolean isSeatNextTo(List<ReservedSeat> reservedSeats, Integer seatNumber) throws Exception;
    public boolean isSeatReservedAlready(List<List<Seat>> seats, int selectedSeatId) throws Exception;
}
