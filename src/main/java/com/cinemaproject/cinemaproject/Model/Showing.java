package com.cinemaproject.cinemaproject.Model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Showing {
    private int id;
    private int filmid;
    private int cinemahallid;
    private Timestamp timeofstart;

    public Showing(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFilmid() {
        return filmid;
    }

    public void setFilmid(int filmid) {
        this.filmid = filmid;
    }

    public int getCinemahallid() {
        return cinemahallid;
    }

    public void setCinemahallid(int cinemahallid) {
        this.cinemahallid = cinemahallid;
    }

    public Timestamp getTimeofstart() {
        return timeofstart;
    }

    public void setTimeofstart(Timestamp timeofstart) {
        this.timeofstart = timeofstart;
    }
}
