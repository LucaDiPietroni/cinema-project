package com.cinemaproject.cinemaproject.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Service
public class BuildDatabaseServiceImpl implements BuildDatabaseService {

    @Autowired
    BuildDatabaseDao buildDatabaseDao;


    @Override
    @Transactional
    public void createDatabase() throws Exception {
        if(!buildDatabaseDao.checkSchema()){
            File file = new File(".\\src\\main\\resources\\project-schema.sql");
            buildDatabaseDao.createDatabase(file);
        }
    }
}
