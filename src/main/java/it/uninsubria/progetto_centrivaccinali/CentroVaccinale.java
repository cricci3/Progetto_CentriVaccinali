package it.uninsubria.progetto_centrivaccinali;

import java.io.*;

public class CentroVaccinale implements Serializable {
    private static final long serialVersionUID = 1L;

    public String nome;
    public String tipologia;
    public String indirizzo; //nome via, numero civico
    public String comune;
    public int cap;
    public String provincia;

    CentroVaccinale(String nome, String indirizzo, String comune, String provincia, int cap, String tipologia){
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.comune = comune;
        this.provincia = provincia;
        this.tipologia = tipologia;
        this.cap = cap;
    }

    public String getNome(){
        return this.nome;
    }

    public String getTipologia() {
        return this.tipologia;
    }

    public String getIndirizzo() {
        return this.indirizzo;
    }

    public String getComune() {
        return this.comune;
    }

    public int getCap() {
        return this.cap;
    }

    public String getProvincia() {
        return this.provincia;
    }

    @Override
    public String toString() {
        return "Centro Vaccinale "+nome+
                ", tipologia: " + tipologia+
                ", indirizzo: "+indirizzo+
                ", comune: "+comune+
                ", cap: "+cap+
                ", provincia: "+provincia;
    }
}
