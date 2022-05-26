package it.uninsubria.progetto_centrivaccinali;

import java.io.*;

public class CittadinoRegistrato extends CittadinoVaccinato implements Serializable {
    private static final long serialVersionUID = 1L;

    public String email;
    public String username;
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

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

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