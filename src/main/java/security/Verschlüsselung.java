package security;

/**
 * Created by Florian on 13.06.2017.
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Verschlüsselung {
    private static final String ERR_KEIN_ALGORITHMUS = "Es wurde kein SHA Verschlüsselungsalgorithmus gefunden!";

    private static byte[] verschlüsselePasswort(String passwort) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            System.err.println(ERR_KEIN_ALGORITHMUS);
        }

        md.update(passwort.getBytes());

        return md.digest();
    }

    public static byte[] generatePasswort(String passwort) {
        return verschlüsselePasswort(passwort);
    }

    // TODO Lösche main nach Test
    public static void main(String args[]) {
        byte[] test = generatePasswort("passwort123");

        for (byte b : test)
            System.out.printf("%02x", b);
    }
}
