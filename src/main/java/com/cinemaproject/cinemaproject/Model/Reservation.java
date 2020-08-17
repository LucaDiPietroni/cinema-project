package com.cinemaproject.cinemaproject.Model;

/**
 * Klasa rezerwacji.
 * @author Marcin Pietroń
 * @version 1.0
 */
public class Reservation {

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
     * @author Marcin Pietroń
     * @return Identyfikator rezerwacji.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter pola "id".
     * @author Marcin Pietroń
     * @param id Nowy identyfikator rezerwacji.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter pola "clientName".
     * @author Marcin Pietroń
     * @return Imię klienta dokonującego rezerwacji.
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Setter pola "clientName".
     * @author Marcin Pietroń
     * @param clientName Nowe imię klienta dokonującego rezerwacji.
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Getter pola "clientMail".
     * @author Marcin Pietroń
     * @return Adres e-mail klienta dokonującego rezerwacji.
     */
    public String getClientMail() {
        return clientMail;
    }

    /**
     * Setter pola "clientMail".
     * @author Marcin Pietroń
     * @param clientMail Nowy adres e-mail klienta dokonującego rezerwacji.
     */
    public void setClientMail(String clientMail) {
        this.clientMail = clientMail;
    }

    /**
     * Getter pola "token".
     * @author Marcin Pietroń
     * @return Kod rezerwacji.
     */
    public String getToken() {
        return token;
    }

    /**
     * Setter pola "token".
     * @author Marcin Pietroń
     * @param token Nowy kod rezerwacji.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Getter pola "showingId".
     * @author Marcin Pietroń
     * @return Identyfikator seansu.
     */
    public int getShowingId() {
        return showingId;
    }

    /**
     * Setter pola "showingId".
     * @author Marcin Pietroń
     * @param showingId Nowy identyfikator seansu.
     */
    public void setShowingId(int showingId) {
        this.showingId = showingId;
    }

    /**
     * Getter pola "clientSecondName".
     * @author Marcin Pietroń
     * @return Nazwisko klienta dokonującego rezerwacji.
     */
    public String getClientSecondName() {
        return clientSecondName;
    }

    /**
     * Setter pola "clientSecondName".
     * @author Marcin Pietroń
     * @param clientSecondName Nowe nazwisko klienta dokonującego rezerwacji.
     */
    public void setClientSecondName(String clientSecondName) {
        this.clientSecondName = clientSecondName;
    }
}
