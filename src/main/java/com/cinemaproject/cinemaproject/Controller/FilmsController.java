package com.cinemaproject.cinemaproject.Controller;

import com.cinemaproject.cinemaproject.Model.Film;
import com.cinemaproject.cinemaproject.Model.OperationService;
import com.cinemaproject.cinemaproject.Model.SelectedDate;
import com.cinemaproject.cinemaproject.Model.Showing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

/**
 * Kontroler podstrony z wyborem filmów.
 * Steruje on działaniami użytkownika oraz wykorzystujący klasy i interfejsy modelu w celu wyświetlenia odpowiedniego widoku.
 * @author Marcin Pietroń
 * @version 1.0
 */
@Controller
public class FilmsController {

    /**
     * Wstrzyknięcie interfejsu ApplicationContext.
     * Umożliwia on korzystanie z interfejsów obsługujących pobieranie zasobów z bazy danych oraz zapisywanie w niej nowych rekordów.
     */
    @Autowired
    private ApplicationContext context;

    /**
     * Metoda nawigująca do strony z wyborem filmów.
     * Wpierw z obiektu sesji pobierana jest data wybrana przez użytkownika. Jeżeli takowa nie istnieje tworzony jest nowy obiekt wybranej daty.
     * Następnie z bazy danych pobierana jest lista wszystkich dat, gdy odbywają się seanse oraz lista filmów wyświetlanych w wybranym przez użytkownika dniu.
     * Wszystkie niezbędne informacje ustawiane są jako atrybuty modelu.
     * Kolejno tworzone są atrybuty wyświetlane na stronie HTML i jeden przechowujący informację o wyborze seansu.
     * @author Marcin Pietroń
     * @param model obiekt przechowujący atrybuty wyświetlane na podstronie.
     * @param session obiekt sesji przechowujący atrybuty unikalne dla każdego użytkownika.
     * @return Odnośnik do podstrony z wyborem filmów.
     */
    @GetMapping("/films")
    public String getFilms(Model model, HttpSession session) {
        try{
            SelectedDate selectedDate = (SelectedDate) session.getAttribute("selectedDate");
            if(selectedDate == null){
                selectedDate = new SelectedDate();
            }

            OperationService filmService = context.getBean(OperationService.class);

            List<Date> datesOfShowings = filmService.findDatesOfShowings();
            List<Film> filmList = filmService.findFilmByDate(selectedDate.getSelectedDate().toLocalDate());

            model.addAttribute("filmList", filmList);
            model.addAttribute("Service", filmService);
            model.addAttribute("newSelectedDate", selectedDate);
            model.addAttribute("showing", new Showing());
            model.addAttribute("datesOfShowings", datesOfShowings);
            return "films";
        }catch (Exception e){
            return "error";
        }
    }

    /**
     * Metoda obsługująca wybór daty seansu.
     * Po pobraniu atrybutu wybranej daty z obiektu sesji nadaje się mu wartość daty wybranej przez użytkownika i ponownie ustawia jako atrybut sesji.
     * @author Marcin Pietroń
     * @param newSelectedDate atrybut modelu dotyczący wybranej przez użytkownika daty.
     * @param request obiekt zawierający informacje o żądaniach klienta. Pozwala pobierać atrybuty z sesji i je do niej dodawać.
     * @return Odnośnik do podstrony z wyborem filmów.
     */
    @PostMapping("/setDate")
    public String setDate(@ModelAttribute SelectedDate newSelectedDate, HttpServletRequest request){
        try{
            SelectedDate selectedDate = (SelectedDate) request.getSession().getAttribute("selectedDate");
            if(selectedDate == null){
                selectedDate = new SelectedDate();
                request.getSession().setAttribute("selectedDate", selectedDate);
            }
            selectedDate = newSelectedDate;
            request.getSession().setAttribute("selectedDate", selectedDate);

            return "redirect:/films";
        }catch (Exception e){
            return "error";
        }
    }

    /**
     * Metoda obsługująca wybór seansu.
     * Informacje o wybranym seansie pobierane są z bazy danych i ustawiane jako atrybut obiektu sesji.
     * @author Marcin Pietroń
     * @param selectedShow obiekt przechowujący identyfikator wybranego przez użytkownika seansu.
     * @param request obiekt zawierający informacje o żądaniach klienta. Pozwala pobierać atrybuty z sesji i je do niej dodawać.
     * @return Odnośnik do podstrony z formularzem użytkownika.
     */
    @PostMapping(value = "/setShow")
    public String setShow(@RequestParam(value = "selectedShow") String selectedShow, HttpServletRequest request) {
        try{
            int id = Integer.parseInt(selectedShow);
            OperationService operationService = context.getBean(OperationService.class);

            Showing chosenShow = operationService.findShowingById(id);
            request.getSession().setAttribute("chosenShow", chosenShow);

            return "redirect:/reservation";
        }catch (Exception e){
            return "error";
        }
    }
}
