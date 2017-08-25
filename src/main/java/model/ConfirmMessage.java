package model;

/**
 * Created by Florian on 26.06.2017.
 * Container fuer ConfirmMessage
 */
public class ConfirmMessage {
    private String message;
    private int statusCode;

    public ConfirmMessage(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
