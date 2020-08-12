package com.cinemaproject.cinemaproject.Controller;

import com.cinemaproject.cinemaproject.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
        Showing sessionSelectedShow = (Showing) request.getSession().getAttribute("sessionSelectedShow");
        if(sessionSelectedShow == null){
            sessionSelectedShow = new Showing();
        }

        AllSeats sessionSeats = (AllSeats) request.getSession().getAttribute("sessionSeats");
        if(sessionSeats == null){
            sessionSeats = new AllSeats();
        }

        request.getSession().setAttribute("sessionSeats", sessionSeats);

        model.addAttribute("reservedShow", sessionSelectedShow);
        model.addAttribute("seats", sessionSeats);

        List<ReservedSeat> sessionSelectedSeats = (List<ReservedSeat>) request.getSession().getAttribute("sessionSelectedSeats");
        if(sessionSelectedSeats == null){
            sessionSelectedSeats = new ArrayList<ReservedSeat>();
        }
        request.getSession().setAttribute("sessionSelectedSeats", sessionSelectedSeats);
        model.addAttribute("reservedSeats", sessionSelectedSeats);

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

        List<ReservedSeat> sessionSelectedSeats = (List<ReservedSeat>) request.getSession().getAttribute("sessionSelectedSeats");

        AllSeats sessionSeats = (AllSeats) request.getSession().getAttribute("sessionSeats");


        if(additionalService.isSeatNextTo(sessionSelectedSeats, id) && additionalService.isSeatReservedAlready(sessionSeats.getSeats(), id)){
            ReservedSeat newReservedSeat = new ReservedSeat();
            newReservedSeat.setSeatId(id);
            sessionSelectedSeats.add(newReservedSeat);

            sessionSeats.setSeatsSelected(id);

        }
        Collections.sort(sessionSelectedSeats);

        request.getSession().setAttribute("sessionSelectedSeats", sessionSelectedSeats);

        return "redirect:/seats";
    }

    /**
     * Metoda usuwająca wszystkie zarezerwowane miejsca z listy.
     * @author Marcin&Rafał
     * @return Odnośnik do podstrony z wyborem miejsc na sali kinowej.
     */
    @PostMapping("/removeSeats")
    public String removeSeats(HttpServletRequest request){

        List<ReservedSeat> sessionSelectedSeats = (List<ReservedSeat>) request.getSession().getAttribute("sessionSelectedSeats");
        AllSeats sessionSeats = (AllSeats) request.getSession().getAttribute("sessionSeats");

        sessionSelectedSeats.clear();
        sessionSeats.setAllSeatsAvailable();

        request.getSession().setAttribute("sessionSelectedSeats", sessionSelectedSeats);
        request.getSession().setAttribute("sessionSeats", sessionSeats);

        return "redirect:/seats";
    }

    /**
     * Metoda ustawijąca domyślną liczbę biletów ulgowych i normalnych.
     * @author Marcin&Rafał
     * @return Odnośnik do podstrony z wyborem liczby biletów ulgowych.
     */
    @PostMapping("/goToReduction")
    public String goToReduction(HttpSession session){
        Counter sessionSeatsWithDiscount = new Counter();
        Counter sessionNormalSeats = new Counter();
        List<ReservedSeat> sessionSelectedSeats = (List<ReservedSeat>) session.getAttribute("sessionSelectedSeats");

        sessionSeatsWithDiscount.setCounter(0);
        sessionNormalSeats.setCounter(sessionSelectedSeats.size());

        session.setAttribute("seatsWithDiscount", sessionSeatsWithDiscount);
        session.setAttribute("normalSeats", sessionNormalSeats);

        return "redirect:/reduction";
    }

}
