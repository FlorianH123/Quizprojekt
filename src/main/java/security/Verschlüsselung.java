package security;

import validation.Validator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static constants.Security_Constants.ERR_MSG_PASSWORT;

/**
 * Created by Florian on 13.06.2017.
 * Algorithmus um die Passwörter zu verschlüsseln
 */
public class Verschlüsselung {
    private static final String ERR_KEIN_ALGORITHMUS = "Es wurde kein SHA Verschlüsselungsalgorithmus gefunden!";

    private static byte [] verschlüsselePasswort(String passwort) {
        MessageDigest md;

        try {
            md = MessageDigest.getInstance("SHA");
            md.update(passwort.getBytes());
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            System.err.println(ERR_KEIN_ALGORITHMUS);
        }
        return null;
    }

    public static String generatePasswort(String passwort) {
        StringBuilder sb = new StringBuilder();

        byte[] mdbytes = verschlüsselePasswort(passwort);
        Validator.check(mdbytes != null, ERR_MSG_PASSWORT);

        for (byte mdbyte : mdbytes) {
            sb.append(Integer.toString((mdbyte & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    //TODO Löschen
    public static void main (String args[]) {
        System.out.println(generatePasswort("passwort123"));
    }
}
