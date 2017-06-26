package rest;

import model.User;
import org.glassfish.jersey.server.Uri;
import service.ProfileService;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;

import static constants.Rest_Constants.MSG_BENUTZER_ANGELEGT;
import static constants.Rest_Constants.PROFILE_PATH;


/**
 * Created by Florian on 22.06.2017.
 * Resource um Benutzer anzulegen udn abzufragen
 */

@Path(PROFILE_PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
    private ProfileService profileService = new ProfileService();

    @POST
    public Response addUser (User aUser, @Context UriInfo uriInfo) {
        String newID = String.valueOf(aUser.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newID).build();

        profileService.addUser(aUser);
        return Response.created(uri)
                .entity(aUser)
                .build();
    }

}
