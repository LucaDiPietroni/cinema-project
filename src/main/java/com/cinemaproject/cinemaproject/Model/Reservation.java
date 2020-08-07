package com.cinemaproject.cinemaproject.Model;

public class Reservation {
    private int id;
    private String clientName;
    private String clientSecondName;
    private String clientMail;
    private String token;
    private int showingId;

    public Reservation(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientMail() {
        return clientMail;
    }

    public void setClientMail(String clientMail) {
        this.clientMail = clientMail;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getShowingId() {
        return showingId;
    }

    public void setShowingId(int showingId) {
        this.showingId = showingId;
    }

    public String getClientSecondName() {
        return clientSecondName;
    }

    public void setClientSecondName(String clientSecondName) {
        this.clientSecondName = clientSecondName;
    }
}
