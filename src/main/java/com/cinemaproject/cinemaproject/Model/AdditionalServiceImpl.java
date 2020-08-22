package com.cinemaproject.cinemaproject.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serwis implemetujący interfejs obsługujący metody pomocnicze wspomagające proces rezerwacji miejsc.
 * @author Marcin Pietroń
 * @version 1.0
 */
@Service
public class AdditionalServiceImpl implements AdditionalService {

    public AdditionalServiceImpl() {
    }

    public AdditionalServiceImpl(ApplicationContext context) {
        this.context = context;
    }

    /**
     * Wstrzyknięcie interfejsu ApplicationContext.
     * Umożliwia on korzystanie z interfejsów obsługujących pobieranie zasobów z bazy danych oraz zapisywanie w niej nowych rekordów.
     */
    @Autowired
    private ApplicationContext context;

    /**
     * Metoda generująca kod rezerwacji.
     * Każdy znak losowany jest z wcześniej zadeklarowanego zbioru i zapisywany w generatorze, który następnie tworzy kod.
     * @author Marcin Pietroń
     * @throws Exception Jakikolwiek błąd.
     * @return Kod rezerwacji złożony z 10 znaków.
     */
    @Override
    public String createToken() throws Exception {
        try{
            String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            StringBuilder builder = new StringBuilder();
            int count = 10;
            while (count-- != 0) {
                int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
                builder.append(ALPHA_NUMERIC_STRING.charAt(character));
            }
            return builder.toString();
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * Metoda sprawdzająca, czy miejsce o podanym Id znajduje się obok jednego z wcześniej wybranych miejsc.
     * Wpierw tworzone są niezbędne obiekty.
     * Jeżeli lista wcześniej wybranych miejsc nie jest pusta zapisywane są wartości skrajnie lewego i skrajnie prawego miejsca.
     * Kolejno z bazy danych pobierane są informacje o wybranym miejscu.
     * Jeżeli wybrane miejsce znajduje się w odpowiednim rzędzie, po lewej stronie skrajnie lewego miejsca lub po prawej stronie skrajnie prawego miejsca to zwracania jest wartość "true".
     * @author Marcin Pietroń
     * @param reservedSeats Lista wcześniej wybranych miejsc przez użytkownika.
     * @param seatNumber Identyfikator wybranego przez użytkownika miejsca.
     * @throws Exception Jakikolwiek błąd.
     * @return Wartość logiczna "true" jeśli miejsce znajduje się obok któregoś z wcześniej wybranych miejsc lub "false" w innym przypadku.
     */
    @Override
    public boolean isSeatNextTo(List<ReservedSeat> reservedSeats, Integer seatNumber) throws Exception {
        try{
            boolean checker;
            OperationService seatService = context.getBean(OperationService.class);
            Seat leftSeat;
            Seat rightSeat;

            if(!reservedSeats.isEmpty()){
                leftSeat = seatService.findSeatById(reservedSeats.get(0).getSeatId());
                rightSeat = seatService.findSeatById(reservedSeats.get(reservedSeats.size() - 1).getSeatId());
            }else{
                leftSeat = new Seat();
                rightSeat = new Seat();
            }

            Seat selectedSeat = seatService.findSeatById(seatNumber);

            if(reservedSeats.isEmpty()){
                checker = true;
            }else if (leftSeat.getId()==(seatNumber + 1) && leftSeat.getLine()==selectedSeat.getLine()) {
                checker = true;
            } else if (rightSeat.getId() == (seatNumber - 1) && rightSeat.getLine()==selectedSeat.getLine()) {
                checker = true;
            } else {
                checker = false;
            }
            return checker;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * Metoda sprawdzająca, czy wybrane przez użytkownika miejsce zostało już zajęte.
     * @author Marcin Pietroń
     * @param seats Lista wszystkich miejsc na sali kinowej.
     * @param selectedSeatId miejsce wybrane przez użytkownika.
     * @throws Exception Jakikolwiek błąd.
     * @return Wartość logiczna "true" jeżeli miejsce nie zostało zajęte lub "false" w przeciwnym przypadku.
     */
    @Override
    public boolean isSeatReservedAlready(List<List<Seat>> seats, int selectedSeatId) throws Exception {
        try{
            boolean result = true;

            for(List<Seat> line:seats){
                for(Seat seat:line){
                    if (seat.getId() == selectedSeatId && seat.getTaken() == 1) {
                        result = false;
                        break;
                    }
                }
            }

            return result;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }
}
