package it.uninsubria.progetto_centrivaccinali;

import java.sql.*;

public class Database {
    /**
     * protocol
     */
    private final static String protocol = "jdbc:postgresql"+"://";
    /**
     * host
     */
    private final static String host = "localhost"+"/";//indirizzo di rete del dbms
    /**
     * resource
     */
    private final static String resource = "CentriVaccinaliDB";//nome db a cui vogliamo collegarci

    /**
     * url
     */
    private final static String url = protocol + host + resource;
    /**
     * user
     */
    private final static String user = "postgres";
    /**
     * password
     */
    private final static String password = "ciao1234";

    /**
     * database
     */
    private static Database database;
    /**
     * connection
     */
    private static Connection connection;
    /**
     * statement
     */
    private static Statement statement;

    /**
     * Costruttore
     * @throws SQLException Eccezione
     */
    private Database() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        statement =  connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
