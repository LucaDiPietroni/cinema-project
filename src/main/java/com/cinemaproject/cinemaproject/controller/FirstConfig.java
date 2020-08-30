package com.cinemaproject.cinemaproject.controller;

import com.cinemaproject.cinemaproject.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Klasa konfiguracyjna.
 * Konfiguracja bean'ów w zakresie sesji
 * @author Marcin Pietroń
 * @version 1.0
 */
@Configuration
public class FirstConfig {

    /**
     * Metoda tworząca obiekt wszystkich miejsc w sali kinowej.
     * @author Marcin Pietroń
     * @return Obiekt wszystkich miejsc w sali kinowej.
     */
    @Bean
    @SessionScope
    public AllSeats seats(){ return new AllSeats(); }

}