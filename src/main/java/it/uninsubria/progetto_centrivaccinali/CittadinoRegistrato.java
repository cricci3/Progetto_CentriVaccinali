package it.uninsubria.progetto_centrivaccinali;

import java.io.*;

/**
 * Classe CittadinoRegistrato
 * @author Claudio Ricci
 * @author Alessandro Macrina
 */
public class CittadinoRegistrato extends CittadinoVaccinato implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * email
     */
    public String email;
    /**
     * username
     */
    public String username;
    /**
     * password
     */
    public String password;

    /**
     * Costruttore
     * @param nomeCV Stringa rappresentante il nome del centro vaccinale presso cui il cittadino si è vaccinato
     * @param id int rappresentante id a 8 bit
     * @param nome Stringa rappresentante nome del cittadino
     * @param cognome Stringa rappresentante cognome del cittadino
     * @param cf Stringa rappresentante codice fiscale del cittadino
     * @param data Stringa rappresentante la data di vaccinazione
     * @param nomeV Stringa rappresentante nome del vaccino somministrato
     * @param email Stringa rappresentante email del cittadino
     * @param username Stringa rappresentante username del cittadino
     * @param password Stringa rappresentante password del cittadino
     */
    CittadinoRegistrato(String nomeCV, int id, String nome, String cognome, String cf, String data, String nomeV,  String email, String username, String password) {
        super(nomeCV, id, nome, cognome,cf, data, nomeV);
        this.email = email;
        this.username= username;
        this.password = password;
    }

    /**
     * @return username dell'oggetto CittadinoRegistrato che ha chiamato il metodo
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @return password dell'oggetto CittadinoRegistrato che ha chiamato il metodo
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @return email dell'oggetto CittadinoRegistrato che ha chiamato il metodo
     */
    public String getEmail() {
        return email;
    }

    /**
     * Metodo che permette di modificare il parametro email
     * @param email String rappresentante la mail che si vuole impostare
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Metodo che permette di modificare il parametro username
     * @param username String rappresentante lo username che si vuole impostare
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Metodo che permette di modificare il parametro password
     * @param password String rappresentante la password che si vuole impostare
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return String rappresentante tutte le informazioni di un oggetto CittadinoRegistrato
     */
    @Override
    public String toString() {
        return "Cittadino Registrato -> nome: "+nome
                +", cognome: "+cognome
                +", nomeCV: "+nomeCV
                +", cf: "+cf
                +", dataVaccinazione: "+dataVaccinazione
                +", nomeVaccino: "+nomeVaccino
                +", idUnivoco: "+idUnivoco
                +", email: "+email
                +", username: "+username
                +", password: "+password;
    }
}