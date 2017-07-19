package model;
import java.util.Random;
import java.util.Random.*;

public class Level {

    private static final int arrayGroesse = 4;

    private String solution;
    private String verbalization;
    private String[] option;
    private int solutionOptions;

    /**
     * Konstruktor
     * @param verbalization
     * @param solution
     */
    public Level(String verbalization, String solution){
        option = new String[arrayGroesse];
        this.verbalization = verbalization;
        this.solution = solution;
        this.option[0] = solution;
        this.solutionOptions = 0;
    }

    public String getVerbalization() {
        return verbalization;
    }

    public void setVerbalization(String verbalization) {
        this.verbalization = verbalization;
    }

    public String[] getOptions() {
        return option;
    }

    public void setOptions(String options, int pos) {
        this.option[pos] = options;
    }

    public int getSolutionOptions() {
        return solutionOptions;
    }

    public void setSolutionOptions(int solutionOptions) {
        this.solutionOptions = solutionOptions;
    }

    public String getSolution() {

        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    /**
     * Jason output für rest API
     * @return
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{\"Verbalization\": "+"\"" + verbalization + "\", " +
                "\"Option0\": "+"\""+ option[0]+ "\", " +
                "\"Option1\": "+"\""+ option[1] + "\", " +
                "\"Option2\": "+"\""+ option[2] + "\", " +
                "\"Option3\": "+"\""+ option[3] + "\", " +
                "\"Solution\": "+"\""+ solution + "\", " +
                "\"SolutionNumber\": "+"\""+ solutionOptions + "\"}");
        return sb.toString();
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
