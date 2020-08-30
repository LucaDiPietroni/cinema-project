package com.cinemaproject.cinemaproject.model;

/**
 * Klasa rezerwacji.
 * @author Rafał Rybarczyk
 * @version 1.1
 */
public class ReservedSeat implements Comparable<ReservedSeat>{

    public ReservedSeat(int id, String token, int seatId, boolean isReduced) {
        this.id = id;
        this.token = token;
        this.seatId = seatId;
        this.isReduced = isReduced;
    }

    /**
     * Pole identyfikatora zarezerwowanego miejsca.
     */
    private int id;

    /**
     * Pole kodu rezerwacji.
     */
    private String token;

    /**
     * Pole identyfikatora miejsca na sali kinowej.
     */
    private int seatId;

    /**
     * Pole statusu ulgi.
     */
    private boolean isReduced;

    public ReservedSeat(){

    }

    /**
     * Getter pola "id".
     * @author Rafał Rybarczyk
     * @return Identyfikator zarezerwowanego miejsca.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter pola "id".
     * @author Rafał Rybarczyk
     * @param id Nowy identyfikator zarezerwowanego miejsca.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter pola "token".
     * @author Rafał Rybarczyk
     * @return Kod rezerwacji.
     */
    public String getToken() {
        return token;
    }

    /**
     * Setter pola "token".
     * @author Rafał Rybarczyk
     * @param token Nowy kod rezerwacji.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Getter pola "seatId".
     * @author Rafał Rybarczyk
     * @return Identyfikator miejsca na sali kinowej.
     */
    public int getSeatId() {
        return seatId;
    }

    /**
     * Setter pola "seatId".
     * @author Rafał Rybarczyk
     * @param seatId Nowy identyfikator miejsca na sali kinowej.
     */
    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    /**
     * Getter pola "isReduced".
     * @author Rafał Rybarczyk
     * @return Wartość "true" jeżeli miejsce jest objęte zniżką lub "false" w przeciwnym przypadku.
     */
    public boolean isReduced() {
        return isReduced;
    }

    /**
     * Setter pola "reduced".
     * @author Rafał Rybarczyk
     * @param reduced Nowy status ulgi.
     */
    public void setReduced(boolean reduced) {
        isReduced = reduced;
    }

    /**
     * Metoda obsługująca porównywanie obiektów klasy "ReservedSeat"
     * @author Rafał Rybarczyk
     * @param reservedSeat Nowy status ulgi.
     * @return Wartość 0 jeżeli obiekty są takie same; Wartość mniejszą od 0 jeżeli jeden obiekt jest mniejszy od drugiego; Wartość większą niż 0 jeżeli drugi obiekt jest mniejszy od pierwszego.
     */
    @Override
    public int compareTo(ReservedSeat reservedSeat) {
        int result = Integer.compare(this.seatId, reservedSeat.getSeatId());
        return result;
    }
}
