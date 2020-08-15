package com.cinemaproject.cinemaproject.Controller;

import com.cinemaproject.cinemaproject.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ReservationController {

    /**
     * Wstrzyknięcie interfejsu ApplicationContext.
     * Umożliwia on korzystanie z interfejsów obsługujących pobieranie zasobów z bazy danych oraz zapisywanie w niej nowych rekordów.
     */
    @Autowired
    private ApplicationContext context;

    /**
     * Wstrzyknięcie interfejsu obsługującego dodatkowe funkcjonalności takie jak: tworzenie tokenów, sprawdzanie sąsiedztwa miejsc na sali kinowej.
     */
    @Autowired
    private AdditionalService additionalService;

    @Resource(name = "seats")
    AllSeats seats;

    /**
     * Metoda nawigująca do strony z formularzem użytkownika.
     * @author Marcin&Rafał
     * @param model obiekt przechowujący atrybut nowej rezerwacji.
     * @return Odnośnik do podstrony z formularzem użytkownika.
     */
    @GetMapping("/reservation")
    public String getReservation(Model model, HttpSession session){
        try{
            Reservation userReservation = (Reservation) session.getAttribute("userReservation");
            if(userReservation == null){
                userReservation = new Reservation();
            }

            model.addAttribute("userReservation", userReservation);
            return "reservation";
        }catch (Exception e){
            return "error";
        }
    }

    /**
     * Metoda zapisująca dane wprowadzone orzez użytkownika w formularzu rezerwacyjnym.
     * Dane wprowadzone przez użytkownika są odbierane, a następnie zapisywane w odpowiednim obiekcie.
     * Obiektowi rezerwacji przypisywany jest wygenerowany losowo token.
     * @author Marcin&Rafał
     * @param newReservation obiekt przechowujący identyfikator wybranego przez użytkownika seansu.
     * @return Odnośnik do podstrony z wyborem miejsc na sali kinowej.
     */
    @PostMapping("/addRes")
    public String addRes(@ModelAttribute Reservation newReservation, HttpServletRequest request) {
        try{
            Reservation userReservation = (Reservation) request.getSession().getAttribute("userReservation");
            if(userReservation == null){
                userReservation = new Reservation();
                request.getSession().setAttribute("userReservation", userReservation);
            }
            Showing chosenShow = (Showing) request.getSession().getAttribute("chosenShow");

            userReservation = newReservation;
            userReservation.setShowingId(chosenShow.getId());
            userReservation.setToken(additionalService.createToken());

            request.getSession().setAttribute("userReservation", userReservation);

            OperationService seatService = context.getBean(OperationService.class);

            seats.setSeats(seatService.findSeatsByCinemaHallId(chosenShow.getCinemahallid()));
            seats.setSeatsNotAvailable(chosenShow);
            request.getSession().setAttribute("seats", seats);

            return "redirect:/seats";
        }catch (Exception e){
            return "error";
        }
    }
}
