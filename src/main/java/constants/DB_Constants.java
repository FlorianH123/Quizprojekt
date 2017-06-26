package constants;

/**
 * Created by Florian on 15.06.2017.
 * Datenbank Konstanten
 */
public class DB_Constants {
// fuer SchnittstelleBenutzer ------------------------------------------------------------------------------------------
    public static final String ID           = "id";
    public static final String E_MAIL       = "e_mail";
    public static final String PASSWORT     = "passwort";
    public static final String AVATAR_LINK  = "avatar_link";
    public static final String NAME         = "name";
    public static final String ANZAHL       = "anzahl";

    public static final String CLASS_NAME   = "class-name";
    public static final String URL          = "url";
    public static final String USER         = "user";
    public static final String DB_PASSWORD  = "password";
    public static final String FILENAME     = "DBconfig.properties";

    public static final String ERR_MSG_CURRENT_ID   = "Fehler: Es konnte keine ID berechnet werden1";
    public static final String ERR_MSG_DRIVER       = "Kein Treiber gefunden!";
    public static final String ERR_MSG_GET_USER     = "Fehler: Es konnte kein User zu dieser ID gefunden werden!";
    public static final String ERR_MSG_UPDATE       = "Fehler: SQL Update Statement fehlgeschlagen!";
    public static final String ERR_MSG_QUERY        = "Fehler: SQL Query Statement fehlgeschlagen!";
    public static final String ERR_MSG_CONNECTION   = "Fehler: Es konnte keine Verbindung aufgebaut werden!";
    public static final String ERR_MSG_GET_PASSWORD = "Fehler: Es konnte kein Passwort zu dieser ID gefunden werden!";
    public static final String ERR_MSG_ID_NOT_FOUND = "Fehler: Die angebene ID ist nicht vorhanden";

    //Prepared Statements
    public static final String PS_CHECK_EMAIL        = "SELECT "+ E_MAIL + " FROM benutzer WHERE " + E_MAIL + " = ?";
    public static final String PS_GET_USER_BY_ID     = "SELECT * FROM benutzer WHERE " + ID + " = ?";
    public static final String PS_CHECK_ID           = "SELECT " + ID + " FROM benutzer WHERE " + ID + " = ?";
    public static final String PS_GET_PASSWORD_BY_ID = "SELECT " + PASSWORT + " FROM benutzer WHERE " + ID + " = ?";
    public static final String PS_ADD_USER           = "INSERT INTO benutzer VALUES (?,?,?,?,?)";
    public static final String PS_GET_NEXT_ID        = "SELECT count(*) AS anzahl FROM benutzer";

    public static final int INDEX_1 = 1;
    public static final int INDEX_2 = 2;
    public static final int INDEX_3 = 3;
    public static final int INDEX_4 = 4;
    public static final int INDEX_5 = 5;
//----------------------------------------------------------------------------------------------------------------------
}
