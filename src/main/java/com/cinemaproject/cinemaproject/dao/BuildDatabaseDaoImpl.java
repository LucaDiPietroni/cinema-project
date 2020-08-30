package com.cinemaproject.cinemaproject.dao;

import com.cinemaproject.cinemaproject.model.Exists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Klasa implementująca interfejs DAO obsługującego warunkowe tworzenie bazy danych.
 * @author Marcin Pietroń
 * @version 1.0
 */
@Repository
public class BuildDatabaseDaoImpl extends JdbcDaoSupport implements BuildDatabaseDao {

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
     * Implementacja metody sprawdzającej istnienie odpowiedniego schematu w bazie danych.
     * Po utworzeniu zapytania jest ono wywoływane, a jego wynik zostaje zapisany w odpowiednim obiekcie.
     * Na koniec zwracana jest wartość logiczna przechowywana w odpowiednim obiekcie.
     * @author Marcin Pietroń
     * @throws Exception Jakikolwiek błąd.
     * @return Wartość logiczna "true", jeżeli istnieje schemat o właściwej nazwie lub "false" w przeciwnym wypadku.
     */
    @Override
    public boolean checkSchema() throws Exception {
        try{
            String sql = "select exists (select * from \"pg_catalog\".\"pg_namespace\" where nspname = 'CinemaMng');";

            return (Boolean) getJdbcTemplate().queryForObject(sql, new RowMapper<Boolean>(){
                @Override
                public Boolean mapRow(ResultSet rs, int rowNumber) throws SQLException {
                    Exists exists = new Exists();
                    exists.setExists(rs.getBoolean("exists"));

                    return exists.isExists();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * Implementacja metody tworzącej odpowiedni schemat w bazie danych.
     * Treść skryptu DDL jest pobierana linijka po linjijce, a następnie wywoływana.
     * @author Marcin Pietroń
     * @param file Struktura bazy danych zapisana w formie tekstu w pliku .sql
     * @throws Exception Jakikolwiek błąd.
     */
    @Override
    public void createDatabase(File file) throws Exception {
        try(Scanner scanner = new Scanner(file, "UTF-8");){
            String sql = "";
            while (scanner.hasNextLine()){
                sql += scanner.nextLine();
                sql += " \n";
            }
            getJdbcTemplate().execute(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }
}
