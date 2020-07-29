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
import java.util.Collection;
import java.util.Collections;
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

//    @Resource(name = "resSeatIds")
//    List<Integer> resSeatIds;

    @Resource(name = "reducedSeats")
    Counter reducedSeats;

    @Resource(name = "normalSeats")
    Counter normalSeats;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private AdditionalService additionalService;

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
        model.addAttribute("reservedSeats", reservedSeats);
//        model.addAttribute("resSeats", resSeatIds);

        OperationService seatService = context.getBean(OperationService.class);
        List<List<Seat>> seats = seatService.findSeatsByCinemaHallId(resShow.getCinemahallid());
        model.addAttribute("seats", seats);

        return "seats";
    }

    @GetMapping("/reservation")
    public String getReservation(Model model){
        model.addAttribute("newReservation", new Reservation());
        return "reservation";
    }

    @GetMapping("/reduction")
    public String getReduction(Model model){
        model.addAttribute("reducedSeats", reducedSeats);
        model.addAttribute("normalSeats", normalSeats);

        return "reduction";
    }

    @PostMapping(value = "/setShow")
    public String setShow(@RequestParam(value = "action") String action) {
        System.out.println(action);
        Integer id = Integer.valueOf(action);
        OperationService filmService = context.getBean(OperationService.class);

        Showing tempShow = filmService.findShowingById(id);

        resShow.setId(tempShow.getId());
        resShow.setFilmid(tempShow.getFilmid());
        resShow.setCinemahallid(tempShow.getCinemahallid());
        resShow.setTimeofstart(tempShow.getTimeofstart());

        // this.cinemaHall.setId(Integer.valueOf(action));
        return "redirect:/reservation";
    }

    @PostMapping("/addRes")
    public String addRes(@ModelAttribute Reservation reqReservation) {
//        OperationService filmService = context.getBean(OperationService.class);

        reservation.setClientName(reqReservation.getClientName());
        reservation.setClientMail(reqReservation.getClientMail());
        reservation.setShowingId(resShow.getId());

        reservation.setToken(additionalService.createToken());

//        filmService.insertReservation(reservation.getClientName(),
//                reservation.getClientMail(),
//                reservation.getToken(),
//                reservation.getShowingId());

        return "redirect:/seats";
    }

    @PostMapping("/addSeat")
    public String addSeat(@RequestParam(value = "action") String action){
        Integer id = Integer.valueOf(action);
        if(additionalService.isSeatNextTo(reservedSeats, id)){
            ReservedSeat newReservedSeat = new ReservedSeat();
            newReservedSeat.setSeatId(id);
            reservedSeats.add(newReservedSeat);
        }
        Collections.sort(reservedSeats);
        return "redirect:/seats";
    }

    @PostMapping("/goToReduction")
    public String goToReduction(){
        reducedSeats.setCounter(0);
        normalSeats.setCounter(reservedSeats.size());

//        List<ReservedSeat> reservedSeats = new ArrayList<ReservedSeat>();
//
//        for (ReservedSeat resSeat : reservedSeats) {
//            resSeat.setToken(reservation.getToken());
//            reservedSeats.add(resSeat);
//        }

//        OperationService filmService = context.getBean(OperationService.class);
//        filmService.insertReservedSeats(reservedSeats);

        return "redirect:/reduction";
    }

    @PostMapping(value = "/setReduction")
    public String setReduction(@RequestParam(value = "discount") String discount) {
        System.out.println(discount);
        if(discount.equals("1")){
            if(normalSeats.equals(reservedSeats.size())){
                System.out.println("cokolwiek");
            }else{
                normalSeats.increment();
                reducedSeats.decrement();
            }
        }else if (discount.equals("2")){
            if(normalSeats.equals(reservedSeats.size())){
                System.out.println("cokolwiek");
            }else{
                normalSeats.decrement();
                reducedSeats.increment();
            }
        }

        return "redirect:/reduction";
    }

    @PostMapping(value = "/reserveSeats")
    public String reserveSeats(){
        OperationService filmService = context.getBean(OperationService.class);
        filmService.insertReservation(reservation.getClientName(),
                reservation.getClientMail(),
                reservation.getToken(),
                reservation.getShowingId());
        //List<ReservedSeat> reservedSeats = new ArrayList<ReservedSeat>();

        for (ReservedSeat resSeat : reservedSeats) {
            resSeat.setToken(reservation.getToken());
            //reservedSeats.add(resSeat);
        }

        for (int i = 0; i < reducedSeats.getCounter(); i++){
            reservedSeats.get(i).setReduced(true);
        }
        filmService.insertReservedSeats(reservedSeats);

        return "end";
    }

    @PostMapping("/removeSeats")
    public String removeSeats(){
        reservedSeats.clear();
        return "redirect:/seats";
    }

}
