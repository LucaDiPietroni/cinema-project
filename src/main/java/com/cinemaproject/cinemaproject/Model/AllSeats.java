package com.cinemaproject.cinemaproject.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class AllSeats {

    @Autowired
    private ApplicationContext context;

    private List<List<Seat>> seats;

    public List<List<Seat>> getSeats() {
        return seats;
    }

    public void setSeats(List<List<Seat>> seats) {
        this.seats = seats;
    }

    public void setAllSeatsAvailable(){
        for (List<Seat> line: seats){
            for (Seat seat:line) {
                seat.setTaken(0);
            }
        }
    }

    public void setSeatsSelected(int selectedSeat){
        for (List<Seat> line: seats){
            for (Seat seat:line) {
                if (seat.getId() == selectedSeat){
                    seat.setTaken(2);
                }
            }
        }
    }

    public void setSeatsNotAvailable(Showing reservedShow){
        OperationService seatService = context.getBean(OperationService.class);

        for (List<Seat> line: seats){
            for (Seat seat:line) {
                if (seatService.takenSeat(reservedShow.getId(), seat.getId()) == null && seat.getTaken() != 2){
                    seat.setTaken(0);
                }
                else if (seat.getTaken() != 2) {
                    seat.setTaken(1);
                }
            }
        }
    }
}
