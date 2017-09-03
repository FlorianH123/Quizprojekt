package rest;

import model.ConfirmMessage;
import model.Game;
import model.Statistik;
import org.json.simple.JSONObject;
import service.StatistikService;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

import static constants.Rest_Constants.*;

/**
 * Created by Cedric on 19.07.2017
 * Resource fuer Statistiken.
 */

@Path(STATISTIC_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class StatisticResource {
    private StatistikService statistikService = new StatistikService();

    @GET
    @Path(STATISTIC_ID_PATH)
    public Response getStatistik(@PathParam(STATISTIC_ID) int statisticID, @QueryParam("gamemode") String gameMode){
        return Response.ok()
                .entity(statistikService.getStatistik(statisticID, gameMode))
                .build();
    }

    @GET
    @Path(STATISTIC_TOP_TEN_PLAYER)
    public List<Statistik> getTopTenPlayer(@PathParam(STATISTIC_GAME_MODE) String gameMode, ContainerRequestContext requestContext) throws IOException{
        return statistikService.getTopTenPlayer(requestContext, gameMode);
    }

    @PUT
    @Path(STATISTIC_ADD)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStatistik(Game game){
        statistikService.updateStatistik(game);
        statistikService.addGame(game);

        JSONObject returnMessage = new JSONObject();
        returnMessage.put("message","Statistik erfolgreich angelegt");

        return Response.ok()
                .entity(returnMessage.toJSONString())
                .build();
    }

    @GET
    @Path(STATISTIC_TOP_TEN_OA)
    public List<Statistik> getTopTenOverall(@PathParam(STATISTIC_GAME_MODE) String gameMode) {
        return statistikService.getTopTenOverall(gameMode);
    }
}
