package it.uninsubria.progetto_centrivaccinali;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.sql.*;
import java.util.*;

public class ServerRMI extends UnicastRemoteObject implements InterfaceRMI {
    private static final long serialVersionUID = 1L;
    //creo riferimenti al Database
    Database db;
    {
        try {
            db = Database.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    DataTables dt = new DataTables();

    protected ServerRMI() throws RemoteException {
        super();
    }

    //funzione chiamata quando operatore inserisce un nuovo centro vaccinale
    //da aggiungere a listenerButton invio dati
    @Override
    public boolean addCentroVaccinale(CentroVaccinale cv){
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

    //funzione chiamata quando operatore inserisce un nuovo vaccinato in un centro vaccinale
    //da aggiungere a listenerButton invio dati
    @Override
    public boolean addCittadinoVaccinato(CittadinoVaccinato cittV) {
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
                registrato = true; //id inserito risulta già nella lista dei registrati
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return registrato;
    }

    //funzione chiamata quando cittadino, in seguito a vaccinazione effettua registrazione al sistema
    //da aggiungere a listenerButton invio dati
    @Override
    public boolean addCittadinoRegistrato(int id, String nomeCV, String nome, String cognome, String email, String username, String password) {
        String query = "SELECT * FROM vaccinati_"+nomeCV+" WHERE idcittadino = '"+id+"'";
        boolean response = false;
        try {
            ResultSet rs = db.submitQuery(query);
            DataTables dt = new DataTables();
            dt.handleCittadiniVaccinatiSet(rs, nomeCV);
            ArrayList<CittadinoVaccinato> listaCittadini = dt.getCittadiniVaccinatiTable();
            if(listaCittadini.isEmpty()){
                System.out.println("La lista è vuota");
            }else{
                //stampa tutto il contenuto di vaccinati_nomeCentro (serve a noi)
                for (CittadinoVaccinato cittadinoVaccinato : listaCittadini){
                    System.out.println(cittadinoVaccinato.toString());
                }
                //crea un vaccinato con i dati inseriti dall'utente e controlla se nei cittadini vaccinati ne esiste uno che corrisponde
                CittadinoVaccinato cittadino = listaCittadini.get(0);
                //CittadinoRegistrato registrato = new CittadinoRegistrato(nomeCV, id, nome, cognome, cittadinoVaccinato.getCf(), cittadinoVaccinato.getDataVaccinazione(), cittadinoVaccinato.getNomeVaccino(), email, username, password);
                //se esiste un cittadino vaccinato corrispondente ai dati inseriti allora può registrarsi
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

    @Override
    public boolean addCittadinoRegistrato2(int id, String nomeCV, String nome, String cognome, String email, String username, String password) throws RemoteException {
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

    //funzione chiamata quando un cittadino inserisce i suoi eventi avversi
    //da aggiungere a listenerButton invio dati eventi avversi
    @Override
    public String addEventiAvversi(EventiAvversi eventi) {
        //CittadinoRegistrato cittadino = eventi.getCittadino();
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

    @Override
    public boolean loginOperatore2(String username, String password) throws RemoteException {
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

    @Override
    public boolean loginOperatore(String username, String password) throws RemoteException {
        String query = "SELECT * FROM operatori";
        boolean response = false;
        try {
            ResultSet rs = db.submitQuery(query);
            DataTables dt = new DataTables();
            dt.handleOperatoriSet(rs);
            ArrayList<Operatore> operatoriList = dt.getOperatoriTable();
            if(operatoriList.isEmpty()){
                System.out.println("LA LISTA è VUOTA");
            }else{
                for (Operatore operatore : operatoriList){
                    System.out.println(operatore.toString());
                }
                Operatore ricercato = new Operatore(username, password);
                for (Operatore operatore : operatoriList){
                    if(operatore.getUsername().equals(ricercato.getUsername()))
                        if(operatore.getPassword().equals(ricercato.getPassword())){
                            response = true;
                            break;
                        }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public boolean loginCittadino(String username, String password) throws RemoteException {
        String query = "SELECT * FROM cittadini_registrati WHERE username = '"+username+"' AND password = '"+password+"'";
        boolean response = false;
        try {
            ResultSet rs = db.submitQuery(query);
            dt.handleLoginCittadini(rs);
            ArrayList<String> lista = dt.getLoggatiTable();
            if(!lista.isEmpty()){
                response = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public int loginCittadinoID(String username, String password) throws RemoteException {
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

    //funzione chiamata quando un cittadino vuole cercare informazioni riguardo a un centro vaccinale
    @Override
    public String visualizzaInfoCentroVaccinale(CentroVaccinale centro){
        String query = "SELECT febbre, avg(febbre) AS media FROM eventiavversi"+
                "WHERE nomeCV = "+centro.getNome()+
                "GROUP BY febbre";

        String informazione = null;
        try {
            ResultSet rs = db.submitQuery(query);
            DataTables dt = new DataTables();
            String media = dt.handleEventiAvversiSet(rs);
            informazione = centro.toString()+media;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return informazione;
    }

    @Override
    public ArrayList<CentroVaccinale> cercaCentroVaccinale(String nomeCV) throws RemoteException {
        DataTables dt = new DataTables();
        ArrayList<CentroVaccinale> listaCentri = new ArrayList<>();
        String query = "SELECT * FROM centrivaccinali WHERE nome LIKE '%"+nomeCV+"%'";
        try {
            ResultSet rs = db.submitQuery(query);
            /*
            while (rs.next()){
                System.out.print(rs.getString("nome"));
                System.out.println(rs.getString("comune"));
            }
             */
            listaCentri = dt.handleCentriVaccinaliSet(rs);
            System.out.println("dentro try arrivo");
            //listaCentri = dt.getCentriVaccinaliTable();
            //System.out.println(listaCentri.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCentri;
    }

    @Override
    public String cercaCentroVaccinale(String comune, String tipologia) throws RemoteException {
        return null;
    }

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
