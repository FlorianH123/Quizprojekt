package dao;

/**
 * Created by Florian on 13.06.2017.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;

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
            System.err.println("Kein Treiber gefunden");
        }
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

    public void addUser( User aUser ) {
        Connection con = getConnection();
        Statement stmt = null;

        try {
            stmt = con.createStatement();

            stmt.executeUpdate( "INSERT INTO user (id, e_mail, passwort, avatar_link, name) VALUES ("
                    + currentID() + "," + aUser.getE_mail() + "," + aUser.getPasswort() +
                    "," + aUser.getAvatar_link() + "," + aUser.getName() + ")" );

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUser( User aUser ) {

    }

    public int currentID() {
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        int nummer = -1;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery( "SELECT count(*) AS anzahl FROM user" );

            rs.next();
            nummer = rs.getInt( "anzahl" );
            nummer++;

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nummer;
    }
}