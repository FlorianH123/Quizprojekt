package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Cedric on 02.07.2017.
 */
@Path("/secured")
public class AutorisierungsService {

    @GET
    @Path("/message")
    @Produces(MediaType.TEXT_PLAIN)
    public String securedMethod(){
        return "Erfolgreich eingeloggt";
    }
}
