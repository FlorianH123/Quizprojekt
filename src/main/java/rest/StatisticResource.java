package rest;

import service.StatistikService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static constants.Rest_Constants.*;

/**
 * Created by Cedric on 19.07.2017
 * Resource fuer Statistiken.
 */

@Path(STATISTIK_PATH)
public class StatisticResource {
    private StatistikService statistikService = new StatistikService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatistik(int id){
        return Response.ok()
                .entity(statistikService.getStatistik(id))
                .build();
    }
}
