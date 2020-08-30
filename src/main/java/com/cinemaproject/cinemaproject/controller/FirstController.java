package com.cinemaproject.cinemaproject.controller;

import com.cinemaproject.cinemaproject.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Kontroler podstrony startowej i obsługi nawigacji do innych podstron.
 * Steruje on działaniami użytkownika oraz wykorzystujący klasy modelu w celu wyświetlenia odpowiedniego widoku.
 * @author Rafał Rybarczyk
 * @version 1.0
 */
@Controller
public class FirstController {

    /**
     * Metoda nawigująca do strony startowej.
     * Po utworzeniu potrzebnych obiektów zostają one ustawione jako atrybuty sesji.
     * @author Rafał Rybarczyk
     * @param session obiekt sesji przechowujący atrybuty unikalne dla każdego użytkownika.
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

    /**
     * Metoda nawigująca do podstrony z wyborem filmów.
     * @author Rafał Rybarczyk
     * @return Odnośnik do metody nawigującej do podstrony z wyborem filmów.
     */
    @PostMapping("/goToFilms")
    public String goToFilms(){
        return "redirect:/films";
    }

    /**
     * Metoda nawigująca do podstrony autoryzacji użytkownika, który zarezerwował miejsca na film.
     * @author Rafał Rybarczyk
     * @return Odnośnik do metody nawigującej do podstrony autoryzacji użytkownika, który zarezerwował miejsca na film.
     */
    @PostMapping("/goToCancel")
    public String goToCancel(){
        return "redirect:/cancelAuthorization";
    }

    /**
     * Metoda nawigująca do strony startowej.
     * @author Rafał Rybarczyk
     * @return Odnośnik do metody nawigującej do strony startowej.
     */
    @PostMapping("/goToStart")
    public String goToStart(){
        return "redirect:/";
    }

    /**
     * Metoda nawigująca do podstrony z informacjami kontaktowymi.
     * @author Rafał Rybarczyk
     * @return Odnośnik do podstrony z informacjami kontaktowymi.
     */
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

    /**
     * Metoda nawigująca do podstrony z informacjami kontaktowymi.
     * @author Rafał Rybarczyk
     * @return Odnośnik do podstrony z informacjami kontaktowymi.
     */
    @PostMapping("/goToKontakt")
    public String goToKontakt(){
        return "/kontakt";
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

    /**
     * Metoda nawigująca do podstrony z informacjami o promocjach.
     * @author Rafał Rybarczyk
     * @return Odnośnik do podstrony z informacjami o promocjach.
     */
    @PostMapping("/goToPromocje")
    public String goToPromocje(){
        return "/promocje";
    }

    /**
     * Metoda nawigująca do podstrony z repertuarem kina.
     * @author Rafał Rybarczyk
     * @return Odnośnik do podstrony z repertuarem kina.
     */
    @PostMapping("/goToRepertuar")
    public String goToRepertuar(){

        return "redirect:/repertuar";
    }

}
