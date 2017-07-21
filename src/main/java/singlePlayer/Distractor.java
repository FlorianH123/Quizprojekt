package singlePlayer;

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
        Stack<String> stack = new Stack<String>();
        Connection connection = null;
        PreparedStatement pstatement = null;
        ResultSet rs = null;

        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","admin");
            //connection für echte DB
            //connection = new dao.ConnectionKlasse().getConnection();
            pstatement = setPstatement(sub_Categorie,anzahl,connection, sol);
            if (pstatement != null) {
                rs = pstatement.executeQuery();
            }
            while(rs.next()){
                stack.push(rs.getString("solution"));
            }
        }catch(SQLException e){
            System.out.println("Error while Generating the Distractors");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return stack;
    }

    public PreparedStatement setPstatement(int cat, int anzahlFragen, Connection connection, String sol){
        PreparedStatement pstatement;
        pstatement =null;
        try {
            pstatement = connection.prepareStatement(PS_GET_DISTRACTOORS);
            pstatement.setInt(INDEX_1,cat );
            pstatement.setString(INDEX_2,sol);
            pstatement.setInt(INDEX_3,anzahlFragen);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return pstatement;
    }

}
