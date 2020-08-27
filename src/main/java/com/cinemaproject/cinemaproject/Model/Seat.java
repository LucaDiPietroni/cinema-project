package com.cinemaproject.cinemaproject.Model;

/**
 * Klasa miejsca na sali kinowej.
 * @author Marcin Pietroń
 * @version 1.0
 */
public class Seat implements Comparable<Seat>{

    public Seat() {
    }

    public Seat(int id, int number, int cinemahallid, double normalprice, double reducedprice, int line, int taken) {
        this.id = id;
        this.number = number;
        this.cinemahallid = cinemahallid;
        this.normalprice = normalprice;
        this.reducedprice = reducedprice;
        this.line = line;
        this.taken = taken;
    }

    public Seat( int number, int cinemahallid, double normalprice, double reducedprice, int line, int taken) {
        this.number = number;
        this.cinemahallid = cinemahallid;
        this.normalprice = normalprice;
        this.reducedprice = reducedprice;
        this.line = line;
        this.taken = taken;
    }

    /**
     * Pole identyfikatora miejsca na sali kinowej.
     */
    private int id;

    /**
     * Pole numeru miejsca na sali kinowej.
     */
    private int number;

    /**
     * Pole identyfikatora sali kinowej.
     */
    private int cinemahallid;

    /**
     * Pole wartości pełnej ceny za miejsce.
     */
    private double normalprice;

    /**
     * Pole wartości ulgowej ceny za miejsce.
     */
    private double reducedprice;

    /**
     * Pole numeru rzędu.
     */
    private int line;

    /**
     * Pole statusu dostępności miejsca.
     */
    private int taken = 0;



    /**
     * Getter pola "id".
     * @author Marcin Pietroń
     * @return Identyfikator miejsca na sali kinowej.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter pola "id".
     * @author Marcin Pietroń
     * @param id Nowy identyfikator miejsca na sali kinowej.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter pola "number".
     * @author Marcin Pietroń
     * @return Numer miejsca na sali kinowej.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Setter pola "number".
     * @author Marcin Pietroń
     * @param number Nowy numer miejsca na sali kinowej.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Getter pola "cinemahallid".
     * @author Marcin Pietroń
     * @return Identyfikator sali kinowej.
     */
    public int getCinemahallid() {
        return cinemahallid;
    }

    /**
     * Setter pola "cinemahallid".
     * @author Marcin Pietroń
     * @param cinemahallid Nowy identyfikator sali kinowej.
     */
    public void setCinemahallid(int cinemahallid) {
        this.cinemahallid = cinemahallid;
    }

    /**
     * Getter pola "normalprice".
     * @author Marcin Pietroń
     * @return Wartość pełnej ceny za miejsce.
     */
    public double getNormalprice() {
        return normalprice;
    }

    /**
     * Setter pola "normlprice".
     * @author Marcin Pietroń
     * @param normalprice Nowa wartość pełnej ceny za miejsce na sali kinowej.
     */
    public void setNormalprice(double normalprice) {
        this.normalprice = normalprice;
    }

    /**
     * Getter pola "reducedprice".
     * @author Marcin Pietroń
     * @return Wartość ulgowej ceny za miejsce.
     */
    public double getReducedprice() {
        return reducedprice;
    }

    /**
     * Setter pola "reducedprice".
     * @author Marcin Pietroń
     * @param reducedprice Nowa wartość ulgowej ceny za miejsce na sali kinowej.
     */
    public void setReducedprice(double reducedprice) {
        this.reducedprice = reducedprice;
    }

    /**
     * Getter pola "line".
     * @author Marcin Pietroń
     * @return Numer rzędu.
     */
    public int getLine() {
        return line;
    }

    /**
     * Setter pola "line".
     * @author Marcin Pietroń
     * @param line Nowy numer rzędu na sali kinowej.
     */
    public void setLine(int line) {
        this.line = line;
    }

    /**
     * Getter pola "taken".
     * @author Marcin Pietroń
     * @return Status dostępności miejsca na sali kinowej. Domyślnie: 0 - dostępne; 1 - niedostępne; 2 - zarezerwowane.
     */
    public int getTaken() {
        return taken;
    }

    /**
     * Setter pola "taken".
     * @author Marcin Pietroń
     * @param taken Nowy status miejsca na sali kinowej.
     */
    public void setTaken(int taken) {
        this.taken = taken;
    }

    /**
     * Metoda obsługująca porównywanie obiektów klasy "Seat"
     * @author Marcin Pietroń
     * @param seat Nowy status ulgi.
     * @return Wartość 0 jeżeli obiekty są takie same; Wartość mniejszą od 0 jeżeli jeden obiekt jest mniejszy od drugiego; Wartość większą niż 0 jeżeli drugi obiekt jest mniejszy od pierwszego.
     */
    @Override
    public int compareTo(Seat seat) {
        int result = Integer.compare(this.id, seat.getId());
        return result;
    }
}
