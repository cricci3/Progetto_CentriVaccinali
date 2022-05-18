package it.uninsubria.progetto_centrivaccinali;

import java.sql.*;

public class Database {
    private final static String protocol = "jdbc:postgresql"+"://";
    private final static String host = "localhost"+"/";//indirizzo di rete del dbms
    private final static String resource = "CentriVaccinaliDB";//nome db a cui vogliamo collegarci

    private final static String url = protocol + host + resource;
    private final static String user = "postgres";
    private final static String password = "ciao1234";

    private static Database database;
    private static Connection connection;
    private static Statement statement;

    //questo costruttore grazie a metodo getInstance potrà essere istanziato una volta sola
    //in questo modo connection e statement inizializzati solo una volta
    private Database() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        statement =  connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    public static Database getInstance() throws SQLException { //SINGLETON per assicurarmi di gestire la comunicazione con db in modo centralizzato
        if(database == null) {
            database = new Database();
        }
        return database;
    }

    public ResultSet submitQuery(String query) throws SQLException{
        if(statement.execute(query)) {
            return statement.getResultSet();
        }
        return null;
    }

}
