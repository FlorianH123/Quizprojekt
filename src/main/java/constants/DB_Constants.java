package constants;

/**
 * Created by Florian on 15.06.2017.
 * Datenbank Konstanten
 */
public class DB_Constants {
    // Klassenuebergreifende Konstanten ------------------------------------------------------------------------------------
    public static final String ERR_MSG_ID_GREATER_ZERO = "Fehler: Die ID muss groesser 0 sein!";

    public static final String ERR_MSG_RS_CLOSE = "Fehler beim Schliessen des Resultset!";
    public static final String ERR_MSG_STMT_CLOSE = "Fehler beim Schliessen des Statements!";
    public static final String ERR_MSG_CON_CLOSE = "Fehler beim Schliessen der Connection!";

    public static final int INDEX_1 = 1;
    public static final int INDEX_2 = 2;
    public static final int INDEX_3 = 3;
    public static final int INDEX_4 = 4;
    public static final int INDEX_5 = 5;
    public static final int INDEX_6 = 6;
//----------------------------------------------------------------------------------------------------------------------

    // fuer SchnittstelleBenutzer ------------------------------------------------------------------------------------------
    public static final String ID = "id";
    public static final String E_MAIL = "e_mail";
    public static final String PASSWORT = "passwort";
    public static final String AVATAR_LINK = "avatar_link";
    public static final String NAME = "name";
    public static final String ANZAHL = "anzahl";

    public static final String CLASS_NAME = "class-name";
    public static final String URL = "url";
    public static final String USER = "user";
    public static final String DB_PASSWORD = "password";
    public static final String FILENAME = "DBconfig.properties";

    public static final String ERR_MSG_DRIVER = "Kein Treiber gefunden!";
    public static final String ERR_MSG_GET_USER = "Fehler: Es konnte kein User zu dieser ID gefunden werden!";
    public static final String ERR_MSG_CONNECTION = "Fehler: Es konnte keine Verbindung aufgebaut werden!";
    public static final String ERR_MSG_GET_PASSWORD = "Fehler: Es konnte kein Passwort zu dieser ID gefunden werden!";
    public static final String ERR_MSG_ID_NOT_FOUND = "Fehler: Die angebene ID ist nicht vorhanden!";
    public static final String ERR_MSG_EMAIL_EMPTY = "Fehler: E-Mail darf nicht leer sein!";
    public static final String ERR_MSG_PROPERTIES = "Fehler beim Oeffnen der Properties Datei!";
    public static final String ERR_MSG_ADD_USER = "Fehler beim Anlegen des Benutzers! ";
    public static final String ERR_MSG_CHECK_MAIL = "Fehler beim Ueberpruefen der E-Mail!";
    public static final String ERR_MSG_CHECK_ID = "Fehler beim Ueberpruefen der ID!";
    public static final String ERR_MSG_EMAIL_NOT_FOUND = "Fehler: Es konnte kein User mit dieser email geunfden werden";

    //Prepared Statements
    public static final String PS_CHECK_EMAIL = "SELECT " + E_MAIL + " FROM benutzer WHERE " + E_MAIL + " = ?";
    public static final String PS_GET_USER_BY_ID = "SELECT * FROM benutzer WHERE " + ID + " = ?";
    public static final String PS_CHECK_ID_BENUTZER = "SELECT " + ID + " FROM benutzer WHERE " + ID + " = ?";
    public static final String PS_GET_PASSWORD_BY_ID = "SELECT " + PASSWORT + " FROM benutzer WHERE " + ID + " = ?";
    public static final String PS_ADD_USER = "INSERT INTO benutzer VALUES (?,?,?,?)";
    public static final String PS_AUTHORIZATION = "SELECT * FROM benutzer WHERE " + E_MAIL + " = ?";
    public static final String PS_CHANGE_PW = "UPDATE benutzer SET passwort = ? WHERE e_mail = ?";
    public static final String PS_CHANGE_AVL = "UPDATE benutzer SET avatar_link = ? WHERE e_mail = ?";
    public static final String PS_GET_ID_BY_EMAIL = "SELECT * FROM benutzer WHERE e_mail = ?";

    //Prepared Statements Single Player

    public static final String PS_GET_QUESTIONS = "SELECT \"Categories\".\"name\", \"Categories\".\"matchID_cat\", \"Level\".\"levelID\", \"Level\".\"verbalization\", \"Level\".\"solution\" " +
            "FROM \"quizDB\".\"Categories\", \"quizDB\".\"Matches\", \"quizDB\".\"Level\"" +
            "WHERE \"Matches\".\"matchID\" = \"Categories\".\"matchID_cat\"" +
            "AND \"Categories\".\"catID\" = \"Level\".\"catID\"" +
            "AND \"Categories\".\"matchID_cat\" = ? " +
            "ORDER BY Random() " +
            "LIMIT ?;" + ";";

