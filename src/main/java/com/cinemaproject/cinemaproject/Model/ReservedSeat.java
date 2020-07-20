package com.cinemaproject.cinemaproject.Model;

public class ReservedSeat {
    private int id;
    private int reservationId;
    private int seatId;
    private boolean isReduced;

    public ReservedSeat(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public boolean isReduced() {
        return isReduced;
    }

    public void setReduced(boolean reduced) {
        isReduced = reduced;
    }
}
