package dao;

import exception.DataNotFoundException;
import model.Game;
import validation.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static constants.DB_Constants.*;


/**
 * Created by Florian on 14.07.2017.
 * Klasse zum Austausch von Daten der singleplayer_statistik Tabelle
 */
public class SchnittstelleStatistik {

    /**
     * Methode um ein gespieltes Spiel in die DB einzutragen
     * @param aGame das Spiel
     */
    public void trackNewGameSession(Game aGame) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_ADD_GAME)) {

            statement.setString(INDEX_1, aGame.getGameMode());
            statement.setInt(INDEX_2, aGame.getUser_id());
            statement.setInt(INDEX_3, aGame.getFragenBeantwortet());
            statement.setInt(INDEX_4, aGame.getFragenRichtig());
            statement.setInt(INDEX_5, aGame.getPunkte());
            statement.setInt(INDEX_6, aGame.getGame_id());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(ERR_MSG_ADD_GAME);
            e.printStackTrace();
        }
    }

    public List<Game> getGameListByID(int userID, String gamemode) {
        Validator.check(userID > 0, ERR_MSG_CHECK_ID);

        List<Game> gameList = new ArrayList<>();
        Game aGame = new Game();
        ResultSet rs;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_GET_GAMES_BY_ID,
             ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            statement.setInt(INDEX_1, userID);
            statement.setString(INDEX_2, gamemode);

            rs = statement.executeQuery();

            if (!rs.next()) {
                throw new DataNotFoundException(ERR_MSG_ID_NOT_FOUND);
            }
            rs.previous();

            while(rs.next()) {
               aGame.setGameMode(gamemode);
               aGame.setUser_id(rs.getInt(USER_ID));
               aGame.setFragenBeantwortet(rs.getInt(FRAGEN_BEANTWORTET));
               aGame.setFragenRichtig(rs.getInt(FRAGEN_RICHTIG));
               aGame.setPunkte(rs.getInt(PUNKTE));

               gameList.add(aGame);
            }
        } catch (SQLException e) {
            System.err.println(ERR_MSG_GET_GAMES);
            e.printStackTrace();
        }

        return gameList;
    }

    /**
     * Methode um eine Verbindung aufzubauen
     *
     * @return Connection
     */
    private Connection getConnection() {
        ConnectionKlasse con = new ConnectionKlasse();

        return con.getConnection();
    }

    public static void main (String[] args) {
        SchnittstelleStatistik sch = new SchnittstelleStatistik();

        Game aGame = new Game("xquiz", 1, 34, 15, 1455, 1);
        sch.trackNewGameSession(aGame);
    }
}
