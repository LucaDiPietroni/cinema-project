package com.cinemaproject.cinemaproject.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * Klasa zbioru wszystkich miejsc na sali kinowej.
 * @author Marcin Pietroń
 * @version 1.0
 */
public class AllSeats {

    /**
     * Wstrzyknięcie interfejsu ApplicationContext.
     * Umożliwia on korzystanie z interfejsów obsługujących pobieranie zasobów z bazy danych oraz zapisywanie w niej nowych rekordów.
     */
    @Autowired
    private ApplicationContext context;

    /**
     * Pole listy list miejsc w poszczególnych rzędach.
     */
    private List<List<Seat>> seats;

    /**
     * Getter pola "seats".
     * @author Marcin Pietroń
     * @return Lista list miejsc w poszczególnych rzędach.
     */
    public List<List<Seat>> getSeats() {
        return seats;
    }

    /**
     * Setter pola "seats".
     * @author Marcin Pietroń
     * @param seats Lista list miejsc w poszczególnych rzędach.
     */
    public void setSeats(List<List<Seat>> seats) {
        this.seats = seats;
    }

    /**
     * Metoda zmieniająca status wszystkich miejsc na "dostępne".
     * @author Marcin Pietroń
     * @throws Exception Jakikolwiek błąd.
     */
    public void setAllSeatsAvailable() throws Exception {
        try{
            for (List<Seat> line: seats){
                for (Seat seat:line) {
                    seat.setTaken(0);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * Metoda zmieniająca status wybranego przez użytkownika miejsca na "zajęte".
     * Najpierw w zbiorze wszystkich miejsc na sali kinowej wyszukiwane jest miejsce wybrane przez użytkownika.
     * Następnie status jego dostępności jest zmieniany na 2 - "zajęte".
     * @author Marcin Pietroń
     * @param selectedSeat Miejsce wybrane przez użytkownika.
     * @throws Exception Jakikolwiek błąd.
     */
    public void setSeatsSelected(int selectedSeat) throws Exception {
        try{
            for (List<Seat> line: seats){
                for (Seat seat:line) {
                    if (seat.getId() == selectedSeat){
                        seat.setTaken(2);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * Metoda zmieniająca status miejsc na sali kinowej w trakcie konkretnego seansu na "niedostępne".
     * Metoda sprawdza na które miejsca na sali kinowej dokonano rezerwacji.
     * Sprawdzanie odbywa się dla każdego miejsca.
     * Jeżeli w bazie danych zostanie znaleziona rezerwacja na jakieś miejsce w trakcie konkretnego seansu to status miejsca zmienia się na "niedostępne".
     * @author Marcin Pietroń
     * @param reservedShow Seans wybrany przez użytkownika.
     * @throws Exception Jakikolwiek błąd.
     */
    public void setSeatsNotAvailable(Showing reservedShow) throws Exception {
        try{
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
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }
}
