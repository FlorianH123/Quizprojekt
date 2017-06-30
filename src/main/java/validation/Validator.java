package validation;

/**
 * Created by Florian on 28.06.2017.
 * Validator Klasse
 */
public class Validator {

    /**
     * Methode um Parameter einer Methode zu ueberpruefen, ob sie den Anfangsbedingungen entsprechen
     * Falls die Bedingung falsch ist wird eine IllegalArgumentException mit der Nachricht msg geworfen
     * Falls die Bedingung richtig ist wird ohne eine Aktion in die aufrufende Methode zurueck gegangen
     *
     * @param argument Ausdruck der ueberprueft werden soll
     * @param msg      Nachricht die ausgegeben werden soll falls die Bedingung falsch ist
     */
    public static void check(boolean argument, String msg) {
        if (!argument) {
            throw new IllegalArgumentException(msg);
        }
    }
}
