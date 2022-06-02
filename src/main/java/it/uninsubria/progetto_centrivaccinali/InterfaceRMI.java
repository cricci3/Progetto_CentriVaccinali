package it.uninsubria.progetto_centrivaccinali;

import java.rmi.*;
import java.util.*;
/**
 * InterfaceRMI
 * @author Macrina Alessandro mtr. 737128 VA
 * @author Ricci Claudio mtr. 747555 VA
 */
public interface InterfaceRMI extends Remote {
    /**
     * Aggiungi centro vaccinale
     * @param centro oggetto CentroVaccinale
     * @return true se centro aggiunto, false altrimenti
     * @throws RemoteException Eccezione
     */
    boolean addCentroVaccinale(CentroVaccinale centro) throws RemoteException;
    /**
     * Aggiungi cittadino vaccinato
     * @param cittadino oggetto CittadinoVaccinato
     * @return true se cittadino aggiunto, false altrimenti
     * @throws RemoteException Eccezione
     */
    boolean addCittadinoVaccinato(CittadinoVaccinato cittadino) throws RemoteException;
    /**
     * Aggiungi cittadino registrato
     * @param id int ID
     * @param nomeCV String nome centro vaccinale
     * @param nome String nome
     * @param cognome String cognome
     * @param email String email
     * @param username String username
     * @param password String password
     * @return true se cittadino aggiunto, false altrimenti
     * @throws RemoteException Eccezione
     */
    boolean addCittadinoRegistrato(int id, String nomeCV, String nome, String cognome, String email, String username, String password) throws RemoteException; //da implementare per migliorare registrazione

    /**
     * Aggiunge eventi avversi
     * @param eventi oggetto EventiAvversi
     * @return Messaggio errore o conferma inserimento dati
     * @throws RemoteException Eccezione
     */
    String addEventiAvversi(EventiAvversi eventi) throws RemoteException;

    /**
     * controlla se id già registrato
     * @param id int ID
     * @param nomeCV String nome centro vaccinale
     * @return true se è già registrato, false altrimenti
     * @throws RemoteException Eccezione
     */
    boolean controlloRegistrazione(int id, String nomeCV) throws RemoteException;

    /**
     * login operatore
     * @param username String username
     * @param password String password
     * @return true se credenziali corrette, false altrimenti
     * @throws RemoteException Eccezione
     */
    boolean loginOperatore(String username, String password) throws RemoteException;

    /**
     * login cittadino
     * @param username String username
     * @param password String password
     * @return true se credenziali corrette, false altrimenti
     * @throws RemoteException Eccezione
     */
    int loginCittadino(String username, String password) throws RemoteException;

    /**
     * crea lista con le informazioni del centro passato come parametro
     * @param centro String nome centro
     * @return arraylist
     * @throws RemoteException Eccezione
     */
    ArrayList<CentroVaccinale> getInfoCentroVaccinale(String centro) throws RemoteException;

    /**
     * fa medie degli eventi avversi relativi a un centro
     * @param centro String nome centro
     * @return arrayList con medie
     * @throws RemoteException Eccezione
     */
    ArrayList<Double> visualizzaInfoCentroVaccinale(String centro) throws RemoteException;

    /**
     * Cerca centri vaccinali che contenengono il nome cercato
     * @param nomeCV String nome centro
     * @return arrayList con centri vaccinali
     * @throws RemoteException Eccezione
     */
    ArrayList<CentroVaccinale> cercaCentroVaccinale(String nomeCV) throws RemoteException;

    /**
     * Cerca centri vaccinali nel comune cercato e della tipologia cercata
     * @param comune String comune centro vaccinale
     * @param tipologia String tipologia
     * @return arrayList con centri vaccinali
     * @throws RemoteException Eccezione
     */
    ArrayList<CentroVaccinale> cercaCentroVaccinale(String comune, String tipologia) throws RemoteException;

}