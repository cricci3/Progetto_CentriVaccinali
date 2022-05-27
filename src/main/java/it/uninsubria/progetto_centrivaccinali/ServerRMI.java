package it.uninsubria.progetto_centrivaccinali;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.sql.*;
import java.util.*;

/**
 * @author Macrina Alessandro mtr. 737128 VA
 * @author Ricci Claudio mtr. 747555 VA
 */
public class ServerRMI extends UnicastRemoteObject implements InterfaceRMI {
    /**
     * serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Istanza database
     */
    Database db;
    {
        try {
            db = Database.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Istanza Datatables
     */
    DataTables dt = new DataTables();

    /**
     * Costruttore del Server
     * @throws RemoteException Eccezione
     */
    protected ServerRMI() throws RemoteException {
        super();
    }

    /**
     * Metodo che permette di inserire un centro vaccinale all'interno della tabella "centrivaccinali" nel database
     * @param cv Oggetto centro vaccinale che verrà inserito nel databse
     * @return true se la query viene eseguita correttamente, false in caso contrario
     */
    @Override
    public synchronized boolean addCentroVaccinale(CentroVaccinale cv) throws RemoteException{
        try{
            String query = "INSERT INTO centrivaccinali VALUES ('" + cv.getNome() + "','" + cv.getIndirizzo() + "','"
                    + cv.getComune() + "','" + cv.getProvincia() + "','" + cv.getTipologia() + "','" + cv.getCap() + "')";
            db.submitQuery(query);

            query = "CREATE TABLE Vaccinati_"+cv.getNome()+" ("
                    +"idcittadino INTEGER PRIMARY KEY,"
                    +"nome VARCHAR(50) NOT NULL,"
                    +"cognome VARCHAR(50) NOT NULL,"
                    +"cf VARCHAR(16) NOT NULL,"
                    +"dataVaccinazione DATE NOT NULL,"
                    +"nomeVaccino VARCHAR(50) NOT NULL )";
            db.submitQuery(query);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Metodo che permette di inserire un cittadino vaccinato presso un centro vaccinale all'interno della tabella "vaccinati_nomeCentroVaccinale" nel database
     * @param cittV oggetto cittadinoVaccinato che verrà inserito nel database
     * @return true se la query viene eseguita correttamente, false in caso contrario
     */
    @Override
    public synchronized boolean addCittadinoVaccinato(CittadinoVaccinato cittV) throws RemoteException {
        String query = "INSERT INTO vaccinati_"+cittV.getNomeCV()+" VALUES ('" + cittV.getIdUnivoco() + "','" + cittV.getNome() + "','"
                + cittV.getCognome() + "','" + cittV.getCf() + "','" + cittV.getDataVaccinazione() + "','" + cittV.getNomeVaccino() + "')";
        try {
            db.submitQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Metodo che permette di controllare se un cittadino è già registrato. Il metodo fa una ricerca all'interno della tabella "cittadini_registrati"
     * @param id numero intero che rappresenta l'id del cittadino di cui vogliamo controllare la registrazione
     * @param nomeCV Stringa rappresentante il nome del centro vaccinale presso cui dichiara di essersi registrato il cittadino
     * @return true se l'id passato come parametro viene trovato nella ricerca e quindi il cittadino è già registrato. false se all'id non corrisponde nessun cittadino registrato
     */
    @Override
    public boolean controlloRegistrazione(int id, String nomeCV) throws RemoteException {
        String query = "SELECT * FROM cittadini_registrati WHERE idcittadino='"+id+"'";
        boolean registrato = false;
        try {
            ResultSet rs = db.submitQuery(query);
            DataTables dt = new DataTables();
            dt.handleCittadiniRegistratiSet(rs, nomeCV);
            ArrayList<CittadinoRegistrato> list = dt.getCittadiniRegistratiTable();
            if(!list.isEmpty()){
                registrato = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return registrato;
    }

    /**
     * Metodo che tramite i parametri passati effettua una nuova registrazione di un cittadino nella tabella del database "cittadini_registrati"
     * @param id numero intero rappresentante id del cittadino
     * @param nomeCV String rappresentante il nome del centro vaccinale presso cui il cittadino si è vaccinato
     * @param nome String rappresentante il nome del cittadino
     * @param cognome String rappresentante il cognome del cittadino
     * @param email String rappresentante la mail del cittadino
     * @param username String rappresentante lo username del cittadino
     * @param password String rappresentante la password inserita dal cittadino
     * @return true se query di inserimento va a buon fine, false altrimenti
     */
    @Override
    public boolean addCittadinoRegistrato(int id, String nomeCV, String nome, String cognome, String email, String username, String password) throws RemoteException {
        String query = "SELECT * FROM vaccinati_"+nomeCV+" WHERE idcittadino='"+id+"'";
        DataTables dt = new DataTables();
        boolean response = false;
        try {
            ResultSet rs = db.submitQuery(query);
            dt.handleCittadiniVaccinatiSet(rs, nomeCV);
            ArrayList<CittadinoVaccinato> list = dt.getCittadiniVaccinatiTable();
            if(!list.isEmpty()){
                CittadinoVaccinato cittadino = list.get(0);
                query = "INSERT INTO cittadini_registrati VALUES ('"+id+"','"+nome+"','"+cognome+"','"+ cittadino.cf+"','"+email+"','"+username+"','"+password+"')";
                try{
                    db.submitQuery(query);
                    response = true;
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Metodo che inserisce nella tabella "eventiavversi" gli eventi avversi inseriti da un cittadino
     * @param eventi Oggetto EventiAvversi che contiene tutti i dati
     * @return Una stringa che comunica l'avvenuta o meno dell'inserimento degli eventi avversi
     */
    @Override
    public String addEventiAvversi(EventiAvversi eventi) throws RemoteException {
        String queryControlloCentro = "SELECT * FROM vaccinati_"+eventi.getCentroVaccinale()+" WHERE idcittadino = '"+eventi.getId()+"'";
        try {
            ResultSet rs = db.submitQuery(queryControlloCentro);
            DataTables dt = new DataTables();
            dt.handleCittadiniVaccinatiSet(rs, eventi.getCentroVaccinale());
            ArrayList<CittadinoVaccinato> cittadinoList = dt.getCittadiniVaccinatiTable();
            if(!cittadinoList.isEmpty()){
                String queryInserimento = "INSERT INTO eventiavversi VALUES ('"+eventi.getId()+"','"+eventi.getCentroVaccinale()+"','"
                        +eventi.getValoreFebbre()+"','"+eventi.getNotaFebbre()+"','"+eventi.getValoreMalDiTesta()+"','"+eventi.getNotaMdT()+"','"
                        +eventi.getValoreDolori()+"','"+eventi.getNotaDolori()+"','"+eventi.getValoreLinfoadenopatia()+"','"+eventi.getNotaLinfoadenopatia()+"','"
                        +eventi.getValoreTachicardia()+"','"+eventi.getNotaTachicardia()+"','"+eventi.getValoreCrisiI()+"','"+eventi.getNotaCrisiI()+"')";
                try {
                    db.submitQuery(queryInserimento);
                }catch (SQLException e){
                    e.printStackTrace();
                    return "ERRORE nel inserimento dei dati";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "ERRORE, centro vaccinale inserito errato";
        }
        return "Dati inseriti correttamente";
    }

    /**
     * Metodo che controla se username e password inseriti da operatore vaccinale siano corretti, effettua una ricerca sulla tabella del database "operatori"
     * @param username Stringa rappresentante username operatore
     * @param password Stringa rappresentante password operatore
     * @return true se le credenziali sono state trovate nella tabella, false altrimenti
     */
    @Override
    public boolean loginOperatore(String username, String password) throws RemoteException {
        String query = "SELECT * FROM operatori WHERE username = '"+username+"' AND password = '"+password+"'";
        boolean response = false;
        try {
            ResultSet rs = db.submitQuery(query);
            dt.handleOperatoriSet(rs);
            ArrayList<Operatore> lista = dt.getOperatoriTable();
            if(!lista.isEmpty()){
                for (Operatore operatore : lista){
                    System.out.println(operatore.toString());
                }
                response = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Metodo che controla se username e password inseriti da cittadino siano corretti, effettua una ricerca sulla tabella del database "cittadini_registrati"
     * @param username Stringa rappresentante username cittadino registrato
     * @param password Stringa rappresentante password cittadino registrato
     * @return true se le credenziali sono state trovate nella tabella, false altrimenti
     */
    @Override
    public int loginCittadino(String username, String password) throws RemoteException {
        String query = "SELECT * FROM cittadini_registrati WHERE username = '"+username+"' AND password = '"+password+"'";
        try {
            ResultSet rs = db.submitQuery(query);
            dt.handleLoginCittadini(rs);
            ArrayList<String> listaCredenziali = dt.getLoggatiTable();
            ArrayList<Integer> listId = dt.getListIdTable();
            if(!listaCredenziali.isEmpty()){
                int id = listId.get(0);
                return id;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Metodo che permette di trovare un centro vaccinale
     * @param centro Stringa rappresentante il nome del centro vaccinale che si vuole cercare
     * @return ArrayList<CentroVaccinale> contente il centro vaccinale cercato
     */
    @Override
    public ArrayList<CentroVaccinale> getInfoCentroVaccinale(String centro) throws RemoteException {
        String query = "SELECT * FROM centrivaccinali WHERE nome = '"+centro+"'";
        ArrayList<CentroVaccinale> infoCentro = new ArrayList<>();
        try {
            ResultSet rs = db.submitQuery(query);
            DataTables dt = new DataTables();
            infoCentro = dt.handleCentriVaccinaliSet(rs);
            if(!infoCentro.isEmpty()){
                return infoCentro;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return infoCentro;
    }

    /**
     * Metodo che permette di calcolare le medie degli eventi avversi relativi a un centro vaccinale
     * @param centro Stringa rappresentante il nome del centro vaccinale di cui si vogliono calcolare le medie
     * @return ArrayList<Double> contente le medie ti tutti gli eventi avversi
     */
    @Override
    public ArrayList<Double> visualizzaInfoCentroVaccinale(String centro) throws RemoteException {
        String query = "SELECT avg(febbre) as febbre," +
                "avg(malditesta) as malDiTesta," +
                "avg(dolorim) as doloriMuscolari," +
                "avg(linfoadenopatia) as linfoadenopatia," +
                "avg(tachicardia) as tachicardia," +
                "avg(crisii) as crisiIpertensive "+
                "FROM eventiavversi WHERE nomecv = '"+centro+"'";
        ArrayList<Double> listaMedie = new ArrayList<>();
        try {
            ResultSet rs = db.submitQuery(query);
            DataTables dt = new DataTables();
            dt.handleMedieEventiAvversi(rs);
            listaMedie = dt.getMedieEventiAvversiTable();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaMedie;
    }

    /**
     * Metodo che permette di trovare un centro vaccinale passata una stringa rappresentante il nome cercato
     * @param nomeCV Stringa rappresentante il nome del centro vaccinale cercato
     * @return ArrayList<CentroVaccinale> contenente tutte le informazioni del centro trovato
     */
    @Override
    public ArrayList<CentroVaccinale> cercaCentroVaccinale(String nomeCV) throws RemoteException {
        DataTables dt = new DataTables();
        ArrayList<CentroVaccinale> listaRisultato = new ArrayList<>();
        String query = "SELECT * FROM centrivaccinali WHERE nome LIKE '%"+nomeCV+"%' ";
        try {
            ResultSet rs = db.submitQuery(query);
            dt.handleCentriVaccinaliSet(rs);
            listaRisultato = dt.getCentriVaccinaliTable();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaRisultato;
    }

    /**
     * Metodo che permette di trovare un centro vaccinale passata una stringa rappresentante il nome cercato
     * @param comune Stringa rappresentante il nome del comune del centro vaccinale cercato
     * @param tipologia Stringa rappresentante la tipologia del centro vaccinale cercato
     * @return ArrayList<CentroVaccinale> contenente tutte le informazioni del centro trovato
     */
    @Override
    public ArrayList<CentroVaccinale> cercaCentroVaccinale(String comune, String tipologia) throws RemoteException {
        DataTables dt = new DataTables();
        ArrayList<CentroVaccinale> listaRisultato = new ArrayList<>();
        String query = "SELECT * FROM centrivaccinali WHERE comune = '"+comune+"' AND tipologia = '"+tipologia+"' ";
        try {
            ResultSet rs = db.submitQuery(query);
            dt.handleCentriVaccinaliSet(rs);
            listaRisultato = dt.getCentriVaccinaliTable();
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(listaRisultato);
        return listaRisultato;
    }

    /**
     * main
     * @param args args[]
     */
    public static void main(String[] args) {
        try {
            ServerRMI obj = new ServerRMI();
            Registry registro = LocateRegistry.createRegistry(1099);
            registro.rebind("CentriVaccinali",obj);
            System.out.println("Server ready");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
