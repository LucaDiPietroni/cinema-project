package com.cinemaproject.cinemaproject.Model;

/**
 * Pomocnicza klasa przechowująca informacje o liczbie np. miejsc.
 * @author Marcin Pietroń
 * @version 1.0
 */
public class Counter {

    /**
     * Pole licznika.
     */
    private int counter;

    /**
     * Getter pola "counter".
     * @author Marcin Pietroń
     * @return Licznik.
     */
    public int getCounter() {
        return counter;
    }

    /**
     * Setter pola "counter".
     * @author Marcin Pietroń
     * @param counter nowa wartość licznika.
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * Inkrementacja licznika.
     */
    public void increment(){
        this.counter+=1;
    }

    /**
     * Dekrementacja licznika.
     */
    public void decrement(){
        this.counter-=1;
    }
}
