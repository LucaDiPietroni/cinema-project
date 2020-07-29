package com.cinemaproject.cinemaproject.Model;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdditionalServiceImpl implements AdditionalService {

    @Override
    public String createToken() {
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        int count = 10;
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    @Override
    public boolean isSeatNextTo(List<ReservedSeat> reservedSeats, Integer seatNumber) {
        boolean checker;
        if(reservedSeats.isEmpty()){
            checker = true;
        }else if (reservedSeats.get(0).getSeatId()==(seatNumber + 1)) {
            checker = true;
        } else if (reservedSeats.get(reservedSeats.size() - 1).getSeatId() == (seatNumber - 1)) {
            checker = true;
        } else {
            checker = false;
        }
        return checker;
    }
}
