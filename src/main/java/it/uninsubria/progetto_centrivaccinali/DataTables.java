package it.uninsubria.progetto_centrivaccinali;

import java.sql.*;
import java.util.*;

/**
 * Datatables
 */
public class DataTables {
    /**
     * list centri vaccinali
     */
    private ArrayList<CentroVaccinale> centriVaccinaliTable;
    /**
     * list cittadini registrati
     */
    private ArrayList<CittadinoRegistrato> cittadiniRegistratiTable;
    /**
     * list cittadini vaccinati
     */
    private ArrayList<CittadinoVaccinato> cittadiniVaccinatiTable;
    /**
     * list eventi avversi
     */
    private ArrayList<EventiAvversi> eventiAvversiTable;
    /**
     * list operatore
     */
    private ArrayList<Operatore> operatoriList;
    /**
     * list loggati
     */
    private ArrayList<String> listLog;
    /**
     * list id
     */
    private ArrayList<Integer> listId;
    /**
     * list medie
     */
    private ArrayList<Double> listaMedie;

    /**
     * Costruttore, istanza tutte le liste che verranno gestite dai metodi della classe
     */
    public DataTables() {
        this.centriVaccinaliTable = new ArrayList<CentroVaccinale>();
        this.cittadiniRegistratiTable = new ArrayList<CittadinoRegistrato>();
        this.cittadiniVaccinatiTable = new ArrayList<CittadinoVaccinato>();
        this.eventiAvversiTable = new ArrayList<EventiAvversi>();
        this.operatoriList = new ArrayList<Operatore>();
        this.listLog = new ArrayList<>();
        this.listId = new ArrayList<>();
        this.listaMedie = new ArrayList<>();
    }

    /**
     * Preso un ResultSet come input, lo scorre e crea degli oggetti Operatore ad ogni iterazione
     * @param rs ResultSet
     * @throws SQLException Eccezione
     */
    public void handleOperatoriSet(ResultSet rs) throws SQLException{
        while (rs.next()){
            Operatore operatore = new Operatore(rs.getString("username"), rs.getString("password"));
            operatoriList.add(operatore);
        }
    }

    /**
     * @return una lista contenente oggetti di tipo Operatore
     */
    public ArrayList<Operatore> getOperatoriTable(){
        return this.operatoriList;
    }

    /**
     * Preso un ResultSet come input, lo scorre e crea degli oggetti CentroVaccinale ad ogni iterazione
     * @param rs ResultSet
     * @throws SQLException Eccezione
     */
    public ArrayList<CentroVaccinale> handleCentriVaccinaliSet(ResultSet rs) throws SQLException {
        while(rs.next()){
            CentroVaccinale cv = new CentroVaccinale(rs.getString("nome"),
                    rs.getString("indirizzo"),
                    rs.getString("comune"),
                    rs.getString("provincia"),
                    rs.getInt("cap"),
                    rs.getString("tipologia"));
            centriVaccinaliTable.add(cv);
        }
        return centriVaccinaliTable;
    }

    /**
     * @return una lista di oggetti CentroVaccinale
     */
    public ArrayList<CentroVaccinale> getCentriVaccinaliTable(){
        return this.centriVaccinaliTable;
    }

    /**
     * Preso un ResultSet come input, lo scorre e crea degli oggetti CittadinoRegistrato ad ogni iterazione
     * @param rs ResultSet
     * @param cv String nome centro vaccinale
     * @throws SQLException Eccezione
     */
    public void handleCittadiniRegistratiSet(ResultSet rs, String cv) throws SQLException {
        while(rs.next()) {
            CittadinoRegistrato cittr = new CittadinoRegistrato(
                    cv,
                    rs.getInt("idcittadino"),
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("cf"),
                    null, null,
                    rs.getString("email"),
                    rs.getString("username"),
                    rs.getString("password")
            );
            cittadiniRegistratiTable.add(cittr);
        }
    }

    /**
     * @return una lista di oggetti CittadinoRegistrato
     */
    public ArrayList<CittadinoRegistrato> getCittadiniRegistratiTable(){
       return this.cittadiniRegistratiTable;
    }

    /**
     * Preso un ResultSet come input, lo scorre e crea una lista contenente String username, password e una con Integer idCittadino
     * @param rs ResultSet
     * @throws SQLException Eccezione
     */
    public void handleLoginCittadini(ResultSet rs) throws SQLException {
        while(rs.next()){
            listLog.add(rs.getString("username"));
            listLog.add(rs.getString("password"));
            listId.add(rs.getInt("idcittadino"));
        }
    }

    /**
     * @return una lista di String
     */
    public ArrayList<String> getLoggatiTable(){
        return this.listLog;
    }

    /**
     * @return una lista di Integer
     */
    public ArrayList<Integer> getListIdTable(){ return this.listId; }

    /**
     * Preso un ResultSet come input, lo scorre e crea degli oggetti CittadinoVaccinato ad ogni iterazione
     * @param rs ResultSet
     * @param nomeCV nome centro vaccinale
     * @throws SQLException Eccezione
     */
    public void handleCittadiniVaccinatiSet(ResultSet rs, String nomeCV) throws SQLException {
        while(rs.next()){
            CittadinoVaccinato cittv = new CittadinoVaccinato(
                    nomeCV,
                    rs.getInt("idcittadino"),
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("cf"),
                    rs.getString("datavaccinazione"),
                    rs.getString("nomevaccino"));
            cittadiniVaccinatiTable.add(cittv);
        }
    }

    /**
     * @return una lista di oggetti CittadinoVaccinato
     */
    public ArrayList<CittadinoVaccinato> getCittadiniVaccinatiTable(){
        return this.cittadiniVaccinatiTable;
    }

    /**
     * Preso un ResultSet come input, lo scorre e aggiunge i risultati del result set a una lista di double
     * @param rs ResultSet
     * @throws SQLException Eccezione
     */
    public void handleMedieEventiAvversi(ResultSet rs) throws SQLException{
        while (rs.next()){
            listaMedie.add(rs.getDouble("febbre"));
            listaMedie.add(rs.getDouble("malDiTesta"));
            listaMedie.add(rs.getDouble("doloriMuscolari"));
            listaMedie.add(rs.getDouble("linfoadenopatia"));
            listaMedie.add(rs.getDouble("tachicardia"));
            listaMedie.add(rs.getDouble("crisiIpertensive"));
        }
    }

    /**
     * @return una lista di double
     */
    public ArrayList<Double> getMedieEventiAvversiTable(){
        return this.listaMedie;
    }

}
