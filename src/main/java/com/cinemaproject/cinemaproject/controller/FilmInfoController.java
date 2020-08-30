package com.cinemaproject.cinemaproject.controller;

import com.cinemaproject.cinemaproject.model.Film;
import com.cinemaproject.cinemaproject.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Kontroler podstrony z repertuarem kina.
 * Steruje on działaniami użytkownika oraz wykorzystujący klasy i interfejsy modelu w celu wyświetlenia odpowiedniego widoku.
 * @author Rafał Rybarczyk
 * @version 1.0
 */
@Controller
public class FilmInfoController {

    /**
     * Wstrzyknięcie interfejsu OperationService.
     * Umożliwia on korzystanie z interfejsów obsługujących pobieranie zasobów z bazy danych oraz zapisywanie w niej nowych rekordów.
     */
    @Autowired
    private OperationService operationService;

    /**
     * Metoda nawigująca do podstrony z repertuarem kina.
     * Z bazy pobierana jest lista wszystkich filmów w kinie.
     * Lista jest następnie zapisywana w modelu.
     * @author Rafał Rybarczyk
     * @param model obiekt przechowujący atrybuty wyświetlane na podstronie.
     * @return Odnośnik do podstrony z repertuarem kina.
     */
    @GetMapping("/repertuar")
    public String getRepertuar(Model model)
    {
        try {
            List<Film> allFilms = operationService.findAllFilms();
            model.addAttribute("allFilms", allFilms);

            return "repertuar";
            } catch (Exception e) {
            return "error";
        }
    }

    /**
     * Metoda obsługująca wybór filmu, którego szczegóły chce zobaczyć użytkownik.
     * Po pobraniu z bazy informaji o filmie o wskazanym identyfikatorze zapisywane są one w obiekcie sesji.
     * @author Rafał Rybarczyk
     * @param displayedFilm Identyfikator filmu wybranego przez użytkownika.
     * @param request obiekt zawierający informacje o żądaniach klienta. Pozwala pobierać atrybuty z sesji i je do niej dodawać.
     * @return Odnośnik do podstrony ze szczegółami filmu.
     */
    @PostMapping("/goToFilmDetails")
    public String goToFilmDetails(@RequestParam(value = "displayedFilm") String displayedFilm, HttpServletRequest request) {
        try {
            int id = Integer.parseInt(displayedFilm);

            Film selectedDisplayedFilm = operationService.findFilmById(id);
            request.getSession().setAttribute("selectedDisplayedFilm", selectedDisplayedFilm);

            return "redirect:/filmDetails";
        } catch (Exception e) {
            return "error";
        }


    }

}