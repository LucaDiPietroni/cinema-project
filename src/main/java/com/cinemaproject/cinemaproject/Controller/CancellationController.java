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
        Reservation reservationToDelete = (Reservation) session.getAttribute("reservationToDelete");

        OperationService operationService = context.getBean(OperationService.class);
        Showing showing = operationService.findShowingById(reservationToDelete.getShowingId());
        Film film = operationService.findFilmById(showing.getFilmid());
        List<ReservedSeat> seatsToDelete = operationService.findReservedSeatsByToken(reservationToDelete.getToken());

        session.setAttribute("film", film);
        session.setAttribute("seatsToDelete", seatsToDelete);

        model.addAttribute("film", film);
        model.addAttribute("seatsToDelete", seatsToDelete);
        return "cancel";
    }



    @PostMapping("/deleteReservation")
    public String deleteReservation(HttpServletRequest request){
        Reservation reservationToDelete = (Reservation) request.getSession().getAttribute("reservationToDelete");

        OperationService operationService = context.getBean(OperationService.class);
        operationService.deleteReservation(reservationToDelete.getId());

        return "end";
    }
}
