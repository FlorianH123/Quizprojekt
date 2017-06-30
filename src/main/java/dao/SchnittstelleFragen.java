package dao;

import model.Frage;
import validation.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static constants.DB_Constants.*;

/**
 * Created by Florian on 30.06.2017.
 * Schnittstelle zum Austausch von Daten aus der Tabelle in der gespeichert wird,
 * ob ein User eine Frage schon beantwortet hatte
 */
public class SchnittstelleFragen {
    private Logger logger = Logger.getLogger(getClass().getName());

    public SchnittstelleFragen() {
        Handler handler;

        //TODO Logger überarbeiten
        //handler = new FileHandler("log/logger.xml", true);
        //logger.addHandler(handler);
    }

    /**
     * Fuegt eine Frage in die DB ein
     *
     * @param aQuestion die Frage
     */
    public void insertQuestion(Frage aQuestion) {

    }

    /**
     * Methode um zu ueberpruefen ob eine ID zu einer Frage in der Datenbank ist
     *
     * @param id id der Frage
     * @return true wenn Frage in der DB, false wenn Frage nicht in der DB
     */
    public boolean hasQuestion(int id) {
        Validator.check(id > 0, ERR_MSG_ID_GREATER_ZERO);

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(PS_HAS_ID);
            statement.setInt(INDEX_1, id);
            rs = statement.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, ERR_MSG_HAS_QUESTION + " " + e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, ERR_MSG_RS_CLOSE + " " + e);
                }
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, ERR_MSG_STMT_CLOSE + " " + e);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, ERR_MSG_CON_CLOSE + " " + e);
                }
            }
        }

        return false;
    }

    /**
     * Methode die ein Connection Objekt erstellt und es zurueck gibt
     *
     * @return Connection
     */
    private Connection getConnection() {
        ConnectionKlasse con = new ConnectionKlasse();

        return con.getConnection();
    }
}
