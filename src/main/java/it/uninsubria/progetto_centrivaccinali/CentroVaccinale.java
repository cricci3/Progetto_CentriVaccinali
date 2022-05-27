package it.uninsubria.progetto_centrivaccinali;

import java.io.*;

/**
 * Classe CentroVaccinale
 * @author Claudio Ricci
 * @author Alessandro Macrina
 */
public class CentroVaccinale implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * nome
     */
    public String nome;
    /**
     * tipologia
     */
    public String tipologia;
    /**
     * indirizzo
     */
    public String indirizzo; //nome via, numero civico
    /**
     * comune
     */
    public String comune;
    /**
     * cap
     */
    public int cap;
    /**
     * provincia
     */
    public String provincia;

    /**
     * Costruttore
     * @param nome Stringa rappresentante il nome del centro vaccinale
     * @param indirizzo Stringa rappresentante indirizzo
     * @param comune Stringa rappresentante comune del centro
     * @param provincia Stringa rappresentante sigla della provincia del centro vaccinale
     * @param cap int rappresentante cap del comune del centro vaccinale
     * @param tipologia Stringa rappresentante tipologia del centro vaccinale
     */
    CentroVaccinale(String nome, String indirizzo, String comune, String provincia, int cap, String tipologia){
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.comune = comune;
        this.provincia = provincia;
        this.tipologia = tipologia;
        this.cap = cap;
    }

    /**
     * @return nome del centro vaccinale che chiama il metodo
     */
    public String getNome(){
        return this.nome;
    }

    /**
     * @return tipologia del centro vaccinale che chiama il metodo
     */
    public String getTipologia() {
        return this.tipologia;
    }
    /**
     * @return indirizzo del centro vaccinale che chiama il metodo
     */
    public String getIndirizzo() {
        return this.indirizzo;
    }

    /**
     * @return comune del centro vaccinale che chiama il metodo
     */
    public String getComune() {
        return this.comune;
    }

    /**
     * @return cap del centro vaccinale che chiama il metodo
     */
    public int getCap() {
        return this.cap;
    }

    /**
     * @return provincia del centro vaccinale che chiama il metodo
     */
    public String getProvincia() {
        return this.provincia;
    }

    /**
     * Metodo che permette di modificare il parametro nome
     * @param nome String rappresentante il nome del centro vaccinale che si vuole impostare
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo che permette di modificare il parametro tipologia
     * @param tipologia String rappresentante la tipologia che si vuole impostare
     */
    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    /**
     * Metodo che permette di modificare il parametro indirizzo
     * @param indirizzo String rappresentante l'indirizzo che si vuole impostare
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Metodo che permette di modificare il parametro comune
     * @param comune String rappresentante il comune che si vuole impostare
     */
    public void setComune(String comune) {
        this.comune = comune;
    }

    /**
     * Metodo che permette di modificare il parametro cap
     * @param cap int rappresentante il cap che si vuole impostare
     */
    public void setCap(int cap) {
        this.cap = cap;
    }

    /**
     * Metodo che permette di modificare il parametro provincia
     * @param provincia String rappresentante la provincia che si vuole impostare
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * @return String rappresentante tutte le informazioni di un oggetto CentroVaccinale
     */
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
