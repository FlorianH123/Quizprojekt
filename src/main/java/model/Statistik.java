package model;

public class Statistik {
    private String userName;
    private int anzahlFragen;
    private int fragenRichtig;
    private int punktZahl;
    private int anzahlSpiele;
    private String gameMode;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Statistik() {

    }

    public Statistik(String userName, int anzahlFragen, int fragenRichtig, int punktZahl, int anzahlSpiele, String gameMode) {
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        return "User ID: " + userId + ",\n" +
                "Anzahl Fragen: " + anzahlFragen + ",\n" +
                "richtige Fragen: " + fragenRichtig + ",\n" +
                "hoechste Punkte: " + punktZahl + ",\n" +
                "Anzahl Spiele: " + anzahlSpiele + ",\n" +
                "Game Mode: " + gameMode;
    }
}
