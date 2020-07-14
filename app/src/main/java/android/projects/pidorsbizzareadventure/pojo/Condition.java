package android.projects.pidorsbizzareadventure.pojo;

import java.io.Serializable;
import java.util.Objects;

public class Condition implements Serializable {
    private String condition = "no";
    private int penalty = 0;

    public Condition(){}

    public Condition(String condition, int penalty) {
        this.condition = condition;
        this.penalty = penalty;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Condition condition1 = (Condition) o;
        return penalty == condition1.penalty &&
                condition.equals(condition1.condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(condition, penalty);
    }

    @Override
    public String toString() {
        return "Condition{" +
                "condition='" + condition + '\'' +
                ", penalty=" + penalty +
                '}';
    }
}
