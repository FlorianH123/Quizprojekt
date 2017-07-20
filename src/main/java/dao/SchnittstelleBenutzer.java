package dao;

import exception.DataNotFoundException;
import exception.EmailNotFoundException;
import model.User;
import validation.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static constants.DB_Constants.*;

/**
 * Created by Florian on 13.06.2017.
 * Schnittstelle zum Austausch von Daten der benutzer Tabelle
 */

public class SchnittstelleBenutzer {
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
                     ResultSet.CONCUR_READ_ONLY)) {
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
            System.err.println(ERR_MSG_GET_USER);
            e.printStackTrace();
        }
        return aUser;
    }

    public User getUserByEmail(String eMail) {
        Validator.check(!eMail.isEmpty(), ERR_MSG_CHECK_MAIL);

        ResultSet rs;
        User re_user = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_AUTHORIZATION)) {
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
        } catch (SQLException e) {
            System.err.println(ERR_MSG_GET_USER);
            e.printStackTrace();
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
             PreparedStatement statement = connection.prepareStatement(PS_GET_PASSWORD_BY_ID)) {

            statement.setInt(INDEX_1, id);
            rs = statement.executeQuery();

            if (!rs.next()) {
                throw new DataNotFoundException(ERR_MSG_ID_NOT_FOUND);
            }

            passwort = rs.getString(PASSWORT);

        } catch (SQLException e) {
            System.err.println(ERR_MSG_GET_PASSWORD);
            e.printStackTrace();
        }
        return passwort;
    }

    public boolean checkID(int id) {
        Validator.check(id > 0, ERR_MSG_ID_GREATER_ZERO);

        ResultSet rs;
        int re_id = 0;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_CHECK_ID_BENUTZER)) {
            statement.setInt(INDEX_1, id);
            rs = statement.executeQuery();

            if (rs.next()) {
                re_id = rs.getInt(ID);
            }

            if (re_id != 0) {
                return true;
            }

        } catch (SQLException e) {
            System.err.println(ERR_MSG_CHECK_ID);
            e.printStackTrace();
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
             PreparedStatement statement = connection.prepareStatement(PS_CHECK_EMAIL)) {
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
            System.err.println(ERR_MSG_CHECK_MAIL);
            e.printStackTrace();
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
             PreparedStatement statement = connection.prepareStatement(PS_ADD_USER)) {

            statement.setString(INDEX_1, aUser.getE_mail());
            statement.setString(INDEX_2, aUser.getPasswort());
            statement.setString(INDEX_3, aUser.getAvatar_link());
            statement.setString(INDEX_4, aUser.getName());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println(ERR_MSG_ADD_USER);
            e.printStackTrace();
        }
    }


    /*
     * Methode die die naechste ID zurueckgibt
     *
     * @return ID + 1
     */
    /*
    public int getNextID() {
        ResultSet rs;
        int nummer = -1;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_GET_NEXT_ID)) {
            rs = statement.executeQuery();

            rs.next();
            nummer = rs.getInt(ANZAHL);
            nummer++;

        } catch (SQLException e) {
            System.err.println(ERR_MSG_CURRENT_ID);
            e.printStackTrace();
        }
        return nummer;
    }
    */

    public void changePassword (User user) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(PS_CHANGE_PW)){
            statement.setString(INDEX_1, user.getPasswort());
            statement.setString(INDEX_2,user.getE_mail());
            statement.executeUpdate();
        }catch(SQLException e){
            System.err.println();
            e.printStackTrace();
        }
    }

    public int getIDbyEMail(User aUser) {
        ResultSet rs;
        int id = 0;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_GET_ID_BY_EMAIL)) {

            statement.setString(INDEX_1, aUser.getE_mail());
            rs = statement.executeQuery();

            if (!rs.next()) {
                throw new DataNotFoundException(ERR_MSG_ID_NOT_FOUND);
            }

            id = rs.getInt(ID);

        } catch (SQLException e) {
            System.err.println(ERR_MSG_GET_PASSWORD);
            e.printStackTrace();
        }
        return id;
    }

    public void changeAvatarLink(User user) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(PS_CHANGE_AVL)){
            statement.setString(INDEX_1, user.getAvatar_link());
            statement.setString(INDEX_2,user.getE_mail());
            statement.executeUpdate();
        }catch(SQLException e){
            System.err.println();
            e.printStackTrace();
        }
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

    public static void main (String[] args) {
        SchnittstelleBenutzer sch = new SchnittstelleBenutzer();
        User aUSer = new User();
        aUSer.setE_mail("testaccount1@mail.de");
        aUSer.setPasswort("passwort1");
        aUSer.setName("testaccount1");
        sch.addUser(aUSer);
    }
}