package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Florian on 13.06.2017.
 */
@Path( "test" )
public class MyResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN )

    public String testMethod() {
        return "It Works!";
    }
}
