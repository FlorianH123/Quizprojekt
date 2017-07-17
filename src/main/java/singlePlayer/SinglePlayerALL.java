package singlePlayer;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static constants.DB_Constants.*;


public class SinglePlayerALL {

    public List SinglePlayerStart(int taken, int anzahlFragen){
        // Abfrage auf verschiedene Column starten
        Connection connection = null;
        PreparedStatement pstatement = null;
        ResultSet rs;
        rs = null;
        Frage frage;
        Level level;
        List<Frage> list = null;
        try{
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","admin");
            //connection für echte DB
            //connection = new dao.ConnectionKlasse().getConnection();
            pstatement = connection.prepareStatement(PS_GET_QUESTIONS);
            pstatement.setInt(INDEX_1,taken );
            pstatement.setInt(INDEX_2,anzahlFragen);
            System.out.println(pstatement);
            rs = pstatement.executeQuery();
            list = new ArrayList<>();

            while(rs.next()) {
                level = new Level();
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
