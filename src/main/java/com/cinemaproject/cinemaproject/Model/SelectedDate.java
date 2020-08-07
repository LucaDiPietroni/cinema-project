package com.cinemaproject.cinemaproject.Model;

import java.sql.Date;
import java.time.LocalDate;

public class SelectedDate {
    private Date selectedDate = new Date(2020, 7, 9);

    public Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }
}
