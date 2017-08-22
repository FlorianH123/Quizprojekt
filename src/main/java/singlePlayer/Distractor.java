package singlePlayer;

import dao.ConnectionKlasse;

import java.sql.*;
import java.util.Stack;

import static constants.DB_Constants.*;

public class Distractor {
     /**
      *Klasse um anhand der cat und der anzahl der Fragen Distractors zu generieren
      * @return stack mit distractors
      **/
    public Stack<String> DistractorCretor(int sub_Categorie, String sol){
        int anzahl=3;
        Stack<String> stack = new Stack<>();
        ResultSet rs;

        try(Connection connection = getConnection();
            PreparedStatement pstatement = connection.prepareStatement(PS_GET_DISTRACTOORS2)){
            pstatement.setInt(INDEX_1,sub_Categorie );
            pstatement.setString(INDEX_2,sol);
            pstatement.setInt(INDEX_3,anzahl);
            rs = pstatement.executeQuery();
            while(rs.next()){
                stack.push(rs.getString("solution"));
            }
        }catch(SQLException e){
            System.out.println("Error while Generating the Distractors");
        }
        return stack;
    }

    private Connection getConnection() {
        ConnectionKlasse con = new ConnectionKlasse();

        return con.getConnection();
    }
}
