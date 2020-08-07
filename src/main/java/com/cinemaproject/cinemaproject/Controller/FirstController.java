package com.cinemaproject.cinemaproject.Controller;

import com.cinemaproject.cinemaproject.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

/**
 * Kontroler aplikacji, sterujący działaniami użytkownika oraz wykorzystujący klasy i interfejsy modelu w celu wyświetlenia odpowiedniego widoku.
 * @author Marcin&Rafał
 * @version 1.0
 */
@Controller
public class FirstController {

    /**
     * ?Pole? przechowujące wybrany seans.
     */
    @Resource(name = "reservedShow")
    Showing reservedShow;

    /**
     * ?Pole? przechowujące obiekt rezerwacji miejsc w kinie na określony seans.
     */
    @Resource(name = "reservation")
    Reservation reservation;

    /**
     * ?Pole? przechowujące listę zarezerwowanych miejsc.
     */
    @Resource(name = "reservedSeats")
    List<ReservedSeat> reservedSeats;

    /**
     * ?Pole? przechowujące liczbę miejsc objętych zniżką.
     */
    @Resource(name = "seatsWithDiscount")
    Counter seatsWithDiscount;

    /**
     * ?Pole? przechowujące liczbę miejsc nie objętych zniżką.
     */
    @Resource(name = "normalSeats")
    Counter normalSeats;

    @Resource(name = "seats")
    List<List<Seat>> seats;

    @Resource(name = "selectedDate")
    SelectedDate selectedDate;

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

    /**
     * Metoda nawigująca do strony startowej.
     * @author Marcin&Rafał
     * @return Odnośnik do podstrony startowej.
     */
    @GetMapping("/start")
    public String getStart() {
        return "start";
    }

    /**
     * Metoda nawigująca do strony z wyborem filmów.
     * W pierwszej kolejności pobierane z bazy danych są wszystkie filmy wyświetlane w kinie danego dnia.
     * Kolejno tworzone są atrybuty wyświetlane na stronie HTML i jeden przechowujący informację o wyborze seansu.
     * @author Marcin&Rafał
     * @param model obiekt przechowujący atrybuty wyświetlane na podstronie z wyborem filmów
     * @return Odnośnik do podstrony z wyborem filmów.
     */
    @GetMapping("/films")
    public String getFilms(Model model) {
        OperationService filmService = context.getBean(OperationService.class);

        List<Date> datesOfShowings = filmService.findDatesOfShowings();

        //LocalDate date = selectedDate;

        List<Film> filmList = filmService.findFilmByDate(selectedDate.getSelectedDate().toLocalDate());

        model.addAttribute("filmList", filmList);
        model.addAttribute("Service", filmService);
        model.addAttribute("newSelectedDate", selectedDate);
        model.addAttribute("showing", new Showing());
        model.addAttribute("datesOfShowings", datesOfShowings);
        return "films";
    }

    /**
     * Metoda nawigująca do strony z formularzem użytkownika.
     * @author Marcin&Rafał
     * @param model obiekt przechowujący atrybut nowej rezerwacji.
     * @return Odnośnik do podstrony z formularzem użytkownika.
     */
    @GetMapping("/reservation")
    public String getReservation(Model model){
        model.addAttribute("newReservation", new Reservation());
        return "reservation";
    }

    /**
     * Metoda nawigująca do strony z wyborem miejsc na sali kinowej.
     * @author Marcin&Rafał
     * @param model obiekt przechowujący atrybuty: wybrany seans oraz lista zarezerwowanych miejsc.
     * @return Odnośnik do podstrony z wyborem miejsc na sali kinowej.
     */
    @GetMapping("/seats")
    public String getSeats(Model model) {
        model.addAttribute("reservedShow", reservedShow);
        model.addAttribute("reservedSeats", reservedSeats);

        OperationService seatService = context.getBean(OperationService.class);

        for (List<Seat> line: seats){
            for (Seat seat:line) {
                if (seatService.takenSeat(reservedShow.getId(), seat.getId()) == null && seat.getTaken() != 2){
                    seat.setTaken(0);
                }
                else if (seat.getTaken() != 2) {
                    seat.setTaken(1);
                }
            }
        }
        model.addAttribute("seats", seats);

        return "seats";
    }

    /**
     * Metoda nawigująca do strony z wyborem ilości biletów ulgowych.
     * @author Marcin&Rafał
     * @param model obiekt przechowujący atrybuty: liczba miejsc ze zniżką, liczba miejsc normalnych.
     * @return Odnośnik do podstrony z wyborem miejsc na sali kinowej.
     */
    @GetMapping("/reduction")
    public String getReduction(Model model){
        model.addAttribute("seatsWithDiscount", seatsWithDiscount);
        model.addAttribute("normalSeats", normalSeats);

        return "reduction";
    }

