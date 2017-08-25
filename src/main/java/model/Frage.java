package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Florian on 13.06.2017.
 * Containerklasse für eine Frage
 */

@XmlRootElement
public class Frage {
    private String question;
    private String answer;
    private String distractor1;
    private String distractor2;
    private String distractor3;

    public Frage() {

    }

    public Frage (String question, String answer, String distractor1, String distractor2, String distractor3) {
        this.question = question;
        this.answer = answer;
        this.distractor1 = distractor1;
        this.distractor2 = distractor2;
        this.distractor3 = distractor3;
    }

    public String toString(){
        return ("Verbalization ->: " + question + "\n" +
                    "Solution ->: " + answer + "\n" +
                    "Distractor1 ->: " + distractor1 + "\n" +
                    "Distractor2 ->: " + distractor2 + "\n" +
                    "Distractor3 ->: " + distractor3 + "\n");
    }
}
