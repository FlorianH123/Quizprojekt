package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import constants.DB_Constants;
import model.User;

/**
 * Created by Florian on 13.06.2017.
 * Schnittstelle zum Austausch von Daten der benutzer Tabelle
 */

public class SchnittstelleBenutzer {
    private static final String CLASS_NAME = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://134.96.217.36:5432/benutzerverwaltung";
    private static final String USER = "benutzerverwaltung";
    private static final String PASSWORD = "D2r@!FG45%";


    public SchnittstelleBenutzer() {
        try {
            Class.forName( CLASS_NAME );
        }

        catch ( ClassNotFoundException e ) {
            System.err.println( DB_Constants.ERR_MSG_DRIVER );
        }
    }

    /**
     * Methode die einen Benutzer zu einer übergebenen ID zurück gibt
     * @param id ID des Benutzers
     * @return Benutzer
     */
    public User getUserByID( int id ) {
        ResultSet rs;
        User aUser = new User();

        String statement = "SELECT * FROM benutzer WHERE id = " + id;
        rs = doSQLQuery( statement );

        try {
            while ( rs.next() ) {
                aUser.setE_mail( rs.getString( 2 ) );
                aUser.setPasswort( rs.getString( 3 ) );
                aUser.setAvatar_link( rs.getString( 4 ) );
                aUser.setName( rs.getString( 5 ) );
            }
        } catch ( SQLException e ) {
            System.err.println( DB_Constants.ERR_MSG_GET_USER );
            e.printStackTrace();
        }

        return aUser;
    }

    /**
     *
     */
    public boolean checkEmail( String email ) {
        ResultSet rs;
        String re_mail = "";
        //TODO Select statement in extra resource auslagern
        String statement = "SELECT e_mail FROM benutzer WHERE e_mail = '" + email + "'";

        rs = doSQLQuery( statement );

        try {
            if( rs.next() ) {
                re_mail = rs.getString("e_mail");
            }

            //Existiert schon weil eine email gefunden wurde
            if ( !re_mail.isEmpty() ) {
                return true;
            }
        } catch ( SQLException e ) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    /**
     * Methode die das Passwort eines Benutzers zurueckgibt
     * @param id ID des Benutzers
     * @return passwort des Benutzers
     */
    public String getPasswordByID( int id ) {
        ResultSet rs;
        String passwort = "";
        String statement = "SELECT passwort FROM benutzer WHERE id = " + id;

        rs = doSQLQuery( statement );

        try {
            rs.next();

            passwort = rs.getString( "passwort" );
        } catch ( SQLException e ) {
            System.err.println( DB_Constants.ERR_MSG_GET_PASSWORD );
            e.printStackTrace();
        }

        return passwort;
    }

    /**
     * Methode die ein Benutzer zu der DB benutzer hinzufügt
     * @param aUser ein Benutzer
     */
    public void addUser( User aUser ) {
        String statement = "INSERT INTO benutzer VALUES ('" + getNextID() + "','" + aUser.getE_mail() + "','" + aUser.getPasswort() + "','" + aUser.getAvatar_link() + "','" + aUser.getName() + "')";

        doSQLUpdate( statement );
    }

    public void removeUser( User aUser ) {

    }

    /**
     * Methode die die naechste ID zurueckgibt
     * @return ID + 1
     */
    public int getNextID() {
        String statement = "SELECT count(*) AS anzahl FROM benutzer";
        ResultSet rs = doSQLQuery( statement );
        int nummer = -1;

        try {
            rs.next();
            nummer = rs.getInt( "anzahl" );
            nummer++;
        } catch ( SQLException e ) {
            System.err.println( DB_Constants.ERR_MSG_CURRENT_ID );
            e.printStackTrace();
        }

        return nummer;
    }

    /**
     * Methode um ein SQL Update auszuführen
     * @param statement SQL Statement
     */
    private void doSQLUpdate( String statement ) {
        Connection con = getConnection();
        Statement stmt;

        try {
            stmt = con.createStatement();
            stmt.executeUpdate(statement);
            con.close();
        } catch ( SQLException e ) {
            System.err.println( DB_Constants.ERR_MSG_UPDATE );
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch ( SQLException e ) {

            }
        }
    }

    /**
     * Methode um ein SQL Query auszuführen
     * @param statement SQL Statement
     * @return ResultSet der Abfrage
     */
    private ResultSet doSQLQuery( String statement ) {
        Connection con = getConnection();
        Statement stmt;
        ResultSet rs = null;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(statement);

            con.close();
        } catch ( SQLException e ) {
            System.err.println( DB_Constants.ERR_MSG_QUERY );
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch ( SQLException e ) {
            }
        }

        return rs;
    }

    /**
     * Methode um eine Verbindung aufzubauen
     * @return Connection
     */
    private Connection getConnection() {
        Connection con = null;

        try {
            con = DriverManager.getConnection( URL, USER, PASSWORD );
        } catch ( SQLException e ) {
            System.err.println( DB_Constants.ERR_MSG_CONNECTION );
            e.printStackTrace();
        }

        return con;
    }

    //TODO Löschen
    public static void main (String argv[]) {
        SchnittstelleBenutzer sch = new SchnittstelleBenutzer();
        User aUser = sch.getUserByID(1);
        System.out.println("Passwort: " + sch.getPasswordByID(1));
        System.out.println(aUser.toString());
        System.out.println(sch.getNextID());
    }
}