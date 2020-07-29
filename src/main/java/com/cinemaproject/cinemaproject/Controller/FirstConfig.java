package com.cinemaproject.cinemaproject.Controller;

import com.cinemaproject.cinemaproject.Model.Counter;
import com.cinemaproject.cinemaproject.Model.Reservation;
import com.cinemaproject.cinemaproject.Model.ReservedSeat;
import com.cinemaproject.cinemaproject.Model.Showing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FirstConfig {

    @Bean
    @SessionScope
    public Showing resShow(){
        return new Showing();
    }

    @Bean
    @SessionScope
    public Reservation reservation(){
        return new Reservation();
    }

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
    public Counter reducedSeats(){
        return new Counter();
    }

    @Bean
    @SessionScope
    public Counter normalSeats(){
        return new Counter();
    }

}