package it.uninsubria.progetto_centrivaccinali;

import java.io.*;

public class EventiAvversi implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String centroVaccinale;
    private int valoreFebbre;
    private String notaFebbre;
    private int valoreMalDiTesta;
    private String notaMdT;
    private int valoreDolori;
    private String notaDolori;
    private int valoreLinfoadenopatia;
    private String notaLinfoadenopatia;
    private int valoreTachicardia;
    private String notaTachicardia;
    private int valoreCrisiI;
    private String notaCrisiI;

    /**
     *
     * @param id
     * @param centro
     * @param febbre
     * @param notaFebbre
     * @param testa
     * @param notaMdT
     * @param dolori
     * @param notaDolori
     * @param linfoadenopatia
     * @param notaLinfo
     * @param tachicardia
     * @param notaTach
     * @param crisi
     * @param notaCrisi
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

    public int getId() {
        return id;
    }

    public String getCentroVaccinale() {
        return centroVaccinale;
    }

    public int getValoreMalDiTesta() {
        return valoreMalDiTesta;
    }
    public String getNotaMdT() {
        return notaMdT;
    }

    public int getValoreFebbre() {
        return valoreFebbre;
    }
    public String getNotaFebbre() {
        return notaFebbre;
    }

    public int getValoreDolori() {
        return valoreDolori;
    }
    public String getNotaDolori() {
        return notaDolori;
    }

    public int getValoreLinfoadenopatia() {
        return valoreLinfoadenopatia;
    }
    public String getNotaLinfoadenopatia() {
        return notaLinfoadenopatia;
    }

    public int getValoreTachicardia() {
        return valoreTachicardia;
    }
    public String getNotaTachicardia() {
        return notaTachicardia;
    }

    public int getValoreCrisiI() {
        return valoreCrisiI;
    }
    public String getNotaCrisiI() {
        return notaCrisiI;
    }
}
