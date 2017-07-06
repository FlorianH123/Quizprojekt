package singlePlayer;

import dao.ConnectionKlasse;

import java.sql.*;

public class Distractor {

    public String DistractorCretor(int sub_Categorie){
        Connection connection = null;
        PreparedStatement pstatement = null;
        ResultSet rs = null;

        try{
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","admin");

            // TODO: 05.07.2017
            //Datenbank abfrage um Distractors zu generieren anhand der gewählten Sub_cat ( übergebenen Sub_cat
        }catch(SQLException e){
            System.out.println("Error while executing the Query!");

        }
        return "hallo";
    }
}
