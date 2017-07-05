package exception;

/**
 * Created by Cedric on 05.07.2017.
 */
public class EmailNotFoundException  extends RuntimeException{
    public EmailNotFoundException(String msg){
        super(msg);
    }
}
