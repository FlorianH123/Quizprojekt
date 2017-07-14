package dao;

import exception.DataNotFoundException;
import exception.EmailNotFoundException;
import model.User;
import validation.Validator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static constants.DB_Constants.*;

/**
 * Created by Florian on 13.06.2017.
 * Schnittstelle zum Austausch von Daten der benutzer Tabelle
 */

public class SchnittstelleBenutzer {
    private Logger logger = Logger.getLogger(getClass().getName());

    public SchnittstelleBenutzer() {
        Handler handler;

        //TODO Logger überarbeiten
        try {
            handler = new FileHandler("log/logger.xml", true);
            logger.addHandler(handler);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Methode die einen Benutzer zu einer übergebenen ID zurück gibt
     * Falls die ID nicht existiert wird eine DataNotFoundException geworfen
     *
     * @param id ID des Benutzers
     * @return Benutzer
     */
    public User getUserByID(int id) {
        Validator.check(id > 0, ERR_MSG_ID_GREATER_ZERO);

        ResultSet rs;
        User aUser = new User();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_GET_USER_BY_ID, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)){
            statement.setInt(INDEX_1, id);
            rs = statement.executeQuery();

            if (rs.next()) {
                aUser.setId(rs.getInt(ID));
                aUser.setE_mail(rs.getString(E_MAIL));
                aUser.setAvatar_link(rs.getString(AVATAR_LINK));
                aUser.setName(rs.getString(NAME));
            } else {
                throw new DataNotFoundException(ERR_MSG_ID_NOT_FOUND);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, ERR_MSG_GET_USER + " " + e);
        }
        return aUser;
    }

    public User getUserByEmail(String eMail){
        Validator.check(!eMail.isEmpty(), ERR_MSG_CHECK_MAIL);

        ResultSet rs;
        User re_user = null;

        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(PS_AUTHORIZATION)){
            statement.setString(INDEX_1, eMail);
            rs = statement.executeQuery();

            if (rs.next()) {
                re_user = new User(rs.getString(E_MAIL),
                        rs.getString(PASSWORT),
                        rs.getString(AVATAR_LINK),
                        rs.getString(NAME));
            } else {
                throw new EmailNotFoundException(ERR_MSG_ID_NOT_FOUND);
            }
        }catch(SQLException e){
            logger.log(Level.SEVERE, ERR_MSG_GET_USER + " " + e);
        }
        return re_user;
    }

    /**
     * Methode die das Passwort eines Benutzers zurueckgibt
     * Falls die ID nicht existiert wird eine DataNotFoundException geworfen
     *
     * @param id ID des Benutzers
     * @return passwort des Benutzers
     */
    public String getPasswordByID(int id) {
        Validator.check(id > 0, ERR_MSG_ID_GREATER_ZERO);

        ResultSet rs;
        String passwort = "";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_GET_PASSWORD_BY_ID, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)){
            statement.setInt(INDEX_1, id);
            rs = statement.executeQuery();

            if (!rs.next()) {
                throw new DataNotFoundException(ERR_MSG_ID_NOT_FOUND);
            }

            passwort = rs.getString(PASSWORT);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, ERR_MSG_GET_PASSWORD + " " + e);
        }
        return passwort;
    }

    public boolean checkID(int id) {
        Validator.check(id > 0, ERR_MSG_ID_GREATER_ZERO);

        ResultSet rs;
        int re_id = 0;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_CHECK_ID)){
            statement.setInt(INDEX_1, id);
            rs = statement.executeQuery();

            if (rs.next()) {
                re_id = rs.getInt(ID);
            }

            if (re_id != 0) {
                return true;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, ERR_MSG_CHECK_ID + " " + e);
        }
        return false;
    }

    /**
     * Methode um zu ueberpruefen, ob eine E-Mail Adresse bereits vorhanden ist
     *
     * @param email E-Mail des gesuchten Users
     * @return true falls vorhanden false falls nicht vorhanden
     */
    public boolean checkEmail(String email) {
        Validator.check(!email.isEmpty(), ERR_MSG_EMAIL_EMPTY);

        ResultSet rs;
        String re_mail = "";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_CHECK_EMAIL)){
            statement.setString(INDEX_1, email);
            rs = statement.executeQuery();

            if (rs.next()) {
                re_mail = rs.getString(E_MAIL);
            }

            //Existiert nicht weil keine email gefunden wurde
            if (re_mail.isEmpty()) {
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, ERR_MSG_CHECK_MAIL + " " + e);
        }
        return true;
    }

    /**
     * Methode die ein Benutzer zu der DB benutzer hinzufügt
     *
     * @param aUser ein Benutzer
     */
    public void addUser(User aUser) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_ADD_USER)){

            statement.setInt(INDEX_1, aUser.getId());
            statement.setString(INDEX_2, aUser.getE_mail());
            statement.setString(INDEX_3, aUser.getPasswort());
            statement.setString(INDEX_4, aUser.getAvatar_link());
            statement.setString(INDEX_5, aUser.getName());
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, ERR_MSG_ADD_USER + " " + e);
        }
    }

    /**
     * Methode die die naechste ID zurueckgibt
     *
     * @return ID + 1
     */
    public int getNextID() {
        ResultSet rs;
        int nummer = -1;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_GET_NEXT_ID)){
            rs = statement.executeQuery();

            rs.next();
            nummer = rs.getInt(ANZAHL);
            nummer++;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, ERR_MSG_CURRENT_ID + " " + e);
        }
        return nummer;
    }

    /**
     * Methode um eine Verbindung aufzubauen
     *
     * @return Connection
     */
    private Connection getConnection() {
        ConnectionKlasse con = new ConnectionKlasse();

        return con.getConnection();
    }
}