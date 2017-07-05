package exception;

/**
 * Created by Cedric on 05.07.2017.
 */
public class PasswordIncorrectException extends RuntimeException{
    public PasswordIncorrectException(String msg){
        super(msg);
    }
}
