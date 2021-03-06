package constants;

/**
 * Created by Florian on 22.06.2017.
 * Rest Konstanten
 */

public class Rest_Constants {
//fuer Autorisierung----------------------------------------------------------------------------------------------------
public static final String AUTORISIERUNGS_PATH = "/auth";
//----------------------------------------------------------------------------------------------------------------------

// fuer ProfileResource-------------------------------------------------------------------------------------------------
public static final String PATH_PROFILE = "/profile";
public static final String PATH_ADD_USER = "";
public static final String PATH_GET_USER = "/auth/{messageID}";
public static final String PATH_CHANGE_PASSWORD = "/auth/chPas";
public static final String PATH_CHANGE_AVATAR = "/auth/chAvl";
public static final String PROFILE_LOGIN = "/auth/login";

public static final String MSG_BENUTZER_ANGELEGT = "Benutzer wurde angelegt";
public static final String MESSAGE_ID = "messageID";
public static final String MSG_PASSWORT_GEAENDERT = "Passwort erfolgreich geaendert";
public static final String MSG_AVATAR_LINK_GEANDERT = "Avatarlink wurde erfolgreich geaendert";

//----------------------------------------------------------------------------------------------------------------------

//fuer FragenResource---------------------------------------------------------------------------------------------------
public static final String FRAGEN_PATH = "/fragenresource/{questionCount}";
public static final String FRAGEN_PATH_COUNT = "questionCount";
public static final String QUERY = "query";
//----------------------------------------------------------------------------------------------------------------------

//fuer StatistikResource------------------------------------------------------------------------------------------------
public static final String STATISTIC_PATH = "/stats";
public static final String STATISTIC_ID_PATH = "/auth/{statisticID}";
public static final String STATISTIC_ID = "statisticID";
public static final String STATISTIC_TOP_TEN_PLAYER = "/auth/TopTenPlayer/{gameMode}";
public static final String STATISTIC_ADD = "/auth";
public static final String STATISTIC_TOP_TEN_OA = "/auth/topTenOverall/{gameMode}";
public static final String MSG_STATISTIC_ADDED = "Statistik wurder erfolgreich aktualisiert";
public static final String STATISTIC_GAME_MODE = "gameMode";
public static final String STATISTIC_PLAYER_RANKING = "/auth/ranking/{id}/{gameMode}";
public static final String STATISTIC_PLAYER_ID = "id";
//----------------------------------------------------------------------------------------------------------------------

//fuer ApplicationHandler-----------------------------------------------------------------------------------------------
public static final String APLLICATION_PATH = "/webapi";
//----------------------------------------------------------------------------------------------------------------------

//fuer Fehlermeldungen--------------------------------------------------------------------------------------------------
public static final String ERR_MSG_PW_INCORRECT = "Email oder Passwort nicht korrekt";
//----------------------------------------------------------------------------------------------------------------------
}
