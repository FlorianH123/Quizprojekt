package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import exception.DataNotFoundException;
import model.User;

import static constants.DB_Constants.*;

/**
 * Created by Florian on 13.06.2017.
 * Schnittstelle zum Austausch von Daten der benutzer Tabelle
 */

public class SchnittstelleBenutzer {
    private Properties properties;

    public SchnittstelleBenutzer() {
        properties = new Properties();

        try {
            InputStream is = SchnittstelleBenutzer.class.getClassLoader().getResourceAsStream(FILENAME);
            properties.load(is);
            Class.forName( properties.getProperty(CLASS_NAME) );
        } catch ( ClassNotFoundException e ) {
            System.err.println( ERR_MSG_DRIVER );
        } catch ( IOException e) {
            System.err.println( e.getMessage());
        }
    }

    /**
     * Methode die einen Benutzer zu einer übergebenen ID zurück gibt
     * @param id ID des Benutzers
     * @return Benutzer
     */
    public User getUserByID( int id ) {
        PreparedStatement statement;
        Connection connection = getConnection();
        ResultSet rs;
        User aUser = new User();

        String statement = "SELECT * FROM benutzer WHERE id = " + id;
        rs = doSQLQuery( statement );

        try {
            if (!rs.next()) {
                throw new DataNotFoundException( ERR_MSG_DATA_NOT_FOUND );
            }

            else {
                rs.previous();
                while ( rs.next() ) {
                    aUser.setId(rs.getInt( ID ));
                    aUser.setE_mail( rs.getString( E_MAIL ) );
                    aUser.setPasswort( rs.getString( PASSWORT ));
                    aUser.setAvatar_link( rs.getString( AVATAR_LINK ));
                    aUser.setName( rs.getString( NAME ));
                }
            }

        } catch ( SQLException e ) {
            System.err.println( ERR_MSG_GET_USER );
            e.printStackTrace();
        }

        return aUser;

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(PS_GET_USER_BY_ID);
            statement.setInt(1, id);
            rs = statement.executeQuery();

            while ( rs.next() ) {
                aUser.setE_mail( rs.getString( E_MAIL ) );
                aUser.setPasswort( rs.getString( PASSWORT ));
                aUser.setAvatar_link( rs.getString( AVATAR_LINK ));
                aUser.setName( rs.getString( NAME ));
            }
        } catch ( SQLException e ) {
            System.err.println( ERR_MSG_GET_USER );
            e.printStackTrace();
        }

        return aUser;
    }

    public boolean checkID ( int id ) {
        PreparedStatement statement;
        Connection connection = getConnection();
        ResultSet rs;
        int re_id = 0;

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(PS_CHECK_ID);
            statement.setInt(1, id);
            rs = statement.executeQuery();

            if( rs.next() ) {
                re_id = rs.getInt( ID );
            }

            if ( re_id != 0 ) {
                return true;
            }
        } catch ( SQLException e ) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    /**
     * Methode um zu ueberpruefen, ob eine E-Mail Adresse bereits vorhanden ist
     * @param email E-Mail des gesuchten Users
     * @return true falls vorhanden false falls nicht vorhanden
     */
    public boolean checkEmail( String email ) {
        Connection connection = getConnection();
        PreparedStatement statement;
        ResultSet rs;
        String re_mail = "";

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(PS_CHECK_EMAIL);
            statement.setString(1, email);
            rs = statement.executeQuery();

            if( rs.next() ) {
                re_mail = rs.getString(E_MAIL);
            }

            //Existiert nicht weil keine email gefunden wurde
            if ( re_mail.isEmpty() ) {
                return false;
            }
        } catch ( SQLException e ) {
            System.err.println(e.getMessage());
        }

        return true;
    }

    /**
     * Methode die das Passwort eines Benutzers zurueckgibt
     * @param id ID des Benutzers
     * @return passwort des Benutzers
     */
    public String getPasswordByID( int id ) {
        Connection connection = getConnection();
        PreparedStatement statement;
        ResultSet rs;
        String passwort = "";

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(PS_GET_PASSWORD_BY_ID);
            statement.setInt(1, id);
            rs = statement.executeQuery();

            rs.next();

            passwort = rs.getString( PASSWORT );
        } catch ( SQLException e ) {
            System.err.println( ERR_MSG_GET_PASSWORD );
            e.printStackTrace();
        }

        return passwort;
    }

    /**
     * Methode die ein Benutzer zu der DB benutzer hinzufügt
     * @param aUser ein Benutzer
     */
    public void addUser( User aUser ) {
        //String statement = "INSERT INTO benutzer VALUES ('" + aUser.getId() + "','" + aUser.getE_mail() + "','" + aUser.getPasswort() + "','" + aUser.getAvatar_link() + "','" + aUser.getName() + "')";
        //"INSERT INTO benutzer VALUES (?,?,?,?,?)";
        PreparedStatement statement;
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(PS_ADD_USER);
            statement.setInt(1,aUser.getId());
            statement.setString(2, aUser.getE_mail());
            statement.setString(3, aUser.getPasswort());
            statement.setString(4, aUser.getAvatar_link());
            statement.setString(5, aUser.getName());
            statement.executeUpdate();

        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        //doSQLUpdate( statement );
    }

    /**
     * Methode die die naechste ID zurueckgibt
     * @return ID + 1
     */
    public int getNextID() {
        //String statement = "SELECT count(*) AS anzahl FROM benutzer";
        //ResultSet rs = doSQLQuery( statement );
        ResultSet rs;
        PreparedStatement statement;
        Connection connection = getConnection();
        int nummer = -1;

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(PS_GET_NEXT_ID);
            rs = statement.executeQuery();

            rs.next();
            nummer = rs.getInt( ANZAHL );
            nummer++;
        } catch ( SQLException e ) {
            System.err.println( ERR_MSG_CURRENT_ID );
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
            System.err.println( ERR_MSG_UPDATE );
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
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(statement);

            con.close();
        } catch ( SQLException e ) {
            System.err.println( ERR_MSG_QUERY );
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
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
        String url, user, password;

        try {
            url = properties.getProperty(URL);
            user = properties.getProperty(USER);
            password = properties.getProperty(DB_PASSWORD);

            con = DriverManager.getConnection( url, user, password );
        } catch ( SQLException e ) {
            System.err.println( ERR_MSG_CONNECTION );
            e.printStackTrace();
        }

        return con;
    }

    //TODO Löschen
    public static void main (String argv[]) {
        SchnittstelleBenutzer sch = new SchnittstelleBenutzer();
        User aUser = sch.getUserByID(1);

        System.out.println(aUser.toString());
    }
}