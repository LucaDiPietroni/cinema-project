package com.cinemaproject.cinemaproject.Controller;

import com.cinemaproject.cinemaproject.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
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
public class SummaryController {

    /**
     * Wstrzyknięcie interfejsu ApplicationContext.
     * Umożliwia on korzystanie z interfejsów obsługujących pobieranie zasobów z bazy danych oraz zapisywanie w niej nowych rekordów.
     */
    @Autowired
    private ApplicationContext context;

    @Autowired
    private FullReservationService fullReservationService;

    /**
     * Metoda nawigująca do podstrony z podsumowaniem dokonanej rezerwacji.
     * Po pobraniu wszystkich potrzebnych informacji z obiektu sesji pobierane są z bazy dane o filmie.
     * Następnie obliczana jest cena biletu, pobierane są dodatkowe dane o miejscach na sali i wszystkie dane zostają ustawione jako atrybuty sesji i modelu.
     * @author Marcin Pietroń
     * @param model obiekt przechowujący atrybuty wyświetlane na podstronie.
     * @param session obiekt sesji przechowujący atrybuty unikalne dla każdego użytkownika.
     * @return Odnośnik do podstrony z podsumowaniem dokonanej rezerwacji.
     */
    @GetMapping("/summary")
    public String getSummary(Model model, HttpSession session){
        try{
            OperationService operationService = context.getBean(OperationService.class);

            SelectedDate selectedDate = (SelectedDate) session.getAttribute("selectedDate");
            Showing chosenShow = (Showing) session.getAttribute("chosenShow");
            List<ReservedSeat> selectedSeats = (List<ReservedSeat>) session.getAttribute("selectedSeats");
            Counter normalSeats = (Counter) session.getAttribute("normalSeats");
            Counter seatsWithDiscount = (Counter) session.getAttribute("seatsWithDiscount");


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
            model.addAttribute("seatsSelectedByUser", seatsSelectedByUser);
            model.addAttribute("price", price);

            return "summary";
        }catch (Exception e){
            return "error";
        }
    }

    /**
     * Metoda obsługująca zapis do bazy wszystkich informacji na temat dokonanej rezerwacji.
     * Po pobraniu wszystkich niezbędnych informacji z sesji, każdemu zarezerwowanemu miejscu przypisywany jest kod rezerwacji.
     * Następnie określona przez użytkownika ilość miejsc jest kategoryzowana jako objęte zniżką lub nie.
     * Kolejno wszystkie informacje zostają zapisane w bazie, a atrybuty sesji zostają wyczyszczone.
     * @author Marcin Pietroń
     * @param request obiekt zawierający informacje o żądaniach klienta. Pozwala pobierać atrybuty z sesji i je do niej dodawać.
     * @return Odnośnik do podstrony z zakończeniem rezerwacji.
     */
    @PostMapping("/confirmReservation")
    public String confirmReservation(HttpServletRequest request){
        try{
            Reservation userReservation = (Reservation) request.getSession().getAttribute("userReservation");
            List<ReservedSeat> selectedSeats = (List<ReservedSeat>) request.getSession().getAttribute("selectedSeats");
            Counter seatsWithDiscount = (Counter) request.getSession().getAttribute("seatsWithDiscount");

            for (ReservedSeat resSeat : selectedSeats) {
                resSeat.setToken(userReservation.getToken());
            }

            for (int i = 0; i < seatsWithDiscount.getCounter(); i++){
                selectedSeats.get(i).setReduced(true);
            }

            fullReservationService.makeFullReservation(userReservation, selectedSeats);

            request.getSession().setAttribute("selectedDate", null);
            request.getSession().setAttribute("chosenShow", null);
            request.getSession().setAttribute("selectedSeats", null);
            request.getSession().setAttribute("normalSeats", null);
            request.getSession().setAttribute("seatsWithDiscount", null);

            return "redirect:/end";
        } catch(Exception e){
            return "error";
        }
    }

}
