package com.cinemaproject.cinemaproject.View;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ViewController {

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

}
