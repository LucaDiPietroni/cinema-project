package com.cinemaproject.cinemaproject.model;

/**
 * Klasa rezerwacji.
 * @author Rafał Rybarczyk
 * @version 1.0
 */
public class Reservation {

    public Reservation(int id, String clientName, String clientSecondName, String clientMail, String token, int showingId) {
        this.id = id;
        this.clientName = clientName;
        this.clientSecondName = clientSecondName;
        this.clientMail = clientMail;
        this.token = token;
        this.showingId = showingId;
    }

    /**
     * Pole identyfikatora rezerwacji.
     */
    private int id;

    /**
     * Pole imienia klienta dokonującego rezerwacji.
     */
    private String clientName;

    /**
     * Pole nazwiska klienta dokonującego rezerwacji.
     */
    private String clientSecondName;

    /**
     * Pole adresu e-mail klienta dokonującego rezerwacji.
     */
    private String clientMail;

    /**
     * Pole kodu rezerwacji.
     */
    private String token;

    /**
     * Pole identyfikatora seansu.
     */
    private int showingId;

    public Reservation(){

    }

    /**
     * Getter pola "id".
     * @author Rafał Rybarczyk
     * @return Identyfikator rezerwacji.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter pola "id".
     * @author Rafał Rybarczyk
     * @param id Nowy identyfikator rezerwacji.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter pola "clientName".
     * @author Rafał Rybarczyk
     * @return Imię klienta dokonującego rezerwacji.
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Setter pola "clientName".
     * @author Rafał Rybarczyk
     * @param clientName Nowe imię klienta dokonującego rezerwacji.
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Getter pola "clientMail".
     * @author Rafał Rybarczyk
     * @return Adres e-mail klienta dokonującego rezerwacji.
     */
    public String getClientMail() {
        return clientMail;
    }

    /**
     * Setter pola "clientMail".
     * @author Rafał Rybarczyk
     * @param clientMail Nowy adres e-mail klienta dokonującego rezerwacji.
     */
    public void setClientMail(String clientMail) {
        this.clientMail = clientMail;
    }

    /**
     * Getter pola "token".
     * @author Rafał Rybarczyk
     * @return Kod rezerwacji.
     */
    public String getToken() {
        return token;
    }

    /**
     * Setter pola "token".
     * @author Rafał Rybarczyk
     * @param token Nowy kod rezerwacji.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Getter pola "showingId".
     * @author Rafał Rybarczyk
     * @return Identyfikator seansu.
     */
    public int getShowingId() {
        return showingId;
    }

    /**
     * Setter pola "showingId".
     * @author Rafał Rybarczyk
     * @param showingId Nowy identyfikator seansu.
     */
    public void setShowingId(int showingId) {
        this.showingId = showingId;
    }

    /**
     * Getter pola "clientSecondName".
     * @author Rafał Rybarczyk
     * @return Nazwisko klienta dokonującego rezerwacji.
     */
    public String getClientSecondName() {
        return clientSecondName;
    }

    /**
     * Setter pola "clientSecondName".
     * @author Rafał Rybarczyk
     * @param clientSecondName Nowe nazwisko klienta dokonującego rezerwacji.
     */
    public void setClientSecondName(String clientSecondName) {
        this.clientSecondName = clientSecondName;
    }
}
