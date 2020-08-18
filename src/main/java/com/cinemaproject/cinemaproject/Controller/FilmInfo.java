package com.cinemaproject.cinemaproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class FilmInfo {

    @Autowired
    private ApplicationContext context;

    @PostMapping("/goToRepertuar")
    public String goToRepertuar(){
        return "repertuar";
    }

    @PostMapping("/goToPromocje")
    public String goToPromocje(){
        return "promocje";
    }

    @PostMapping("/goToKontakt")
    public String goToKontakt(){
        return "kontakt";
    }

}