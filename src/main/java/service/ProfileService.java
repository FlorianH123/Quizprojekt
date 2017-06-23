package service;

import dao.SchnittstelleBenutzer;
import model.User;

/**
 * Created by Florian on 22.06.2017.
 * Klasse um die Profile zu verwalten
 */
public class ProfileService {
    private SchnittstelleBenutzer schnittBenutzer = new SchnittstelleBenutzer();

    /**
     * Methode um einen Benutzer in die Datenbank einzufuegen
     * @param aUser Benutzer
     * @return User
     */
    public String addUser(User aUser) {
        //TODO Methodenname angeben
        if (schnittBenutzer.checkEmail(aUser.getE_mail()) == false){
            //TODO Passwort verschlüsseln
            //TODO Rückgabetyp und Wert ändern z.B ob alles geklappt hat
            schnittBenutzer.addUser(aUser);
            return "Hat geklappt!";
        }

        return "Benutzer bereits vorhanden";
    }
}

