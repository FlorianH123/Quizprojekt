package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Florian on 13.06.2017.
 * Algorithmus um die Passwörter zu verschlüsseln
 */
public class Verschlüsselung {
    private static final String ERR_KEIN_ALGORITHMUS = "Es wurde kein SHA Verschlüsselungsalgorithmus gefunden!";

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

    public static byte[] generatePasswort(String passwort) {
        return verschlüsselePasswort(passwort);
    }


    // TODO Lösche main nach Test
    public static void main(String args[]) {
        byte[] test = generatePasswort("passwort123");
        String s = new String(test);
        System.out.println(s);

        for (byte b : test)
            System.out.printf("%02x", b);
    }
}
