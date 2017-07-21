package singlePlayer;

import dao.ConnectionKlasse;
import exception.LevelCantBeCreatedException;
import model.Frage;
import model.Level;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static constants.DB_Constants.*;


public class SinglePlayer10 {

        /**
         *@return Game-Liste mit Level Objekten
         * erstellen der SinglePlayer Abfrage mit x-Fragen in einer bestimmten cat
         **/
        public List SinglePlayerStart(int cat, int anzahlFragen){

            Connection connection = null;
            ResultSet rs;
            rs = null;
            Level level;
            List<Level> list = null;
            PreparedStatement pstatement;
            pstatement=null;

            try{
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","admin");
                //connection für echte DB
                //connection = new dao.ConnectionKlasse().getConnection();
                pstatement = setPstatement(cat, anzahlFragen, connection);
                rs = pstatement.executeQuery();
                list = new ArrayList<>();

                while(rs.next()){
                    level = null;
                    String solution = rs.getString("solution");
                    list.add(setLevel(rs,level,cat));
                }
            }catch (SQLException e){
                System.out.println("Error while execute the Query!");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        System.out.println(ERR_MSG_RS_CLOSE + " " + e);
                    }
                }

                if (pstatement != null) {
                    try {
                        pstatement.close();
                    } catch (SQLException e) {
                        System.out.println(ERR_MSG_STMT_CLOSE + " " + e);
                    }
                }

                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        System.out.println(ERR_MSG_CON_CLOSE +" " + e);
                    }
                }
            }
            return list;
        }

    /**
     * Setting the Prepared Statement with the given indexes
      * @param cat
     * @param anzahlFragen
     * @param connection
     * @return
     */
    public PreparedStatement setPstatement(int cat, int anzahlFragen, Connection connection){
            PreparedStatement pstatement;
            pstatement =null;
            try {
                pstatement = connection.prepareStatement(PS_GET_QUESTIONS);
                pstatement.setInt(INDEX_1,cat );
                pstatement.setInt(INDEX_2,anzahlFragen);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return pstatement;
        }

    /**
     * Setting the Levels parameter which are contains in the ResultSet
     * @param rs
     * @param level
     * @param cat
     * @return
     */
        public Level setLevel(ResultSet rs, Level level,int cat){
            try {

                String verbalization = rs.getString("verbalization");
                String solution = rs.getString("solution");
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

}