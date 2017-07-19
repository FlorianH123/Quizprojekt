package dao;

import exception.DataNotFoundException;
import model.Game;
import model.Statistik;
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
                Game aGame = new Game();
                aGame.setGameMode(gamemode);
                aGame.setUser_id(rs.getInt(USER_ID));
                aGame.setFragenBeantwortet(rs.getInt(FRAGEN_BEANTWORTET));
                aGame.setFragenRichtig(rs.getInt(FRAGEN_RICHTIG));
                aGame.setPunkte(rs.getInt(PUNKTE));
                aGame.setGame_id(rs.getInt(GAME_ID));

               gameList.add(aGame);
            }
        } catch (SQLException e) {
            System.err.println(ERR_MSG_GET_GAMES);
            e.printStackTrace();
        }

        return gameList;
    }

    public Statistik getStatistik(int userID) {
        Validator.check(userID > 0, ERR_MSG_CHECK_ID);

        ResultSet rs;
        Statistik stat = new Statistik();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_GET_STATISTIK)) {

            statement.setInt(INDEX_1, userID);
            rs = statement.executeQuery();

            if (!rs.next()) {
                throw new DataNotFoundException(ERR_MSG_ID_NOT_FOUND);
            }
                stat.setUserId(rs.getInt(USER_ID_STAT));
                stat.setAnzahlFragen(rs.getInt(FRAGEN_BEANTWORTET));
                stat.setFragenRichtig(rs.getInt(FRAGEN_RICHTIG));
                stat.setPunktZahl(rs.getInt(HOECHSTE_PUNKTE));
                stat.setAnzahlSpiele(rs.getInt(ANZAHL_SPIELE));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stat;
    }

    public void initStatOverall (int id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_INIT_STAT)) {

            statement.setInt(INDEX_1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeOverallStat (Statistik stat) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_ADD_STAT)) {

            statement.setInt(INDEX_1, stat.getAnzahlFragen());
            statement.setInt(INDEX_2, stat.getFragenRichtig());
            statement.setInt(INDEX_3, stat.getPunktZahl());
            statement.setInt(INDEX_4, stat.getAnzahlSpiele());
            statement.setInt(INDEX_5, stat.getUserId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(ERR_MSG_ADD_GAME);
            e.printStackTrace();
        }
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
        Statistik st = new Statistik();
        st.setUserId(1);
        st.setAnzahlFragen(10);
        st.setFragenRichtig(5);
        st.setPunktZahl(1240);
        st.setAnzahlSpiele(100);

        sch.changeOverallStat(st);
        System.out.println(sch.getStatistik(1).toString());
    }
}
