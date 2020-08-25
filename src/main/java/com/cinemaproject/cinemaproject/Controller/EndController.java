package com.cinemaproject.cinemaproject.Controller;

import com.cinemaproject.cinemaproject.Model.Reservation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * Kontroler podstrony zakończenia rezerwacji.
 * Steruje on działaniami użytkownika oraz wykorzystujący klasy i interfejsy modelu w celu wyświetlenia odpowiedniego widoku.
 * @author Marcin Pietroń
 * @version 1.0
 */
@Controller
public class EndController {


    /**
     * Metoda nawigująca do podstrony kończącej rezerwację.
     * Z obiektu sesji pobierane są informacje o rezerwacji użytkownika w celu wyświetlenia kodu rezerwacji.
     * Następnie zapisywane są one w modelu i sesji.
     * @author Marcin Pietroń
     * @param model obiekt przechowujący atrybuty wyświetlane na podstronie.
     * @param session obiekt sesji przechowujący atrybuty unikalne dla każdego użytkownika.
     * @return Odnośnik do podstrony z zakończeniem rezerwacji.
     */
    @GetMapping("/end")
    private String getEnd(Model model, HttpSession session){
        try{
            Reservation userReservation = (Reservation) session.getAttribute("userReservation");
            model.addAttribute("userReservation", userReservation);

            session.setAttribute("userReservation", null);

            return "end";
        }catch(Exception e){
            return "error";
        }
    }
}
