package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import service.FragenService;

import static constants.Rest_Constants.*;

/**
 * Created by Florian on 13.06.2017.
 * Resource für Fragen
 * Path: /fragenresource/{questionCount}
 */

@Path (FRAGEN_PATH)
public class FragenResource {
    private FragenService service = new FragenService();

    /**
     * @param anzahlFragen Teilt dem Server mit wie viele Fragen generiert werden sollnen
     * @param fragenArt Bestimmt die Kategorie der Fragen
     * @return Gibt den String zurueck der alle Fragen mit Antworten und Distractoren
     * QueryParam: query
     */
    @GET
    @Produces (MediaType.APPLICATION_JSON)
    public String getFragen (@PathParam(FRAGEN_PATH_COUNT) int anzahlFragen, @QueryParam(QUERY) int fragenArt) {
        return service.getFragen(anzahlFragen, fragenArt).toString();
    }
}