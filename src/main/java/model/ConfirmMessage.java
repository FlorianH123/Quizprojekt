package model;

/**
 * Created by Florian on 26.06.2017.
 * Container fuer ConfirmMessage
 */
public class ConfirmMessage {
    private String message;
    private int statusCode;

    public ConfirmMessage() {
    }

    public ConfirmMessage(String message, int statusCode) {

        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
