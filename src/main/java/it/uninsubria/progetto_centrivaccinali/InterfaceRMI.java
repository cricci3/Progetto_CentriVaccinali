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
    public String addEventiAvversi(EventiAvversi eventi) throws RemoteException; //funziona male

    public boolean controlloRegistrazione(int id, String nomeCV) throws RemoteException; //si può implementare per migliorare registrazione

    public boolean loginOperatore(String username, String password) throws RemoteException;
    public boolean loginOperatore2(String username, String password) throws RemoteException;
    public boolean loginCittadino(String username, String password) throws RemoteException;
    public int loginCittadinoID(String username, String password) throws RemoteException;

    public ArrayList<CentroVaccinale> getInfoCentroVaccinale(String centro) throws RemoteException;
    public ArrayList<Double> getMedieCentroVaccinale(String centro) throws RemoteException;
    public ArrayList<CentroVaccinale> cercaCentroVaccinale(String nomeCV) throws RemoteException;
    public ArrayList<CentroVaccinale> cercaCentroVaccinale(String comune, String tipologia) throws RemoteException;

}