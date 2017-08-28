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
     * Methode um ein beendeter Spielmodus in die DB einzutragen
     *
     * @param aGame das Spiel
     */
    public void trackNewGame(Game aGame) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_TACK_NEW_GAME_TO_SINGLEPLAYER_STAT)) {

            statement.setString(INDEX_1, aGame.getGameMode());
            statement.setInt(INDEX_2, aGame.getUser_id());
            statement.setInt(INDEX_3, aGame.getFragenBeantwortet());
            statement.setInt(INDEX_4, aGame.getFragenRichtig());
            statement.setInt(INDEX_5, aGame.getPunkte());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(ERR_MSG_TRACK_NEW_GAME);
            e.printStackTrace();
        }
    }

    /**
     * Methode um die Overall Statistik zu einer gegebenen User ID + Game Mode aus der DB zurueckzugeben
     *
     * @param userID   user ID des Users
     * @param gamemode Gamemode
     * @return Overall Statistik des Users in einem bestimmten Gamemode
     */
    public Statistik getOverallStatistikPersonal(int userID, String gamemode) {
        Validator.check(userID > 0, ERR_MSG_CHECK_ID);

        ResultSet rs;
        Statistik stat = new Statistik();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_GET_OVERALL_STATISTIK)) {

            statement.setInt(INDEX_1, userID);
            statement.setString(INDEX_2, gamemode);

            rs = statement.executeQuery();

            if (!rs.next()) {
                throw new DataNotFoundException(ERR_MSG_ID_NOT_FOUND);
            }

            stat.setUserName(rs.getString(USER_ID_STAT));
            stat.setAnzahlFragen(rs.getInt(ANZAHL_BEANTWORTETER_FRAGEN));
            stat.setFragenRichtig(rs.getInt(ANZAHL_FRAGEN_RICHTIG_BEANTWORTET));
            stat.setPunktZahl(rs.getInt(HOECHSTE_PUNKTZAHL));
            stat.setAnzahlSpiele(rs.getInt(ANZAHL_SPIELE));
            stat.setGameMode(rs.getString(GAME_MODE));

        } catch (SQLException e) {
            System.err.println(ERR_MSG_GET_OVERALL_STATISTIK);
            e.printStackTrace();
        }

        return stat;
    }

    /**
     * Methode um die DB singleplayer_stat_result mit 0 zu initialiersieren, wenn ein neuer Benutzer angelegt wird
     *
     * @param id       User ID
     * @param gamemode Game Mode der zu initialisieren ist
     */
    public void initOverallStatistik(int id, String gamemode) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_INIT_STATISTIK_OVERALL)) {

            statement.setInt(INDEX_1, id);
            statement.setString(INDEX_2, gamemode);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(ERR_MSG_INIT_STATSTIK_OVERALL);
            e.printStackTrace();
        }
    }

    /**
     * Methode um die Overall Statistik zu updaten
     *
     * @param stat neue Statistik
     */
    public void changeOverallStatistik(Statistik stat) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_CHANGE_OVERALL_STATISTIK)) {

            statement.setInt(INDEX_1, stat.getAnzahlFragen());
            statement.setInt(INDEX_2, stat.getFragenRichtig());
            statement.setInt(INDEX_3, stat.getPunktZahl());
            statement.setInt(INDEX_4, stat.getAnzahlSpiele());
            statement.setInt(INDEX_5, stat.getUserId());
            statement.setString(INDEX_6, stat.getGameMode());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(ERR_MSG_CHANGE_OVERALL_STATISTIK);
            e.printStackTrace();
        }
    }

    public List<Statistik> getTopTenOverallWorldWide(){
        ResultSet rs;
        ArrayList<Statistik> topTenList = null;
        Statistik statistik;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_GET_TOP_10)) {

            topTenList = new ArrayList<>();
            rs = statement.executeQuery();
            while(rs.next()){
                statistik = new Statistik();
                statistik.setAnzahlFragen(rs.getInt("fragen_beantwortet"));
                statistik.setAnzahlSpiele(rs.getInt("gespielte_spiele"));
                statistik.setFragenRichtig(rs.getInt("fragen_richtig"));
                statistik.setGameMode(rs.getString("gamemode"));
                statistik.setPunktZahl(rs.getInt("hoechste_punktezahl"));
                statistik.setUserName(rs.getString("name"));
                topTenList.add(statistik);
            }
        } catch (SQLException e) {
            System.err.println(ERR_MSG_GET_TOP_OVERALL);
            e.printStackTrace();
        }
        return topTenList;
    }

    public List<Statistik> getTopTenOverallPersonal(int id, String gameMode) {
        ResultSet rs;
        ArrayList<Statistik> topTenList = null;
        Statistik statistik;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_GET_TOP_10_PLAYER)) {
            statement.setInt(INDEX_1, id);
            statement.setString(INDEX_2, gameMode);
            topTenList = new ArrayList<>();
            rs = statement.executeQuery();
            while(rs.next()){
                statistik = new Statistik();
                statistik.setAnzahlFragen(rs.getInt("fragen_beantwortet"));
                //statistik.setAnzahlSpiele(rs.getInt("anzahlSpiele"));
                statistik.setFragenRichtig(rs.getInt("fragen_richtig"));
                statistik.setGameMode(rs.getString("gamemode"));
                statistik.setPunktZahl(rs.getInt("punkte"));
                statistik.setUserName(rs.getString("name"));
                topTenList.add(statistik);
            }
        } catch (SQLException e) {
            System.err.println(ERR_MSG_GET_TOP_OVERALL);
            e.printStackTrace();
        }
        return topTenList;
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
}
