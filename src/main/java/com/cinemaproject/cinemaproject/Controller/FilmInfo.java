package com.cinemaproject.cinemaproject.Controller;

import com.cinemaproject.cinemaproject.Model.Film;
import com.cinemaproject.cinemaproject.Model.OperationService;
import com.cinemaproject.cinemaproject.Model.Showing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class FilmInfo {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/repertuar")
    public String getRepertuar(Model model, HttpSession session)
    {

        try {

            OperationService operationService = context.getBean(OperationService.class);
            List<Film> allFilms = operationService.findAllFilms();
            model.addAttribute("allFilms", allFilms);
            return "repertuar";
            }

        catch(Exception e)
        {
            return "error";
        }
    }

    @PostMapping("/goToRepertuar")
    public String goToRepertuar(){

        return "redirect:/repertuar";
    }

    @GetMapping("/kontakt")
    public String getKontakt()
    {
        try{
            return "kontakt";
        }
        catch(Exception e)
        {
            return "error";
        }
    }

    @PostMapping("/goToKontakt")
    public String goToKontakt(){
        return "redirect:/kontakt";
    }


    @GetMapping("/promocje")
    public String getPromocje()
    {
        try{
            return "promocje";
        }
        catch(Exception e)
        {
            return "error";
        }
    }

    @PostMapping("/goToPromocje")
    public String goToPromocje(){
        return "redirect:/promocje";
    }

    @PostMapping("/goToFilmDetails")
    public String goToFilmDetails(@RequestParam(value = "displayedFilm") String displayedFilm, HttpServletRequest request) throws Exception {
        int id = Integer.parseInt(displayedFilm);

        OperationService operationService = context.getBean(OperationService.class);
        Film selectedDisplayedFilm = operationService.findFilmById(id);
        request.getSession().setAttribute("selectedDisplayedFilm", selectedDisplayedFilm);

        return "redirect:/filmDetails";
    }

}