package service;

import dao.SchnittstelleBenutzer;
import dao.SchnittstelleStatistik;
import exception.DataNotFoundException;
import model.Game;
import model.Statistik;

import javax.ws.rs.container.ContainerRequestContext;
import java.io.IOException;
import java.util.List;

import static constants.Service_Constants.*;

/**
 * Created by Cedric on 19.07.2017.
 * Klasse um die Statistiken des Users zu verwalten
 */
public class StatistikService {
    private SchnittstelleStatistik sch = new SchnittstelleStatistik();

    public Statistik getStatistik(int id, String gamemode){
        SchnittstelleBenutzer schB = new SchnittstelleBenutzer();
        if(!schB.checkID(id)){
            throw new DataNotFoundException(MSG_ID_NOT_FOUND);
        }

        return sch.getOverallStatistikPersonal(id, gamemode);
    }

    public void updateStatistik(Game game){
        Statistik statistik = sch.getOverallStatistikPersonal(game.getUser_id(), game.getGameMode());
        if(statistik.getPunktZahl() < game.getPunkte()){
            statistik.setPunktZahl(game.getPunkte());
        }
        statistik.setFragenRichtig(statistik.getFragenRichtig() + game.getFragenRichtig());
        statistik.setAnzahlFragen(statistik.getAnzahlFragen() + game.getFragenBeantwortet());
        statistik.setAnzahlSpiele(statistik.getAnzahlSpiele() + 1);
        sch.changeOverallStatistik(statistik);
    }

    public void addGame(Game game){
        sch.trackNewGame(game);
    }

    public List<Statistik> getTopTenOverall(){
        return sch.getTopTenOverallWorldWide();
    }

    public List<Statistik> getTopTenPlayer(ContainerRequestContext requestContext, String gameMode) throws IOException{
        return sch.getTopTenOverallPersonal(ProfileService.getAuthorizationData(requestContext).getId(), gameMode);
    }
}
