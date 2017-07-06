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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDistractor1() {
        return distractor1;
    }

    public void setDistractor1(String distractor1) {
        this.distractor1 = distractor1;
    }

    public String getDistractor2() {
        return distractor2;
    }

    public void setDistractor2(String distractor2) {
        this.distractor2 = distractor2;
    }

    public String getDistractor3() {
        return distractor3;
    }

    public void setDistractor3(String distractor3) {
        this.distractor3 = distractor3;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Verbalization ->: " + question + "\n" +
                    "Solution ->: " + answer + "\n" +
                    "Distractor1 ->: " + distractor1 + "\n" +
                    "Distractor2 ->: " + distractor2 + "\n" +
                    "Distractor3 ->: " + distractor3 + "\n");
        return sb.toString();
    }
}
