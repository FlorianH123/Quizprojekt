package service;

import dao.SchnittstelleBenutzer;
import exception.DataAlreadyExistsException;
import model.User;

import static constants.Service_Constants.MSG_USER_ALREADY_EXISTS;

/**
 * Created by Florian on 22.06.2017.
 * Klasse um die Profile zu verwalten
 */
public class ProfileService {
    private SchnittstelleBenutzer schnittBenutzer = new SchnittstelleBenutzer();

    /**
     * Methode um einen Benutzer in die Datenbank einzufuegen
     * @param aUser Benutzer
     */
    public void addUser(User aUser) {
        if (schnittBenutzer.checkEmail(aUser.getE_mail())) {
            throw new DataAlreadyExistsException(MSG_USER_ALREADY_EXISTS);
        }

        //TODO Passwort verschlüsseln
        schnittBenutzer.addUser(aUser);
    }
}

