package it.uninsubria.progetto_centrivaccinali;

import java.io.*;

public class EventiAvversi implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String centroVaccinale;
    private int valoreMalDiTesta;
    private String notaMdT;
    private int valoreFebbre;
    private String notaFebbre;
    private int valoreDolori;
    private String notaDolori;
    private int valoreLinfoadenopatia;
    private String notaLinfoadenopatia;
    private int valoreTachicardia;
    private String notaTachicardia;
    private int valoreCrisiI;
    private String notaCrisiI;

    EventiAvversi(int id, String centro, int testa, String notaMdT, int febbre, String notaFebbre,
                  int dolori, String notaDolori, int linfoadenopatia, String notaLinfo,
                  int tachicardia, String notaTach, int crisi, String notaCrisi){
        this.id = id;
        this.centroVaccinale = centro;
        this.valoreMalDiTesta=testa;
        this.notaMdT=notaMdT;
        this.valoreFebbre=febbre;
        this.notaFebbre=notaFebbre;
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
