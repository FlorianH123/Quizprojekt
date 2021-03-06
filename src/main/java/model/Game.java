package model;

/**
 * Created by Florian on 14.07.2017.
 * Container Klasse fuer ein Spiel
 */
public class Game {
    private String gameMode;
    private int user_id;
    private int fragenBeantwortet;
    private int fragenRichtig;
    private int punkte;

    public Game() {

    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setFragenBeantwortet(int fragenBeantwortet) {
        this.fragenBeantwortet = fragenBeantwortet;
    }

    public void setFragenRichtig(int fragenRichtig) {
        this.fragenRichtig = fragenRichtig;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }

    public Game (String gameMode, int user_id, int fragenBeantwortet, int fragenRichtig, int punkte) {
        this.gameMode = gameMode;
        this.user_id = user_id;
        this.fragenBeantwortet = fragenBeantwortet;
        this.fragenRichtig = fragenRichtig;
        this.punkte = punkte;

    }

    public int getUser_id() {
        return user_id;
    }

    public String getGameMode() {
        return gameMode;
    }

    public int getFragenBeantwortet() {
        return fragenBeantwortet;
    }

    public int getFragenRichtig() {
        return fragenRichtig;
    }

    public int getPunkte() {
        return punkte;
    }

    //TODO Testmethode, an am Schluss entfernt werden
    public String toString() {
        return ("Gamemode: " + gameMode + " User ID: " + user_id + " Fragen beantwortet: " +
                fragenBeantwortet + " Fragen richtig: " + fragenRichtig + " Punkte: " + punkte);
    }
}
