package singlePlayer;

import dao.ConnectionKlasse;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static constants.DB_Constants.*;


public class SinglePlayerALL {
    /**
     * SinglePlayerKlasse die anhand der anzahl der Fragen Random Fragen aller Themengebiete als liste zurück gibt
     * @param anzahlFragen gibt an wieviele Fragen vom Server generiert werden sollen
     * @return Liste mit erstellten Fragen
     */
    public List SinglePlayerStart(int anzahlFragen, int resource){
        // Abfrage auf verschiedene Column starten
        //Connection connection = null;
        //PreparedStatement pstatement = null;
        ResultSet rs;

        Level level;
        List<Level> list = null;
        try(Connection connection = getConnection();
            PreparedStatement pstatement = connection.prepareStatement(PS_GET_RANDOM_QUESTION)){
            //connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","admin");
            //connection für echte DB
            //connection = new dao.ConnectionKlasse().getConnection();
            //pstatement = connection.prepareStatement(PS_GET_QUESTIONS);
            //pstatement = setPstatement(anzahlFragen, connection);
            pstatement.setInt(INDEX_1,anzahlFragen);
            //System.out.println(pstatement);
            rs = pstatement.executeQuery();
            list = new ArrayList<>();

            while(rs.next()) {
                level = null;
                list.add(setLevel(rs, level));
            }
        }catch (SQLException e){
            System.out.println("Error while execute the Query!");
        }
        return list;
    }
/*
    /**
     * Setting the prepared Statement on the given values
     * @param anzahlFragen
     * @param connection
     * @return pstatement
     *
    public PreparedStatement setPstatement(int anzahlFragen, Connection connection){
        PreparedStatement pstatement;
        pstatement =null;
        try {
            pstatement = connection.prepareStatement(PS_GET_RANDOM_QUESTION);
            pstatement.setInt(INDEX_1,anzahlFragen);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstatement;
    }
*/

    public Level setLevel(ResultSet rs, Level level){
        try {
            String verbalization = rs.getString("verbalization");
            String solution = rs.getString("solution");
            int cat = rs.getInt("catid");
            Stack<String> stack = new Distractor().DistractorCretor(cat, solution);
            level = new Level(verbalization, solution);
            level.setOptions((stack.pop()), INDEX_1);
            level.setOptions((stack.pop()), INDEX_2);
            level.setOptions((stack.pop()), INDEX_3);
            level.mergeDistractors();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return level;
    }

    private Connection getConnection() {
        ConnectionKlasse con = new ConnectionKlasse();

        return con.getConnection();
    }
}
