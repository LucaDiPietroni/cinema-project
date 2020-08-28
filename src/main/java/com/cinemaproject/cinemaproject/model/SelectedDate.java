package com.cinemaproject.cinemaproject.model;

import java.sql.Date;

/**
 * Klasa daty wybranej przez użytkownika.
 * @author Marcin Pietroń
 * @version 1.0
 */
public class SelectedDate {

    /**
     * Pole daty wybranej przez użytkownika.
     */
    private Date selectedDate = new Date(2020, 7, 9);

    /**
     * Getter pola "selectedDate".
     * @author Marcin Pietroń
     * @return Data wybrana przez użytkownika.
     */
    public Date getSelectedDate() {
        return selectedDate;
    }

    /**
     * Setter pola "selectedDate".
     * @author Marcin Pietroń
     * @param selectedDate Nowa data wybrana przez użytkownika.
     */
    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }
}
