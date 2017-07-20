package rest;

import service.StatistikService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static constants.Rest_Constants.*;

/**
 * Created by Cedric on 19.07.2017
 * Resource fuer Statistiken.
 */

@Path(STATISTIC_PATH)
public class StatisticResource {
    private StatistikService statistikService = new StatistikService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(STATISTIC_ID_PATH)
    public Response getStatistik(@PathParam(STATISTIC_ID) int statisticID, @QueryParam("gamemode") String gameMode){
        return Response.ok()
                .entity(statistikService.getStatistik(statisticID, gameMode))
                .build();
    }
}
