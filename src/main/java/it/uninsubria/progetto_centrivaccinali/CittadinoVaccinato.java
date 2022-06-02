package it.uninsubria.progetto_centrivaccinali;

import java.io.*;

/**
 * Classe CittadinoVaccinato
 * @author Claudio Ricci mtr. 747555, VA
 * @author Alessandro Macrina mtr. 737128, VA
 */
public class CittadinoVaccinato implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * nome
     */
    public String nome;
    /**
     * cognome
     */
    public String cognome;
    /**
     * nome centro vaccinale
     */
    public String nomeCV;
    /**
     * codice fiscale
     */
    public String cf;
    /**
     * data vaccinazione
     */
    public String dataVaccinazione;
    /**
     * nome vaccino
     */
    public String nomeVaccino;
    /**
     * id
     */
    public int idUnivoco;

    /**
     * Costruttore
     * @param nomeCV String rappresentante il nome del centro vaccinale
     * @param id int rappresentnate id a 8 bit
     * @param nome String rappresentante nome del cittadino vaccinato
     * @param cognome String rappresentante cognome del cittadino vaccinato
     * @param cf String rappresentante codice fiscale del cittadino vaccinato
     * @param data String rappresentante data di vaccinazione
     * @param nomeV String rappresentante nome del vaccino somministrato
     */
    CittadinoVaccinato(String nomeCV, int id, String nome, String cognome, String cf, String data, String nomeV){
        this.nomeCV=nomeCV;
        this.idUnivoco=id;
        this.nome=nome;
        this.cognome=cognome;
        this.cf=cf;
        this.dataVaccinazione=data;
        this.nomeVaccino=nomeV;
    }

    /**
     * @return nome del CittadinoVaccinato che chiama il metodo
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * @return cognome del CittadinoVaccinato che chiama il metodo
     */
    public String getCognome() {
        return this.cognome;
    }

    /**
     * @return nome centro vaccinale del CittadinoVaccinato che chiama il metodo
     */
    public String getNomeCV() {
        return this.nomeCV;
    }

    /**
     * @return codice fiscale del CittadinoVaccinato che chiama il metodo
     */
    public String getCf() {
        return this.cf;
    }

    /**
     * @return data vaccinazione del CittadinoVaccinato che chiama il metodo
     */
    public String getDataVaccinazione() {
        return this.dataVaccinazione;
    }

    /**
     * @return nome vaccino del CittadinoVaccinato che chiama il metodo
     */
    public String getNomeVaccino() {
        return this.nomeVaccino;
    }

    /**
     * Metodo che permette di modificare il parametro nome
     * @param nome String rappresentante il nome che si vuole impostare
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo che permette di modificare il parametro cognome
     * @param cognome String rappresentante il cognome che si vuole impostare
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Metodo che permette di modificare il parametro nome centro vaccinale
     * @param nomeCV String rappresentante il nome del centro vaccinale che si vuole impostare
     */
    public void setNomeCV(String nomeCV) {
        this.nomeCV = nomeCV;
    }

    /**
     * Metodo che permette di modificare il parametro codice fiscale
     * @param cf String rappresentante il codice fiscale che si vuole impostare
     */
    public void setCf(String cf) {
        this.cf = cf;
    }

    /**
     * Metodo che permette di modificare il parametro data vaccinazione
     * @param dataVaccinazione String rappresentante la data che si vuole impostare
     */
    public void setDataVaccinazione(String dataVaccinazione) {
        this.dataVaccinazione = dataVaccinazione;
    }

    /**
     * Metodo che permette di modificare il parametro nome vaccino
     * @param nomeVaccino String rappresentante il nome vaccino che si vuole impostare
     */
    public void setNomeVaccino(String nomeVaccino) {
        this.nomeVaccino = nomeVaccino;
    }

    /**
     * Metodo che permette di modificare il parametro ID univoco
     * @param idUnivoco int rappresentante l'ID univoco che si vuole impostare
     */
    public void setIdUnivoco(int idUnivoco) {
        this.idUnivoco = idUnivoco;
    }

    /**
     * @return id del CittadinoVaccinato che chiama il metodo
     */
    public int getIdUnivoco() {
        return this.idUnivoco;
    }

    /**
     * @return String rappresentante tutte le informazioni di un oggetto CittadinoVaccinato
     */
    @Override
    public String toString() {
        return "Cittadino Vaccinato -> nome: "+nome+", cognome: "+cognome+", nomeCV: "
                +nomeCV+", cf: "+cf+", dataVaccinazione: "+dataVaccinazione+", nomeVaccino: "
                +nomeVaccino+", idUnivoco: " + idUnivoco;
    }
}
