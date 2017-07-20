package service;

import dao.SchnittstelleBenutzer;
import dao.SchnittstelleStatistik;
import exception.DataAlreadyExistsException;
import exception.DataNotFoundException;
import model.User;
import security.Verschlüsselung;

import static constants.Service_Constants.*;

/**
 * Created by Florian on 22.06.2017.
 * Klasse um die Profile zu verwalten
 */
public class ProfileService {
    private SchnittstelleBenutzer schnittBenutzer = new SchnittstelleBenutzer();

    /**
     * Methode um einen Benutzer in die Datenbank einzufuegen
     *
     * @param aUser Benutzer
     */
    public void addUser(User aUser) {
        if (schnittBenutzer.checkEmail(aUser.getE_mail())) {
            throw new DataAlreadyExistsException(MSG_USER_ALREADY_EXISTS);
        }

        aUser.setPasswort(Verschlüsselung.generatePasswort(aUser.getPasswort()));
        aUser.setAvatar_link(STANDARD_LINK);
        schnittBenutzer.addUser(aUser);
        new SchnittstelleStatistik().initStatOverall(aUser.getId());
    }

    public void changePassword(User user) {
        if (!schnittBenutzer.checkEmail(user.getE_mail())) {
            throw new DataNotFoundException(ERR_MSG_CHECK_MAIL);
        }
        user.setPasswort(Verschlüsselung.generatePasswort(user.getPasswort()));
        schnittBenutzer.changePassword(user);
    }

    public void changeAvatarLink(User user) {
        if (!schnittBenutzer.checkEmail(user.getE_mail())) {
            throw new DataNotFoundException(ERR_MSG_CHECK_MAIL);
        }
        schnittBenutzer.changeAvatarLink(user);
    }

    /**
     * Methode um einen User aus der DB zu holen und un ihn an den Webserver weiterzugeben
     *
     * @param id id des users
     * @return User mit id, e_mail, name, avatar_link
     */
    public User getUser(int id) {
        if (!schnittBenutzer.checkID(id)) {
            throw new DataNotFoundException(MSG_ID_NOT_FOUND);
        }

        return schnittBenutzer.getUserByID(id);
    }
}

