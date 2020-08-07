package com.cinemaproject.cinemaproject.Controller;

import com.cinemaproject.cinemaproject.Model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class FirstConfig {

    @Bean
    @SessionScope
    public Showing reservedShow(){
        return new Showing();
    }

    @Bean
    @SessionScope
    public Reservation reservation(){
        return new Reservation();
    }

    @Bean
    @SessionScope
    public List<List<Seat>> seats(){ return new ArrayList<List<Seat>>(); }

    @Bean
    @SessionScope
    public List<ReservedSeat> reservedSeats(){
        return new ArrayList<ReservedSeat>();
    }

    @Bean
    @SessionScope
    public List<Integer> resSeatIds(){
        return new ArrayList<Integer>();
    }

    @Bean
    @SessionScope
    public Counter seatsWithDiscount(){
        return new Counter();
    }

    @Bean
    @SessionScope
    public Counter normalSeats(){
        return new Counter();
    }

    @Bean
    @SessionScope
    public SelectedDate selectedDate(){
        return new SelectedDate();
    }

}