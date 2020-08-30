package com.cinemaproject.cinemaproject.controller;

import com.cinemaproject.cinemaproject.model.Film;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * Kontroler podstrony ze szczegółowymi informacjami o filmie.
 * Steruje on działaniami użytkownika oraz wykorzystujący klasy i interfejsy modelu w celu wyświetlenia odpowiedniego widoku.
 * @author Rafał Rybarczyk
 * @version 1.0
 */
@Controller
public class FilmDetailsController {

    /**
     * Metoda nawigująca do podstrony ze szczegółowymi informacjami o filmie.
     * Z obiektu sesji pobierane są informacje o wybranym przez użytkownika filmie.
     * Następnie zapisywane są one w modelu.
     * @author Rafał Rybarczyk
     * @param model obiekt przechowujący atrybuty wyświetlane na podstronie.
     * @param session obiekt sesji przechowujący atrybuty unikalne dla każdego użytkownika.
     * @return Odnośnik do podstrony ze szczegółowymi informacjami o filmie.
     */
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
