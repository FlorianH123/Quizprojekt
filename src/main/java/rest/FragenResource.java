package rest;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Frage;
import service.FragenService;

import static constants.Rest_Constants.FRAGEN_PATH;

/**
 * Created by Florian on 13.06.2017.
 * Resource für Fragen
 */

@Path (FRAGEN_PATH)
public class FragenResource {
    private FragenService service = new FragenService();

    @GET
    @Produces (MediaType.APPLICATION_XML)
    public List<Frage> getFragen () {
        return service.getFragen();
    }
}