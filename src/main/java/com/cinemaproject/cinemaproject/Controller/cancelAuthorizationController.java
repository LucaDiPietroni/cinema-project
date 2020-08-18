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

/**
 * Kontroler podstrony autoryzacji użytkownika, który zarezerwował miejsca na film.
 * Steruje on działaniami użytkownika oraz wykorzystujący klasy i interfejsy modelu w celu wyświetlenia odpowiedniego widoku.
 * @author Marcin Pietroń
 * @version 1.0
 */
@Controller
public class cancelAuthorizationController {

    /**
     * Wstrzyknięcie interfejsu ApplicationContext.
     * Umożliwia on korzystanie z interfejsów obsługujących pobieranie zasobów z bazy danych oraz zapisywanie w niej nowych rekordów.
     */
    @Autowired
    private ApplicationContext context;

    /**
     * Metoda nawigująca do strony z autoryzacją użytkownika, który dokonał rezerwacji.
     * W pierwszej kolejności pobierane są z obiektu sesji dane potrzebne do autoryzacji użytkownika.
     * Jeżeli takie dane nie istnieją tworzony jest odpowiedni obiekt, który następnie zostaje ustawiony jako atrybut sesji i modelu.
     * @author Marcin Pietroń
     * @param model obiekt przechowujący atrybuty wyświetlane na podstronie.
     * @param session obiekt sesji przechowujący atrybuty unikalne dla każdego użytkownika.
     * @return Odnośnik do podstrony z autoryzacją użytkownika.
     */
    @GetMapping("/cancelAuthorization")
    public String getCancelAuthorization(Model model, HttpSession session){
        try{
            AuthorizationData authorizationData = (AuthorizationData) session.getAttribute("authorizationData");
            if(authorizationData == null){
                authorizationData = new AuthorizationData();
            }
            session.setAttribute("authorizationData", authorizationData);

            model.addAttribute("authorizationData", authorizationData);

            return "cancelAuthorization";
        }catch (Exception e){
            return "error";
        }
    }

    /**
     * Metoda werfikująca dane autoryzcyjne wprowadzone przez użytkownika.
     * Po pobraniu odpowiednich danych z obiektu sesji pobierane są z bazy informacje o rezerwacji i ustawiane jako atrybut modelu i sesji
     * @author Marcin Pietroń
     * @param newAuthorizationData atrybut obiektu dotyczący danych autoryzacyjnych.
     * @param request obiekt zawierający informacje o żądaniach klienta. Pozwala pobierać atrybuty z sesji i je do niej dodawać.
     * @return Odnośnik do podstrony z wyborem filmów.
     */
    @PostMapping("/addAuthorization")
    public String addAuthorization(@ModelAttribute AuthorizationData newAuthorizationData, HttpServletRequest request){
        try{
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
        }catch (Exception e){
            return "error";
        }
    }
}
