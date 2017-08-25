package model;
import java.util.Random;

public class Level {

    private static final int arrayGroesse = 4;

    private String solution;
    private String verbalization;
    private String[] option;
    private int solutionOptions;

    /**
     * Konstruktor
     * @param verbalization enthaelt die Frage
     * @param solution enthaelt die Antwort
     */
    public Level(String verbalization, String solution){
        option = new String[arrayGroesse];
        this.verbalization = verbalization;
        this.solution = solution;
        this.option[0] = solution;
        this.solutionOptions = 0;
    }

    public void setOptions(String options, int pos) {
        this.option[pos] = options;
    }

    /**
     * Jason output für rest API
     * @return gibt den String zurueck mit Frage und Antworten
     */
    public String toString(){
        return ("{\"Verbalization\": "+"\"" + verbalization + "\", " +
                "\"Option0\": "+"\""+ option[0]+ "\", " +
                "\"Option1\": "+"\""+ option[1] + "\", " +
                "\"Option2\": "+"\""+ option[2] + "\", " +
                "\"Option3\": "+"\""+ option[3] + "\", " +
                "\"Solution\": "+"\""+ solution + "\", " +
                "\"SolutionNumber\": "+"\""+ solutionOptions + "\"}");
    }

    /**
     * mischt das LevelObjekt und setzt die Lösung um
     */
    public void mergeDistractors(){
        int zufall = new Random().nextInt(4);
        System.out.println("randomzahl -> : "+zufall);
        System.out.println("option[0]=" + option[0]);
            String save = option[zufall];
            option[zufall] = solution;
            solutionOptions = zufall;
            option[0] = save;
            System.out.println("option[0]=" + option[0]);


    }
}
