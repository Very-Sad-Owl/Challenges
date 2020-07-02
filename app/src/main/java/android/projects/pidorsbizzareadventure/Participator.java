package android.projects.pidorsbizzareadventure;

import java.io.Serializable;
import java.util.Objects;

public class Participator implements Serializable {

    private String name;
    private int totalScore;


    public Participator(){}

    public Participator(String name) {
        this.name = name;
        this.totalScore = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participator that = (Participator) o;
        return totalScore == that.totalScore &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, totalScore);
    }

    @Override
    public String toString() {
        return "Participator{" +
                "name='" + name + '\'' +
                ", totalScore=" + totalScore +
                '}';
    }
}
