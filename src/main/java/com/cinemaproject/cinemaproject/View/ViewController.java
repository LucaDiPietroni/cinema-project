package com.cinemaproject.cinemaproject.View;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller

public class ViewController  {

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/home", "/js/**", "/css/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//    }

    @RequestMapping("/FirstScreen")
    public String FirstScreen()
    {
        return "FirstScreen";
    }

    @RequestMapping("/FilmListFirst")
    public String FilmListFirst()
    {
        return "FilmListFirst";
    }

    @RequestMapping("/FilmList")
    public String FilmList()
    {
        return "FilmList";
    }

    @RequestMapping("/FilmListLast")
    public String FilmListLast()
    {
        return "FilmListLast";
    }

    @RequestMapping("/FilmSelectHour")
    public String FilmSelectHour()
    {
        return "FilmSelectHour";
    }

    @RequestMapping("/FilmSelectPayment")
    public String FilmSelectPayment()
    {
        return "FilmSelectPayment";
    }

    @RequestMapping("/FilmSelectSeats")
    public String FilmSelectSeats()
    {
        return "FilmSelectSeats";
    }

    @RequestMapping("/FilmSelectTickets")
    public String FilmSelectTickets()
    {
        return "FilmSelectTickets";
    }

}



