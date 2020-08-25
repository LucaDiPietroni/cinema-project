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

/**
 * Klasa implementująca interfejs DAO obsługujący interakcje z bazą danych.
 * @author Marcin Pietroń
 * @version 1.1
 */
@Repository
public class OperationDaoImpl extends JdbcDaoSupport implements OperationDao {

    public OperationDaoImpl() {
    }

    public OperationDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Wstrzyknięcie interfejsu odpowiedzialnego z połączenie z fizycznym źródłem danych.
     */
    @Autowired
    DataSource dataSource;

    /**
     * Inicjalizacja źródła danych.
     */
    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    /**
     * Implementacja metody pobierającej z bazy danych listę filmów wyświetlanych w kinie danego dnia.
     * Po stworzeniu zapytania jest ono wywoływane.
     * Jego wyniki zapisywane są do wcześniej utworzonego obiektu.
     * @author Marcin Pietroń
     * @param dateOfFilm Data seansu.
     * @throws Exception Jakikolwiek błąd.
     * @return Lista filmów wyświetlanych danego dnia.
     */
    @Override
    public List<Film> findFilmByDate(LocalDate dateOfFilm) throws Exception {
        try{
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
                film.setImage((String) row.get("image"));
                result.add(film);
            }
            return result;
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * Implementacja metody pobierającej z bazy danych listę seansów danego filmu i odbywających się danego dnia.
     * Po stworzeniu zapytania jest ono wywoływane.
     * Jego wyniki zapisywane są do wcześniej utworzonego obiektu.
     * @author Marcin Pietroń
     * @param filmid Identyfikator filmu.
     * @param date Data seansu.
     * @throws Exception Jakikolwiek błąd.
     * @return Lista seansów danego filmu i odbywających się danego dnia.
     */
    @Override
    public List<Showing> findShowingsByDateAndId(int filmid, LocalDate date) throws Exception {
        try{
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
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * Implementacja metody pobierającej z bazy danych listę miejsc w danym rzędzie na określonej sali kinowej.
     * Po stworzeniu zapytania jest ono wywoływane.
     * Jego wyniki zapisywane są do wcześniej utworzonego obiektu.
     * @author Marcin Pietroń
     * @param cinemaHallId Identyfikator sali kinowej.
     * @param line Numer rzędu na sali kinowej.
     * @throws Exception Jakikolwiek błąd.
     * @return Lista miejsc w danym rzędzie na określonej sali kinowej.
     */
    @Override
    public List<Seat> findSeatsInLineByCinemaHallId(int cinemaHallId, int line) throws Exception {
        try{
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
                seat.setNormalprice((double) row.get("normalprice"));
                seat.setReducedprice((double) row.get("reducedprice"));
                seat.setLine((int) row.get("line"));
                result.add(seat);
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * Implementacja metody pobierającej z bazy danych listę rzędów na określonej sali kinowej.
     * Po stworzeniu zapytania jest ono wywoływane.
     * Jego wyniki zapisywane są do wcześniej utworzonego obiektu.
     * @author Marcin Pietroń
     * @param cinemaHallId Identyfikator sali kinowej.
     * @throws Exception Jakikolwiek błąd.
     * @return Lista rzędów na określonej sali kinowej.
     */
    @Override
    public List<Integer> findRowsByCinemaHall(int cinemaHallId) throws Exception {
        try{
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
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * Implementacja metody zapisującej do bazy informacje o rezerwacji użytkownika.
     * Po stworzeniu zapytania jest ono wywoływane, a dane zostają zapisane w bazie danych.
     * @author Marcin Pietroń
     * @param clientName Imię klienta.
     * @param clientSecondName Nazwisko klienta.
     * @param clientMail Adres e-mail klienta.
     * @param token Kod rezerwacji.
     * @param showingId Identyfikator wybranego seansu.
     * @throws Exception Jakikolwiek błąd.
     */
    @Override
    public void insertReservation(String clientName, String clientSecondName, String clientMail, String token, int showingId) throws Exception {
        try{
            String sql = "INSERT INTO \"CinemaMng\".\"Reservation\" \n" +
                    "(clientname, clientsecondname, clientmail, token, showingid)\n" +
                    "VALUES (?, ?, ?, ?, ?)";

            getJdbcTemplate().update(sql, clientName, clientSecondName, clientMail, token, showingId);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * Implementacja metody zapisującej do bazy listę wybranych przez użytkownika miejsc.
     * Po stworzeniu zapytania jest ono wywoływane w trybie batchowym.
     * Kolejno każde miejsce zostaje zapisane do bazy danych.
     * @author Marcin Pietroń
     * @param rSeatList Lista wybranych miejsc.
     * @throws Exception Jakikolwiek błąd.
     */
    @Override
    public void insertReservedSeats(List<ReservedSeat> rSeatList) throws Exception {
        try{
            String sql = "INSERT INTO \"CinemaMng\".\"ReservedSeat\" \n" +
                    "(token, seatid, isreduced, showingid)\n" +
                    "VALUES (?, ?, ?, ?)";

            getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ReservedSeat rSeat = rSeatList.get(i);
                    ps.setString(1, rSeat.getToken());
                    ps.setInt(2, rSeat.getSeatId());
                    ps.setBoolean(3, rSeat.isReduced());
                    ps.setInt(4, rSeat.getShowingId());
                }

                @Override
                public int getBatchSize() {
                    return rSeatList.size();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * Implementacja metody pobierającej z bazy danych seans określony przez podany identyfikator.
     * Po stworzeniu zapytania jest ono wywoływane.
     * Jego wyniki są od razu zwracane jako odpowiedni obiekt.
     * @author Marcin Pietroń
     * @param id Identyfikator seansu.
     * @throws Exception Jakikolwiek błąd.
     * @return Seans określony przez podany identyfikator.
     */
    @Override
    public Showing findShowingById(int id) throws Exception {
        try{
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
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * Implementacja metody pobierającej z bazy danych identyfikator miejsca zarezerwowanego na konkretny seans.
     * Metoda zwróci wartość identyfikatora jeżeli rezerwacja miejsca nastąpiła.
     * W przeciwnym wypadku zwrócony zostanie "null".
     * @author Marcin Pietroń
     * @param showingId Identyfikator seansu.
     * @param seatId Identyfikator miejsca.
     * @throws Exception Jakikolwiek błąd.
     * @return Identyfikator zarezerwowanego miejsca.
     */
    @Override
    public Integer takenSeat(int showingId, int seatId) throws Exception {
        try{
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
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * Implementacja metody pobierającej z bazy danych listę dni, gdy odbywają się jakieś seanse.
     * Po stworzeniu zapytania jest ono wywoływane.
     * Jego wyniki zapisywane są do wcześniej utworzonego obiektu.
     * @author Marcin Pietroń
     * @throws Exception Jakikolwiek błąd.
     * @return Lista dni, gdy odbywają się jakieś seanse.
     */
    @Override
    public List<Date> findDatesOfShowings() throws Exception {
        try{
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
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * Implementacja metody pobierającej z bazy danych miejsce na sali kinowej określone przez podany identyfikator.
     * Po stworzeniu zapytania jest ono wywoływane.
     * Jego wyniki są od razu zwracane jako odpowiedni obiekt.
     * @author Marcin Pietroń
     * @param id Identyfikator miejsca na sali kinowej.
     * @throws Exception Jakikolwiek błąd.
     * @return Miejsce na sali kinowej.
     */
    @Override
    public Seat findSeatById(int id) throws Exception {
        try{
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
                    seat.setNormalprice(rs.getDouble("normalprice"));
                    seat.setReducedprice(rs.getDouble("reducedprice"));
                    seat.setLine(rs.getInt("line"));
                    return seat;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * Implementacja metody pobierającej z bazy danych rezerwację identyfikowaną przez adres e-mail i kod rezerwacji.
     * Po stworzeniu zapytania jest ono wywoływane.
     * Jego wyniki są od razu zwracane jako odpowiedni obiekt.
     * @author Marcin Pietroń
     * @param email Adres e-mail użytkownika.
     * @param token Kod rezerwacji.
     * @throws Exception Jakikolwiek błąd.
     * @return Rezerwacja dokonana przez konkretnego użytkownika.
     */
    @Override
    public Reservation findReservationByMailAndToken(String email, String token) throws Exception {
        try{
            String sql = "SELECT *\n" +
                    "FROM \"CinemaMng\".\"Reservation\"\n" +
                    "WHERE clientmail = ?\n" +
                    "AND token = ?";

            return (Reservation)getJdbcTemplate().queryForObject(sql, new Object[]{email, token}, new RowMapper<Reservation>(){
                @Override
                public Reservation mapRow(ResultSet rs, int rowNumber) throws SQLException {
                    Reservation reservation = new Reservation();
                    reservation.setId(rs.getInt("id"));
                    reservation.setClientName(rs.getString("clientname"));
                    reservation.setClientSecondName(rs.getString("clientsecondname"));
                    reservation.setClientMail(rs.getString("clientmail"));
                    reservation.setShowingId(rs.getInt("showingid"));
                    reservation.setToken(rs.getString("token"));

                    return reservation;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * Implementacja metody pobierającej z bazy danych listę zarezerwowanych miejsc na sali kinowej.
     * Po stworzeniu zapytania jest ono wywoływane.
     * Jego wyniki zapisywane są do wcześniej utworzonego obiektu.
     * @author Marcin Pietroń
     * @param token Kod rezerwacji.
     * @throws Exception Jakikolwiek błąd.
     * @return Lista zarezerwowanych miejsc na sali kinowej.
     */
    @Override
    public List<ReservedSeat> findReservedSeatsByToken(String token) throws Exception {
        try{
            String sql = "SELECT *\n" +
                    "FROM \"CinemaMng\".\"ReservedSeat\"\n" +
                    "WHERE token = ?";

            List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, token);
            List<ReservedSeat> result = new ArrayList<ReservedSeat>();

            for (Map<String, Object> row : rows) {
                ReservedSeat reservedSeat = new ReservedSeat();
                reservedSeat.setId((int) row.get("id"));
                reservedSeat.setSeatId((int) row.get("seatid"));
                reservedSeat.setReduced((boolean) row.get("isreduced"));
                reservedSeat.setToken((String) row.get("token"));
                reservedSeat.setShowingId((int) row.get("showingid"));

                result.add(reservedSeat);
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * Implementacja metody pobierającej z bazy danych film określony przez identyfikator.
     * Po stworzeniu zapytania jest ono wywoływane.
     * Jego wyniki są od razu zwracane jako odpowiedni obiekt.
     * @author Marcin Pietroń
     * @param id Identyfikator filmu.
     * @throws Exception Jakikolwiek błąd.
     * @return Film.
     */
    @Override
    public Film findFilmById(int id) throws Exception {
        try{
            String sql = "SELECT *\n" +
                    "FROM \"CinemaMng\".\"Film\"\n" +
                    "WHERE id = ?";

            return (Film)getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<Film>(){
                @Override
                public Film mapRow(ResultSet rs, int rowNumber) throws SQLException {
                    Film film = new Film();
                    film.setId(rs.getInt("id"));
                    film.setTitle(rs.getString("title"));
                    film.setYearOfPremiere(rs.getInt("yearOfPremiere"));
                    film.setDirector(rs.getString("director"));
                    film.setMainRole(rs.getString("mainRole"));
                    film.setFilmGenre(rs.getString("filmGenre"));
                    film.setScenarist(rs.getString("scenarist"));
                    film.setProduction(rs.getString("production"));
                    film.setImage(rs.getString("image"));

                    return film;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * Implementacja metody usuwającej z bazy danych rezerwację o podanym identyfikatorze.
     * Po stworzeniu zapytania jest ono wywoływane.
     * @author Marcin Pietroń
     * @param id Identyfikator rezerwacji.
     * @throws Exception Jakikolwiek błąd.
     */
    @Override
    public void deleteReservation(int id) throws Exception {
        try{
            String sql = "DELETE FROM \"CinemaMng\".\"Reservation\"\n" +
                    "WHERE id = ?";

            getJdbcTemplate().update(sql, id);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * Implementacja metody pobierającej z bazy danych listę wszystkich filmów wyświetlanych w kinie.
     * Po stworzeniu zapytania jest ono wywoływane.
     * Jego wyniki zapisywane są do wcześniej utworzonego obiektu.
     * @author Marcin Pietroń
     * @throws Exception Jakikolwiek błąd.
     * @return Lista wszystkich filmów wyświetlanych w kinie.
     */
    @Override
    public List<Film> findAllFilms() throws Exception {
        try{
            String sql = "SELECT * FROM \"CinemaMng\".\"Film\"";

            assert getJdbcTemplate() != null;
            List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
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
                film.setImage((String) row.get("image"));
                result.add(film);
            }
            return result;
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }
}