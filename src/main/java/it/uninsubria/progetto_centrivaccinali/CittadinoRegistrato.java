package it.uninsubria.progetto_centrivaccinali;

import java.io.*;

public class CittadinoRegistrato extends CittadinoVaccinato implements Serializable {
    private static final long serialVersionUID = 1L;

    public String email;
    public String username;
    public String password;

    CittadinoRegistrato(String nomeCV, int id, String nome, String cognome, String cf, String data, String nomeV,  String email, String username, String password) {
        super(nomeCV, id, nome, cognome,cf, data, nomeV);
        this.email = email;
        this.username= username;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
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