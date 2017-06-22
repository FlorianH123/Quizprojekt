package rest;

import model.User;
import service.ProfileService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * Created by Florian on 22.06.2017.
 */

@Path("/profile")
public class ProfileResource {
    private ProfileService pfService = new ProfileService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public User addUser (User aUser) {
        return pfService.addUser(aUser);
    }

}
