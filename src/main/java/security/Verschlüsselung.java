package security;

import validation.Validator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static constants.Security_Constants.*;

/**
 * Created by Florian on 13.06.2017.
 * Algorithmus um die Passwörter zu verschlüsseln
 */
public class Verschlüsselung {

    /**
     * Methode um ein Passwort mit Hash Algorithmen zu verschluesseln
     *
     * @param passwort das Passwort, das zu verschluesseln ist
     * @return verschluesseltes Passwort als byte Array
     */
    private static byte[] verschlüsselePasswort(String passwort) {
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

    /**
     * Methode um ein Passwort als byte Array zu generieren und dann in ein String zu konvertieren
     *
     * @param passwort das Passwort, das zu verschluesseln ist
     * @return verschluesseltes Passwort als String
     */
    public static String generatePasswort(String passwort) {
        Validator.check(!passwort.isEmpty(), ERR_MSG_PASSWORD_EMPTY);

        StringBuilder sb = new StringBuilder();

        byte[] mdbytes = verschlüsselePasswort(passwort);
        Validator.check(mdbytes != null, ERR_MSG_PASSWORT);

        for (byte mdbyte : mdbytes) {
            sb.append(Integer.toString((mdbyte & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    /**
     * Methode um zu Ueberpruefen ob zwei Passwoerter gleich sind
     *
     * @param rawPassword Passwort des Benutzers der sich einloggt
     * @param dbPassword  Passwort das zum Abgleich aus der Datenbank genommen wird
     * @return true wenn gleich, false wenn nicht gleich
     */
    public static boolean checkPasswordEquals(String rawPassword, String dbPassword) {
        Validator.check(!rawPassword.isEmpty() && !dbPassword.isEmpty(), ERR_MSG_PASSWORD_EMPTY);

        String convertedPassword = generatePasswort(rawPassword);

        return convertedPassword.equals(dbPassword);
    }
}
