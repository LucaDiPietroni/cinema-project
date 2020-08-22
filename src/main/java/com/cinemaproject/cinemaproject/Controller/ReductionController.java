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

/**
 * Kontroler podstrony z wyborem ilości biletów ulgowych.
 * Steruje on działaniami użytkownika oraz wykorzystujący klasy i interfejsy modelu w celu wyświetlenia odpowiedniego widoku.
 * @author Marcin Pietroń
 * @version 1.0
 */
@Controller
public class ReductionController {

    /**
     * Wstrzyknięcie interfejsu ApplicationContext.
     * Umożliwia on korzystanie z interfejsów obsługujących pobieranie zasobów z bazy danych oraz zapisywanie w niej nowych rekordów.
     */
    @Autowired
    private ApplicationContext context;

    /**
     * Metoda nawigująca do podstrony z wyborem ilości biletów ulgowych.
     * Po pobraniu informacji o ilości biletów normalnych z obiektu sesji zostają one ustawione jako atrybuty sesji i modelu
     * @author Marcin Pietroń
     * @param model obiekt przechowujący atrybuty wyświetlane na podstronie.
     * @param session obiekt sesji przechowujący atrybuty unikalne dla każdego użytkownika.
     * @return Odnośnik do podstrony z wyborem ilości biletów ulgowych.
     */
    @GetMapping("/reduction")
    public String getReduction(Model model, HttpSession session){
        try{
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
        }catch (Exception e){
            return "error";
        }
    }

    /**
     * Metoda obsługująca wybór liczby biletów ulgowych i normalnych według użytkownika.
     * Po pobraniu potrzebnych informacji z sesji, w zależności od wyboru użytkownika zwiększa się liczba biletów ulgowych lub normalnych.
     * Następnie informacje te ustawiane są jako atrybuty sesji.
     * @author Marcin Pietroń
     * @param discount obiekt przechowujący identyfikator wybranego przez użytkownika seansu.
     * @param request obiekt zawierający informacje o żądaniach klienta. Pozwala pobierać atrybuty z sesji i je do niej dodawać.
     * @return Odnośnik do podstrony z wyborem liczby biletów ulgowych.
     */
    @PostMapping(value = "/setReduction")
    public String setReduction(@RequestParam(value = "discount") String discount, HttpServletRequest request) {
        try{
            Counter seatsWithDiscount = (Counter) request.getSession().getAttribute("seatsWithDiscount");
            Counter normalSeats = (Counter) request.getSession().getAttribute("normalSeats");
            List<ReservedSeat> selectedSeats = (List<ReservedSeat>) request.getSession().getAttribute("selectedSeats");

            if(discount.equals("1")){
                if(normalSeats.getCounter() == (selectedSeats.size())){
                }else{
                    normalSeats.increment();
                    seatsWithDiscount.decrement();
                }
            }else if (discount.equals("2")){
                if(seatsWithDiscount.getCounter() == (selectedSeats.size())){
                }else{
                    normalSeats.decrement();
                    seatsWithDiscount.increment();
                }
            }
            request.getSession().setAttribute("seatsWithDiscount", seatsWithDiscount);
            request.getSession().setAttribute("normalSeats", normalSeats);

            return "redirect:/reduction";
        }catch (Exception e){
            return "error";
        }
    }

    /**
     * Metoda obsługująca zapis do bazy wszystkich informacji na temat dokonanej rezerwacji.
     * Po pobraniu wszystkich niezbędnych informacji z sesji do bazy zapisywane są informacje o rezerwacji.
     * Następnie kod rezerwacji jest przypisywany do zarezerwowanych miejsc oraz określona przez użytkownika ilość miejsc jest kategoryzowana jako objęte zniżką lub nie.
     * Na kończu informacje o zarezerwowanych miejscach na sali kinowej zapisywane są w bazie danych,
     * @author Marcin Pietroń
     * @return Odnośnik do podstrony podsumowującej rezerwację.
     */
    @PostMapping(value = "/goToSummary")
    public String goToSummary(){
        try{

            return "redirect:/summary";
        }catch (Exception e){
            return "error";
        }
    }
}
