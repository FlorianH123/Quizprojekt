package rest;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import model.*;
import service.FragenService;

import static constants.Rest_Constants.*;

/**
 * Created by Florian on 13.06.2017.
 * Resource für Fragen
 */

@Path (FRAGEN_PATH)
public class FragenResource {
    private FragenService service = new FragenService();

    @GET
    @Produces (MediaType.APPLICATION_JSON)
    public String getFragen (@PathParam(FRAGEN_PATH_COUNT) int anzahlFragen, @QueryParam(QUERY) int fragenArt) {
        return service.getFragen(anzahlFragen, fragenArt).toString();
    }
}