    /**
     * Metoda zapisująca wybór seansu przez użykownika.
     * Po wyborze seansu przekazywany jest identyfikator na podstawie którego z bazy pobierane są szczegółowe informacje o wybranym seansie, a następnie zapisywane są one w odpowiednim obiekcie.
     * @author Marcin&Rafał
     * @param selectedShow obiekt przechowujący identyfikator wybranego przez użytkownika seansu.
     * @return Odnośnik do podstrony z formularzem użytkownika.
     */
    @PostMapping(value = "/setShow")
    public String setShow(@RequestParam(value = "selectedShow") String selectedShow) {
        int id = Integer.parseInt(selectedShow);
        OperationService filmService = context.getBean(OperationService.class);

        Showing chosenShow = filmService.findShowingById(id);

        reservedShow.setId(chosenShow.getId());
        reservedShow.setFilmid(chosenShow.getFilmid());
        reservedShow.setCinemahallid(chosenShow.getCinemahallid());
        reservedShow.setDateOfShowing(chosenShow.getDateOfShowing());
        reservedShow.setTimeOfStart(chosenShow.getTimeOfStart());

        return "redirect:/reservation";
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
    public String addRes(@ModelAttribute Reservation newReservation) {

        reservation.setClientName(newReservation.getClientName());
        reservation.setClientSecondName(newReservation.getClientSecondName());
        reservation.setClientMail(newReservation.getClientMail());
        reservation.setShowingId(reservedShow.getId());

        reservation.setToken(additionalService.createToken());

        OperationService seatService = context.getBean(OperationService.class);
        seats = seatService.findSeatsByCinemaHallId(reservedShow.getCinemahallid());

        return "redirect:/seats";
    }

    /**
     * Metoda zapisująca do listy miejsce na sali konowej zarezerwowane przez użytkownika.
     * Do metody przekazywany jest identyfikator miejsca w kinie. Jeżeli miejsce znajduje się obok miejsca wcześniej wybranego lub jest to pierwsze wybrane miejsce, to jest ono zapisywane do listy wybranych miejsc.
     * Na koniec lista wybranych miejsc kinowych jest sortowana.
     * @author Marcin&Rafał
     * @param chosenSeat obiekt przechowujący identyfikator wybranego przez użytkownika miejsca.
     * @return Odnośnik do podstrony z wyborem miejsc na sali kinowej.
     */
    @PostMapping("/addSeat")
    public String addSeat(@RequestParam(value = "chosenSeat") String chosenSeat){
        int id = Integer.parseInt(chosenSeat);

        if(additionalService.isSeatNextTo(reservedSeats, id) && additionalService.isSeatReservedAlready(seats, id)){
            ReservedSeat newReservedSeat = new ReservedSeat();
            newReservedSeat.setSeatId(id);
            reservedSeats.add(newReservedSeat);

            for (List<Seat> line: seats){
                for (Seat seat:line) {
                    if (seat.getId() == id){
                        seat.setTaken(2);
                    }
                }
            }
        }
        Collections.sort(reservedSeats);

        return "redirect:/seats";
    }

    /**
     * Metoda ustawijąca domyślną liczbę biletów ulgowych i normalnych.
     * @author Marcin&Rafał
     * @return Odnośnik do podstrony z wyborem liczby biletów ulgowych.
     */
    @PostMapping("/goToReduction")
    public String goToReduction(){
        seatsWithDiscount.setCounter(0);
        normalSeats.setCounter(reservedSeats.size());

        return "redirect:/reduction";
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
    public String setReduction(@RequestParam(value = "discount") String discount) {
        System.out.println(discount);
        if(discount.equals("1")){
            if(normalSeats.getCounter() == (reservedSeats.size())){
                System.out.println("cokolwiek");
            }else{
                normalSeats.increment();
                seatsWithDiscount.decrement();
            }
        }else if (discount.equals("2")){
            if(seatsWithDiscount.getCounter() == (reservedSeats.size())){
                System.out.println("cokolwiek");
            }else{
                normalSeats.decrement();
                seatsWithDiscount.increment();
            }
        }

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
    public String reserveSeats(){
        OperationService filmService = context.getBean(OperationService.class);
        filmService.insertReservation(reservation.getClientName(),
                reservation.getClientSecondName(),
                reservation.getClientMail(),
                reservation.getToken(),
                reservation.getShowingId());

        for (ReservedSeat resSeat : reservedSeats) {
            resSeat.setToken(reservation.getToken());
        }

        for (int i = 0; i < seatsWithDiscount.getCounter(); i++){
            reservedSeats.get(i).setReduced(true);
        }
        filmService.insertReservedSeats(reservedSeats);

        reservedSeats.clear();
        selectedDate = new SelectedDate();
        reservation = new Reservation();
        reservedShow = new Showing();

        return "end";
    }

    /**
     * Metoda usuwająca wszystkie zarezerwowane miejsca z listy.
     * @author Marcin&Rafał
     * @return Odnośnik do podstrony z wyborem miejsc na sali kinowej.
     */
    @PostMapping("/removeSeats")
    public String removeSeats(){
        reservedSeats.clear();

        for (List<Seat> line: seats){
            for (Seat seat:line) {
                seat.setTaken(0);
            }
        }
        return "redirect:/seats";
    }

    @PostMapping("/setDate")
    public String setDate(@ModelAttribute SelectedDate newSelectedDate){

        this.selectedDate.setSelectedDate(newSelectedDate.getSelectedDate());
        System.out.println("Działa");
        return "redirect:/films";
    }

}
