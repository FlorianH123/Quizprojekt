package model;
import java.util.Random;
import java.util.Random.*;

public class Level {

    private static final int arrayGroesse = 4;

    private String solution;
    private String verbalization;
    private String[] option;
    private int solutionOptions;

    public Level(){
        option = new String[arrayGroesse];
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

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Verbalization ->: " + verbalization + "\n" +
                "Option0 ->: " + option[0]+ "\n" +
                "Option1 ->: " + option[1] + "\n" +
                "Option2 ->: " + option[2] + "\n" +
                "Option3 ->: " + option[3] + "\n" +
                "Solution ->: " + solution + "\n" +
                "SolutionNumber ->: " + solutionOptions + "\n");
        return sb.toString();
    }

    public void mergeDistractors(){
        int zufall = new Random().nextInt(4);
        String save = option[zufall];
        System.out.println(save);
        option[zufall] =solution;
        solutionOptions =zufall;
        option[0] = save;

    }
}
