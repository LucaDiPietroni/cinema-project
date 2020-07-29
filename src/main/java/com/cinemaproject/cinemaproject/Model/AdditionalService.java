package com.cinemaproject.cinemaproject.Model;

import java.util.List;

public interface AdditionalService {
    public String createToken();
    public boolean isSeatNextTo(List<ReservedSeat> reservedSeats, Integer seatNumber);
}
