package com.cinemaproject.cinemaproject.Model;

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

@Repository
public class BuildDatabaseDaoImpl extends JdbcDaoSupport implements BuildDatabaseDao{

    /**
     *
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

    @Override
    public void createDatabase(File file) throws Exception {
        try(Scanner scanner = new Scanner(file);){
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
