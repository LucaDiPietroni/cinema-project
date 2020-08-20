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

/**
 * Kontroler podstrony z formularzem użytkownika.
 * Steruje on działaniami użytkownika oraz wykorzystujący klasy i interfejsy modelu w celu wyświetlenia odpowiedniego widoku.
 * @author Marcin Pietroń
 * @version 1.0
 */
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
     * Po pobraniu informacji o rezerwacji z obiektu sesji zostają one ustawione jako atrybut modelu.
     * @author Marcin&Rafał
     * @param model obiekt przechowujący atrybuty wyświetlane na podstronie.
     * @param session obiekt sesji przechowujący atrybuty unikalne dla każdego użytkownika.
     * @return Odnośnik do podstrony z formularzem użytkownika.
     */
    @GetMapping("/reservation")
    public String getReservation(Model model, HttpSession session){
        try{
            Reservation userReservation = (Reservation) session.getAttribute("userReservation");
            if(userReservation == null){
                userReservation = new Reservation();
            }

            Showing chosenShow = (Showing) session.getAttribute("chosenShow");
            OperationService operationService = context.getBean(OperationService.class);
            Film userFilm = operationService.findFilmById(chosenShow.getFilmid());
            model.addAttribute("userFilm",userFilm);

            model.addAttribute("userReservation", userReservation);
            return "reservation";
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * Metoda obsługująca dodawanie danych niezbędnych do dokonania rezerwacji.
     * Po pobraniu atrybutów rezerwacji użytkownika oraz wybranyego seansu z sesji ustawiane są nowe wartości tego pierwszego.
     * Generowany jest również kod rezerwacji, a następnie informacje o rezerwacji użytkownika ustawiane są jako atrybut sesji.
     * Kolejno pobierane są z bazy danych informacje o miejscach na konkretnej sali kinowej i sprawdzane jest, które z nich są zajęte.
     * Na koniec informacje o wszystkich miejscach na sali kinowej ustawiane są jako atrybut sesji.
     * @author Marcin Pietroń
     * @param newReservation atrybut modelu dotyczący rezerwacji dokonywanej przez użytkownika.
     * @param request obiekt zawierający informacje o żądaniach klienta. Pozwala pobierać atrybuty z sesji i je do niej dodawać.
     * @return Odnośnik do podstrony z z wyborem miejsc na sali kinowej.
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
