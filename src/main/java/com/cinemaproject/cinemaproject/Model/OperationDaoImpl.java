package com.cinemaproject.cinemaproject.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class OperationDaoImpl extends JdbcDaoSupport implements OperationDao {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public List<Film> findFilmByDate(LocalDate dateOfFilm) {
        String sql = "Select fi.* " +
                "from \"CinemaMng\".\"Film\" as fi left join \"CinemaMng\".\"Showing\" as sh on fi.id = sh.filmid " +
                "where date(sh.timeofstart) = ? " +
                "group by fi.id;";

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, new Object[]{dateOfFilm});
        List<Film> result = new ArrayList<Film>();

        for (Map<String, Object> row : rows) {
            Film film = new Film();
            film.setId((int) row.get("id"));
            film.setTitle((String) row.get("title"));
            film.setYearOfPremiere((int) row.get("yearOfPremiere"));
            film.setDirector((String) row.get("director"));
            film.setMainRole((String) row.get("mainRole"));
            film.setFilmGenre((String) row.get("filmGenre"));
            film.setScenarist((String) row.get("scenarist"));
            film.setProduction((String) row.get("production"));
            result.add(film);
        }
        return result;
    }

    @Override
    public List<Showing> findShowingsByDateAndId(int filmid, LocalDate date) {
        String sql = "SELECT * " +
                "FROM \"CinemaMng\".\"Showing\" " +
                "WHERE date(timeofstart) = ? " +
                "AND filmid = ?;";

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, date, filmid);
        List<Showing> result = new ArrayList<Showing>();

        for (Map<String, Object> row : rows) {
            Showing show = new Showing();
            show.setId((int) row.get("id"));
            show.setFilmid((int) row.get("filmid"));
            show.setCinemahallid((int) row.get("cinemahallid"));
            show.setTimeofstart((Timestamp) row.get("timeofstart"));
            result.add(show);
        }
        return result;
    }

    @Override
    public List<Seat> findSeatsByCinemaHallId(int cinemaHallId) {
        String sql = "SELECT * " +
                "FROM \"CinemaMng\".\"Seat\" " +
                "WHERE cinemahallid = ?;";

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, cinemaHallId);
        List<Seat> result = new ArrayList<Seat>();

        for (Map<String, Object> row : rows) {
            Seat seat = new Seat();
            seat.setId((int) row.get("id"));
            seat.setNumber((int) row.get("number"));
            seat.setCinemahallid((int) row.get("cinemahallid"));
            seat.setNormlprice((double) row.get("normalprice"));
            seat.setReducedprice((double) row.get("reducedprice"));
            seat.setRow((String) row.get("row"));
            result.add(seat);
        }
        return result;
    }

    @Override
    public void insertReservation(String clientName, String clientMail, int token, int showingId) {
        String sql = "INSERT INTO \"CinemaMng\".\"Reservation\" \n" +
                "(clientname, clientmail, token, showingid)\n" +
                "VALUES (?, ?, ?, ?)";

        getJdbcTemplate().update(sql, clientName, clientMail, token, showingId);
    }

    @Override
    public void insertReservedSeats(List<ReservedSeat> rSeatList) {
        String sql = "INSERT INTO \"CinemaMng\".\"ReservedSeat\" \n" +
                "(reservationid, seatid, isreduced)\n" +
                "VALUES (?, ?, ?)";

        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ReservedSeat rSeat = rSeatList.get(i);
                ps.setInt(1, rSeat.getReservationId());
                ps.setInt(2, rSeat.getSeatId());
                ps.setBoolean(3, rSeat.isReduced());
            }

            @Override
            public int getBatchSize() {
                return rSeatList.size();
            }
        });
    }
}
