package com.cinemaproject.cinemaproject.controller;

import com.cinemaproject.cinemaproject.model.*;
import com.cinemaproject.cinemaproject.service.AdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * Kontroler podstrony z wyborem miejsc na sali kinowej.
 * Steruje on działaniami użytkownika oraz wykorzystujący klasy i interfejsy modelu w celu wyświetlenia odpowiedniego widoku.
 * @author Marcin Pietroń
 * @version 1.1
 */
@Controller
public class SeatsController {

    /**
     * Wstrzyknięcie interfejsu obsługującego dodatkowe funkcjonalności takie jak: tworzenie tokenów, sprawdzanie sąsiedztwa miejsc na sali kinowej.
     */
    @Autowired
    private AdditionalService additionalService;

    /**
     * Metoda nawigująca do strony z wyborem miejsc na sali kinowej.
     * Pobierane są informacje o wybranym seansie, wszystkich miejscach na sali kinowej i zajętych miejscach z obiektu sesji.
     * Ustawiany jest odpowiedni status dostępności zajętych miejsc.
     * Następnie informacje te są ustawiane jako atrybuty obiektów modelu i sesji.
     * @author Marcin Pietroń
     * @param model obiekt przechowujący atrybuty: wybrany seans oraz lista zarezerwowanych miejsc.
     * @param session obiekt sesji przechowujący atrybuty unikalne dla każdego użytkownika.
     * @return Odnośnik do podstrony z wyborem miejsc na sali kinowej.
     */
    @GetMapping("/seats")
    public String getSeats(Model model, HttpSession session) {
        try{
            Showing chosenShow = (Showing) session.getAttribute("chosenShow");
            AllSeats seats = (AllSeats) session.getAttribute("seats");
            List<ReservedSeat> selectedSeats = (List<ReservedSeat>) session.getAttribute("selectedSeats");

            for (ReservedSeat selecedSeat: selectedSeats) {
                seats.setSeatsSelected(selecedSeat.getSeatId());
            }

            session.setAttribute("seats", seats);
            model.addAttribute("chosenShow", chosenShow);
            model.addAttribute("seats", seats);

            session.setAttribute("selectedSeats", selectedSeats);
            model.addAttribute("selectedSeats", selectedSeats);

            return "seats";
        }catch (Exception e){
            return "error";
        }
    }

    /**
     * Metoda obsługująca zapis miejsc na sali konowej zarezerwowanych przez użytkownika.
     * Wpierw pobierane są z sesji informacje o wybranych miejscach i wszystkich miejscach na sali.
     * Jeżeli wybrane przez użytkownika miejsce nie jest zajęte i znajduje się obok któregoś z wcześniej wybranych miejsc to wybrane miejsce dodawane jest do listy.
     * Wybrane miejsca na sali kinowej zyskują status zajętych.
     * Na koniec lista wybranych miejsc kinowych jest sortowana i ustawiana jako atrybut sesji.
     * @author Marcin Pietroń
     * @param selectedSeat obiekt przechowujący identyfikator wybranego przez użytkownika miejsca.
     * @param request obiekt zawierający informacje o żądaniach klienta. Pozwala pobierać atrybuty z sesji i je do niej dodawać.
     * @return Odnośnik do podstrony z wyborem miejsc na sali kinowej.
     */
    @PostMapping("/addSeat")
    public String addSeat(@RequestParam(value = "selectedSeat") String selectedSeat, HttpServletRequest request){
        try{
            int id = Integer.parseInt(selectedSeat);

            List<ReservedSeat> selectedSeats = (List<ReservedSeat>) request.getSession().getAttribute("selectedSeats");
            Showing chosenShow = (Showing) request.getSession().getAttribute("chosenShow");
            AllSeats seats = (AllSeats) request.getSession().getAttribute("seats");


            if(additionalService.isSeatNextTo(selectedSeats, id) && additionalService.isSeatReservedAlready(seats.getSeats(), id)){
                ReservedSeat newReservedSeat = new ReservedSeat();
                newReservedSeat.setSeatId(id);
                newReservedSeat.setShowingId(chosenShow.getId());
                selectedSeats.add(newReservedSeat);
            }
            Collections.sort(selectedSeats);

            request.getSession().setAttribute("selectedSeats", selectedSeats);

            return "redirect:/seats";
        }catch (Exception e){
            return "error";
        }
    }

    /**
     * Metoda obsługująca usuwanie wszystkich zarezerwowane miejsc z listy.
     * Po pobraniu niezbędnych informacji z sesji lista zarezerwowanych miejsc zostaje wyczyszczona, a poszczególne miejsca na sali kinowej tracą status zajętych.
     * Miejsca niedostępnie otrzymują odpowiedni status.
     * Następnie informacje te zostają ustawione jako atrybuty sesji.
     * @author Marcin Pietroń
     * @param request obiekt zawierający informacje o żądaniach klienta. Pozwala pobierać atrybuty z sesji i je do niej dodawać.
     * @return Odnośnik do podstrony z wyborem miejsc na sali kinowej.
     */
    @PostMapping("/removeSeats")
    public String removeSeats(HttpServletRequest request){
        try{
            List<ReservedSeat> selectedSeats = (List<ReservedSeat>) request.getSession().getAttribute("selectedSeats");
            AllSeats seats = (AllSeats) request.getSession().getAttribute("seats");
            Showing chosenShow = (Showing) request.getSession().getAttribute("chosenShow");

            selectedSeats.clear();
            seats.setAllSeatsAvailable();
            seats.setSeatsNotAvailable(chosenShow);

            request.getSession().setAttribute("selectedSeats", selectedSeats);
            request.getSession().setAttribute("sests", seats);

            return "redirect:/seats";
        }catch (Exception e){
            return "error";
        }
    }

    /**
     * Metoda obsługująca ustawienie domyślnej liczby biletów ulgowych i normalnych.
     * Tworzone są odpowiednie obiekty, a następnie pobierane są informacje o wybranych przez użytkownika miejscach.
     * Następnie ustawiana jest domyślna liczba biletów ulgowych i normalnych.
     * Informacje te ustawione zostają jako atrybut sesji.
     * @author Marcin Pietroń
     * @param request obiekt zawierający informacje o żądaniach klienta. Pozwala pobierać atrybuty z sesji i je do niej dodawać.
     * @return Odnośnik do podstrony z wyborem liczby biletów ulgowych.
     */
    @PostMapping("/goToReduction")
    public String goToReduction(HttpServletRequest request){
        try{
            Counter seatsWithDiscount = new Counter();
            Counter normalSeats = new Counter();
            List<ReservedSeat> selectedSeats = (List<ReservedSeat>) request.getSession().getAttribute("selectedSeats");

            seatsWithDiscount.setCounter(0);
            normalSeats.setCounter(selectedSeats.size());

            request.getSession().setAttribute("seatsWithDiscount", seatsWithDiscount);
            request.getSession().setAttribute("normalSeats", normalSeats);

            return "redirect:/reduction";
        }catch (Exception e){
            return "error";
        }
    }

}
