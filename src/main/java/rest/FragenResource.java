package rest;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import model.Frage;
import service.FragenService;

import static constants.Rest_Constants.*;

/**
 * Created by Florian on 13.06.2017.
 * Resource für Fragen
 */

@Path (FRAGEN_PATH)
public class FragenResource {
    private FragenService service = new FragenService();
    @PathParam("questionCount") private int anzahlFragen;
    @QueryParam("query") private int fragenArt;

    @GET
    @Produces (MediaType.APPLICATION_JSON)
    public List<Frage> getFragen () {
        return service.getFragen(anzahlFragen, fragenArt);
    }
}