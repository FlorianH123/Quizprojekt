package rest;

import dao.SchnittstelleBenutzer;
import exception.DataNotFoundException;
import exception.EmailNotFoundException;
import exception.PasswordIncorrectException;
import model.User;
import security.Verschlüsselung;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static constants.Rest_Constants.*;

/**
 * Created by Cedric on 02.07.2017.
 * Ueberprueft ob der User in der Datenbank registriert ist
 */
@Provider
public class Filter implements ContainerRequestFilter{
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String email, password, decodeString, authToken;
        StringTokenizer tokenizer;
        byte[] decoded;
        SchnittstelleBenutzer sch = new SchnittstelleBenutzer();
        User user;

        if(requestContext.getUriInfo().getPath().contains("auth")){
            //Ueberprueft ob im Header Authorization steht
            List<String> authHeader = new ArrayList<>();
            String test = "";
            if(requestContext.getHeaders().get("Authorization") != null){
                authHeader = requestContext.getHeaders().get("Authorization");
            }else if(requestContext.getHeaders().get("access-control-request-headers") != null){
                //authHeader = requestContext.getHeaders().get("access-control-request-headers");
                test = requestContext.getHeaders().get("access-control-request-headers").toString();
                System.out.println(System.getProperty("catalina.base"));
                authHeader.add(test);
            }else{
                authHeader = null;
            }
            List<String> authHeader = requestContext.getHeaders().get("Authorization");
            if(authHeader != null && authHeader.size() > 0){
                //authToken enthaehlt Email und Passwort in Base64
                authToken = authHeader.get(0);
                authToken = authToken.replaceFirst("Basic", "");
                //Umwandlung von Email und Passwort
                decoded = DatatypeConverter.parseBase64Binary(authToken);
                decodeString = new String(decoded, "UTF-8");
                tokenizer = new StringTokenizer(decodeString, ":");
                email = tokenizer.nextToken();
                password = tokenizer.nextToken();
                user = sch.getUserByEmail(email);
                if(Verschlüsselung.checkPasswordEquals(password,user.getPasswort())){
                    return;
                }else{
                    throw new PasswordIncorrectException(ERR_MSG_PW_INCORRECT);
                }

            }
            Response unauthorizedStatus = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("User cant acces the resource")
                    .build();
            requestContext.abortWith(unauthorizedStatus);
        }
    }
}
