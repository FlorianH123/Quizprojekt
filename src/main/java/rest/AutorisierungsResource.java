package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static constants.Rest_Constants.AUTORISIERUNGS_PATH;

/**
 * Created by Cedric on 02.07.2017.
 * Resource fuer Autorisierungsanfrage
 * Path: /auth
 */

@Path(AUTORISIERUNGS_PATH)
public class AutorisierungsResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String securedMethod(){
        return "Erfolgreich eingeloggt";
    }
}
