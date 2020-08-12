package com.cinemaproject.cinemaproject.Controller;

import com.cinemaproject.cinemaproject.Model.Counter;
import com.cinemaproject.cinemaproject.Model.OperationService;
import com.cinemaproject.cinemaproject.Model.Reservation;
import com.cinemaproject.cinemaproject.Model.ReservedSeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ReductionController {

    /**
     * Wstrzyknięcie interfejsu ApplicationContext.
     * Umożliwia on korzystanie z interfejsów obsługujących pobieranie zasobów z bazy danych oraz zapisywanie w niej nowych rekordów.
     */
    @Autowired
    private ApplicationContext context;

    /**
     * Metoda nawigująca do strony z wyborem ilości biletów ulgowych.
     * @author Marcin&Rafał
     * @param model obiekt przechowujący atrybuty: liczba miejsc ze zniżką, liczba miejsc normalnych.
     * @return Odnośnik do podstrony z wyborem miejsc na sali kinowej.
     */
    @GetMapping("/reduction")
    public String getReduction(Model model, HttpSession session){
        Counter seatsWithDiscount = (Counter) session.getAttribute("seatsWithDiscount");
        if(seatsWithDiscount == null){
            seatsWithDiscount = new Counter();
        }
        session.setAttribute("seatsWithDiscount", seatsWithDiscount);

        Counter normalSeats = (Counter) session.getAttribute("normalSeats");
        if(normalSeats == null){
            normalSeats = new Counter();
        }
        session.setAttribute("seatsWithDiscount", seatsWithDiscount);

        model.addAttribute("seatsWithDiscount", seatsWithDiscount);
        model.addAttribute("normalSeats", normalSeats);

        return "reduction";
    }

    /**
     * Metoda ustawiająca liczbę biletów ulgowych i normalnych według użytkownika.
     * Do metody przekazywany jest parametr definiujący, czy należy zwiększyć liczbę biletów ulgowych, czy normalnych
     * Następnie jeżeli liczba biletów wybranej kategorii nie przekracza liczby wybranych miejsc dodawany jest kolejny bilet i odejmowany ten drugi.
     * @author Marcin&Rafał
     * @param discount obiekt przechowujący identyfikator wybranego przez użytkownika seansu.
     * @return Odnośnik do podstrony z wyborem liczby biletów ulgowych.
     */
    @PostMapping(value = "/setReduction")
    public String setReduction(@RequestParam(value = "discount") String discount, HttpServletRequest request) {
        System.out.println(discount);

        Counter seatsWithDiscount = (Counter) request.getSession().getAttribute("seatsWithDiscount");
        Counter normalSeats = (Counter) request.getSession().getAttribute("normalSeats");
        List<ReservedSeat> selectedSeats = (List<ReservedSeat>) request.getSession().getAttribute("sessionSelectedSeats");

        if(discount.equals("1")){
            if(normalSeats.getCounter() == (selectedSeats.size())){
                System.out.println("cokolwiek");
            }else{
                normalSeats.increment();
                seatsWithDiscount.decrement();
            }
        }else if (discount.equals("2")){
            if(seatsWithDiscount.getCounter() == (selectedSeats.size())){
                System.out.println("cokolwiek");
            }else{
                normalSeats.decrement();
                seatsWithDiscount.increment();
            }
        }
        request.getSession().setAttribute("seatsWithDiscount", seatsWithDiscount);
        request.getSession().setAttribute("normalSeats", normalSeats);

        return "redirect:/reduction";
    }

    /**
     * Metoda zapisująca do bazy wszystkie informacje na temat dokonanej rezerwacji.
     * Najpierw zapisywany jest obiekt rezerwacji.
     * Następnie obiektom zarezerwowanych miejsc przypisywany jest token jednolity dla nich i samej rezerwacji.
     * Następnie wybranej przez użytkownika liczbie zarezerwowanych miejsc przypisywana jest ulga.
     * Na końcu lista zarezerwowanych miejsc jest zapisywana do bazy danych.
     * @author Marcin&Rafał
     * @return Odnośnik do podstrony końcowej.
     */
    @PostMapping(value = "/reserveSeats")
    public String reserveSeats(HttpServletRequest request){
        OperationService filmService = context.getBean(OperationService.class);

        Reservation reservation = (Reservation) request.getSession().getAttribute("sessionReservation");
        List<ReservedSeat> selectedSeats = (List<ReservedSeat>) request.getSession().getAttribute("sessionSelectedSeats");
        Counter seatsWithDiscount = (Counter) request.getSession().getAttribute("seatsWithDiscount");

        filmService.insertReservation(reservation.getClientName(),
                reservation.getClientSecondName(),
                reservation.getClientMail(),
                reservation.getToken(),
                reservation.getShowingId());

        for (ReservedSeat resSeat : selectedSeats) {
            resSeat.setToken(reservation.getToken());
        }

        for (int i = 0; i < seatsWithDiscount.getCounter(); i++){
            selectedSeats.get(i).setReduced(true);
        }
        filmService.insertReservedSeats(selectedSeats);

        return "end";
    }
}
