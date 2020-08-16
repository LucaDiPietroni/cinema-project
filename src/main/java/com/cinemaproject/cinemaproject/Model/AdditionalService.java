package com.cinemaproject.cinemaproject.Model;

import java.util.List;

/**
 * Interfejs obsługujący metody pomocnicze wspomagające proces rezerwacji miejsc.
 * @author Marcin Pietroń
 * @version 1.0
 */
public interface AdditionalService {

    /**
     * Metoda generująca kod rezerwacji.
     * @author Marcin Pietroń
     * @throws Exception Jakikolwiek błąd.
     * @return Kod rezerwacji złożony z 10 znaków.
     */
    public String createToken() throws Exception;

    /**
     * Metoda sprawdzająca, czy miejsce o podanym Id znajduje się obok jednego z wcześniej wybranych miejsc.
     * @author Marcin Pietroń
     * @param reservedSeats Lista wcześniej wybranych miejsc przez użytkownika.
     * @param seatNumber Identyfikator wybranego przez użytkownika miejsca.
     * @throws Exception Jakikolwiek błąd.
     * @return Wartość logiczna "true" jeśli miejsce znajduje się obok któregoś z wcześniej wybranych miejsc lub "false" w innym przypadku.
     */
    public boolean isSeatNextTo(List<ReservedSeat> reservedSeats, Integer seatNumber) throws Exception;

    /**
     * Metoda sprawdzająca, czy wybrane przez użytkownika miejsce zostało już zajęte.
     * @author Marcin Pietroń
     * @param seats Lista wszystkich miejsc na sali kinowej.
     * @param selectedSeatId miejsce wybrane przez użytkownika.
     * @throws Exception Jakikolwiek błąd.
     * @return Wartość logiczna "true" jeżeli miejsce nie zostało zajęte lub "false" w przeciwnym przypadku.
     */
    public boolean isSeatReservedAlready(List<List<Seat>> seats, int selectedSeatId) throws Exception;
}
