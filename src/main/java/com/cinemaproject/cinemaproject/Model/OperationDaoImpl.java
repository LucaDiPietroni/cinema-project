package com.cinemaproject.cinemaproject.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
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
                "where date(sh.dateofshowing) = ? " +
                "group by fi.id;";

        assert getJdbcTemplate() != null;
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, dateOfFilm);
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
                "WHERE date(dateofshowing) = ? " +
                "AND filmid = ?;";

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, date, filmid);
        List<Showing> result = new ArrayList<Showing>();

        for (Map<String, Object> row : rows) {
            Showing show = new Showing();
            show.setId((int) row.get("id"));
            show.setFilmid((int) row.get("filmid"));
            show.setCinemahallid((int) row.get("cinemahallid"));
            show.setDateOfShowing((Date) row.get("dateOfShowing"));
            show.setTimeOfStart((Time) row.get("timeofstart"));
            result.add(show);
        }
        return result;
    }

    @Override
    public List<Seat> findSeatsInLineByCinemaHallId(int cinemaHallId, int line) {
        String sql = "SELECT *\n" +
                "FROM \"CinemaMng\".\"Seat\"\n" +
                "WHERE cinemahallid = ?\n" +
                "AND line = ?\n" +
                "ORDER BY number";

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, cinemaHallId, line);
        List<Seat> result = new ArrayList<Seat>();

        for (Map<String, Object> row : rows) {
            Seat seat = new Seat();
            seat.setId((int) row.get("id"));
            seat.setNumber((int) row.get("number"));
            seat.setCinemahallid((int) row.get("cinemahallid"));
            seat.setNormlprice((double) row.get("normalprice"));
            seat.setReducedprice((double) row.get("reducedprice"));
            seat.setRow((int) row.get("line"));
            result.add(seat);
        }
        return result;
    }

    @Override
    public List<Integer> findRowsByCinemaHall(int cinemaHallId) {
        String sql = "SELECT line\n" +
                "FROM \"CinemaMng\".\"Seat\"\n" +
                "WHERE cinemahallid = ?\n" +
                "GROUP BY line\n" +
                "ORDER BY line";

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, cinemaHallId);
        List<Integer> result = new ArrayList<Integer>();

        for (Map<String, Object> row : rows) {
            Integer line = (Integer) row.get("line");
            result.add(line);
        }
        return result;
    }

    @Override
    public void insertReservation(String clientName, String clientSecondName, String clientMail, String token, int showingId) {
        String sql = "INSERT INTO \"CinemaMng\".\"Reservation\" \n" +
                "(clientname, clientsecondname, clientmail, token, showingid)\n" +
                "VALUES (?, ?, ?, ?, ?)";

        getJdbcTemplate().update(sql, clientName, clientSecondName, clientMail, token, showingId);
    }

    @Override
    public void insertReservedSeats(List<ReservedSeat> rSeatList) {
        String sql = "INSERT INTO \"CinemaMng\".\"ReservedSeat\" \n" +
                "(token, seatid, isreduced)\n" +
                "VALUES (?, ?, ?)";

        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ReservedSeat rSeat = rSeatList.get(i);
                ps.setString(1, rSeat.getToken());
                ps.setInt(2, rSeat.getSeatId());
                ps.setBoolean(3, rSeat.isReduced());
            }

            @Override
            public int getBatchSize() {
                return rSeatList.size();
            }
        });
    }

    @Override
    public Showing findShowingById(int id){
        String sql = "SELECT * " +
                "FROM \"CinemaMng\".\"Showing\" " +
                "WHERE id = ?;";

        return (Showing)getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<Showing>(){
            @Override
            public Showing mapRow(ResultSet rs, int rwNumber) throws SQLException {
                Showing show = new Showing();
                show.setId(rs.getInt("id"));
                show.setFilmid(rs.getInt("filmid"));
                show.setCinemahallid(rs.getInt("cinemahallid"));
                show.setDateOfShowing(rs.getDate("dateOfShowing"));
                show.setTimeOfStart(rs.getTime("timeofstart"));
                return show;
            }
        });
    }

    @Override
    public Integer takenSeat(int showingId, int seatId){
        String sql = "SELECT se.seatid\n" +
                "FROM (\"CinemaMng\".\"Showing\" AS sh \n" +
                "\t  LEFT JOIN \n" +
                "\t  (SELECT clientname, clientmail, showingid, token\n" +
                "\t  FROM \"CinemaMng\".\"Reservation\") AS re \n" +
                "\t  ON sh.id = re.showingid) AS t1\n" +
                "LEFT JOIN \"CinemaMng\".\"ReservedSeat\" AS se ON t1.token = se.token\n" +
                "WHERE t1.id = ?\n" +
                "AND seatid = ?;";

        assert getJdbcTemplate() != null;
        try{
            return getJdbcTemplate().queryForObject(sql, new Object[]{showingId, seatId}, Integer.class);
        } catch(EmptyResultDataAccessException e){
            return null;
        }

    }

    @Override
    public List<Date> findDatesOfShowings(){
        String sql = "SELECT dateofshowing\n" +
                "FROM \"CinemaMng\".\"Showing\"\n" +
                "GROUP BY dateofshowing\n" +
                "ORDER BY dateofshowing;";

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<Date> result = new ArrayList<Date>();

        for (Map<String, Object> row : rows) {
            Date date = (Date) row.get("dateofshowing");
            result.add(date);
        }
        return result;
    }

    @Override
    public Seat findSeatById(int id){
        String sql = "SELECT *\n" +
                "FROM \"CinemaMng\".\"Seat\"\n" +
                "WHERE id = ?;";

        return (Seat)getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<Seat>(){
            @Override
            public Seat mapRow(ResultSet rs, int rowNumber) throws SQLException {
                Seat seat = new Seat();
                seat.setId(rs.getInt("id"));
                seat.setNumber(rs.getInt("number"));
                seat.setCinemahallid(rs.getInt("cinemahallid"));
                seat.setNormlprice(rs.getDouble("normalprice"));
                seat.setReducedprice(rs.getDouble("reducedprice"));
                seat.setLine(rs.getInt("line"));
                return seat;
            }
        });
    }
}