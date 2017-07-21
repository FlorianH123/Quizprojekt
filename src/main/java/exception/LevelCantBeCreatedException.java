package exception;

import java.sql.SQLException;

/**
 * Created by Darth Vader on 21.07.2017.
 */
public class LevelCantBeCreatedException extends SQLException {
    public LevelCantBeCreatedException(String msg) {
        super(msg);
    }
}