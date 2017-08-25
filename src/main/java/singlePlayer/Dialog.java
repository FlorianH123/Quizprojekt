package singlePlayer;

import java.sql.SQLException;
import java.util.*;

import model.Frage;


public class Dialog {

    private static final int EXIT   = 99;
    private static final int a =1;

    private static Scanner input;
    private List<Frage> list;

    public Dialog(){
        input = new Scanner(System.in);
        start();
    }

    private void start(){
        int auswahl=-1;
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

    public static void main(String [] args){
        new Dialog();
    }

    private int DialogAbfrage(){
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

    private void EingabeVerarbeitung(int auswahl) throws SQLException {
        int auswahlEingabe = MenuChoose();
        List<Frage> list = new SinglePlayer10().SinglePlayerStart(auswahlEingabe, auswahl);
    }

    private int MenuChoose(){
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

    public List getList() {
        return this.list;
    }

}
