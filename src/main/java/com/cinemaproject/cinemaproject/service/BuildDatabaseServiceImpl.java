package com.cinemaproject.cinemaproject.service;

import com.cinemaproject.cinemaproject.model.BuildDatabaseDao;
import com.cinemaproject.cinemaproject.service.BuildDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

/**
 * Serwis implemetujący interfejs obsługujący moduł DAO odpowiedzialny za warunkowe tworzenie bazy danych.
 * @author Marcin Pietroń
 * @version 1.0
 */
@Service
public class BuildDatabaseServiceImpl implements BuildDatabaseService {

    /**
     * Wstrzyknięcie interfejsu BuildDatabaseDao.
     * Umożliwia on warunkowe tworzenie niezbędnego do działania aplikacji schematu w bazie danych.
     */
    @Autowired
    BuildDatabaseDao buildDatabaseDao;


    /**
     * Implementacja metody tworzącej niezbędny schemat w bazie danych w przypadku gdy nie istnieje.
     * Jeżeli dany schemat nie istnieje tworzony jest on na bazie podanej struktury.
     * @author Marcin Pietroń
     * @throws Exception Jakikolwiek błąd.
     */
    @Override
    @Transactional
    public void createDatabase() throws Exception {
        if(!buildDatabaseDao.checkSchema()){
            File file = new File(".\\src\\main\\resources\\project-schema.sql");
            buildDatabaseDao.createDatabase(file);
        }
    }
}
