package com.cinemaproject.cinemaproject.model;

/**
 * Klasa autorzacji użytkownika, który dokonał rezerwacji.
 * @author Marcin Pietroń
 * @version 1.0
 */
public class AuthorizationData {

    /**
     * Pole adresu e-mail użytkownika.
     */
    private String email;

    /**
     * Pole kodu rezerwacji.
     */
    private String token;

    /**
     * Pole statusu autoryzacji.
     */
    private boolean status = true;

    public AuthorizationData(){}

    /**
     * Getter pola "email".
     * @author Marcin Pietroń
     * @return Adres e-mail użytkownika.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter pola "email".
     * @author Marcin Pietroń
     * @param email nowy adres e-mail użytkownika.
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @param token nowy kod rezerwacji.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Getter pola "status".
     * @author Marcin Pietroń
     * @return Wartość logiczna statusu.
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Setter pola "status".
     * @author Marcin Pietroń
     * @param status nowa wartość logiczna statusu.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}
