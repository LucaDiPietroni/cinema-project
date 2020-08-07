package com.cinemaproject.cinemaproject.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdditionalServiceImpl implements AdditionalService {

    @Autowired
    private ApplicationContext context;

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
        OperationService seatService = context.getBean(OperationService.class);
        Seat leftSeat;
        Seat rightSeat;

        if(!reservedSeats.isEmpty()){
            leftSeat = seatService.findSeatById(reservedSeats.get(0).getSeatId());
            rightSeat = seatService.findSeatById(reservedSeats.get(reservedSeats.size() - 1).getSeatId());
        }else{
            leftSeat = new Seat();
            rightSeat = new Seat();
        }

        Seat selectedSeat = seatService.findSeatById(seatNumber);

        if(reservedSeats.isEmpty()){
            checker = true;
        }else if (leftSeat.getId()==(seatNumber + 1) && leftSeat.getLine()==selectedSeat.getLine()) {
            checker = true;
        } else if (rightSeat.getId() == (seatNumber - 1) && rightSeat.getLine()==selectedSeat.getLine()) {
            checker = true;
        } else {
            checker = false;
        }
        return checker;
    }

    @Override
    public boolean isSeatReservedAlready(List<List<Seat>> seats, int selectedSeatId){
        boolean result = true;

        for(List<Seat> line:seats){
            for(Seat seat:line){
                if (seat.getId() == selectedSeatId && seat.getTaken() == 1) {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }
}
