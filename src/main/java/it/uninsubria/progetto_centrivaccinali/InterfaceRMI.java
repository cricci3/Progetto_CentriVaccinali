package it.uninsubria.progetto_centrivaccinali;

import java.rmi.*;
import java.util.*;
/**
 * @author Macrina Alessandro mtr. 737128 VA
 * @author Ricci Claudio mtr. 747555 VA
 */
public interface InterfaceRMI extends Remote {
    /**
     * Aggiungi centro vaccinale
     * @param centro
     * @return true se centro aggiunto, false altrimenti
     * @throws RemoteException
     */
    boolean addCentroVaccinale(CentroVaccinale centro) throws RemoteException;
    /**
     * Aggiungi cittadino vaccinato
     * @param cittadino
     * @return true se cittadino aggiunto, false altrimenti
     * @throws RemoteException
     */
    boolean addCittadinoVaccinato(CittadinoVaccinato cittadino) throws RemoteException;
    /**
     * Aggiungi cittadino registrato
     * @param id
     * @param nomeCV
     * @param nome
     * @param cognome
     * @param email
     * @param username
     * @param password
     * @return true se cittadino aggiunto, false altrimenti
     * @throws RemoteException
     */
    boolean addCittadinoRegistrato(int id, String nomeCV, String nome, String cognome, String email, String username, String password) throws RemoteException; //da implementare per migliorare registrazione

    /**
     * Aggiunge eventi avversi
     * @param eventi
     * @return Messaggio errore o ok
     * @throws RemoteException
     */
    String addEventiAvversi(EventiAvversi eventi) throws RemoteException;

    /**
     * controlla se id già registrato
     * @param id
     * @param nomeCV
     * @return true se è già registrato, false altrimenti
     * @throws RemoteException
     */
    boolean controlloRegistrazione(int id, String nomeCV) throws RemoteException;

    /**
     * login operatore
     * @param username
     * @param password
     * @return true se credenziali corrette, false altrimenti
     * @throws RemoteException
     */
    boolean loginOperatore(String username, String password) throws RemoteException;

    /**
     * login cittadino
     * @param username
     * @param password
     * @return true se credenziali corrette, false altrimenti
     * @throws RemoteException
     */
    int loginCittadino(String username, String password) throws RemoteException;

    /**
     * crea lista con le informazioni del centro passato come parametro
     * @param centro
     * @return arraylist
     * @throws RemoteException
     */
    ArrayList<CentroVaccinale> getInfoCentroVaccinale(String centro) throws RemoteException;

    /**
     * fa medie degli eventi avversi relativi a un centro
     * @param centro
     * @return arrayList con medie
     * @throws RemoteException
     */
    ArrayList<Double> visualizzaInfoCentroVaccinale(String centro) throws RemoteException;

    /**
     * Cerca centri vaccinali che contenengono il nome cercato
     * @param nomeCV
     * @return arrayList con centri vaccinali
     * @throws RemoteException
     */
    ArrayList<CentroVaccinale> cercaCentroVaccinale(String nomeCV) throws RemoteException;

    /**
     * Cerca centri vaccinali nel comune cercato e della tipologia cercata
     * @param comune
     * @param tipologia
     * @return arrayList con centri vaccinali
     * @throws RemoteException
     */
    ArrayList<CentroVaccinale> cercaCentroVaccinale(String comune, String tipologia) throws RemoteException;

}