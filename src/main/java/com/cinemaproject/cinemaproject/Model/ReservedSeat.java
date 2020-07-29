package com.cinemaproject.cinemaproject.Model;

public class ReservedSeat implements Comparable<ReservedSeat>{
    private int id;
    private String token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    @Override
    public int compareTo(ReservedSeat reservedSeat) {
        int result = Integer.compare(this.seatId, reservedSeat.getSeatId());
        return result;
    }
}
