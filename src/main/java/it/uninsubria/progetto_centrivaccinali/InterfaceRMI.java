package it.uninsubria.progetto_centrivaccinali;

import java.rmi.*;
import java.util.*;

public interface InterfaceRMI extends Remote {
    boolean addCentroVaccinale(CentroVaccinale centro) throws RemoteException;
    boolean addCittadinoVaccinato(CittadinoVaccinato cittadino) throws RemoteException;
    boolean addCittadinoRegistrato(int id, String nomeCV, String nome, String cognome, String email, String username, String password) throws RemoteException; //da implementare per migliorare registrazione
    String addEventiAvversi(EventiAvversi eventi) throws RemoteException;

    boolean controlloRegistrazione(int id, String nomeCV) throws RemoteException;
    boolean loginOperatore(String username, String password) throws RemoteException;
    int loginCittadino(String username, String password) throws RemoteException;
    ArrayList<CentroVaccinale> getInfoCentroVaccinale(String centro) throws RemoteException;
    ArrayList<Double> visualizzaInfoCentroVaccinale(String centro) throws RemoteException;
    ArrayList<CentroVaccinale> cercaCentroVaccinale(String nomeCV) throws RemoteException;
    ArrayList<CentroVaccinale> cercaCentroVaccinale(String comune, String tipologia) throws RemoteException;

}