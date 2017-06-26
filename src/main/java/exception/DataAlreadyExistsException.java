package exception;

/**
 * Created by Florian on 26.06.2017.
 * Kann geworfen werden, falls die Daten bereits in der Datenbak vorhanden sind
 */
public class DataAlreadyExistsException extends RuntimeException {
    public DataAlreadyExistsException(String msg) {
        super(msg);
    }
}