package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static constants.DB_Constants.*;

/**
 * Created by Darth Vader on 06.06.2017.
 * Klasse um eine SQL Connection zu erstellen
 */
public class ConnectionKlasse {
    private Properties properties;
    private Logger logger = Logger.getLogger(getClass().getName());

    public ConnectionKlasse() {
        properties = new Properties();
        InputStream is;

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
     * Methode um eine Verbindung aufzubauen
     *
     * @return Connection
     */
    public Connection getConnection() {
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
