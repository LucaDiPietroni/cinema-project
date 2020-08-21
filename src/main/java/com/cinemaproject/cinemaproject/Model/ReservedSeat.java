package com.cinemaproject.cinemaproject.Model;

/**
 * Klasa rezerwacji.
 * @author Marcin Pietroń
 * @version 1.0
 */
public class ReservedSeat implements Comparable<ReservedSeat>{

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

    private int showingId;

    public ReservedSeat(){

    }

    /**
     * Getter pola "id".
     * @author Marcin Pietroń
     * @return Identyfikator zarezerwowanego miejsca.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter pola "id".
     * @author Marcin Pietroń
     * @param id Nowy identyfikator zarezerwowanego miejsca.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter pola "token".
     * @author Marcin Pietroń
     * @return Kod rezerwacji.
     */
    public String getToken() {
        return token;
    }

    /**
     * Setter pola "token".
     * @author Marcin Pietroń
     * @param token Nowy kod rezerwacji.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Getter pola "seatId".
     * @author Marcin Pietroń
     * @return Identyfikator miejsca na sali kinowej.
     */
    public int getSeatId() {
        return seatId;
    }

    /**
     * Setter pola "seatId".
     * @author Marcin Pietroń
     * @param seatId Nowy identyfikator miejsca na sali kinowej.
     */
    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    /**
     * Getter pola "isReduced".
     * @author Marcin Pietroń
     * @return Wartość "true" jeżeli miejsce jest objęte zniżką lub "false" w przeciwnym przypadku.
     */
    public boolean isReduced() {
        return isReduced;
    }

    /**
     * Setter pola "reduced".
     * @author Marcin Pietroń
     * @param reduced Nowy status ulgi.
     */
    public void setReduced(boolean reduced) {
        isReduced = reduced;
    }

    public int getShowingId() {
        return showingId;
    }

    public void setShowingId(int showingId) {
        this.showingId = showingId;
    }

    /**
     * Metoda obsługująca porównywanie obiektów klasy "ReservedSeat"
     * @author Marcin Pietroń
     * @param reservedSeat Nowy status ulgi.
     * @return Wartość 0 jeżeli obiekty są takie same; Wartość mniejszą od 0 jeżeli jeden obiekt jest mniejszy od drugiego; Wartość większą niż 0 jeżeli drugi obiekt jest mniejszy od pierwszego.
     */
    @Override
    public int compareTo(ReservedSeat reservedSeat) {
        int result = Integer.compare(this.seatId, reservedSeat.getSeatId());
        return result;
    }
}
