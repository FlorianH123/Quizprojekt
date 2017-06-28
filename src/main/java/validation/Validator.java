package validation;

/**
 * Created by Florian on 28.06.2017.
 * Validator Klasse
 */
public class Validator {

    public static void check (boolean argument, String msg) {
        if (argument == false) {
            throw new IllegalArgumentException(msg);
        }
    }
}
