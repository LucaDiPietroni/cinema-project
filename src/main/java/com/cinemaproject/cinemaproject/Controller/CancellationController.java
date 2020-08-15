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
import java.util.List;

@Controller
public class CancellationController {

    /**
     * Wstrzyknięcie interfejsu ApplicationContext.
     * Umożliwia on korzystanie z interfejsów obsługujących pobieranie zasobów z bazy danych oraz zapisywanie w niej nowych rekordów.
     */
    @Autowired
    private ApplicationContext context;

    @GetMapping("/cancel")
    public String getCancel(Model model, HttpSession session){
        try{
            Reservation reservationToDelete = (Reservation) session.getAttribute("reservationToDelete");

            OperationService operationService = context.getBean(OperationService.class);
            Showing reservedShowing = operationService.findShowingById(reservationToDelete.getShowingId());
            Film film = operationService.findFilmById(reservedShowing.getFilmid());

            List<ReservedSeat> seatsToDelete = operationService.findReservedSeatsByToken(reservationToDelete.getToken());
            Double price = 0.0;
            List<Seat> reservedSeats = new ArrayList<Seat>();

            for(ReservedSeat oneSeat: seatsToDelete){
                if(oneSeat.isReduced()){
                    price += 12.5;
                }else{
                    price += 25;
                }
                Seat seat = operationService.findSeatById(oneSeat.getSeatId());
                reservedSeats.add(seat);
            }

            session.setAttribute("film", film);
            session.setAttribute("seatsToDelete", seatsToDelete);
            session.setAttribute("price", price);
            session.setAttribute("reservedSeats", reservedSeats);
            session.setAttribute("reservedShowing", reservedShowing);

            model.addAttribute("film", film);
            model.addAttribute("seatsToDelete", seatsToDelete);
            model.addAttribute("price", price);
            model.addAttribute("reservedSeats", reservedSeats);
            model.addAttribute("reservedShowing", reservedShowing);

            return "cancel";
        }catch (Exception e){
            return "error";
        }
    }



    @PostMapping("/deleteReservation")
    public String deleteReservation(HttpServletRequest request){
        try{
            Reservation reservationToDelete = (Reservation) request.getSession().getAttribute("reservationToDelete");

            OperationService operationService = context.getBean(OperationService.class);
            operationService.deleteReservation(reservationToDelete.getId());

            return "endOfCancellation";
        }catch (Exception e){
            return "error";
        }
    }
}
