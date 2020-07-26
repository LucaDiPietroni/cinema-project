package com.cinemaproject.cinemaproject.Controller;

import com.cinemaproject.cinemaproject.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FirstController {
//    private Showing resShow = new Showing();
//    private Reservation reservation = new Reservation();
//    private List<ReservedSeat> reservedSeats = new ArrayList<ReservedSeat>();
//    private List<Integer> resSeatIds = new ArrayList<Integer>();

    @Resource(name = "resShow")
    Showing resShow;

    @Resource(name = "reservation")
    Reservation reservation;

    @Resource(name = "reservedSeats")
    List<ReservedSeat> reservedSeats;

    @Resource(name = "resSeatIds")
    List<Integer> resSeatIds;


    @Autowired
    private ApplicationContext context;

    @GetMapping("/start")
    public String getStart() {
        return "start";
    }

    @GetMapping("/films")
    public String getFilms(Model model) {
        OperationService filmService = context.getBean(OperationService.class);
        LocalDate date = LocalDate.of(2020, 9, 7);
        List<Film> filmList = filmService.findFilmByDate(date);

        model.addAttribute("filmList", filmList);
        model.addAttribute("Service", filmService);
        model.addAttribute("date", date);
        model.addAttribute("showing", new Showing());
        return "films";
    }

    @GetMapping("/seats")
    public String getSeats(Model model) {
        model.addAttribute("resShow", resShow);
        model.addAttribute("newReservation", new Reservation());
        model.addAttribute("reservedSeats", reservedSeats);
        model.addAttribute("resSeats", resSeatIds);

        OperationService seatService = context.getBean(OperationService.class);
        List<List<Seat>> seats = seatService.findSeatsByCinemaHallId(resShow.getCinemahallid());
        model.addAttribute("seats", seats);


        return "seats";
    }

    @PostMapping(value = "/setShow")
    public String setShow(@RequestParam(value = "action", required = true) String action) {
        System.out.println(action);
        Integer id = Integer.valueOf(action);
        OperationService filmService = context.getBean(OperationService.class);

        Showing tempShow = filmService.findShowingById(id);

        resShow.setId(tempShow.getId());
        resShow.setFilmid(tempShow.getFilmid());
        resShow.setCinemahallid(tempShow.getCinemahallid());
        resShow.setTimeofstart(tempShow.getTimeofstart());

        // this.cinemaHall.setId(Integer.valueOf(action));
        return "redirect:/seats";
    }

    @PostMapping("/addRes")
    public String addRes(@ModelAttribute Reservation reqReservation) {
        OperationService filmService = context.getBean(OperationService.class);

        reservation.setClientName(reqReservation.getClientName());
        reservation.setClientMail(reqReservation.getClientMail());
        reservation.setShowingId(resShow.getId());

        reservation.setToken(1);

        filmService.insertReservation(reservation.getClientName(),
                reservation.getClientMail(),
                reservation.getToken(),
                reservation.getShowingId());

        return "redirect:/seats";
    }

    @PostMapping("/addSeat")
    public String addSeat(@RequestParam(value = "action", required = true) String action){
        Integer id = Integer.valueOf(action);
        resSeatIds.add(id);
        return "redirect:/seats";
    }

}
