package rest;

import dao.SchnittstelleBenutzer;
import model.User;
import org.glassfish.jersey.server.Uri;
import service.ProfileService;


import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;

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

    /**
     * Methode um einen neuen Benutzer ueber die Resource zu erhalten
     * Falls die E-Mail Adresse bereits verwendet wurde wird der Status Code 302 zurueckgegben und eine Exception geworfen
     * mit Statuscode 302 und einer Error Message
     *
     * Falls der Benutzer korrekt angelegt wurde wird der Statuscode 201 und der User selbst als JSON zurueckgegeben
     *
     * Ansonsten wird Statuscode Internal Server Error zurueckgegeben
     */
    @POST
    public Response addUser (User aUser, @Context UriInfo uriInfo) {
        String newID = String.valueOf(aUser.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newID).build();

        profileService.addUser(aUser);
        return Response.created(uri)
                .entity(aUser)
                .build();
    }

    @GET
    @Path("/{messageID}")
    public Response getUser (@PathParam("messageID") int messageID) {
        return Response.ok()
                .entity(profileService.getUser(messageID))
                .build();

    }
}
