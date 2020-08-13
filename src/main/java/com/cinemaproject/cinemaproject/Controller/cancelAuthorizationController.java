package com.cinemaproject.cinemaproject.Controller;

import com.cinemaproject.cinemaproject.Model.AuthorizationData;
import com.cinemaproject.cinemaproject.Model.OperationService;
import com.cinemaproject.cinemaproject.Model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class cancelAuthorizationController {

    /**
     * Wstrzyknięcie interfejsu ApplicationContext.
     * Umożliwia on korzystanie z interfejsów obsługujących pobieranie zasobów z bazy danych oraz zapisywanie w niej nowych rekordów.
     */
    @Autowired
    private ApplicationContext context;

    @GetMapping("/cancelAuthorization")
    public String getCancelAuthorization(Model model, HttpSession session){
        AuthorizationData authorizationData = (AuthorizationData) session.getAttribute("authorizationData");
        if(authorizationData == null){
            authorizationData = new AuthorizationData();
        }
        session.setAttribute("authorizationData", authorizationData);

        model.addAttribute("authorizationData", authorizationData);

        return "cancelAuthorization";
    }

    @PostMapping("/addAuthorization")
    public String addAuthorization(@ModelAttribute AuthorizationData newAuthorizationData, HttpServletRequest request){
        String result;

        AuthorizationData authorizationData = (AuthorizationData) request.getSession().getAttribute("authorizationData");
        if(authorizationData == null){
            authorizationData = new AuthorizationData();
        }

        authorizationData.setEmail(newAuthorizationData.getEmail());
        authorizationData.setToken(newAuthorizationData.getToken());

        OperationService operationService = context.getBean(OperationService.class);

        Reservation reservationToDelete = new Reservation();

        try{
            reservationToDelete = operationService.findReservationByMailAndToken(authorizationData.getEmail(),
                    authorizationData.getToken());
            result = "redirect:/cancel";
        }catch (EmptyResultDataAccessException e){
            result = "redirect:/cancelAuthorization";
            authorizationData.setStatus(false);
        }

        request.getSession().setAttribute("authorizationData", authorizationData);
        request.getSession().setAttribute("reservationToDelete", reservationToDelete);

        return result;
    }
}
