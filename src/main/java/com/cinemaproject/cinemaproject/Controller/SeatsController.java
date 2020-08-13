package com.cinemaproject.cinemaproject.Controller;

import com.cinemaproject.cinemaproject.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class SeatsController {

    /**
     * Wstrzyknięcie interfejsu obsługującego dodatkowe funkcjonalności takie jak: tworzenie tokenów, sprawdzanie sąsiedztwa miejsc na sali kinowej.
     */
    @Autowired
    private AdditionalService additionalService;

    /**
     * Metoda nawigująca do strony z wyborem miejsc na sali kinowej.
     * @author Marcin&Rafał
     * @param model obiekt przechowujący atrybuty: wybrany seans oraz lista zarezerwowanych miejsc.
     * @return Odnośnik do podstrony z wyborem miejsc na sali kinowej.
     */
    @GetMapping("/seats")
    public String getSeats(Model model, HttpServletRequest request) {
        Showing chosenShow = (Showing) request.getSession().getAttribute("selectedShow");
        if(chosenShow == null){
            chosenShow = new Showing();
        }

        AllSeats seats = (AllSeats) request.getSession().getAttribute("seats");
        if(seats == null){
            seats = new AllSeats();
        }

        request.getSession().setAttribute("seats", seats);

        model.addAttribute("selectedShow", chosenShow);
        model.addAttribute("seats", seats);

        List<ReservedSeat> selectedSeats = (List<ReservedSeat>) request.getSession().getAttribute("selectedSeats");
        if(selectedSeats == null){
            selectedSeats = new ArrayList<ReservedSeat>();
        }
        request.getSession().setAttribute("selectedSeats", selectedSeats);
        model.addAttribute("selectedSeats", selectedSeats);

        return "seats";
    }

    /**
     * Metoda zapisująca do listy miejsce na sali konowej zarezerwowane przez użytkownika.
     * Do metody przekazywany jest identyfikator miejsca w kinie. Jeżeli miejsce znajduje się obok miejsca wcześniej wybranego lub jest to pierwsze wybrane miejsce, to jest ono zapisywane do listy wybranych miejsc.
     * Na koniec lista wybranych miejsc kinowych jest sortowana.
     * @author Marcin&Rafał
     * @param selectedSeat obiekt przechowujący identyfikator wybranego przez użytkownika miejsca.
     * @return Odnośnik do podstrony z wyborem miejsc na sali kinowej.
     */
    @PostMapping("/addSeat")
    public String addSeat(@RequestParam(value = "selectedSeat") String selectedSeat, HttpServletRequest request){
        int id = Integer.parseInt(selectedSeat);

        List<ReservedSeat> selectedSeats = (List<ReservedSeat>) request.getSession().getAttribute("selectedSeats");

        AllSeats seats = (AllSeats) request.getSession().getAttribute("seats");


        if(additionalService.isSeatNextTo(selectedSeats, id) && additionalService.isSeatReservedAlready(seats.getSeats(), id)){
            ReservedSeat newReservedSeat = new ReservedSeat();
            newReservedSeat.setSeatId(id);
            selectedSeats.add(newReservedSeat);

            seats.setSeatsSelected(id);

        }
        Collections.sort(selectedSeats);

        request.getSession().setAttribute("selectedSeats", selectedSeats);

        return "redirect:/seats";
    }

    /**
     * Metoda usuwająca wszystkie zarezerwowane miejsca z listy.
     * @author Marcin&Rafał
     * @return Odnośnik do podstrony z wyborem miejsc na sali kinowej.
     */
    @PostMapping("/removeSeats")
    public String removeSeats(HttpServletRequest request){

        List<ReservedSeat> selectedSeats = (List<ReservedSeat>) request.getSession().getAttribute("selectedSeats");
        AllSeats seats = (AllSeats) request.getSession().getAttribute("seats");

        selectedSeats.clear();
        seats.setAllSeatsAvailable();

        request.getSession().setAttribute("selectedSeats", selectedSeats);
        request.getSession().setAttribute("sests", seats);

        return "redirect:/seats";
    }

    /**
     * Metoda ustawijąca domyślną liczbę biletów ulgowych i normalnych.
     * @author Marcin&Rafał
     * @return Odnośnik do podstrony z wyborem liczby biletów ulgowych.
     */
    @PostMapping("/goToReduction")
    public String goToReduction(HttpSession session){
        Counter seatsWithDiscount = new Counter();
        Counter normalSeats = new Counter();
        List<ReservedSeat> selectedSeats = (List<ReservedSeat>) session.getAttribute("selectedSeats");

        seatsWithDiscount.setCounter(0);
        normalSeats.setCounter(selectedSeats.size());

        session.setAttribute("seatsWithDiscount", seatsWithDiscount);
        session.setAttribute("normalSeats", normalSeats);

        return "redirect:/reduction";
    }

}
