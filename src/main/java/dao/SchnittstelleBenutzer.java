package dao;

import exception.DataNotFoundException;
import model.User;
import validation.Validator;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
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
    private Properties properties;
    private Logger logger = Logger.getLogger(getClass().getName());

    public SchnittstelleBenutzer() {
        properties = new Properties();
        InputStream is;
        Handler handler;

        try {
            is = SchnittstelleBenutzer.class.getClassLoader().getResourceAsStream(FILENAME);
            properties.load(is);

            //TODO Logger überarbeiten
            //handler = new FileHandler("log/logger.xml", true);
            //logger.addHandler(handler);

            Class.forName(properties.getProperty(CLASS_NAME));
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, ERR_MSG_DRIVER + " " + e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, ERR_MSG_PROPERTIES + " " + e);
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

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        User aUser = new User();

        try {
            connection = getConnection();
            statement = connection.prepareStatement(PS_GET_USER_BY_ID, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            statement.setInt(INDEX_1, id);
            rs = statement.executeQuery();

            if (!rs.next()) {
                throw new DataNotFoundException(ERR_MSG_ID_NOT_FOUND);
            } else {
                rs.previous();
                while (rs.next()) {
                    aUser.setId(rs.getInt(ID));
                    aUser.setE_mail(rs.getString(E_MAIL));
                    aUser.setAvatar_link(rs.getString(AVATAR_LINK));
                    aUser.setName(rs.getString(NAME));
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, ERR_MSG_GET_USER + " " + e);
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

        return aUser;
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

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String passwort = "";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(PS_GET_PASSWORD_BY_ID, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            statement.setInt(INDEX_1, id);
            rs = statement.executeQuery();

            if (!rs.next()) {
                throw new DataNotFoundException(ERR_MSG_ID_NOT_FOUND);
            }

            passwort = rs.getString(PASSWORT);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, ERR_MSG_GET_PASSWORD + " " + e);
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

        return passwort;
    }

    public boolean checkID(int id) {
        Validator.check(id > 0, ERR_MSG_ID_GREATER_ZERO);

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        int re_id = 0;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(PS_CHECK_ID);
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
     * Methode um zu ueberpruefen, ob eine E-Mail Adresse bereits vorhanden ist
     *
     * @param email E-Mail des gesuchten Users
     * @return true falls vorhanden false falls nicht vorhanden
     */
    public boolean checkEmail(String email) {
        Validator.check(!email.isEmpty(), ERR_MSG_EMAIL_EMPTY);

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String re_mail = "";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(PS_CHECK_EMAIL);
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

        return true;
    }

    /**
     * Methode die ein Benutzer zu der DB benutzer hinzufügt
     *
     * @param aUser ein Benutzer
     */
    public void addUser(User aUser) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(PS_ADD_USER);

            statement.setInt(INDEX_1, aUser.getId());
            statement.setString(INDEX_2, aUser.getE_mail());
            statement.setString(INDEX_3, aUser.getPasswort());
            statement.setString(INDEX_4, aUser.getAvatar_link());
            statement.setString(INDEX_5, aUser.getName());
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, ERR_MSG_ADD_USER + " " + e);
        } finally {
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
    }

    /**
     * Methode die die naechste ID zurueckgibt
     *
     * @return ID + 1
     */
    public int getNextID() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        int nummer = -1;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(PS_GET_NEXT_ID);
            rs = statement.executeQuery();

            rs.next();
            nummer = rs.getInt(ANZAHL);
            nummer++;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, ERR_MSG_CURRENT_ID + " " + e);
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

        return nummer;
    }

    /**
     * Methode um eine Verbindung aufzubauen
     *
     * @return Connection
     */
    private Connection getConnection() {
        Connection con = null;
        String url, user, password;

        try {
            url = properties.getProperty(URL);
            user = properties.getProperty(USER);
            password = properties.getProperty(DB_PASSWORD);

            con = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, ERR_MSG_CONNECTION + " " + e);
        }

        return con;
    }
}