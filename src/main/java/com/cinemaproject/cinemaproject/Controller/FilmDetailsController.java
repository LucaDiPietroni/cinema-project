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

import javax.servlet.http.HttpSession;

@Controller
public class FilmDetailsController {

    /**
     * Wstrzyknięcie interfejsu ApplicationContext.
     * Umożliwia on korzystanie z interfejsów obsługujących pobieranie zasobów z bazy danych oraz zapisywanie w niej nowych rekordów.
     */
    @Autowired
    private ApplicationContext context;

    @GetMapping("/filmDetails")
    public String getFilmDetails(Model model, HttpSession session)
    {
        try{
            Film selectedDisplayedFilm = (Film) session.getAttribute("selectedDisplayedFilm");
            model.addAttribute("selectedDisplayedFilm", selectedDisplayedFilm);

            return "filmDetails";
        }
        catch(Exception e)
        {
            return "error";
        }
    }
}
