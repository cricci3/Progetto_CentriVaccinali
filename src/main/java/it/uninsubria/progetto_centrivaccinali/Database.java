package it.uninsubria.progetto_centrivaccinali;

import java.sql.*;

/**
 * Database
 */
public class Database {
    /** protocol */
    private final static String protocol = "jdbc:postgresql://";
    /** host */
    private final static String host = "localhost/";
    /** resource */
    private final static String resource = "CentriVaccinaliDB";

    /** url */
    private final static String url = protocol + host + resource;

    /** database */
    private static Database database;
    /** connection */
    private static Connection connection;
    /** statement */
    private static Statement statement;
    /** verifica credenziali DB */
    public static boolean credenzialiOk;

    /**
     * Costruttore
     */
    private Database(){
        try {
            connection = DriverManager.getConnection(url, ServerRMI.username, ServerRMI.password);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            credenzialiOk = true;
        }catch (SQLException e){
            credenzialiOk = false;
        }
    }

    /**
     * Metodo che usa il design pattern Singleton, se il db non è ancora stato istanziato lo fa, altrimenti passa un riferimento all'istanza già creata
     * @return un'istanza database
     * @throws SQLException Eccezione
     */
    public static Database getInstance() throws SQLException { //SINGLETON per assicurarmi di gestire la comunicazione con db in modo centralizzato
        if(database == null) {
            database = new Database();
        }
        return database;
    }

    /**
     * Metodo che permette di eseguire una query sul database
     * @param query String rappresentante la query
     * @return true se query effettuata con successo, false altrimento
     * @throws SQLException Eccezione
     */
    public ResultSet submitQuery(String query) throws SQLException{
        if(statement.execute(query)) {
            return statement.getResultSet();
        }
        return null;
    }

}
