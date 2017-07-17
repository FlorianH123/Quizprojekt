package constants;

/**
 * Created by Florian on 22.06.2017.
 * Rest Konstanten
 */

public class Rest_Constants {
// fuer ProfileResource-------------------------------------------------------------------------------------------------
    public static final String PROFILE_PATH = "/profile";
    public static final String MSG_BENUTZER_ANGELEGT = "Benutzer wurde angelegt";
    public static final String MESSAGE_ID_PATH = "/{messageID}";
    public static final String MESSAGE_ID = "messageID";
    public static final String MSG_PASSWORT_GEAENDERT = "Passwort erfolgreich geaendert";
    public static final String CHANGE_PW_PATH = "/chPas";
    public static final String CHANGE_AVATAR_LINK_PATH = "chAvl";
    public static final String MSG_AVATAR_LINK_GEANDERT = "Avatarlink wurde erfolgreich geaendert";
//----------------------------------------------------------------------------------------------------------------------

//fuer FragenResource---------------------------------------------------------------------------------------------------
    public static final String FRAGEN_PATH = "/fragenresource/{questionCount}";
    public static final String FRAGEN_PATH_COUNT = "questionCount";
    public static final String QUERY = "query";
//----------------------------------------------------------------------------------------------------------------------

//fuer ApplicationHandler-----------------------------------------------------------------------------------------------
    public static final String APLLICATION_PATH = "/webapi";
//----------------------------------------------------------------------------------------------------------------------

//fuer Fehlermeldungen--------------------------------------------------------------------------------------------------
    public static final String ERR_MSG_PW_INCORRECT = "Email oder Passwort nicht korrekt";
//----------------------------------------------------------------------------------------------------------------------

//fuer Autorisierung
    public static final String AUTORISIERUNGS_PATH = "/auth";
}
