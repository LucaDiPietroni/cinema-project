package com.cinemaproject.cinemaproject.controller;

import com.cinemaproject.cinemaproject.model.*;
import com.cinemaproject.cinemaproject.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Kontroler podstrony z informacjami o dokonanej rezerwacji.
 * Steruje on działaniami użytkownika oraz wykorzystujący klasy i interfejsy modelu w celu wyświetlenia odpowiedniego widoku.
 * @author Marcin Pietroń
 * @version 1.1
 */
@Controller
public class CancellationController {

    /**
     * Wstrzyknięcie interfejsu ApplicationContext.
     * Umożliwia on korzystanie z interfejsów obsługujących pobieranie zasobów z bazy danych oraz zapisywanie w niej nowych rekordów.
     */
    @Autowired
    private ApplicationContext context;

    /**
     * Metoda nawigująca do podstrony z informacjami o dokonanej rezerwacji.
     * Po pobraniu informacji o rezerwacji z obiektu sesji pobierane są z bazy dane o seansie, filmie i zarezerwowanych miejscach.
     * Następnie obliczana jest cena biletu i wszystkie dane zostają ustawione jako atrybuty modelu.
     * Atrybuty sesji zostają wyczyszczone.
     * @author Marcin Pietroń
     * @param model obiekt przechowujący atrybuty wyświetlane na podstronie.
     * @param session obiekt sesji przechowujący atrybuty unikalne dla każdego użytkownika.
     * @return Odnośnik do podstrony z informacjami o dokonanej rezerwacji.
     */
    @GetMapping("/cancel")
    public String getCancel(Model model, HttpSession session){
        try{
            Reservation reservationToDelete = (Reservation) session.getAttribute("reservationToDelete");

            OperationService operationService = context.getBean(OperationService.class);
            Showing reservedShowing = operationService.findShowingById(reservationToDelete.getShowingId());
            Film film = operationService.findFilmById(reservedShowing.getFilmid());
            List<ReservedSeat> seatsToDelete = operationService.findReservedSeatsByToken(reservationToDelete.getToken());

            Double price = 0.0;
            List<Seat> reservedSeats = new ArrayList<Seat>();

            for(ReservedSeat oneSeat: seatsToDelete){
                if(oneSeat.isReduced()){
                    price += 12.5;
                }else{
                    price += 25;
                }
                Seat seat = operationService.findSeatById(oneSeat.getSeatId());
                reservedSeats.add(seat);
            }

            model.addAttribute("film", film);
            model.addAttribute("seatsToDelete", seatsToDelete);
            model.addAttribute("price", price);
            model.addAttribute("reservedSeats", reservedSeats);
            model.addAttribute("reservedShowing", reservedShowing);

            session.setAttribute("film", null);
            session.setAttribute("seatsToDelete", null);
            session.setAttribute("price", null);
            session.setAttribute("reservedSeats", null);
            session.setAttribute("reservedShowing", null);

            return "cancel";
        }catch (Exception e){
            return "error";
        }
    }

    /**
     * Metoda obsługująca anulowanie rezerwacji.
     * Po pobraniu danych o rezerwacji z obiektu sesji zostaje ona usunięta z bazy danych
     * @author Marcin Pietroń
     * @param request obiekt zawierający informacje o żądaniach klienta. Pozwala pobierać atrybuty z sesji i je do niej dodawać.
     * @return Odnośnik do podstrony potwierdzającej anulowanie rezerwacji.
     */
    @PostMapping("/deleteReservation")
    public String deleteReservation(HttpServletRequest request){
        try{
            Reservation reservationToDelete = (Reservation) request.getSession().getAttribute("reservationToDelete");

            OperationService operationService = context.getBean(OperationService.class);
            operationService.deleteReservation(reservationToDelete.getId());

            return "endOfCancellation";
        }catch (Exception e){
            return "error";
        }
    }
}
