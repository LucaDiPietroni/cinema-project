package com.cinemaproject.cinemaproject.Model;

public class Seat {
    private int id;
    private int number;
    private int cinemahallid;
    private double normlprice;
    private double reducedprice;
    private String row;

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

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }
}
