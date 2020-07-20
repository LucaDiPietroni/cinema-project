package com.cinemaproject.cinemaproject.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    OperationDao operationDao;

    @Override
    public List<Film> findFilmByDate(LocalDate dateOfFilm) {
        List<Film> filmList = operationDao.findFilmByDate(dateOfFilm);
        return filmList;

//        for (Film oneFilm:filmList){
//            System.out.print(oneFilm.getId() + " ");
//            System.out.print(oneFilm.getTitle() + " ");
//            System.out.print(oneFilm.getYearOfPremiere() + " ");
//            System.out.print(oneFilm.getDirector() + " ");
//            System.out.print(oneFilm.getMainRole() + " ");
//            System.out.print(oneFilm.getFilmGenre() + " ");
//            System.out.print(oneFilm.getScenarist() + " ");
//            System.out.println(oneFilm.getProduction() + " ");
//        }
    }

    @Override
    public List<Showing> findShowingsByDateAndId(int filmid, LocalDate date){
        List<Showing> showList = operationDao.findShowingsByDateAndId(filmid, date);
        return showList;

//        for (Showing oneShow:showList){
//            System.out.print(oneShow.getId() + " ");
//            System.out.print(oneShow.getFilmid() + " ");
//            System.out.print(oneShow.getCinemahallid() + " ");
//            System.out.println(oneShow.getTimeofstart().toLocalDateTime().getHour() + ":" +
//                    oneShow.getTimeofstart().toLocalDateTime().getMinute() + " ");
//        }
    }

    @Override
    public void findSeatsByCinemaHallId(int cinemaHallId){
        List<Seat> seatList = operationDao.findSeatsByCinemaHallId(cinemaHallId);

        for (Seat oneSeat:seatList){
            System.out.print(oneSeat.getId() + " ");
            System.out.print(oneSeat.getCinemahallid() + " ");
            System.out.print(oneSeat.getNumber() + " ");
            System.out.println(oneSeat.getRow() + " ");
        }
    }

    @Override
    public void insertReservation(String clientName, String clientMail, int token, int showingId){
        operationDao.insertReservation(clientName, clientMail, token, showingId);
    }

    @Override
    public void insertReservedSeats(List<ReservedSeat> rSeatList){
        operationDao.insertReservedSeats(rSeatList);
    }
}
