package model;

/**
 * Created by Cedric on 26.07.2017.
 * Wandelt uebergebene Exception in JSON um
 */
public class ExceptionToJson {

    public static String exceptionMessageToJson(Exception value){
        return "{\n\t\"message\":" + "\"" + value.getMessage() + "\"\n}";
    }
}
