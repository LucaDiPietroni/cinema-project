package com.cinemaproject.cinemaproject.Controller;

import com.cinemaproject.cinemaproject.Model.CinemaHall;
import com.cinemaproject.cinemaproject.Model.Film;
import com.cinemaproject.cinemaproject.Model.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class FirstController {
    private CinemaHall cinemaHall = new CinemaHall();
//    private int cinemaHall;

    @Autowired
    private ApplicationContext context;

    @GetMapping("/start")
    public String getStart(){
        return "start";
    }

    @GetMapping("/films")
    public String getFilms(Model model){
        OperationService filmService = context.getBean(OperationService.class);
        LocalDate date = LocalDate.of(2020,9,7);
        List<Film> filmList = filmService.findFilmByDate(date);

        model.addAttribute("filmList", filmList);
        model.addAttribute("Service", filmService);
        model.addAttribute("date", date);
        model.addAttribute("cinemaHall", new CinemaHall());
        return "films";
    }

    @GetMapping("/seats")
    public String getSeats(Model model){
        model.addAttribute("cinemaHall", cinemaHall);
        return "seats";
    }

    @PostMapping("/poop")
    public String setCinemaHall(@ModelAttribute CinemaHall cinemaHall){
        System.out.print(cinemaHall.getId());
//        this.cinemaHall = cinemaHall;
//        System.out.println(this.cinemaHall);
//        System.out.println(this.cinemaHall);
        return "redirect:/films";

        //<a href="seats" th:action="${cinemaHall.setId(hour.getCinemahallid())}" th:text="${hour.getTimeofstart()}"/>
    }

}
