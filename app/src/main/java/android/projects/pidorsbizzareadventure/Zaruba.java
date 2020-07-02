package android.projects.pidorsbizzareadventure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Zaruba implements Serializable {
    private String title;
    private List<Participator> participators;
    private String description;
    private String winnerReward;
    private String looserReward;
    private List<String> conditions;
    private boolean isOnRun;


    public Zaruba(String title, String participators, String description,
                  String winnerReward, String looserReward, String conditions) {
        this.title = !title.isEmpty() ? title : "Untitled";
        if(participators != null) {
            String[] names = participators.replace(", ", ",").split(",");
            this.participators = new LinkedList<>();
            for (String name : names) {
                this.participators.add(new Participator(name));
            }
        }
        this.description = !description.isEmpty() ? description : "";
        this.winnerReward = !winnerReward.isEmpty() ? winnerReward : "none";
        this.looserReward = !looserReward.isEmpty() ? looserReward : "none";
        if(conditions != null) {
            this.conditions = new ArrayList<String>
                    (Arrays.asList(conditions.replace(", ", ",").split(",")));
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Participator> getParticipators() {
        return participators;
    }

    public void setParticipators(List<Participator> participators) {
        this.participators = participators;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWinnerReward() {
        return winnerReward;
    }

    public void setWinnerReward(String winnerReward) {
        this.winnerReward = winnerReward;
    }

    public String getLooserReward() {
        return looserReward;
    }

    public void setLooserReward(String looserReward) {
        this.looserReward = looserReward;
    }

    public List<String> getCondotions() {
        return conditions;
    }

    public void setCondotions(List<String> condotions) {
        this.conditions = condotions;
    }

//    @Override
//    public String toString() {
//        return "Zaruba{" +
//                "title='" + title + '\'' +
//                ", participators=" + participators.toString() +
//                ", description='" + description + '\'' +
//                ", winnerReward='" + winnerReward + '\'' +
//                ", looserReward='" + looserReward + '\'' +
//                '}';
//    }
    @Override
    public String toString() {
        return title + '\n' + description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zaruba zaruba = (Zaruba) o;
        return Objects.equals(title, zaruba.title) &&
                Objects.equals(participators, zaruba.participators) &&
                Objects.equals(description, zaruba.description) &&
                Objects.equals(winnerReward, zaruba.winnerReward) &&
                Objects.equals(looserReward, zaruba.looserReward);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, participators, description, winnerReward, looserReward);
    }

    public boolean isOnRun() {
        return isOnRun;
    }

    public void setOnRun(boolean onRun) {
        isOnRun = onRun;
    }
}
