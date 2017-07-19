package model;

public class Statistik {
    private int userId;
    private int anzahlFragen;
    private int fragenRichtig;
    private int punktZahl;
    private int anzahlSPiele;

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

    public int getAnzahlSPiele() {
        return anzahlSPiele;
    }

    public void setAnzahlSPiele(int anzahlSPiele) {
        this.anzahlSPiele = anzahlSPiele;
    }
}
