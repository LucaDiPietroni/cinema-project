package com.cinemaproject.cinemaproject.Model;

import java.sql.Date;
import java.sql.Time;

public class Showing implements Comparable<Showing>{
    private int id;
    private int filmid;
    private int cinemahallid;
    private Date dateOfShowing;
    private Time timeOfStart;

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

    public Date getDateOfShowing() {
        return dateOfShowing;
    }

    public void setDateOfShowing(Date dateOfShowing) {
        this.dateOfShowing = dateOfShowing;
    }

    public Time getTimeOfStart() {
        return timeOfStart;
    }

    public void setTimeOfStart(Time timeOfStart) {
        this.timeOfStart = timeOfStart;
    }

    @Override
    public int compareTo(Showing showing) {
        int result = this.timeOfStart.compareTo(showing.timeOfStart);
        return result;
    }
}
