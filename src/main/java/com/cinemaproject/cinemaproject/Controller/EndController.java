package com.cinemaproject.cinemaproject.Controller;

import com.cinemaproject.cinemaproject.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Kontroler podstrony podsumowania dokonanej rezerwacji.
 * Steruje on działaniami użytkownika oraz wykorzystujący klasy i interfejsy modelu w celu wyświetlenia odpowiedniego widoku.
 * @author Marcin Pietroń
 * @version 1.0
 */
@Controller
public class EndController {

    /**
     * Wstrzyknięcie interfejsu ApplicationContext.
     * Umożliwia on korzystanie z interfejsów obsługujących pobieranie zasobów z bazy danych oraz zapisywanie w niej nowych rekordów.
     */
    @Autowired
    private ApplicationContext context;

    /**
     * Metoda nawigująca do podstrony z podsumowaniem dokonanej rezerwacji.
     * Po pobraniu wszystkich potrzebnych informacji z obiektu sesji pobierane są z bazy dane o filmie.
     * Następnie obliczana jest cena biletu, pobierane są dodatkowe dane o miejscach na sali i wszystkie dane zostają ustawione jako atrybuty sesji i modelu.
     * @author Marcin Pietroń
     * @param model obiekt przechowujący atrybuty wyświetlane na podstronie.
     * @param session obiekt sesji przechowujący atrybuty unikalne dla każdego użytkownika.
     * @return Odnośnik do podstrony z podsumowaniem dokonanej rezerwacji.
     */
    @GetMapping("/end")
    public String getEnd(Model model, HttpSession session){
        try{
            OperationService operationService = context.getBean(OperationService.class);

            SelectedDate selectedDate = (SelectedDate) session.getAttribute("selectedDate");
            Showing chosenShow = (Showing) session.getAttribute("chosenShow");
            List<ReservedSeat> selectedSeats = (List<ReservedSeat>) session.getAttribute("selectedSeats");
            Counter normalSeats = (Counter) session.getAttribute("normalSeats");
            Counter seatsWithDiscount = (Counter) session.getAttribute("seatsWithDiscount");
            Reservation userReservation = (Reservation) session.getAttribute("userReservation");

            Film selectedFilm = operationService.findFilmById(chosenShow.getFilmid());

            List<Seat> seatsSelectedByUser = new ArrayList<Seat>();
            Double price = normalSeats.getCounter() * 25 + seatsWithDiscount.getCounter() * 12.5;

            for(ReservedSeat seat:selectedSeats){
                Seat oneSeat = operationService.findSeatById(seat.getSeatId());
                seatsSelectedByUser.add(oneSeat);
            }

            Collections.sort(seatsSelectedByUser);

            model.addAttribute("selectedDate", selectedDate);
            model.addAttribute("chosenShow", chosenShow);
            model.addAttribute("selectedFilm", selectedFilm);
            model.addAttribute("userReservation", userReservation);
            model.addAttribute("seatsSelectedByUser", seatsSelectedByUser);
            model.addAttribute("price", price);

            session.setAttribute("selectedDate", null);
            session.setAttribute("userReservation", null);
            session.setAttribute("chosenShow", null);
            session.setAttribute("selectedSeats", null);
            session.setAttribute("normalSeats", null);
            session.setAttribute("seatsWithDiscount", null);

            return "end";
        }catch (Exception e){
            return "error";
        }
    }

}
