package singlePlayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Darth Vader on 07.06.2017.
 */
public class SinglePlayer10 {


   // public static void main(String [] args){
     //   Connection connection =null;


//        try{
  //          connection = ConnectionConfiguration.getConnection();
    //        if(connection != null){
      //          System.out.println("Connection established!");
       //     }
       // }catch(Exception e){
       //     e.printStackTrace();
       // }finally {
         //   if(connection != null){
           //     try{
             //       connection.close();
              //  }catch(SQLException e){
               //     e.printStackTrace();
                //}
           // }

       // }

    //}


        public static void SinglePlayerStart(Connection connection ,int taken, int anzahlFragen) throws SQLException {
            // Abfrage auf verschiedene Column starten

            Statement st = null;
            try {
                st = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ResultSet rs = st.executeQuery("Select \"Categories\".\"name\", \"Categories\".\"matchID_cat\", \"Level\".\"levelID\", \"Level\".\"verbalization\" , \"Level\".\"solution\"\n" +
                    "From \"quizDB\".\"Categories\",\"quizDB\".\"Matches\",\"quizDB\".\"Level\"\n" +
                    "where \"Matches\".\"matchID\" = \"Categories\".\"matchID_cat\" AND \"Categories\".\"catID\" = \"Level\".\"catID\" AND \"Categories\".\"matchID_cat\"="+ taken +
                    "ORDER BY Random()"+
                    "Limit "+anzahlFragen+";");
            while (rs.next())
            {
                System.out.println("-----------------------------------");
                System.out.println("Abfrage x");
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                System.out.println(rs.getString(4));
                System.out.println(rs.getString(5));
                System.out.println("-----------------------------------");
            } rs.close();
            st.close();

            //

        }

    }

