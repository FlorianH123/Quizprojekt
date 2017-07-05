package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static constants.DB_Constants.*;

/**
 * Created by Darth Vader on 06.06.2017.
 * Klasse um eine SQL Connection zu erstellen
 */
public class ConnectionKlasse {
    private Properties properties;

    public ConnectionKlasse() {
        properties = new Properties();
        InputStream is;

        try {
            is = ConnectionKlasse.class.getClassLoader().getResourceAsStream(FILENAME);
            properties.load(is);

            Class.forName(properties.getProperty(CLASS_NAME));
        } catch (ClassNotFoundException e) {
            System.err.println(ERR_MSG_DRIVER);
        } catch (IOException e) {
            System.err.println(ERR_MSG_PROPERTIES);
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
            System.err.println(ERR_MSG_CONNECTION);
        }

        return con;
    }
}
