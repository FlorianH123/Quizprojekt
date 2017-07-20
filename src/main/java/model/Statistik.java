package model;

public class Statistik {
    private int userId;
    private int anzahlFragen;
    private int fragenRichtig;
    private int punktZahl;
    private int anzahlSpiele;

    public Statistik() {

    }

    public Statistik(int userId, int anzahlFragen, int fragenRichtig, int punktZahl, int anzahlSpiele, String gameMode) {
        this.userId = userId;
        this.anzahlFragen = anzahlFragen;
        this.fragenRichtig = fragenRichtig;
        this.punktZahl = punktZahl;
        this.anzahlSpiele = anzahlSpiele;
        this.gameMode = gameMode;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    private String gameMode;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAnzahlFragen() {
        return anzahlFragen;
    }

    public void setAnzahlFragen(int anzahlFragen) {
        this.anzahlFragen = anzahlFragen;
    }

    public int getFragenRichtig() {
        return fragenRichtig;
    }

    public void setFragenRichtig(int fragenRichtig) {
        this.fragenRichtig = fragenRichtig;
    }

    public int getPunktZahl() {
        return punktZahl;
    }

    public void setPunktZahl(int punktZahl) {
        this.punktZahl = punktZahl;
    }

    public int getAnzahlSpiele() {
        return anzahlSpiele;
    }

    public void setAnzahlSpiele(int anzahlSpiele) {
        this.anzahlSpiele = anzahlSpiele;
    }

    public String toString() {
        return "User ID: " + userId + " Anzahl Fragen: " + anzahlFragen + " richtige Fragen: " + fragenRichtig +
                " hoechste Punkte: " + punktZahl + " Anzahl Spiele " + anzahlSpiele + " Game Mode: " + gameMode;
    }
}
