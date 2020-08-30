package com.cinemaproject.cinemaproject;

import com.cinemaproject.cinemaproject.service.BuildDatabaseService;
import com.cinemaproject.cinemaproject.service.OperationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Klasa uruchomieniowa aplikacji.
 * @author Marcin Pietroń
 * @version 1.1
 */
@SpringBootApplication
public class CinemaProjectApplication {

    /**
     * Metoda uruchamiająca aplikację.
     * W przypadku braku odpowiedniego schematu w bazie danych jest on tworzony.
     * @author Marcin Pietroń
     * @param args Wszystkie parametry.
     * @throws Exception Jakikolwiek błąd.
     */
    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(CinemaProjectApplication.class, args);

        BuildDatabaseService buildDatabaseService = context.getBean(BuildDatabaseService.class);
        buildDatabaseService.createDatabase();
    }

}