    public static final String PS_GET_QUESTIONS_R = "SELECT categories.name, categories.matchid, level.levelid, level.verbalization, level.solution" +
            "FROM categories, matches, level" +
            "WHERE matches.matchid = categories.matchid" +
            "AND categories.catid = level.catid" +
            "AND categories.matchid = ?" +
            "AND level.ready = ready" +
            "ORDER BY Random()" +
            "LIMIT ?;";

    public static final String PS_GET_RANDOM_QUESTION = "SELECT level.levelid, level.catid, level.solution, level.verbalization " +
            "FROM level " +
            "WHERE level.ready = false " +
            "ORDER BY Random() " +
            "LIMIT ?; ";

    public static final String PS_GET_DISTRACTOORS = "SELECT \"Level\".\"solution\"" +
            "FROM \"quizDB\".\"Level\"" +
            "WHERE \"catID\" = ?" +
            "AND \"Level\".\"solution\" != ?" +
            "ORDER BY Random()" +
            "LIMIT ?;";

    public static final String PS_GET_DISTRACTOORS2 = "SELECT solution " +
            "FROM level " +
            "WHERE catid = ? " +
            "AND level.solution != ? " +
            "ORDER BY Random() " +
            "LIMIT ?; ";
    public static final String d = "";
    public static final String f = "";
    public static final String k = "";


//----------------------------------------------------------------------------------------------------------------------

    //fuer SchnittstelleStatistik-------------------------------------------------------------------------------------------
    public static final String USER_ID = "id_user";
    public static final String USER_ID_STAT = "user_id";
    public static final String ANZAHL_BEANTWORTETER_FRAGEN = "fragen_beantwortet";
    public static final String ANZAHL_FRAGEN_RICHTIG_BEANTWORTET = "fragen_richtig";
    public static final String PUNKTE = "punkte";
    public static final String GAME_ID = "game_id";
    public static final String HOECHSTE_PUNKTZAHL = "hoechste_punktezahl";
    public static final String ANZAHL_SPIELE = "gespielte_spiele";
    public static final String GAME_MODE = "gamemode";

    public static final String ERR_MSG_TRACK_NEW_GAME = "Fehler beim Einfuegen des Spielstatistik!\n";
    public static final String ERR_MSG_GET_OVERALL_STATISTIK = "Fehler: Es konnte keine Overall Statistik aus der DB " +
            "gelesen werden!\n";
    public static final String ERR_MSG_INIT_STATSTIK_OVERALL = "Fehler: Es konnte die Overall Statistik nicht " +
            "initialisiert werden!\n";
    public static final String ERR_MSG_CHANGE_OVERALL_STATISTIK = "Fehler: Es konnte die Overall Statistik nicht geupdatet werden!\n";
    public static final String ERR_MSG_GET_TOP_OVERALL = "Fehler: Top 10 konnten nicht geladen werden!\n";

    //Prepared Statements
    public static final String PS_TACK_NEW_GAME_TO_SINGLEPLAYER_STAT = "INSERT INTO singleplayer_stat VALUES (?,?,?,?,?)";
    public static final String PS_GET_OVERALL_STATISTIK = "SELECT * " +
            "FROM singleplayer_stat_result " +
            "WHERE user_id = ? AND gamemode = ?";
    public static final String PS_INIT_STATISTIK_OVERALL = "INSERT INTO singleplayer_stat_result VALUES (?,0,0,0,0,?)";
    public static final String PS_CHANGE_OVERALL_STATISTIK = "UPDATE singleplayer_stat_result " +
            "SET fragen_beantwortet = ?, fragen_richtig = ?, hoechste_punktezahl = ?, gespielte_spiele = ? " +
            "WHERE user_id = ? AND gamemode = ?";

    public static final String PS_GET_TOP_10 = "SELECT * " +
            "FROM singleplayer_stat_result, benutzer " +
            "WHERE singleplayer_stat_result.user_id = benutzer.id AND gamemode = ?" +
            "ORDER BY singleplayer_stat_result.hoechste_punktezahl DESC " +
            "LIMIT 10";
    public static final String PS_GET_TOP_10_PLAYER = "SELECT * " +
            "FROM singleplayer_stat, benutzer " +
            "WHERE singleplayer_stat.id_user = benutzer.id AND id_user = ? AND gamemode = ? " +
            "ORDER BY punkte " +
            "LIMIT 10";
}