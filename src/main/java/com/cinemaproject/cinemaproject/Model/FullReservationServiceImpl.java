package com.cinemaproject.cinemaproject.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FullReservationServiceImpl implements FullReservationService{

    @Autowired
    OperationService operationService;


    @Override
    @Transactional
    public void makeFullReservation(Reservation reservation, List<ReservedSeat> reservedList) throws Exception {
        operationService.insertReservation(reservation.getClientName(),
                reservation.getClientSecondName(),
                reservation.getClientMail(),
                reservation.getToken(),
                reservation.getShowingId());

        operationService.insertReservedSeats(reservedList);
    }
}
