package com.cinemaproject.cinemaproject.Controller;

import com.cinemaproject.cinemaproject.Model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Kontroler aplikacji, sterujący działaniami użytkownika oraz wykorzystujący klasy i interfejsy modelu w celu wyświetlenia odpowiedniego widoku.
 * @author Marcin&Rafał
 * @version 1.0
 */
@Controller
public class FirstController {

    /**
     * Metoda nawigująca do strony startowej.
     * @author Marcin&Rafał
     * @return Odnośnik do podstrony startowej.
     */
    @GetMapping("/")
    public String getStart(HttpSession session) {
        SelectedDate selectedDate = new SelectedDate();
        Reservation userReservation = new Reservation();
        Showing chosenShow = new Showing();
        List<ReservedSeat> selectedSeats = new ArrayList<ReservedSeat>();
        AuthorizationData authorizationData = new AuthorizationData();
        Reservation reservationToDelete = new Reservation();

        session.setAttribute("selectedDate", selectedDate);
        session.setAttribute("userReservation", userReservation);
        session.setAttribute("chosenShow", chosenShow);
        session.setAttribute("selectedSeats", selectedSeats);
        session.setAttribute("authorizationData", authorizationData);
        session.setAttribute("reservationToDelete", reservationToDelete);

        return "start";
    }

    @PostMapping("/goToFilms")
    public String goToFilms(){
        return "redirect:/films";
    }

    @PostMapping("/goToCancel")
    public String goToCancel(){
        return "redirect:/cancelAuthorization";
    }

    @PostMapping("goToStart")
    public String goToStart(){
        return "redirect:/";
    }

}
