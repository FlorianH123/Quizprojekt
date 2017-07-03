package rest;

import dao.ConnectionKlasse;
import dao.SchnittstelleBenutzer;

import static constants.DB_Constants.*;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Cedric on 02.07.2017.
 * Ueberprueft ob der User in der Datenbank registriert ist
 */
@Provider
public class Filter implements ContainerRequestFilter{
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        ConnectionKlasse conKlasse = new ConnectionKlasse();
        Connection connection;
        String username, password, decodeString, authToken;
        StringTokenizer tokenizer;
        byte[] decoded;
        PreparedStatement statement;
        ResultSet rs;
        SchnittstelleBenutzer sch = new SchnittstelleBenutzer();

        if(requestContext.getUriInfo().getPath().contains("secured")){
            List<String> authHeader = requestContext.getHeaders().get("Authorization");
            if(authHeader != null && authHeader.size() > 0){
                authToken = authHeader.get(0);
                authToken = authToken.replaceFirst("Basic", "");
                decoded = DatatypeConverter.parseBase64Binary(authToken);
                decodeString = new String(decoded, "UTF-8");
                tokenizer = new StringTokenizer(decodeString, ":");
                username = tokenizer.nextToken();
                password = tokenizer.nextToken();


                /*
                try{
                    connection = conKlasse.getConnection();
                    statement = connection.prepareStatement(PS_AUTHORIZATION);
                    statement.setString(1, password);
                    statement.setString(2, username);
                    rs = statement.executeQuery();

                    if(rs.next()){
                        return;
                    }
                }catch(SQLException e){
                    //TODO Logger
                }
                /*
                if ("user".equals(username) && "password".equals(password)) {
                    return;
                }
                */

            }
            Response unauthorizedStatus = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("User cant acces the resource")
                    .build();
            requestContext.abortWith(unauthorizedStatus);
        }
    }
}
