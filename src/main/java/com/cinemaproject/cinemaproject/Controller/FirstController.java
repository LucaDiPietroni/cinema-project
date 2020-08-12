package com.cinemaproject.cinemaproject.Controller;

import com.cinemaproject.cinemaproject.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    AllSeats seats;

    @Resource(name = "selectedDate")
    SelectedDate selectedDate;

    @Resource(name = "authorizationData")
    AuthorizationData authorizationData;

    @Resource(name = "reservationToDelete")
    Reservation reservationToDelete;

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
    @GetMapping("/")
    public String getStart() {
        reservedSeats.clear();
        selectedDate = new SelectedDate();
        reservation = new Reservation();
        reservedShow = new Showing();
        authorizationData = new AuthorizationData();
        reservationToDelete = new Reservation();
        selectedDate = new SelectedDate();

        return "start";
    }





    @PostMapping("/goToFilms")
    public String goToFilms(){
        return "redirect:/films";
    }

    @PostMapping("/goToCancel")
    public String goToCancel(){
        return "redirect:/cancelAuthorization";
    }

    @GetMapping("/cancelAuthorization")
    public String getCancelAuthorization(Model model){
        model.addAttribute("authorizationData", authorizationData);

        return "cancelAuthorization";
    }

    @GetMapping("/cancel")
    public String getCancel(Model model){
        OperationService operationService = context.getBean(OperationService.class);
        Showing showing = operationService.findShowingById(reservationToDelete.getShowingId());
        Film film = operationService.findFilmById(showing.getFilmid());
        List<ReservedSeat> seatsToDelete = operationService.findReservedSeatsByToken(reservationToDelete.getToken());

        model.addAttribute("film", film);
        model.addAttribute("seatsToDelete", seatsToDelete);
        return "cancel";
    }

    @PostMapping("/addAuthorization")
    public String addAuthorization(@ModelAttribute AuthorizationData newAuthorizationData){
        String result;

        authorizationData.setEmail(newAuthorizationData.getEmail());
        authorizationData.setToken(newAuthorizationData.getToken());

        OperationService operationService = context.getBean(OperationService.class);

        try{
            reservationToDelete = operationService.findReservationByMailAndToken(authorizationData.getEmail(),
                    authorizationData.getToken());
            result = "redirect:/cancel";
        }catch (EmptyResultDataAccessException e){
            result = "redirect:/cancelAuthorization";
            authorizationData.setStatus(false);
        }

        return result;
    }

    @PostMapping("/deleteReservation")
    public String deleteReservation(){
        OperationService operationService = context.getBean(OperationService.class);
        operationService.deleteReservation(reservationToDelete.getId());

        return "end";
    }

    @PostMapping("goToStart")
    public String goToStart(){
        return "redirect:/";
    }

}
