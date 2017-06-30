package singlePlayer;

import java.sql.SQLException;
import java.util.*;

import dao.ConnectionKlasse;

import java.sql.Connection;


public class Dialog {

    private static final int EXIT   = 99;
    private static final int a =1;

    private static Scanner input;

    public Dialog(){
        input = new Scanner(System.in);
    }

    public static void main(String [] args){
        int auswahl=-1;
        new Dialog();
        while(auswahl != EXIT ){
            try{
                auswahl = DialogAbfrage();
                EingabeVerarbeitung(auswahl);
            }catch(Exception e){
                e.printStackTrace();
                input.reset();
                input.nextLine();
            }
        }
    }

    public static int DialogAbfrage(){
        int choosen;

        System.out.println("\n" +"Was möchten sie tun ?" + "\n" +
                "=================================================\n" +
                "|" + "1) Spiel mit 1 Fragen" + "\n"                             +
                "|" + "2) Spiel mit 2 Fragen" + "\n"                            +
                "|" + "3) Spiel mit 3 Fragen" + "\n"                            +
                "|" + "0) Exit" + "\n"                            +
                "=================================================\n" +
                "--->");
        choosen = input.nextInt();
        return choosen;
    }

    private static void EingabeVerarbeitung(int auswahl) throws SQLException {
        Connection connection = null;
        int anzahlFragen = auswahl;
        int auswahlEingabe = MenuChoose();
        ConnectionKlasse con = new ConnectionKlasse();
                    connection = con.getConnection();
                    SinglePlayer10.SinglePlayerStart(connection ,auswahlEingabe, anzahlFragen);
    }

    public static int MenuChoose(){
        int auswahl;

        System.out.println("\n" +"Welches Match wollen sie wählen ?" + "\n" +
                "=================================================\n" +
                "|" + "0) Presidents of the United States" + "\n"                             +
                "|" + "1) Football" + "\n"                            +
                "|" + "2) American Entertainer" + "\n"                            +
                "|" + "3) Metall" + "\n"                             +
                "|" + "4) 90's" + "\n"                            +

                "=================================================\n" +
                "--->");
        auswahl = input.nextInt();
        return auswahl;
    }

}
