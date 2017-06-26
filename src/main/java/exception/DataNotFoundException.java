package exception;

/**
 * Created by Florian on 26.06.2017.
 * Kann geworfen werden, falls die Daten nicht in der Datenbank sind
 */
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String msg) {
        super(msg);
    }
}
