package rest;

import model.User;
import service.ProfileService;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    private ProfileService pfService = new ProfileService();

    @POST
    public String addUser (User aUser) {
        pfService.addUser(aUser);

        return MSG_BENUTZER_ANGELEGT;
    }

}
