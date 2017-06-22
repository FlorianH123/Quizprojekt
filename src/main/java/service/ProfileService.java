package service;

import dao.SchnittstelleBenutzer;
import model.User;
import security.Verschlüsselung;

/**
 * Created by Florian on 22.06.2017.
 */
public class ProfileService {
    private SchnittstelleBenutzer schnittBenutzer = new SchnittstelleBenutzer();

    public User addUser(User aUser) {
        aUser.setId(schnittBenutzer.getNextID());
        aUser.setPasswort((String)Verschlüsselung.generatePasswort(aUser.getPasswort()));
        schnittBenutzer.addUser(aUser);
        return aUser;
    }
}
