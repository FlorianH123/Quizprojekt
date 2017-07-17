package model;

public class Level {

    //private static final int arrayGroesse = 4;

    private String solution;
    private String verbalization;

    private String[] option;
    private int solutionOptions;

    public Level(){
        option = new String[4];
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
                "Option0 ->: " + option[0]+
                "Option1 ->: " + option[1] + "\n" +
                "Option2 ->: " + option[2] + "\n" +
                "Option3 ->: " + option[3] + "\n" +
                "Solution ->: " + solution + "\n");
        return sb.toString();
    }

}
