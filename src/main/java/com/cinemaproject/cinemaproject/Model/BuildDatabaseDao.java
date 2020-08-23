package com.cinemaproject.cinemaproject.Model;

import java.io.File;

public interface BuildDatabaseDao {
    boolean checkSchema() throws Exception;
    void createDatabase(File file) throws Exception;
}
