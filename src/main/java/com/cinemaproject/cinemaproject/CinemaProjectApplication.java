package com.cinemaproject.cinemaproject;

import com.cinemaproject.cinemaproject.service.BuildDatabaseService;
import com.cinemaproject.cinemaproject.service.OperationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CinemaProjectApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(CinemaProjectApplication.class, args);

        OperationService filmService = context.getBean(OperationService.class);
        BuildDatabaseService buildDatabaseService = context.getBean(BuildDatabaseService.class);
        buildDatabaseService.createDatabase();
    }

}
