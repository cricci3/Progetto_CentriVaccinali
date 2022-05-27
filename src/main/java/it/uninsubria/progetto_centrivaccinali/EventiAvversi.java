package it.uninsubria.progetto_centrivaccinali;

import java.io.*;

/**
 * EventiAvversi
 */
public class EventiAvversi implements Serializable {
    /**
     * serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private int id;
    /**
     * centro vaccinale nome
     */
    private String centroVaccinale;
    /**
     * valore evento avverso
     */
    private int valoreFebbre;
    /**
     * nota evento avverso
     */
    private String notaFebbre;
    /**
     * valore evento avverso
     */
    private int valoreMalDiTesta;
    /**
     * nota evento avverso
     */
    private String notaMdT;
    /**
     * valore evento avverso
     */
    private int valoreDolori;
    /**
     * nota evento avverso
     */
    private String notaDolori;
    /**
     * valore evento avverso
     */
    private int valoreLinfoadenopatia;
    /**
     * nota evento avverso
     */
    private String notaLinfoadenopatia;
    /**
     * valore evento avverso
     */
    private int valoreTachicardia;
    /**
     * nota evento avverso
     */
    private String notaTachicardia;
    /**
     * valore evento avverso
     */
    private int valoreCrisiI;
    /**
     * nota evento avverso
     */
    private String notaCrisiI;

    /**
     * Costruttore
     * @param id int id univoco
     * @param centro String nome centro vaccinale
     * @param febbre int valore intensità febbre
     * @param notaFebbre String note febbre
     * @param testa int valore intensità mal di testa
     * @param notaMdT String note mal di testa
     * @param dolori int valore intensità valori muscolari e articolari
     * @param notaDolori String note dolori muscolari e articolari
     * @param linfoadenopatia int valore intensità linfoadenopatia
     * @param notaLinfo String note Linfoadenopatia
     * @param tachicardia int intensità tachicardia
     * @param notaTach String note tachicardia
     * @param crisi int intensità crisi ipertensiva
     * @param notaCrisi String note crisi ipertensiva
     */
    EventiAvversi(int id, String centro, int febbre, String notaFebbre, int testa, String notaMdT,
                  int dolori, String notaDolori, int linfoadenopatia, String notaLinfo,
                  int tachicardia, String notaTach, int crisi, String notaCrisi){
        this.id = id;
        this.centroVaccinale = centro;
        this.valoreFebbre=febbre;
        this.notaFebbre=notaFebbre;
        this.valoreMalDiTesta=testa;
        this.notaMdT=notaMdT;
        this.valoreDolori=dolori;
        this.notaDolori=notaDolori;
        this.valoreLinfoadenopatia=linfoadenopatia;
        this.notaLinfoadenopatia=notaLinfo;
        this.valoreTachicardia=tachicardia;
        this.notaTachicardia=notaTach;
        this.valoreCrisiI=crisi;
        this.notaCrisiI=notaCrisi;
    }

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @return centro vaccinale
     */
    public String getCentroVaccinale() {
        return centroVaccinale;
    }

    /**
     * @return valore mal di testa
     */
    public int getValoreMalDiTesta() {
        return valoreMalDiTesta;
    }

    /**
     * @return nota mal di testa
     */
    public String getNotaMdT() {
        return notaMdT;
    }

    /**
     * @param id che si vuole impostare
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param centroVaccinale nome del centro vaccinale che si vuole impostare
     */
    public void setCentroVaccinale(String centroVaccinale) {
        this.centroVaccinale = centroVaccinale;
    }

    /**
     * @param valoreFebbre intensità febbre che si vuole impostare
     */
    public void setValoreFebbre(int valoreFebbre) {
        this.valoreFebbre = valoreFebbre;
    }

    /**
     * @param notaFebbre nota febbre che si vuole impostare
     */
    public void setNotaFebbre(String notaFebbre) {
        this.notaFebbre = notaFebbre;
    }

    /**
     * @param valoreMalDiTesta che si vuole impostare
     */
    public void setValoreMalDiTesta(int valoreMalDiTesta) {
        this.valoreMalDiTesta = valoreMalDiTesta;
    }

    /**
     * @param notaMdT note mal di testa che si vuole impostare
     */
    public void setNotaMdT(String notaMdT) {
        this.notaMdT = notaMdT;
    }

    /**
     * @param valoreDolori che si vuole impostare
     */
    public void setValoreDolori(int valoreDolori) {
        this.valoreDolori = valoreDolori;
    }

    /**
     * @param notaDolori che si vuole impostare
     */
    public void setNotaDolori(String notaDolori) {
        this.notaDolori = notaDolori;
    }

    /**
     * @param valoreLinfoadenopatia che si vuole impostare
     */
    public void setValoreLinfoadenopatia(int valoreLinfoadenopatia) {
        this.valoreLinfoadenopatia = valoreLinfoadenopatia;
    }

    /**
     * @param notaLinfoadenopatia che si vuole impostare
     */
    public void setNotaLinfoadenopatia(String notaLinfoadenopatia) {
        this.notaLinfoadenopatia = notaLinfoadenopatia;
    }

    /**
     * @param valoreTachicardia che si vuole impostare
     */
    public void setValoreTachicardia(int valoreTachicardia) {
        this.valoreTachicardia = valoreTachicardia;
    }

    /**
     * @param notaTachicardia che si vuole impostare
     */
    public void setNotaTachicardia(String notaTachicardia) {
        this.notaTachicardia = notaTachicardia;
    }

    /**
     * @param valoreCrisiI che si vuole impostare
     */
    public void setValoreCrisiI(int valoreCrisiI) {
        this.valoreCrisiI = valoreCrisiI;
    }

    /**
     * @param notaCrisiI che si vuole impostare
     */
    public void setNotaCrisiI(String notaCrisiI) {
        this.notaCrisiI = notaCrisiI;
    }

    /**
     * @return valore febbre
     */
    public int getValoreFebbre() {
        return valoreFebbre;
    }

    /**
     * @return nota mal di testa
     */
    public String getNotaFebbre() {
        return notaFebbre;
    }

    /**
     * @return valore dolori muscolari e articolari
     */
    public int getValoreDolori() {
        return valoreDolori;
    }

    /**
     * @return nota dolori muscolari e articolari
     */
    public String getNotaDolori() {
        return notaDolori;
    }

    /**
     * @return valore linfoadenopatia
     */
    public int getValoreLinfoadenopatia() {
        return valoreLinfoadenopatia;
    }

    /**
     * @return nota linfoadenopatia
     */
    public String getNotaLinfoadenopatia() {
        return notaLinfoadenopatia;
    }

    /**
     * @return valore tachicardia
     */
    public int getValoreTachicardia() {
        return valoreTachicardia;
    }

    /**
     * @return nota tachicardia
     */
    public String getNotaTachicardia() {
        return notaTachicardia;
    }

    /**
     * @return valore crisi ipertensiva
     */
    public int getValoreCrisiI() {
        return valoreCrisiI;
    }

    /**
     * @return nota crisi ipertensiva
     */
    public String getNotaCrisiI() {
        return notaCrisiI;
    }
}
