package singlePlayer;

import model.Frage;
import model.Level;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static constants.DB_Constants.*;


public class SinglePlayer10 {

        public List SinglePlayerStart(int cat, int anzahlFragen){
            // Abfrage auf verschiedene Column starten
            Connection connection = null;
            PreparedStatement pstatement = null;
            ResultSet rs;
            rs = null;
            Frage frage;
            Level level;
            List<Level> list = null;
            try{
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","admin");
                //connection für echte DB
                //connection = new dao.ConnectionKlasse().getConnection();
                pstatement = connection.prepareStatement(PS_GET_QUESTIONS);
                pstatement.setInt(INDEX_1,cat );
                pstatement.setInt(INDEX_2,anzahlFragen);
                System.out.println(pstatement);
                rs = pstatement.executeQuery();
                list = new ArrayList<>();
                Stack<String> stack = new Distractor().DistractorCretor(cat, anzahlFragen);
                while(rs.next()){
                    level = new Level();
                    level.setVerbalization(rs.getString("verbalization"));
                    level.setSolution(rs.getString("solution"));
                    /*
                    frage = new Frage();
                    frage.setQuestion(rs.getString("verbalization"));
                    frage.setAnswer(rs.getString("solution"));

                    // TODO: 05.07.2017
                    //Aufruf der Distractor class um anhand abgefragter Sub_Cat die zugehörigen Distractors zu setzen

                    frage.setDistractor1(stack.pop());
                    frage.setDistractor2(stack.pop());
                    frage.setDistractor3(stack.pop());

                    //frage.setDistractor1("Dis1");
                    //frage.setDistractor2("Dis2");
                    //frage.setDistractor3("Dis3");
                    */

                    level.setOptions((stack.pop()),INDEX_1);
                    level.setOptions((stack.pop()),INDEX_2);
                    level.setOptions((stack.pop()),INDEX_3);
                    list.add(level);
                }
            }catch (SQLException e){
                System.out.println("Error while execute the Query!");
            }finally {
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

}