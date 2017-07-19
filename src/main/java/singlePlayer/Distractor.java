package singlePlayer;

import java.sql.*;
import java.util.Stack;

import static constants.DB_Constants.*;

public class Distractor {
     /**
      *Klasse um anhand der cat und der anzahl der Fragen Distractors zu generieren
      * @return stack mit distractors
      **/
    public Stack<String> DistractorCretor(int sub_Categorie , int anzahl){
        anzahl*=3;
        Stack<String> stack = new Stack<String>();
        Connection connection = null;
        PreparedStatement pstatement = null;
        ResultSet rs = null;

        try{
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","admin");
            pstatement = connection.prepareStatement(PS_GET_DISTRACTOORS);
            //connection für echte DB
            //connection = new dao.ConnectionKlasse().getConnection();
            pstatement.setInt(INDEX_1,sub_Categorie);
            pstatement.setInt(INDEX_2,anzahl);
            System.out.println(pstatement);
            rs = pstatement.executeQuery();
            while(rs.next()){
                stack.push(rs.getString("solution"));
            }
            System.out.println(stack.toString());
        }catch(SQLException e){
            System.out.println("Error while executing the Query!");

        }
        return stack;
    }

}
