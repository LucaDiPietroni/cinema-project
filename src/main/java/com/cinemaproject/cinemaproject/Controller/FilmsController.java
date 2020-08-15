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
     * W pierwszej kolejności pobierane z bazy danych są wszystkie filmy wyświetlane w kinie danego dnia.
     * Kolejno tworzone są atrybuty wyświetlane na stronie HTML i jeden przechowujący informację o wyborze seansu.
     * @author Marcin&Rafał
     * @param model obiekt przechowujący atrybuty wyświetlane na podstronie z wyborem filmów
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
     * Metoda zapisująca wybór seansu przez użykownika.
     * Po wyborze seansu przekazywany jest identyfikator na podstawie którego z bazy pobierane są szczegółowe informacje o wybranym seansie, a następnie zapisywane są one w odpowiednim obiekcie.
     * @author Marcin&Rafał
     * @param selectedShow obiekt przechowujący identyfikator wybranego przez użytkownika seansu.
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
