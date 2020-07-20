package com.cinemaproject.cinemaproject;

import com.cinemaproject.cinemaproject.Model.OperationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CinemaProjectApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(CinemaProjectApplication.class, args);

        OperationService filmService = context.getBean(OperationService.class);
//        LocalDate date = LocalDate.of(2020,9,8);
//        filmService.findFilmByDate(date);
//        System.out.println();
//        filmService.findShowingsByDateAndId(8, date);
//        System.out.println();
//        filmService.findSeatsByCinemaHallId(2);
        //filmService.insertReservation("Marcin Pietroń", "mar5011@wp.pl", 131313, 2);

//        List<ReservedSeat> rSeatList = new ArrayList<ReservedSeat>();
//        ReservedSeat rSeat = new ReservedSeat();
//        rSeat.setReservationId(2);
//        rSeat.setSeatId(3);
//        rSeat.setReduced(true);
//        rSeatList.add(rSeat);
//
//        rSeat = new ReservedSeat();
//        rSeat.setReservationId(2);
//        rSeat.setSeatId(4);
//        rSeat.setReduced(false);
//        rSeatList.add(rSeat);
//
//        filmService.insertReservedSeats(rSeatList);
    }

}
