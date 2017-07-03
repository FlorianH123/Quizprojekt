package rest;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
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
        if(requestContext.getUriInfo().getPath().contains("secured")){
            List<String> authHeader = requestContext.getHeaders().get("Authorization");
            if(authHeader != null && authHeader.size() > 0){
                String authToken = authHeader.get(0);
                authToken = authToken.replaceFirst("Basic", "");
                byte[] decoded = DatatypeConverter.parseBase64Binary(authToken);
                String decodeString = new String(decoded, "UTF-8");
                StringTokenizer tokenizer = new StringTokenizer(decodeString, ":");
                String username = tokenizer.nextToken();
                String password = tokenizer.nextToken();
                if ("user".equals(username) && "password".equals(password)) {
                    return;
                }
            }
            Response unauthorizedStatus = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("User cannon acces the resource")
                    .build();
            requestContext.abortWith(unauthorizedStatus);
        }
    }
}
