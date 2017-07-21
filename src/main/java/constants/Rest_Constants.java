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
public static final String PATH_PROFILE = "/";
public static final String PATH_ADD_USER = "profile";
public static final String PATH_GET_USER = "auth/profile/{messageID}";
public static final String CHANGE_PW_PATH = "/chPas";
public static final String PATH_CHANGE_AVATAR = "auth/profile/chAvl";

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
public static final String STATISTIC_ID_PATH = "/{statisticID}";
public static final String STATISTIC_ID = "statisticID";
public static final String MSG_STATISTIC_ADDED = "Statistik wurder erfolgreich aktualisiert";
//----------------------------------------------------------------------------------------------------------------------

//fuer ApplicationHandler-----------------------------------------------------------------------------------------------
public static final String APLLICATION_PATH = "/webapi";
//----------------------------------------------------------------------------------------------------------------------

//fuer Fehlermeldungen--------------------------------------------------------------------------------------------------
public static final String ERR_MSG_PW_INCORRECT = "Email oder Passwort nicht korrekt";
//----------------------------------------------------------------------------------------------------------------------
}
