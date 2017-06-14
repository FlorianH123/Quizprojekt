package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.User;

/**
 * Created by Florian on 13.06.2017.
 * Schnittstelle zum Austausch von Daten der user Tabelle
 */

public class SchnittstelleBenutzer {
    private static final String CLASS_NAME = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://134.96.217.36:5432/benutzerverwaltung";
    private static final String USER = "benutzerverwaltung";
    private static final String PASSWORD = "D2r@!FG45%";

    public SchnittstelleBenutzer() {
        try {
            Class.forName(CLASS_NAME);
        }

        catch (ClassNotFoundException e) {
            System.err.println( "Kein Treiber gefunden" );
        }
    }

    //TODO Implementieren
    public User getUserByID(int id) {
        Connection con = getConnection();
        Statement stmt;
        ResultSet rs;
        User aUser = new User("","","","");

        String statement = "SELECT * FROM benutzer WHERE id = " + id;
        rs = doSQLQuery(statement);

        try {
            while (rs.next()) {
                aUser.setE_mail(rs.getString(2));
                aUser.setPasswort(rs.getString(3));
                aUser.setAvatar_link(rs.getString(4));
                aUser.setName(rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aUser;
    }

    public void addUser( User aUser ) {
        Connection con = getConnection();
        Statement stmt;
        String statement = "INSERT INTO benutzer VALUES ('" + currentID() + "','" + aUser.getE_mail() + "','" + aUser.getPasswort() + "','" + aUser.getAvatar_link() + "','" + aUser.getName() + "')";

        doSQLUpdate(statement);
    }

    public void removeUser( User aUser ) {

    }

    private int currentID() {
        String statement = "SELECT count(*) AS anzahl FROM benutzer";
        ResultSet rs = doSQLQuery(statement);
        int nummer = -1;

        try {
            rs.next();
            nummer = rs.getInt("anzahl");
            nummer++;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nummer;
    }

    private void doSQLUpdate(String statement) {
        Connection con = getConnection();
        Statement stmt;

        try {
            stmt = con.createStatement();

            stmt.executeUpdate(statement);

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ResultSet doSQLQuery(String statement) {
        Connection con = getConnection();
        Statement stmt;
        ResultSet rs = null;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(statement);

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    private Connection getConnection() {
        Connection con = null;

        try {
            con = DriverManager.getConnection( URL, USER, PASSWORD );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public static void main (String argv[]) {
        SchnittstelleBenutzer sch = new SchnittstelleBenutzer();
        User aUser = sch.getUserByID(1);
        System.out.println(aUser.getE_mail() + aUser.getAvatar_link() + aUser.getName() + aUser.getPasswort());
        System.out.println(sch.currentID());
    }
}