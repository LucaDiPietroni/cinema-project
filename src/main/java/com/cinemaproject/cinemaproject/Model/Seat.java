package com.cinemaproject.cinemaproject.Model;

public class Seat implements Comparable<Seat>{
    private int id;
    private int number;
    private int cinemahallid;
    private double normlprice;
    private double reducedprice;
    private int line;
    private int taken = 0;

    public Seat(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCinemahallid() {
        return cinemahallid;
    }

    public void setCinemahallid(int cinemahallid) {
        this.cinemahallid = cinemahallid;
    }

    public double getNormlprice() {
        return normlprice;
    }

    public void setNormlprice(double normlprice) {
        this.normlprice = normlprice;
    }

    public double getReducedprice() {
        return reducedprice;
    }

    public void setReducedprice(double reducedprice) {
        this.reducedprice = reducedprice;
    }

    public int getRow() {
        return line;
    }

    public void setRow(int line) {
        this.line = line;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getTaken() {
        return taken;
    }

    public void setTaken(int taken) {
        this.taken = taken;
    }

    @Override
    public int compareTo(Seat seat) {
        int result = Integer.compare(this.id, seat.getId());
        return result;
    }
}
