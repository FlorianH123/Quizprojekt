package service;

import dao.SchnittstelleBenutzer;
import dao.SchnittstelleStatistik;
import enumContainer.GameModeEnum;
import exception.DataAlreadyExistsException;
import exception.DataNotFoundException;
import model.User;
import security.Verschlüsselung;

import javax.ws.rs.container.ContainerRequestContext;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import static constants.Service_Constants.*;

/**
 * Created by Florian on 22.06.2017.
 * Klasse um die Profile zu verwalten
 */
public class ProfileService {
    private SchnittstelleBenutzer schnittBenutzer = new SchnittstelleBenutzer();
    private SchnittstelleStatistik schnittStat = new SchnittstelleStatistik();

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
        int id = schnittBenutzer.getIDbyEMail(aUser);
        for (GameModeEnum gameMode : GameModeEnum.values()) {
            schnittStat.initOverallStatistik(id, gameMode.name());
        }
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

    public User login(ContainerRequestContext requestContext) throws IOException{
        return getAuthorizationData(requestContext);
    }
     public static User getAuthorizationData(ContainerRequestContext requestContext) throws IOException{
         String email, decodeString, authToken;
         StringTokenizer tokenizer;
         byte[] decoded;
         SchnittstelleBenutzer sch = new SchnittstelleBenutzer();
         User user;

         List<String> authHeader = requestContext.getHeaders().get("Authorization");
         //authToken enthaehlt Email und Passwort in Base64
         authToken = authHeader.get(0);
         authToken = authToken.replaceFirst("Basic", "");
         //Umwandlung von Email und Passwort
         decoded = DatatypeConverter.parseBase64Binary(authToken);
         decodeString = new String(decoded, "UTF-8");
         tokenizer = new StringTokenizer(decodeString, ":");
         email = tokenizer.nextToken();
         user = sch.getUserByEmail(email);
         return user;
     }
}

