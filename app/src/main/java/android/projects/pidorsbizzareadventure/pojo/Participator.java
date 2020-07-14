package android.projects.pidorsbizzareadventure.pojo;

import java.io.Serializable;

public class Participator implements Serializable {

    private String name;
    private String uid;
    private int totalScore;


    public Participator(){}

    public Participator(String name, String uid) {
        this.name = name;
        this.uid = uid;
        this.totalScore = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
