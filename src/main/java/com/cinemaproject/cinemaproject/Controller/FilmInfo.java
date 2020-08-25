package com.cinemaproject.cinemaproject.Controller;

import com.cinemaproject.cinemaproject.Model.Film;
import com.cinemaproject.cinemaproject.Model.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
 * @author Marcin Pietroń
 * @version 1.0
 */
@Controller
public class FilmInfo {

    /**
     * Wstrzyknięcie interfejsu ApplicationContext.
     * Umożliwia on korzystanie z interfejsów obsługujących pobieranie zasobów z bazy danych oraz zapisywanie w niej nowych rekordów.
     */
    @Autowired
    private ApplicationContext context;

    /**
     * Metoda nawigująca do podstrony z repertuarem kina.
     * Z bazy pobierana jest lista wszystkich miejsc w kinie.
     * Lista jest następnie zapisywana w modelu.
     * @author Marcin Pietroń
     * @param model obiekt przechowujący atrybuty wyświetlane na podstronie.
     * @return Odnośnik do podstrony z repertuarem kina.
     */
    @GetMapping("/repertuar")
    public String getRepertuar(Model model)
    {
        try {
            OperationService operationService = context.getBean(OperationService.class);
            List<Film> allFilms = operationService.findAllFilms();
            model.addAttribute("allFilms", allFilms);

            return "repertuar";
            } catch (Exception e) {
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

    /**
     * Metoda obsługująca wybór daty seansu.
     * Po pobraniu z bazy informaji o filmie o wskazanym identyfikatorze zapisywane są one w obiekcie sesji.
     * @author Marcin Pietroń
     * @param displayedFilm Identyfikator filmu wybranego przez użytkownika.
     * @param request obiekt zawierający informacje o żądaniach klienta. Pozwala pobierać atrybuty z sesji i je do niej dodawać.
     * @return Odnośnik do podstrony ze szczegółami filmu.
     */
    @PostMapping("/goToFilmDetails")
    public String goToFilmDetails(@RequestParam(value = "displayedFilm") String displayedFilm, HttpServletRequest request) throws Exception {
        int id = Integer.parseInt(displayedFilm);

        OperationService operationService = context.getBean(OperationService.class);
        Film selectedDisplayedFilm = operationService.findFilmById(id);
        request.getSession().setAttribute("selectedDisplayedFilm", selectedDisplayedFilm);

        return "redirect:/filmDetails";
    }

}