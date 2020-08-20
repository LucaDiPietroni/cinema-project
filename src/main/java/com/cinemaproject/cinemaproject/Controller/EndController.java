package com.cinemaproject.cinemaproject.Controller;

import com.cinemaproject.cinemaproject.Model.Reservation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class EndController {


    @GetMapping("/end")
    private String getEnd(Model model, HttpSession session){
        try{
            Reservation userReservation = (Reservation) session.getAttribute("userReservation");
            model.addAttribute("userReservation", userReservation);

            session.setAttribute("userReservation", null);

            return "end";
        }catch(Exception e){
            return "error";
        }
    }
}
