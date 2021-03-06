package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static constants.Rest_Constants.AUTORISIERUNGS_PATH;

/**
 * Created by Cedric on 02.07.2017.
 * Resource fuer Autorisierungsanfrage
 * Path: /auth
 */

@Path(AUTORISIERUNGS_PATH)
public class AutorisierungsResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response securedMethod(){
        return Response.accepted()
                .build();
    }
}
