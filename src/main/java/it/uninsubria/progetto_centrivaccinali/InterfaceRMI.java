package it.uninsubria.progetto_centrivaccinali;

import java.rmi.*;
import java.util.*;

public interface InterfaceRMI extends Remote {
    //metodi di modifica del db
    //restituiscono solamente true/false se operazione riuscita/fallita
    public boolean addCentroVaccinale(CentroVaccinale centro) throws RemoteException;//funzionante
    public boolean addCittadinoVaccinato(CittadinoVaccinato cittadino) throws RemoteException;//funzionante
    public boolean addCittadinoRegistrato(int id, String nomeCV, String nome, String cognome, String email, String username, String password) throws RemoteException; //funzionante (ma migliorabile)
    public boolean addCittadinoRegistrato2(int id, String nomeCV, String nome, String cognome, String email, String username, String password) throws RemoteException; //da implementare per migliorare registrazione
    public boolean addEventiAvversi(EventiAvversi eventi) throws RemoteException; //funziona male

    public boolean controlloRegistrazione(int id) throws RemoteException; //si può implementare per migliorare registrazione

    public boolean loginOperatore(String username, String password) throws RemoteException;
    public boolean loginOperatore2(String username, String password) throws RemoteException;
    public boolean loginCittadino(String username, String password) throws RemoteException;

    //metodi lettura db
    //restiuiscono dei valori che cittadino deve leggere, server rimanda robe al client
    public String visualizzaInfoCentroVaccinale(CentroVaccinale centro) throws RemoteException; //non funziona
    public ArrayList<CentroVaccinale> cercaCentroVaccinale(String nomeCV) throws RemoteException; //non funziona
    public String cercaCentroVaccinale(String comune, String tipologia) throws RemoteException;
}