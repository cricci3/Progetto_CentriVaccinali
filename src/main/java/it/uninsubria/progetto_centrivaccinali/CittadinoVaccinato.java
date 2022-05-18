package it.uninsubria.progetto_centrivaccinali;

import java.io.*;

public class CittadinoVaccinato implements Serializable {
    private static final long serialVersionUID = 1L;

    public String nome;
    public String cognome;
    public String nomeCV;
    public String cf;
    public String dataVaccinazione;
    public String nomeVaccino;
    public int idUnivoco;

    CittadinoVaccinato(String nomeCV, int id, String nome, String cognome, String cf, String data, String nomeV){
        this.nomeCV=nomeCV;
        this.idUnivoco=id;
        this.nome=nome;
        this.cognome=cognome;
        this.cf=cf;
        this.dataVaccinazione=data;
        this.nomeVaccino=nomeV;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public String getNomeCV() {
        return this.nomeCV;
    }

    public String getCf() {
        return this.cf;
    }

    public String getDataVaccinazione() {
        return this.dataVaccinazione;
    }

    public String getNomeVaccino() {
        return this.nomeVaccino;
    }

    public int getIdUnivoco() {
        return this.idUnivoco;
    }

    @Override
    public String toString() {
        return "Cittadino Vaccinato -> nome: "+nome+", cognome: "+cognome+", nomeCV: "+nomeCV+", cf: "+cf+", dataVaccinazione: "+dataVaccinazione+", nomeVaccino: "+nomeVaccino+", idUnivoco: " + idUnivoco;
    }
}
