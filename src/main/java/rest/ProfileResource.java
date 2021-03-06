package rest;

import model.User;
import org.json.simple.JSONObject;
import service.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.net.URI;
import static constants.Rest_Constants.*;


/**
 * Created by Florian on 22.06.2017.
 * Resource um Benutzer anzulegen und abzufragen
 * Path: / <- Wird genutzt damit Funktionen sowohl mit Autorisierung als auch ohne aufgerufen werden koennen
 */

@Path(PATH_PROFILE)
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
     * Path: profile
     */
    @PUT
    @Path(PATH_ADD_USER)
    public Response addUser (User aUser, @Context UriInfo uriInfo) {
        String newID = String.valueOf(aUser.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newID).build();
        JSONObject returnMessage = new JSONObject();
        returnMessage.put("message","User erfolgreich hinzugefügt");
        profileService.addUser(aUser);
        return Response.created(uri)
                .entity(returnMessage.toJSONString())
                .build();
    }

    /**
     * Methode um zu einer gegebenen ID den dazugehörigen Benutzer zu liefern
     * @param messageID ID des Users
     * @return Response Nachricht
     * Path: auth/profile/{messageID}
     */
    @GET
    @Path(PATH_GET_USER)
    public Response getUser (@PathParam(MESSAGE_ID) int messageID) {
        return Response.ok()
                .entity(profileService.getUser(messageID))
                .build();

    }

    @GET
    @Path(PROFILE_LOGIN)
    public Response login(ContainerRequestContext requestContext) throws IOException{
        return Response.ok()
                .entity(profileService.login(requestContext))
                .build();

    }

    /**
     * Methode um das Passwort zu andern
     * @param user bei dem das Passwort geaendert werden soll
     * Path: auth/chPas
     */
    @PUT
    @Path(PATH_CHANGE_PASSWORD)
    public Response changePassword (User user) {
        profileService.changePassword(user);

        JSONObject returnMessage = new JSONObject();
        returnMessage.put("message","Passwort erfolgreich geändert");
        return Response.ok()
                .entity(returnMessage.toJSONString())
                .build();
    }

    /**
     * Methode um den Avatar zu aendern
     * @param user bei dem der Avatar geaendert werden soll
     * @return ConfirmMessage
     * Path: auth/profile/chAvl
     */
    @PUT
    @Path(PATH_CHANGE_AVATAR)
    public Response changeAvatarLink (User user) {
        profileService.changeAvatarLink(user);

        JSONObject returnMessage = new JSONObject();
        returnMessage.put("message","Avatar erfolgreich verändert");
        return Response.ok()
                .entity(returnMessage.toJSONString())
                .build();
    }
}