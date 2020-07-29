package com.cinemaproject.cinemaproject.Model;

public class Counter {
    private int counter;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void increment(){
        this.counter+=1;
    }

    public void decrement(){
        this.counter-=1;
    }
}
